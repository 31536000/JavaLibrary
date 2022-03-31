package com._31536000.util.collect;

/**
 * 固定長の配列を提供します。これは、配列ベースのAPIとコレクションベースのAPIの橋渡し役として機能します。
 * @author 31536000
 *
 * @param <E> この配列内に存在する要素の型
 */
public abstract class Array<E> extends java.util.AbstractList<E> implements Cloneable, java.util.RandomAccess {
	/**
	 * このコレクション中の要素の数を返します。
	 * @return このコレクションの要素数
	 * @see {@link Array#size()}
	 */
	public int length() {
		return size();
	}

	/**
	 * 指定されたgenerator関数を使用してこのコレクションのすべての要素を含む配列を返し、返された配列を割り当てます。<br>
	 * イテレータによって要素が返される順序をコレクションが保証する場合、このメソッドは同じ順序で要素を返さなければなりません。
	 * @param <T> コレクションを格納する配列のコンポーネント型
	 * @param generator 要求された型と指定された長さを持つ新しい配列を生成する関数
	 * @return コレクションのすべての要素が格納されている配列
	 * @exception ArrayStoreException このコレクションのいずれかの要素の実行時タイプが、生成された配列の「ランタイム・コンポーネント・タイプ」に割り当てられない場合
	 * @exception NullPointerException ジェネレータ・ファンクションがNullの場合
	 */
	public <T> T[] toArray(java.util.function.IntFunction<T[]> generator) {
		return toArray(generator.apply(size()));
	}

	/**
	 * リスト内の指定された位置にある要素を、指定された要素に置き換えます。
	 * @param index 置換される要素のインデックス
	 * @param element 指定された位置に格納される要素
	 * @return 指定された位置に以前あった要素
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(index < 0||index>= size())
	 */
	@Override
	public abstract E set(int index, E element);

	@Override
	public abstract Array<E> clone();

	@Override
	public Array<E> subList(int fromIndex, int toIndex) {
		return subArray(fromIndex, toIndex);
	}

	/**
	 * この配列の、指定されたfromIndex (これを含む)からtoIndex (これを含まない)までの部分のビューを返します。
	 * fromIndexとtoIndexが等しい場合は、空の配列が返されます。
	 * 返される配列はこの配列に連動しているため、返される配列内での非構造的な変更はこの配列に反映され、この配列内での変更は返される配列に反映されます。
	 * 返される配列は、この配列によってサポートされている、任意の配列・オペレーションをすべてサポートします。<br>
	 * このメソッドでは、配列に一般的に見られるような、明示的な範囲操作は必要ありません。
	 * 配列を予期する操作は、配列全体ではなくsubArrayビューを渡すことで範囲操作として使用できます。
	 * たとえば、次のイディオムは、ある範囲の要素を指定した要素に上書きします。
	 * <p>
	 * {@code array.subArray(from, to).fill(val);}
	 * </p>
	 * @param fromIndex subArrayの下端点(これを含む)
	 * @param toIndex subArrayの上端点(これを含まない)
	 * @return この配列内の指定された範囲のビュー
	 * @exception IndexOutOfBoundsException 端点のインデックス値が範囲外の場合(fromIndex < 0||toIndex> size)
	 * @exception IllegalArgumentException 端点のインデックスの順番が正しくない場合(fromIndex> toIndex)
	 */
	public abstract Array<E> subArray(int fromIndex, int toIndex);

	/**
	 * 配列の各要素に、指定されたObject参照を代入します。
	 * @param val 配列のすべての要素に格納する値
	 */
	public void fill(E val) {
		for (int i = 0, l = size();i < l;++ i) set(i, val);
	}

	private static class ObjectArray<E> extends Array<E> implements java.io.Serializable {
		private static final long serialVersionUID = 4462707572782630404L;
		private final Object[] array;

		public ObjectArray(int size) {
			array = new Object[size];
		}

		public ObjectArray(Object[] array) {
			this.array = array;
		}

		@Override
		public E set(int index, E element) {
			E ret = get(index);
			array[index] = element;
			return ret;
		}

		@Override
		public E get(int index) {
			@SuppressWarnings("unchecked")
			E ret = (E) array[index];
			return ret;
		}

		@Override
		public int size() {
			return array.length;
		}

		@Override
		public int hashCode() {
			return java.util.Arrays.hashCode(array);
		}

		class Iter implements java.util.Iterator<E> {
			private int index;

			private Iter() {
				index = 0;
			}

			@Override
			public boolean hasNext() {
				return index < array.length;
			}

			@Override
			public E next() {
				return get(index++);
			}
		}

		@Override
		public java.util.Iterator<E> iterator() {
			return new Iter();
		}

		@Override
		public Object[] toArray() {
			return java.util.Arrays.copyOf(array, array.length);
		}

