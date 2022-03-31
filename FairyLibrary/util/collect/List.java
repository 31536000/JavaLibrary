package com._31536000.util.collect;


public interface List<E> extends Collection<E>, java.util.List<E> {
	@Override
	public default E getFirst() {
		try {
			return get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new java.util.NoSuchElementException();
		}
	}
	@Override
	public default E getLast() {
		try {
			return get(size() - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new java.util.NoSuchElementException();
		}
	}
	public default void reverse(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > size()) throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
		if (fromIndex > toIndex) throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
		while(fromIndex < -- toIndex) {
			E swap = get(fromIndex);
			set(fromIndex ++, get(toIndex));
			set(toIndex, swap);
		}
	}
	public default void reverse() {
		reverse(0, size());
	}
	public default void merge(java.util.Collection<? extends E> c) {
		addAll(c);
		c.clear();
	}
	public List<E> split(int fromIndex, int toIndex);
	@Override
	public List<E> subList(int fromIndex, int toIndex);
	/**
	 * このリスト内の要素を(適切な順序で)反復するリスト・イテレータを返します。
	 * このメソッドによって返されるリスト・イテレータの反復順は、{@link #listIterator()}と逆順になります。
	 * @return このリスト内の要素を(適切な順序で)反復するリスト・イテレータ
	 */
	public default java.util.ListIterator<E> reverseIterator() {
		java.util.ListIterator<E> iterator = listIterator(size());
		class ReverseIterator implements java.util.ListIterator<E> {
			@Override
			public boolean hasNext() {
				return iterator.hasPrevious();
			}
			@Override
			public E next() {
				return iterator.previous();
			}
			@Override
			public boolean hasPrevious() {
				return iterator.hasNext();
			}
			@Override
			public E previous() {
				return iterator.next();
			}
			@Override
			public int nextIndex() {
				return iterator.previousIndex();
			}
			@Override
			public int previousIndex() {
				return iterator.nextIndex();
			}
			@Override
			public void remove() {
				iterator.remove();
			}
			@Override
			public void set(E e) {
				iterator.set(e);
			}
			@Override
			public void add(E e) {
				iterator.add(e);
				iterator.previous();
			}
		}
		return new ReverseIterator();
	}
}
