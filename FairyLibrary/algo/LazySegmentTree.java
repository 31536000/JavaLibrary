package com._31536000.algo;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import com._31536000.math.algebraic.group.CommutativeMonoid;

public class LazySegmentTree<T, E> implements SegmentTreeInterface<T, E>{
	private final CommutativeMonoid<T> semigroup;
	private final CommutativeMonoid<E> lazygroup;
	private final BiFunction<T, E, T> mapping;
	private final Object dat[];
	private final Object lazy[];
	private final int size;

	public <X extends CommutativeMonoid<T>, Y extends CommutativeMonoid<E>> LazySegmentTree(int N, T dat, X semigroup, Y lazygroup, BiFunction<T, E, T> mapping) {
		this.semigroup = semigroup;
		this.lazygroup = lazygroup;
		this.mapping = mapping;
		@SuppressWarnings("unchecked")
		T[] data = (T[]) new Object[N];
		Arrays.fill(data, dat);
		size = N;
		this.dat = new Object[Math.max(2, Integer.highestOneBit(N) << 2)]; // 要素数を超える最小の2冪*2
		this.lazy = new Object[this.dat.length << 1]; // その遅延評価に使う関数
		Arrays.fill(this.dat, this.dat.length >> 1, (this.dat.length >> 1) + N, dat); // 最下段を埋める
		Arrays.fill(this.dat, (this.dat.length >> 1) + N, this.dat.length, semigroup.identity());
		for (int i = this.dat.length / 2 - 1;i > 0; -- i) {
			@SuppressWarnings("unchecked")
			T t = (T)this.dat[i << 1];
			@SuppressWarnings("unchecked")
			T u = (T)this.dat[i << 1 | 1];
			this.dat[i] = semigroup.apply(t, u);
		} // 最下段以外すべて、下から埋める
	}

	public <X extends CommutativeMonoid<T>, Y extends CommutativeMonoid<E>> LazySegmentTree(T[] dat, X semigroup, Y lazygroup, BiFunction<T, E, T> mapping) {
		this.semigroup = semigroup;
		this.lazygroup = lazygroup;
		this.mapping = mapping;
		size = dat.length;
		this.dat = new Object[Math.max(2, Integer.highestOneBit(dat.length) << 2)]; // 要素数を超える最小の2冪*2
		this.lazy = new Object[this.dat.length << 1]; // その遅延評価に使う関数
		System.arraycopy(dat, 0, this.dat, this.dat.length >> 1, dat.length); // 最下段を埋める
		Arrays.fill(this.dat, (this.dat.length >> 1) + dat.length, this.dat.length, semigroup.identity());
		for (int i = this.dat.length / 2 - 1;i > 0; -- i) {
			@SuppressWarnings("unchecked")
			T u = (T)this.dat[i << 1 | 1];
			@SuppressWarnings("unchecked")
			T t = (T)this.dat[i << 1];
			this.dat[i] = semigroup.apply(t, u);
		} // 最下段以外すべて、下から埋める
	}

	@SuppressWarnings("unchecked")
	private void lazy(int index, int length) {
		if (lazy[index] != null) dat[index] = function(index, length);
		lazy[index << 1] = lazygroup.apply((E)lazy[index], (E)lazy[index << 1]);
		lazy[index << 1 | 1] = lazygroup.apply((E)lazy[index], (E)lazy[index << 1 | 1]);
		lazy[index] = lazygroup.identity();
	}

	private void merge(int index, int length) {
		dat[index] = semigroup.apply(function(index << 1, length >> 1), function(index << 1 | 1, length >> 1));
	}

	@SuppressWarnings("unchecked")
	private T getLazy(int index, int length) {
		lazy(index, length);
		return (T)dat[index];
	}

	@SuppressWarnings("unchecked")
	private void lazyUpdate(E lazy, int index, int length) {
		this.lazy[index] = lazygroup.apply((E)this.lazy[index], lazy);
		lazy(index, length);
	}

	@SuppressWarnings("unchecked")
	private T function(int index, int length) {
		if (lazy[index] != null) return mapping.apply((T)dat[index], lazygroup.hyper((E)lazy[index], length));
		return (T)dat[index];
	}

	/**
	 * indexで指定した値に対して更新操作を行います。
	 * @param dat 更新するデータ
	 * @param index 更新する場所
	 */
	@Override
	public void update(E dat, int index) {
		update(dat, index, index + 1);
	}

