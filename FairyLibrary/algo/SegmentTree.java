package com._31536000.algo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.Monoid;
import com._31536000.util.BinarySearch;
import com._31536000.util.collect.Array;
import com._31536000.util.collect.IntRange;

/**
 * セグメント木です。<br>
 * １点更新をO(logN)、範囲取得をO(logN)でできるデータ構造です。
 * @author 31536000
 *
 * @param <T> 更新及び範囲取得を行いたいクラス
 */
public abstract class SegmentTree<T> extends Array<T> implements BinarySearch<T>{

	private static final long serialVersionUID = -6906482277965513109L;

	public static <T> SegmentTree<T> create(int N, Monoid<T> group) {
		if (N < 1 || group == null) throw new IllegalArgumentException();
		return new MonoidSegmentTree<>(N, group);
	}

	public static <T> SegmentTree<T> create(int N, Supplier<T> dat, Monoid<T> group) {
		if (N < 1 || dat == null || group == null) throw new IllegalArgumentException();
		return new MonoidSegmentTree<>(N, dat, group);
	}

	public static <T> SegmentTree<T> create(T[] dat, Monoid<T> group) {
		if (dat == null || dat.length < 1 || group == null) throw new IllegalArgumentException();
		return new MonoidSegmentTree<>(dat, group);
	}

	public static <T extends Monoid<T>> SegmentTree<T> create(T[] dat) {
		if (dat == null || dat.length < 1) throw new IllegalArgumentException();
		return new MonoidSegmentTree<>(dat, dat[0]);
	}

	public static <T> SegmentTree<T> create(int N, Abelian<T> group) {
		if (N < 1 || group == null) throw new IllegalArgumentException();
		return new FenwickTree<T>(N, group);
	}

	public static <T> SegmentTree<T> create(int N, Supplier<T> dat, Abelian<T> group) {
		if (N < 1 || dat == null || group == null) throw new IllegalArgumentException();
		return new FenwickTree<>(N, dat, group);
	}

	public static <T> SegmentTree<T> create(T[] dat, Abelian<T> group) {
		if (dat == null || dat.length < 1 || group == null) throw new IllegalArgumentException();
		return new FenwickTree<>(dat, group);
	}

	public static <T extends Abelian<T>> SegmentTree<T> create(T[] dat) {
		if (dat == null || dat.length < 1) throw new IllegalArgumentException();
		return new FenwickTree<>(dat, dat[0]);
	}

	private SegmentTree(int length) {
		super(length);
	}

	private Object[] getLocal() {
		return super.get();
	}

	private static final class FenwickTree<T> extends SegmentTree<T> {

		private static final long serialVersionUID = -6757039207238116416L;
		private final Abelian<T> group;
		private final Object[] array;
		private final int size;

		private T getLocal(int index) {
			@SuppressWarnings("unchecked")
			T ret = (T)array[index];
			return ret;
		}

		private FenwickTree(int N, Abelian<T> group) {
			super(N + 1);
			size = N;
			this.group = group;
			array = super.getLocal();
			for (int i = 0;i < array.length;++ i) array[i] = group.identity();
		}

		private FenwickTree(int N, Supplier<T> dat, Abelian<T> group) {
			super(N + 1);
			size = N;
			this.group = group;
			array = super.getLocal();
			for (int i = 0;i < array.length;++ i) array[i] = dat.get();
			for (int i = 1, j;i <= size;++ i) if ((j = i + (i & -i)) <= size) array[j] = group.apply(getLocal(i), getLocal(j));
		}

		private FenwickTree(T[] dat, Abelian<T> group) {
			super(dat.length + 1);
			size = dat.length;
			this.group = group;
			array = super.getLocal();
			System.arraycopy(dat, 0, array, 1, dat.length);
			for (int i = 1, j;i <= size;++ i) if ((j = i + (i & -i)) <= size) array[j] = group.apply(getLocal(i), getLocal(j));
		}

		@Override
		public T set(int index, T dat) {
			if (index < 0 || index >= size) throw new IndexOutOfBoundsException("out of bounds: " + index);
			T ret = get(index);
			apply(index, group.apply(group.inverse(ret), dat));
			return ret;
		}

		@Override
		public void apply(int index, T dat) {
			if (index < 0 || index >= size) throw new IndexOutOfBoundsException("out of bounds: " + index);
			for (++ index;index <= size; index += index & -index) array[index] = group.apply(getLocal(index), dat);
		}

		@Override
		public Object[] get() {
			Object[] ret = new Object[size];
			System.arraycopy(array, 1, ret, 0, size);
			for (int i = 1, j;i <= size;++ i) {
				if ((j = i + (i & -i)) <= size) {
					@SuppressWarnings("unchecked")
					T t = (T)ret[j - 1];
					ret[j - 1] = group.apply(t, group.inverse(getLocal(i)));
				}
			}
			return ret;
		}

