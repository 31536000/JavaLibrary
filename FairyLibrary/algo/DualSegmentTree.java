package com._31536000.algo;

import java.util.Arrays;
import java.util.function.BiFunction;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.group.Monoid;
import com._31536000.util.collect.Array;
import com._31536000.util.collect.IntRange;

/**
 * 双対セグメント木です。<br>
 * 範囲更新をO(logN)、1点取得をO(logN)でできるデータ構造です。
 * @author 31536000
 *
 * @param <T> 範囲更新及び1点取得を行いたいクラス
 */
public abstract class DualSegmentTree<T, U> extends Array<T>{

	private static final long serialVersionUID = 2563254887475803038L;

	private DualSegmentTree(int size) {
		super(size);
	}

	private DualSegmentTree(T[] array) {
		super(array);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param N 要素数
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 * @param function 作用関数
	 */
	public static <T, U> DualSegmentTree<T, U> create(int N, T dat, Monoid<U> semigroup, BiFunction<T, U, T> function) {
		return new MonoidDualSegmentTree<T, U>(N, dat, semigroup, function);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 * @param function 作用関数
	 */
	public static <T, U> DualSegmentTree<T, U> create(T[] dat, Monoid<U> semigroup, BiFunction<T, U, T> function) {
		return new MonoidDualSegmentTree<T, U>(dat, semigroup, function);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param N 要素数
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 */
	public static <T> DualSegmentTree<T, T> create(int N, T dat, Monoid<T> semigroup) {
		return new MonoidDualSegmentTree<T, T>(N, dat, semigroup, semigroup);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 */
	public static <T> DualSegmentTree<T, T> create(T[] dat, Monoid<T> semigroup) {
		return new MonoidDualSegmentTree<T, T>(dat, semigroup, semigroup);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param N 要素数
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 * @param function 作用関数
	 */
	public static <T, U> DualSegmentTree<T, U> create(int N, T dat, CommutativeMonoid<U> semigroup, BiFunction<T, U, T> function) {
		return new CommutativeMonoidDualSegmentTree<T, U>(N, dat, semigroup, function);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 * @param function 作用関数
	 */
	public static <T, U> DualSegmentTree<T, U> create(T[] dat, CommutativeMonoid<U> semigroup, BiFunction<T, U, T> function) {
		return new CommutativeMonoidDualSegmentTree<T, U>(dat, semigroup, function);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param N 要素数
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 */
	public static <T> DualSegmentTree<T, T> create(int N, T dat, CommutativeMonoid<T> semigroup) {
		return new CommutativeMonoidDualSegmentTree<T, T>(N, dat, semigroup, semigroup);
	}

	/**
	 * 双対セグメント木を構築します。
	 * @param dat 初期値
	 * @param semigroup 演算関数
	 */
	public static <T> DualSegmentTree<T, T> create(T[] dat, CommutativeMonoid<T> semigroup) {
		return new CommutativeMonoidDualSegmentTree<T, T>(dat, semigroup, semigroup);
	}

	/**
	 * データを更新します。
	 * @param data 更新するデータ
	 * @param left 更新する場所の左閉区間
	 * @param right 更新する場所の右開区間
	 */
	public abstract void update(U data, int left, int right);

	/**
	 * データを更新します。
	 * @param data 更新するデータ
	 * @param range 更新する区間
	 */
	public abstract void update(U data, IntRange range);

	/**
	 * データを更新します。
	 * @param data 更新するデータ
	 * @param index 更新する位置
	 */
	public abstract void update(U data, int index);

	private static final class MonoidDualSegmentTree<T, U> extends DualSegmentTree<T, U> {

		private static final long serialVersionUID = -1419891313339411745L;
		private final Monoid<U> semigroup;
		private final BiFunction<T, U, T> function;
		private final Object[] dat;

		private MonoidDualSegmentTree(int N, T dat, Monoid<U> semigroup, BiFunction<T, U, T> function) {
			super(N);
			this.semigroup = semigroup;
			this.function = function;
			this.dat = new Object[Integer.highestOneBit(N) << 2];
			Arrays.fill(super.get(), dat);
			Arrays.fill(this.dat, semigroup.identity());
		}

		private MonoidDualSegmentTree(T[] dat, Monoid<U> semigroup, BiFunction<T, U, T> function) {
			super(dat);
			this.semigroup = semigroup;
			this.function = function;
			this.dat = new Object[Integer.highestOneBit(dat.length) << 2];
			Arrays.fill(this.dat, semigroup.identity());
		}

		@SuppressWarnings("unchecked")
		private void lazy(int index) {
			dat[index << 1] = semigroup.apply((U)dat[index], (U)dat[index << 1]);
			dat[index << 1 | 1] = semigroup.apply((U)dat[index], (U)dat[index << 1 | 1]);
			dat[index] = semigroup.identity();
		}

		@SuppressWarnings("unchecked")
		private void merge(int index, U merge) {
			dat[index] = semigroup.apply((U)dat[index], merge);
		}

		@Override
		public void update(U data, int left, int right) {
			if (left < 0 || left > right || right > size()) throw new IndexOutOfBoundsException("[" + left + ", " + right + ") is undefined.");
			int mid = left ^ right, m = 1, l, r, length = this.dat.length >> 1;
			while ((mid & length) == 0) {
				if ((left & length) != 0) {
					left &= ~length;
					right &= ~length;
					++ m;
				}
				lazy(m);
				m <<= 1;
				length >>= 1;
			}
			l = m;
			int lengthL = length;
			while(left != 0) {
				lazy(l);
				l <<= 1;
				lengthL >>= 1;
				if ((left & lengthL) == 0) {
					merge(l + 1, data);
				} else {
					left &= ~lengthL;
					++ l;
				}
			}
			merge(l, data);
			r = m + 1;
			int lengthR = length;
			right &= ~lengthR;
			while(right != 0) {
				lazy(r);
				r <<= 1;
				lengthR >>= 1;
				if ((right & lengthR) != 0) {
					right &= ~lengthR;
					merge(r, data);
					++ r;
				}
			}
		}

		@Override
		public void update(U data, IntRange range) {
			update(data, range.getClosedLower(), range.getOpenUpper());
		}

		@Override
		public void update(U data, int index) {
			update(data, index, index + 1);
		}

		private void update() {
			for (int i = 1, l = dat.length >> 1;i < l;++ i) lazy(i);
		}

		@Override
		public Object[] get() {
			update();
			Object[] ret = super.get();
			for (int i = 0;i < size();++ i) {
				@SuppressWarnings("unchecked")
				T tmp1 = (T)ret[i];
				@SuppressWarnings("unchecked")
				U tmp2 = (U)dat[i | dat.length >> 1];
				ret[i] = function.apply(tmp1, tmp2);
			}
			return ret;
		}

		@Override
		public T[] get(T[] array) {
			update();
			T[] ret  = super.get(array);
			for (int i = 0;i < size();++ i) {
				@SuppressWarnings("unchecked")
				U tmp = (U)dat[i | dat.length >> 1];
				ret[i] = function.apply(ret[i], tmp);
			}
			return ret;
		}

		@Override
		public T get(int index) {
			if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("out of bounds: " + index);
			T ret = super.get(index);
			for (int i = dat.length >> 1 | index; i != 0; i >>= 1) {
				@SuppressWarnings("unchecked")
				U u = (U)dat[i];
				ret = function.apply(ret, u);
			}
			return ret;
		}
	}

	private static final class CommutativeMonoidDualSegmentTree<T, U> extends DualSegmentTree<T, U> {

		private static final long serialVersionUID = 795275390653428368L;
		private final CommutativeMonoid<U> semigroup;
		private final BiFunction<T, U, T> function;
		private final Object[] dat;

		private CommutativeMonoidDualSegmentTree(int N, T dat, CommutativeMonoid<U> semigroup, BiFunction<T, U, T> function) {
			super(N);
			this.semigroup = semigroup;
			this.function = function;
			this.dat = new Object[Integer.highestOneBit(N) << 2];
			Arrays.fill(super.get(), dat);
			Arrays.fill(this.dat, semigroup.identity());
		}

		private CommutativeMonoidDualSegmentTree(T[] dat, CommutativeMonoid<U> semigroup, BiFunction<T, U, T> function) {
			super(dat);
			this.semigroup = semigroup;
			this.function = function;
			this.dat = new Object[Integer.highestOneBit(dat.length) << 2];
			Arrays.fill(this.dat, semigroup.identity());
		}

		@SuppressWarnings("unchecked")
		private void lazy(int index) {
			dat[index << 1] = semigroup.apply((U)dat[index], (U)dat[index << 1]);
			dat[index << 1 | 1] = semigroup.apply((U)dat[index], (U)dat[index << 1 | 1]);
			dat[index] = semigroup.identity();
		}

		@SuppressWarnings("unchecked")
		private void merge(int index, U merge) {
			dat[index] = semigroup.apply((U)dat[index], merge);
		}

		@Override
		public void update(U data, int left, int right) {
			if (left < 0 || left > right || right > size()) throw new IndexOutOfBoundsException("[" + left + ", " + right + ") is undefined.");
			left |= dat.length >> 1;
			right |= dat.length >> 1;
			while (left < right) {
				if ((left & 1) != 0) merge(left++, data);
				if ((right & 1) != 0) merge(right ^ 1, data);
				left >>= 1;
				right >>= 1;
			}
		}


		@Override
		public void update(U data, IntRange range) {
			update(data, range.getClosedLower(), range.getOpenUpper());
		}

		@Override
		public void update(U data, int index) {
			update(data, index, index + 1);
		}

		private void update() {
			for (int i = 1, l = dat.length >> 1;i < l;++ i) lazy(i);
		}

		@Override
		public Object[] get() {
			update();
			Object[] ret = super.get();
			for (int i = 0;i < size();++ i) {
				@SuppressWarnings("unchecked")
				T tmp1 = (T)ret[i];
				@SuppressWarnings("unchecked")
				U tmp2 = (U)dat[i | dat.length >> 1];
				ret[i] = function.apply(tmp1, tmp2);
			}
			return ret;
		}

		@Override
		public T[] get(T[] array) {
			update();
			T[] ret  = super.get(array);
			for (int i = 0;i < size();++ i) {
				@SuppressWarnings("unchecked")
				U tmp = (U)dat[i | dat.length >> 1];
				ret[i] = function.apply(ret[i], tmp);
			}
			return ret;
		}

		@Override
		public T get(int index) {
			if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("out of bounds: " + index);
			T ret = super.get(index);
			for (int i = dat.length >> 1 | index; i != 0; i >>= 1) {
				@SuppressWarnings("unchecked")
				U u = (U)dat[i];
				ret = function.apply(ret, u);
			}
			return ret;
		}
	}
}