	/**
	 * 区間[left, right)に対して更新操作を行います。
	 * @param dat 更新するデータ
	 * @param left 更新する区間の左閉区間
	 * @param right 更新する区間の右開区間
	 */
	@Override
	public void update(E dat, int left, int right) {
		if (left < 0 || left > right || right > size) throw new IllegalArgumentException("[" + left + ", " + right + ") is undefined.");
		int mid = left ^ right, m = 1, l, r, length = this.dat.length >> 1;
		while ((mid & length) == 0) { // 共通区間
			if ((left & length) != 0) { // 右側だったので1個移動
				left &= ~length;
				right &= ~length;
				++ m;
			}
			lazy(m, length); // 境界を見つけるまで遅延評価
			m <<= 1;
			length >>= 1;
		} // この時点で左と右は違う区間を指す
		l = m;
		int lengthL = length;
		while(left != 0) { // 左側評価開始
			lazy(l, lengthL);
			l <<= 1;
			lengthL >>= 1;
			if ((left & lengthL) == 0) { // 1個右は含まれる区間なので
				lazyUpdate(dat, l + 1, lengthL);
			} else {
				left &= ~lengthL;
				++ l;
			}
		}
		lazyUpdate(dat, l, lengthL);
		r = m + 1;
		int lengthR = length;
		right &= ~lengthR;
		while(right != 0) { // 右側評価開始
			lazy(r, lengthR);
			r <<= 1;
			lengthR >>= 1;
			if ((right & lengthR) != 0) { // 1個左は含まれる区間なので
				right &= ~lengthR;
				lazyUpdate(dat, r, lengthR);
				++ r;
			}
		}
		// これで全ての遅延評価が終わったので、次は戻していく操作
		while(l > m) {
			l >>= 1;
			lengthL <<= 1;
			merge(l, lengthL);
		}
		++ m;
		while(r > m) {
			r >>= 1;
			lengthR <<= 1;
			merge(r, lengthR);
		}
		-- m;
		while(m > 1) {
			m >>= 1;
			length <<= 1;
			merge(m, length);
		}
	}

	@Override
	public Object[] get() {
		for (int i = 1;i < dat.length;i <<= 1) for (int j = i, l = i << 1;j < l;++ j) lazy(j, i);
		for (int i = dat.length >> 2;i >= 1;i >>= 1) for (int j = i, l = i << 1;j < l;++ j) merge(j, i);
		Object[] ret = new Object[size];
		System.arraycopy(dat, dat.length >> 1, ret, 0, size);
		return  ret;
	}

	/**
	 * 全ての値を取得します。
	 * @param array データを格納する配列
	 * @return 現在の値
	 */
	@Override
	public T[] get(T[] array) {
		for (int i = 1;i < dat.length;i <<= 1) for (int j = i, l = i << 1;j < l;++ j) lazy(j, i);
		for (int i = dat.length >> 2;i >= 1;i >>= 1) for (int j = i, l = i << 1;j < l;++ j) merge(j, i);
		if (array.length < size) {
			@SuppressWarnings("unchecked")
			T[] ret  = (T[])Arrays.copyOfRange(dat, dat.length >> 1, (dat.length >> 1) + size, array.getClass());
			return ret;
		}
		System.arraycopy(dat, dat.length >> 1, array, 0, size);
		return array;
	}

	@Override
	public T get(int index) {
		return get(index, index + 1);
	}

	@Override
	public T get(int left, int right) {
		if (left >= right || right > size) throw new IllegalArgumentException("[" + left + ", " + right + ") is undefined.");
		int mid = left ^ right, m = 1, l, r, length = this.dat.length >> 1;
		T ret = null;
		while ((mid & length) == 0) { // 共通区間
			if ((left & length) != 0) { // 右側だったので1個移動
				left &= ~length;
				right &= ~length;
				++ m;
			}
			lazy(m, length); // 境界を見つけるまで遅延評価
			m <<= 1;
			length >>= 1;
		} // この時点で左と右は違う区間を指す
		l = m;
		int lengthL = length;
		while(left != 0) { // 左側評価開始
			lazy(l, lengthL);
			l <<= 1;
			lengthL >>= 1;
			if ((left & lengthL) == 0) { // 1個右は含まれる区間なので
				ret = semigroup.apply(getLazy(l | 1, lengthL), ret);
			} else {
				left &= ~lengthL;
				++ l;
			}
		}
		ret = semigroup.apply(getLazy(l, lengthL), ret);
		r = m | 1;
		int lengthR = length;
		right &= ~lengthR;
		while(right != 0) { // 右側評価開始
			lazy(r, lengthR);
			r <<= 1;
			lengthR >>= 1;
			if ((right & lengthR) != 0) { // 1個左は含まれる区間なので
				right &= ~lengthR;
				ret = semigroup.apply(ret, getLazy(r++, lengthR));
			}
		}
		// これで全ての遅延評価が終わったので、次は戻していく操作
		while(l > m) {
			l >>= 1;
			lengthL <<= 1;
			merge(l, lengthL);
		}
		++ m;
		while(r > m) {
			r >>= 1;
			lengthR <<= 1;
			merge(r, lengthR);
		}
		-- m;
		while(m > 1) {
			m >>= 1;
			length <<= 1;
			merge(m, length);
		}
		return ret;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int binarySearch(int left, int right, Predicate<T> f) { // TODO 未実装
		throw new UnsupportedOperationException("未実装、ちょっと待ってて");
	}

	@Override
	public T getAvoid(int left, int right) {
		return semigroup.apply(get(0, left), get(right, size));
	}
}