		@Override
		public T[] get(T[] array) {
			if (array.length < size) {
				@SuppressWarnings("unchecked")
				T[] ret  = (T[])Arrays.copyOfRange(get(), 1, size + 1, array.getClass());
				array = ret;
			} else System.arraycopy(this.array, 1, array, 0, size);
			for (int i = 1, j;i <= size;++ i) if ((j = i + (i & -i)) <= size) array[j - 1] = group.apply(array[j - 1], group.inverse(getLocal(i)));
			return array;
		}

		@Override
		public T get(int l, int r) {
			if (l < 0 || l > r || r > size) throw new IndexOutOfBoundsException("[" + l + ", " + r + ") is undefined.");
			T left = group.identity(), right = group.identity();
			for (;l != 0;l ^= l & -l) left = group.apply(left, getLocal(l));
			for (;r != 0;r ^= r & -r) right = group.apply(right, getLocal(r));
			return group.apply(group.inverse(left), right);
		}

		@Override
		public T getAvoid(int left, int right) {
			return group.apply(get(0, left), get(right, size));
		}

		@Override
		public int binarySearch(Predicate<T> f) {
			int ret = 0;
			T left = group.identity();
			for (int i = Integer.highestOneBit(size);i != 0;i >>= 1) {
				if (ret + i <= size && f.test(group.apply(left, getLocal(ret + i)))) {
					left = group.apply(left, getLocal(ret + i));
					ret += i;
				}
			}
			return ret - 1;
		}

		@Override
		public int binarySearch(int left, int right, Predicate<T> f) {
			int ret = left;
			T l = get(0, left);
			for (int i = Integer.highestOneBit(size);i != 0;i >>= 1) {
				if (ret + i <= size && f.test(group.apply(l, getLocal(ret + i)))) {
					l = group.apply(l, getLocal(ret + i));
					ret += i;
				}
			}
			return ret - 1;
		}

		private class Iter implements Iterator<T> {
			private int index;

			private Iter() {
				index = 0;
			}

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public T next() {
				return get(index++);
			}

			@Override
			public void remove() {
				set(index-1, group.identity());
			}
		}

		@Override
		public Iterator<T> iterator() {
			return new Iter();
		}
	}

	private static final class MonoidSegmentTree<T> extends SegmentTree<T> {

		private static final long serialVersionUID = 1099468778793692403L;
		private final Monoid<T> group;
		private final Object[] array;
		private final int size;

		private MonoidSegmentTree(int N, Monoid<T> group) {
			super(Integer.highestOneBit(N) << 2);
			this.group = group;
			size = N;
			array = super.getLocal();
			for (int i = 1;i < array.length;++ i) array[i] = group.identity();
		}

		private MonoidSegmentTree(int N, Supplier<T> dat, Monoid<T> group) {
			super(Integer.highestOneBit(N) << 2);
			this.group = group;
			size = N;
			array = super.getLocal();
			for (int i = array.length >> 1, l = size | array.length >> 1;i < l;++ i) array[i] = dat.get();
			for (int i = size | array.length >> 1;i < array.length;++ i) array[i] = group.identity();
			for (int i = array.length / 2 - 1; i > 0; --i) localSet(i, group.apply(localGet(i << 1), localGet(i << 1 | 1)));
		}

		private MonoidSegmentTree(T[] dat, Monoid<T> group) {
			super(Integer.highestOneBit(dat.length) << 2);
			this.group = group;
			size = dat.length;
			array = super.getLocal();
			System.arraycopy(dat, 0, array, array.length >> 1, dat.length);
			for (int i = size | array.length >> 1;i < array.length;++ i) array[i] = group.identity();
			for (int i = array.length / 2 - 1; i > 0; --i) localSet(i, group.apply(localGet(i << 1), localGet(i << 1 | 1)));
		}

		private void localSet(int index, T value) {
			array[index] = value;
		}

		@SuppressWarnings("unchecked")
		private T localGet(int index) {
			return (T)array[index];
		}

		@Override
		public T set(int index, T dat) {
			if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("out of bounds: " + index);
			T ret = get(index);
			index |= array.length >> 1;
			localSet(index, dat);
			for (int i = index >> 1; i > 0; i >>= 1) localSet(i, group.apply(localGet(i << 1), localGet(i << 1 | 1)));
			return ret;
		}

		@Override
		public void apply(int index, T dat) {
			if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("out of bounds: " + index);
			set(index, group.apply(get(index), dat));
		}

		@Override
		public Object[] get() {
			Object[] ret = new Object[size];
			System.arraycopy(array, array.length >> 1, ret, 0, size);
			return ret;
		}

		@Override
		public T[] get(T[] array) {
			if (array.length < size) {
				@SuppressWarnings("unchecked")
				T[] ret  = (T[])Arrays.copyOfRange(array, array.length >> 1, size | array.length >> 1, array.getClass());
				array = ret;
			} else System.arraycopy(array, array.length >> 1, array, 0, size);
			return array;
		}

		@Override
		public T get(int index) {
			if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("out of bounds: " + index);
			return localGet(index | array.length >> 1);
		}

