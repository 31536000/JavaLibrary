package com._31536000.util;

import java.util.Collection;
import java.util.Iterator;

public abstract class Enumeration<V> implements Iterator<Enumeration.Entry<V>>, Iterable<Enumeration.Entry<V>> {

	public static class Entry<V> {
		public final int index;
		public final V value;

		public Entry(int index, V value) {
			this.index = index;
			this.value = value;
		}

		public int getIndex() {
			return index;
		}

		public V getValue() {
			return value;
		}

		@Override
		public String toString() {
			return index + ":" + value.toString();
		}
	}

	@Override
	public Iterator<Entry<V>> iterator() {
		return this;
	}

	public static <V> Enumeration<V> create(V[] array) {
		return new EnumerationArray<>(array);
	}

	public static <V> Enumeration<V> create(Collection<? extends V> collection) {
		return new EnumerationCollection<>(collection);
	}

	private static class EnumerationArray<V> extends Enumeration<V> {
		private int index;
		private final V[] array;

		public EnumerationArray(V[] array) {
			index = 0;
			this.array = array;
		}
		@Override
		public boolean hasNext() {
			return index != array.length;
		}

		@Override
		public Entry<V> next() {
			Entry<V> ret = new Entry<>(index, array[index]);
			++ index;
			return ret;
		}
	}

	private static class EnumerationCollection<V> extends Enumeration<V> {
		private int index;
		private final Iterator<? extends V> iterator;

		public EnumerationCollection(Collection<? extends V> collection) {
			index = 0;
			iterator = collection.iterator();
		}
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Entry<V> next() {
			Entry<V> ret = new Entry<>(index, iterator.next());
			++ index;
			return ret;
		}
	}
}
