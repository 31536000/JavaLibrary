package com._31536000.util.collect;

import java.util.Iterator;

/**
 * 要素とそのindexを効率的に取得する関数を提供します。
 * @author 31536000
 *
 */
public class Enumeration {
	/**
	 * 要素とそのindexを管理するクラスです。
	 * @author 31536000
	 *
	 * @param <E> 保持する要素
	 */
	public static class Enumerate<E> {
		public final E value;
		public final int index;

		/**
		 * 要素とそのindexを渡します。<br>
		 * indexは必ずしも元の配列またはコレクションのindexと一致する必要はありませんが、一致する値を返すことが推奨されます。
		 * @param value
		 * @param index
		 */
		public Enumerate(E value, int index) {
			this.value = value;
			this.index = index;
		}

		/**
		 * 要素を返します。
		 * @return 要素
		 */
		public E getValue() {
			return value;
		}

		/**
		 * indexを返します。
		 * @return index
		 */
		public int getIndex() {
			return index;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Enumerate) return ((Enumerate<?>)o).getValue().equals(value) && ((Enumerate<?>)o).getIndex() == index;
			return false;
		}

		@Override
		public int hashCode() {
			return value.hashCode() ^ index;
		}

		@Override
		public String toString() {
			return "{" + value.toString() + ", " + index + "}";
		}
	}

	private static class IteratorArray<E> implements Iterator<Enumerate<E>>{
		private final E[] array;
		private final int start;
		private int index;

		public IteratorArray(E[] array, int index) {
			this.array = array;
			this.start = index;
			this.index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < array.length;
		}

		@Override
		public Enumerate<E> next() {
			Enumerate<E> ret = new Enumerate<>(array[index], index+++start);
			return ret;
		}
	}

	private static class IteratorCollection<E> implements Iterator<Enumerate<E>>{
		private final Iterator<E> iter;
		private int start;

		public IteratorCollection(Iterator<E> iter, int index) {
			this.iter = iter;
			this.start = index;
		}

		@Override
		public boolean hasNext() {
			return iter.hasNext();
		}

		@Override
		public Enumerate<E> next() {
			Enumerate<E> ret = new Enumerate<>(iter.next(), start++);
			return ret;
		}
	}

	/**
	 * 配列の各要素とそのindexを順に返すIteratorを生成します。
	 * @param <E> 配列の型
	 * @param array 配列
	 * @return Enumerate&lt;E&gt;のIterator
	 */
	public static <E> Iterator<Enumerate<E>> enumerate(E[] array) {
		return enumerate(array, 0);
	}

	/**
	 * 配列の各要素とそのindexを順に返すIteratorを生成します。
	 * @param <E> 配列の型
	 * @param array 配列
	 * @param start 添字の初期値、この値だけindexが足されたものが返る
	 * @return Enumerate&lt;E&gt;のIterator
	 */
	public static <E> Iterator<Enumerate<E>> enumerate(E[] array, int start) {
		if (array == null) throw new NullPointerException("array is null");
		return new IteratorArray<E>(array, start);
	}

	/**
	 * Iteratorの各要素とそのindexを順に返すIteratorを生成します。
	 * @param <E> Iteratorの型
	 * @param iter Iterator
	 * @return Enumerate&lt;E&gt;のIterator
	 */
	public static <E> Iterator<Enumerate<E>> enumerate(Iterator<E> iter) {
		return enumerate(iter, 0);
	}

	/**
	 * Iteratorの各要素とそのindexを順に返すIteratorを生成します。
	 * @param <E> Iteratorの型
	 * @param iter Iterator
	 * @param start 添字の初期値、この値だけindexが足されたものが返る
	 * @return Enumerate&lt;E&gt;のIterator
	 */
	public static <E> Iterator<Enumerate<E>> enumerate(Iterator<E> iter, int start) {
		if (iter == null) throw new NullPointerException("iterator is null");
		return new IteratorCollection<E>(iter, start);
	}
}