		@Override
		public T get(int l, int r) {
			if (l < 0 || l > r || r > size()) throw new IndexOutOfBoundsException("[" + l + ", " + r + ") is undefined.");
			T L = group.identity(), R = group.identity();
			l |= array.length >> 1;
			r |= array.length >> 1;
			while (l < r) {
				if ((l & 1) != 0) L = group.apply(L, localGet(l++));
				if ((r & 1) != 0) R = group.apply(localGet(r ^ 1), R);
				l >>= 1;
				r >>= 1;
			}
			return group.apply(L, R);
		}

		@Override
		public T getAvoid(int left, int right) {
			return group.apply(get(0, left), get(right, size));
		}

		@Override
		public int binarySearch(int left, int right, Predicate<T> f) { // FIXME バグってるので直して
			if (left < 0 || right > size()) throw new IndexOutOfBoundsException("[" + left + ", " + right + ") is undefined.");
			if (left > right) return revBinarySearch(left - 1, right - 1, f);
			left |= array.length >> 1;
			right |= array.length >> 1;
			T last = group.identity(), next;
			int i = 0;
			while (left < right >>> i) {
				if ((left & 1) == 0) { // 上がある
					left >>>= 1;
					++ i;
				} else {
					if (f.test(next = group.apply(last, localGet(left)))) { // このleft全部を含むか
						last = next;
						++ left;
					} else right = (left + 1 << i) - 1; // 左辺を移動
				}
			}
			while (i > 0) { // 今度は降下しながら見ていく
				left <<= 1;
				-- i;
				if ((right >>> i & 1) != 0) {
					if (f.test(next = group.apply(last, localGet(left)))) {
						last = next;
						++ left;
					} else right = (left + 1 << i) - 1;
				}
			};
			return left - (array.length >> 1) - 1;
		}

		private int revBinarySearch(int right, int left, Predicate<T> f) { // FIXME バグってるので直して
			if (left < 0 || right > size()) throw new IndexOutOfBoundsException("[" + left + ", " + right + ") is undefined.");
			left |= array.length >> 1;
			right |= array.length >> 1;
			T last = group.identity(), next;
			int i = 0;
			while (left >>> i < right) {
				if ((right & 1) == 0) { // 上がある
					if (f.test(next = group.apply(localGet(right), last))) { // このright全部を含むか
						last = next;
						-- right;
					} else left = right << i; // 左辺を移動
				} else {
					right >>>= 1;
					++ i;
				}
			}
			while (i > 0) { // 今度は降下しながら見ていく
				right = right << 1 | 1;
				-- i;
				if ((left >>> i & 1) == 0) {
					if (f.test(next = group.apply(localGet(right), last))) {
						last = next;
						-- right;
					} else left = right << i;
				}
			};
			return right - (array.length >> 1) + 1;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public int length() {
			return size;
		}

		private class Iter implements Iterator<T> {
			private int index;

			private Iter() {
				index = 0;
			}

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public T next() {
				return get(index++);
			}

			@Override
			public void remove() {
				set(index-1, group.identity());
			}
		}

		@Override
		public Iterator<T> iterator() {
			return new Iter();
		}
	}

	/**
	 * データを更新します。<br>
	 * この操作により、indexの値は元の値をdatに置換した値となります。
	 * @param index 更新する場所
	 * @param dat 更新するデータ
	 */
	public abstract void apply(int index, T dat);

	/**
	 * データを更新します。<br>
	 * この操作により、indexの値は元の値にdatを演算した値となります。
	 * @param index 更新する場所
	 * @param dat 更新するデータ
	 */
	public void apply(int index, UnaryOperator<T> dat) {
		apply(index, dat.apply(get(index)));
	}

	/**
	 * データを更新します。<br>
	 * この操作により、indexの位置の更新が他に伝播されます。
	 * @param index 更新する場所
	 */
	public void apply(int index) {
		apply(index, get(index));
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("out of bounds: " + index);
		return get(index, index + 1);
	}

	/**
	 * 指定した範囲の合計を取得します。
	 * @param left 範囲の左区間
	 * @param right 範囲の右区間
	 * @return 半開区間[left, right)の合計
	 */
	public abstract T get(int l, int r);

	/**
	 * 指定した範囲の合計を取得します。
	 * @param range 範囲
	 * @return 区間range内の合計
	 */
	public T get(IntRange range) {
		return get(range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 指定した値を除く場所の値を取得します。
	 * @param index 取得しない場所
	 * @return 全体からindexを取り除いた値
	 */
	public T getAvoid(int index) {
		return getAvoid(index, index + 1);
	}

	/**
	 * 指定した範囲を除く区間の合計を取得します。
	 * @param left 範囲の左区間
	 * @param right 範囲の右区間
	 * @return 半開区間[0, left)+[right, size)の合計
	 */
	public abstract T getAvoid(int left, int right);

	/**
	 * 指定した範囲を除く区間の合計を取得します。
	 * @param range 区間
	 * @return 区間rangeを除く範囲の合計
	 */
	public T getAvoid(IntRange range) {
		return getAvoid(range.getClosedLower(), range.getOpenUpper());
	}
}