		@Override
		public <T> T[] toArray(T[] a) {
			int n = size();
			if (a.length < n) {
				@SuppressWarnings("unchecked")
				T[] copy = (T[]) java.lang.reflect.Array.newInstance(a.getClass(), n);
				a = copy;
			}
			System.arraycopy(array, 0, a, 0, array.length);
			return a;
		}

		@Override
		public <T> T[] toArray(java.util.function.IntFunction<T[]> generator) {
			T[] ret = generator.apply(array.length);
			System.arraycopy(array, 0, ret, 0, array.length);
			return ret;
		}

		@Override
		public java.util.Spliterator<E> spliterator() {
			return java.util.Spliterators.spliterator(array, java.util.Spliterator.ORDERED);
		}

		@Override
		public Array<E> clone() {
			return new ObjectArray<>(toArray());
		}

		@Override
		public void fill(E val) {
			java.util.Arrays.fill(array, val);
		}

		private class SubArray extends Array<E> implements java.io.Serializable {
			private static final long serialVersionUID = -5157970391331531932L;
			final int fromIndex;
			final int toIndex;

			SubArray(int from, int to) {
				fromIndex = from;
				toIndex = to;
			}

			@Override
			public E set(int index, E element) {
				return ObjectArray.this.set(index + fromIndex, element);
			}

			@Override
			public E get(int index) {
				return ObjectArray.this.get(index + fromIndex);
			}

			@Override
			public int size() {
				return toIndex - fromIndex;
			}

			@Override
			public int hashCode() {
				int hashCode = 1;
				for (int i = fromIndex; i < toIndex; ++i)
					hashCode = 31 * hashCode + (array[i] == null ? 0 : array[i].hashCode());
				return hashCode;
			}

			class Iter implements java.util.Iterator<E> {
				private int index;

				private Iter() {
					index = fromIndex;
				}

				@Override
				public boolean hasNext() {
					return index < toIndex;
				}

				@Override
				public E next() {
					return get(index++);
				}
			}

			@Override
			public java.util.Iterator<E> iterator() {
				return new Iter();
			}

			@Override
			public Object[] toArray() {
				return java.util.Arrays.copyOfRange(array, fromIndex, toIndex);
			}

			@Override
			public <T> T[] toArray(T[] a) {
				int n = size();
				if (a.length < n) {
					@SuppressWarnings("unchecked")
					T[] copy = (T[]) java.lang.reflect.Array.newInstance(a.getClass(), n);
					a = copy;
				}
				System.arraycopy(array, fromIndex, a, 0, size());
				return a;
			}

			@Override
			public <T> T[] toArray(java.util.function.IntFunction<T[]> generator) {
				T[] ret = generator.apply(size());
				System.arraycopy(array, fromIndex, ret, 0, ret.length);
				return ret;
			}

			@Override
			public java.util.Spliterator<E> spliterator() {
				return java.util.Spliterators.spliterator(array, fromIndex, toIndex, java.util.Spliterator.ORDERED);
			}

			@Override
			public Array<E> clone() {
				return new ObjectArray<>(toArray());
			}

			@Override
			public void fill(E val) {
				java.util.Arrays.fill(array, fromIndex, toIndex, val);
			}

			@Override
			public Array<E> subArray(int fromIndex, int toIndex) {
				if (fromIndex < 0 || toIndex > size())
					throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
				if (fromIndex > toIndex)
					throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
				return new SubArray(this.fromIndex + fromIndex, this.fromIndex + toIndex);
			}

		}

		@Override
		public Array<E> subArray(int fromIndex, int toIndex) {
			if (fromIndex < 0 || toIndex > size())
				throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
			if (fromIndex > toIndex) throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
			return new SubArray(fromIndex, toIndex);
		}
	}

	/**
	 * 指定された初期容量で空の配列を作成します。
	 * @param <E> 配列内のオブジェクトのクラス
	 * @param size 配列の容量
	 * @return 指定された配列の容量を持つ配列
	 * @exception IllegalArgumentException 指定された初期容量が負の場合
	 */
	public static <E> Array<E> create(int size) {
		if (size < 0) throw new IllegalArgumentException("" + size);
		return new ObjectArray<>(size);
	}

	/**
	 * 指定された配列に連動する配列を返します。
	 * 返された配列への変更は、そのままこの配列に書き込まれます。これは、配列ベースのAPIとコレクションベースのAPIの橋渡し役として機能します。
	 * @param <E> 配列内のオブジェクトのクラス
	 * @param array 連動する配列
	 * @return 指定された配列と連動するコレクションベースの配列
	 * @exception NullPointerException 指定された配列がnullの場合
	 */
	public static <E> Array<E> create(E[] array) {
		if (array == null) throw new NullPointerException();
		return new ObjectArray<>(array);
	}
}
