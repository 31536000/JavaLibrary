package com._31536000.util.collect;

import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class IntArray extends Array<Integer> {
	@Override
	public boolean contains(Object o) {
		return o instanceof Number && contains(((Number) o).intValue());
	}

	/**
	 * 指定の要素がこの配列に含まれている場合にtrueを返します。
	 * つまり、この配列に、o==eとなる要素eが1つ以上含まれている場合にのみtrueを返します。
	 * @param o このリスト内にあるかどうかが判定される要素
	 * @return 指定された要素がこのリスト内にある場合はtrue
	 */
	public abstract boolean contains(int o);

	/**
	 * 指定された配列のすべての要素がこの配列に含まれている場合にtrueを返します。
	 * @param c この配列にあるかどうかがチェックされる配列
	 * @return 指定された配列のすべての要素がこの配列に含まれている場合はtrue
	 * @exception NullPointerException 指定された配列がnullの場合
	 */
	public abstract boolean containsAll(int... c);

	@Override
	public PrimitiveIterator.OfInt iterator() {
		return listIterator();
	}

	/**
	 * このコレクション内のすべての要素を適切な順序で(最初の要素から最後の要素へ)含んでいる配列を返します。<br>
	 * 返される配列は、それへの参照がこのコレクションで保持されない場合に、安全になります。
	 * つまり、このメソッドは、このコレクションが配列に連動している場合でも、新しい配列を割り当てる必要があります。
	 * このため、呼出し側は、返された配列を自由に変更できます。<br>
	 * このメソッドは、配列ベースのAPIとコレクションベースのAPIの間の橋渡し役として機能します。
	 * @return このコレクション内のすべての要素を適切な順序で含んでいる配列
	 */
	public abstract int[] toArrayAsInt();

	@Override
	public Integer get(int index) {
		return getAsInt(index);
	}

	/**
	 * この配列内の指定された位置にある要素を返します。
	 * @param index 返される要素のインデックス
	 * @return このリスト内の指定された位置にある要素
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(index < 0||index>= size())
	 */
	public abstract int getAsInt(int index);

	/**
	 * この配列内の指定された位置にある要素を、指定された要素に置き換えます。
	 * @param index 置換される要素のインデックス
	 * @param element 指定された位置に格納される要素
	 * @return 指定された位置に以前あった要素
	 * @exception NullPointerException 指定された要素がnullの場合
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(index < 0||index>= size())
	 */
	@Override
	public Integer set(int index, Integer element) {
		return set(index, (int) element);
	}

	/**
	 * この配列内の指定された位置にある要素を、指定された要素に置き換えます。
	 * @param index 置換される要素のインデックス
	 * @param element 指定された位置に格納される要素
	 * @return 指定された位置に以前あった要素
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(index < 0||index>= size())
	 */
	public abstract int setAsInt(int index, int element);

	@Override
	public int indexOf(Object o) {
		return o instanceof Number ? indexOf(((Number) o).intValue()) : -1;
	}

	/**
	 * 指定された要素がこの配列内で最初に検出された位置のインデックスを返します。
	 * 指定された要素がこの配列にない場合は -1を返します。
	 * @param o 検索する要素
	 * @return 指定された要素がこのリスト内で最初に検出された位置のインデックス。その要素がこの配列にない場合は -1
	 */
	public abstract int indexOf(int o);

	@Override
	public int lastIndexOf(Object o) {
		return o instanceof Number ? lastIndexOf(((Number) o).intValue()) : -1;
	}

	/**
	 * 指定された要素がこの配列内で最後に検出された位置のインデックスを返します。
	 * 指定された要素がこの配列にない場合は -1を返します。
	 * @param o 検索する要素
	 * @return 指定された要素がこのリスト内で最後に検出された位置のインデックス。その要素がこの配列にない場合は -1
	 */
	public abstract int lastIndexOf(int o);

	@Override
	public abstract PrimitiveListIterator.OfInt listIterator();

	@Override
	public abstract PrimitiveListIterator.OfInt listIterator(int index);

	@Override
	public abstract IntArray subArray(int fromIndex, int toIndex);

	public abstract void forEach(IntConsumer action);

	/**
	 * このコレクションをソースとして使用して、逐次的なIntStreamを返します。
	 * @return このコレクション内の要素に対する順次IntStream
	 */
	public abstract IntStream intstream();

	@Override
	public abstract Spliterator.OfInt spliterator();

	@Override
	public void fill(Integer val) {
		fillAsInt(val);
	}

	/**
	 * 配列の各要素に、指定された値を代入します。
	 * @param val 配列のすべての要素に格納する値
	 */
	public void fillAsInt(int val) {
		for (int i = 0, l = size(); i < l; ++i) setAsInt(i, val);
	}

	private static class IntegerArray extends IntArray implements Serializable {
		private static final long serialVersionUID = -839380667360123241L;
		private final int[] array;

		private IntegerArray(int fromIndex, int toIndex, int[] array) {
			this.array = array;
		}

		private IntegerArray(int size) {
			array = new int[size];
		}

		private IntegerArray(int[] array) {
			this.array = array;
		}

		@Override
		public int size() {
			return array.length;
		}

		@Override
		public boolean contains(int o) {
			for (int i : array) if (i == o) return true;
			return false;
		}

		private class Iterator implements PrimitiveIterator.OfInt {
			private int index;

			public Iterator() {
				index = 0;
			}

			@Override
			public boolean hasNext() {
				return index < array.length;
			}

			@Override
			public int nextInt() {
				return array[index++];
			}
		}

		@Override
		public PrimitiveIterator.OfInt iterator() {
			return new Iterator();
		}

		@Override
		public int[] toArrayAsInt() {
			return Arrays.copyOf(array, array.length);
		}

		@Override
		public boolean containsAll(int... c) {
			cont: for (int i : c) {
				for (int j : array) if (i == j) continue cont;
				return false;
			}
			return true;
		}

		@Override
		public Integer get(int index) {
			return getAsInt(index);
		}

		@Override
		public int getAsInt(int index) {
			try {
				return array[index];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(String.valueOf(index));
			}
		}

		@Override
		public Integer set(int index, Integer element) {
			return setAsInt(index, element);
		}

		@Override
		public int setAsInt(int index, int element) {
			int last = array[index];
			array[index] = element;
			return last;
		}

		@Override
		public int indexOf(int o) {
			for (int i = 0; i < array.length; ++i) if (array[i] == o) return i;
			return -1;
		}

		@Override
		public int lastIndexOf(int o) {
			for (int i = array.length - 1; i >= 0; --i) if (array[i] == o) return i;
			return -1;
		}

		private class ListIterator implements PrimitiveListIterator.OfInt {
			int index, last = -1;

			public ListIterator(int index) {
				this.index = index;
			}

			@Override
			public boolean hasNext() {
				return index < array.length;
			}

			@Override
			public boolean hasPrevious() {
				return index > 0;
			}

			@Override
			public int nextIndex() {
				return index;
			}

			@Override
			public int previousIndex() {
				return index - 1;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int nextInt() {
				try {
					return array[last = index++];
				} catch (IndexOutOfBoundsException e) {
					throw new NoSuchElementException(String.valueOf(last));
				}
			}

			@Override
			public int previousInt() {
				try {
					return array[last = --index];
				} catch (IndexOutOfBoundsException e) {
					throw new NoSuchElementException(String.valueOf(last));
				}
			}

			@Override
			public void setAsInt(int e) {
				try {
					array[last] = e;
				} catch (IndexOutOfBoundsException e2) {
					throw new IllegalStateException();
				}
			}

			@Override
			public void addAsInt(int e) {
				throw new UnsupportedOperationException();
			}
		}

		@Override
		public PrimitiveListIterator.OfInt listIterator() {
			return new ListIterator(0);
		}

		@Override
		public PrimitiveListIterator.OfInt listIterator(int index) {
			return new ListIterator(index);
		}

		@Override
		public IntArray subArray(int fromIndex, int toIndex) {
			if (fromIndex < 0 || toIndex >= size()) throw new IndexOutOfBoundsException();
			return new SubArray(fromIndex, toIndex, array);
		}

		@Override
		public void fillAsInt(int val) {
			Arrays.fill(array, val);
		}

		@Override
		public void forEach(IntConsumer action) {
			for (int i : array) action.accept(i);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof IntArray) {
				IntArray o2 = (IntArray) o;
				for (int i = 0; i < array.length; ++i) if (array[i] != o2.getAsInt(i)) return false;
				return size() == o2.size();
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(array);
		}

		@Override
		public String toString() {
			return Arrays.toString(array);
		}

		@Override
		public IntStream intstream() {
			return Arrays.stream(array);
		}

		@Override
		public IntArray clone() {
			return create(array);
		}

		@Override
		public Spliterator.OfInt spliterator() {
			return Spliterators.spliterator(array, Spliterator.NONNULL | Spliterator.ORDERED);
		}
	}

	private static class SubArray extends IntArray implements Serializable {
		private static final long serialVersionUID = -8676999422326323439L;
		private final int fromIndex, toIndex;
		private final int[] array;

		private SubArray(int fromIndex, int toIndex, int[] array) {
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
			this.array = array;
		}

		@Override
		public int size() {
			return toIndex - fromIndex;
		}

		@Override
		public boolean contains(int o) {
			for (int i = fromIndex; i < toIndex; ++i) if (array[i] == o) return true;
			return false;
		}

		private class Iter implements PrimitiveIterator.OfInt {
			private int index;

			public Iter() {
				index = fromIndex;
			}

			@Override
			public boolean hasNext() {
				return index < toIndex;
			}

			@Override
			public int nextInt() {
				return array[index++];
			}
		}

		@Override
		public PrimitiveIterator.OfInt iterator() {
			return new Iter();
		}

		@Override
		public int[] toArrayAsInt() {
			return Arrays.copyOfRange(array, fromIndex, toIndex);
		}

		@Override
		public boolean containsAll(int... c) {
			cont: for (int i = fromIndex; i < toIndex; ++i) {
				int n = array[i];
				for (int j : array) if (j == n) continue cont;
				return false;
			}
			return true;
		}

		@Override
		public Integer get(int index) {
			return getAsInt(index);
		}

		@Override
		public int getAsInt(int index) {
			try {
				return array[index - fromIndex];
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(String.valueOf(index));
			}
		}

		@Override
		public Integer set(int index, Integer element) {
			return setAsInt(index, element);
		}

		@Override
		public int setAsInt(int index, int element) {
			int last = array[index - fromIndex];
			array[index - fromIndex] = element;
			return last;
		}

		@Override
		public int indexOf(int o) {
			for (int i = fromIndex; i < toIndex; ++i) if (array[i] == o) return i;
			return -1;
		}

		@Override
		public int lastIndexOf(int o) {
			for (int i = toIndex - 1; i >= fromIndex; --i) if (array[i] == o) return i;
			return -1;
		}

		private class ListIterator implements PrimitiveListIterator.OfInt {
			int index, last = -1;

			public ListIterator(int index) {
				this.index = index;
			}

			@Override
			public boolean hasNext() {
				return index < toIndex;
			}

			@Override
			public boolean hasPrevious() {
				return index > fromIndex;
			}

			@Override
			public int nextIndex() {
				return index;
			}

			@Override
			public int previousIndex() {
				return index - 1;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int nextInt() {
				try {
					return array[last = index++];
				} catch (IndexOutOfBoundsException e) {
					throw new NoSuchElementException(String.valueOf(last));
				}
			}

			@Override
			public int previousInt() {
				try {
					return array[last = --index];
				} catch (IndexOutOfBoundsException e) {
					throw new NoSuchElementException(String.valueOf(last));
				}
			}

			@Override
			public void setAsInt(int e) {
				try {
					array[last] = e;
				} catch (IndexOutOfBoundsException e2) {
					throw new IllegalStateException();
				}
			}

			@Override
			public void addAsInt(int e) {
				throw new UnsupportedOperationException();
			}
		}

		@Override
		public PrimitiveListIterator.OfInt listIterator() {
			return new ListIterator(fromIndex);
		}

		@Override
		public PrimitiveListIterator.OfInt listIterator(int index) {
			return new ListIterator(index + fromIndex);
		}

		@Override
		public IntArray subArray(int fromIndex, int toIndex) {
			if (fromIndex < this.fromIndex || toIndex >= size()) throw new IndexOutOfBoundsException();
			return new SubArray(fromIndex, toIndex, array);
		}

		@Override
		public void fillAsInt(int val) {
			Arrays.fill(array, fromIndex, toIndex, val);
		}

		@Override
		public void forEach(IntConsumer action) {
			for (int i = fromIndex; i < toIndex; ++i) action.accept(array[i]);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof IntArray) {
				IntArray o2 = (IntArray) o;
				for (int i = fromIndex; i < toIndex; ++i) if (array[i] != o2.getAsInt(i - fromIndex)) return false;
				return size() == o2.size();
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hashCode = 1;
			for (int i = fromIndex; i < toIndex; ++i) hashCode = 31 * hashCode + array[i];
			return hashCode;
		}

		@Override
		public String toString() {
			return intstream().mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(", ", "[", "]"));
		}

		@Override
		public IntStream intstream() {
			return Arrays.stream(array, fromIndex, toIndex);
		}

		@Override
		public IntArray clone() {
			return subArray(0, size());
		}

		@Override
		public Spliterator.OfInt spliterator() {
			return Spliterators.spliterator(array, fromIndex, toIndex, Spliterator.NONNULL | Spliterator.ORDERED);
		}
	}

	@SuppressWarnings("unchecked")
	public static IntArray create(int size) {
		return new IntegerArray(size);
	}

	public static IntArray create(int[] array) {
		return new IntegerArray(array);
	}
}
