package com._31536000.util.collect;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class LinkedList<E> implements List<E>, Deque<E>, Cloneable {

	private static class ObjectLinkedList<E> extends LinkedList<E>{
		private static final int MAX_ARRAY_SIZE = 32;
		private class Node {
			Node previous, next;
			int size = 0;
			final Object[] array;

			public Node() {
				previous = next = this;
				array = new Object[0];
			}

			public Node(Node previous, Node next) {
				this.previous = previous;
				this.next = next;
				array = new Object[MAX_ARRAY_SIZE];
			}
		}

		private final Node sentinel = new Node();
		private int size = 0;

		public ObjectLinkedList() {

		}

		public ObjectLinkedList(Collection<? extends E> c) {
			addAll(c);
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean isEmpty() {
			return size == 0;
		}

		@Override
		public boolean contains(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public Iterator<E> iterator() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public boolean add(E e) {
			Node put = sentinel.previous;
			if (put.size < put.array.length) {
				put.array[put.size ++] = e;
				return true;
			}
			Node next = new Node(put, sentinel);
			if (put.next == sentinel) put.next = next;
			else put.previous = next;
			sentinel.previous = next;
			next.array[next.size ++] = e;
			return true;
		}

		@Override
		public boolean remove(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public void clear() {
			sentinel.previous = sentinel.next = sentinel;
			size = 0;
		}

		@Override
		public E get(int index) {
			if (index < 0 || index >= size) throw new IndexOutOfBoundsException("out of range: " + index);
			Node last = sentinel, check = sentinel.next;
			do {
				if (check.previous == last) {
					if (check.size <= index) {
						index -= check.size;
						last = check;
						check = check.next;
						continue;
					}
					@SuppressWarnings("unchecked")
					E ret = (E) check.array[index];
					return ret;
				}
				if (check.size <= index) {
					index -= check.size;
					last = check;
					check = check.previous;
					continue;
				}
				@SuppressWarnings("unchecked")
				E ret = (E) check.array[size - index - 1];
				return ret;
			}while(true);
		}

		@Override
		public E set(int index, E element) {
			if (index < 0 || index >= size) throw new IndexOutOfBoundsException("out of range: " + index);
			Node last = sentinel, check = sentinel.next;
			do {
				if (check.previous == last) {
					if (check.size <= index) {
						index -= check.size;
						last = check;
						check = check.next;
						continue;
					}
					@SuppressWarnings("unchecked")
					E ret = (E) check.array[index];
					check.array[index] = element;
					return ret;
				}
				if (check.size <= index) {
					index -= check.size;
					last = check;
					check = check.previous;
					continue;
				}
				@SuppressWarnings("unchecked")
				E ret = (E) check.array[size - index - 1];
				check.array[size - index - 1] = element;
				return ret;
			}while(true);
		}

		@Override
		public void add(int index, E element) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public E remove(int index) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public int indexOf(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			return 0;
		}

		@Override
		public ListIterator<E> listIterator() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public ListIterator<E> listIterator(int index) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public void addFirst(E e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void addLast(E e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public boolean offerFirst(E e) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean offerLast(E e) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public E removeFirst() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E removeLast() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E pollFirst() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E pollLast() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E getFirst() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E getLast() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E peekFirst() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E peekLast() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public boolean removeFirstOccurrence(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean removeLastOccurrence(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean offer(E e) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public E remove() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E poll() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E element() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public E peek() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public void push(E e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public E pop() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public Iterator<E> descendingIterator() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public void merge(LinkedList<? extends E> list) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public LinkedList<E> split(int fromIndex, int toIndex) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public void reverse() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void reverse(int fromIndex, int toIndex) {
			// TODO 自動生成されたメソッド・スタブ

		}
	}

	public abstract void merge(LinkedList<? extends E> list);

	public abstract LinkedList<E> split(int fromIndex, int toIndex);

	public abstract void reverse();

	public abstract void reverse(int fromIndex, int toIndex);
	@Override
	public String toString() {
		return stream().map(i -> String.valueOf(i)).collect(java.util.stream.Collectors.joining(", ", "[", "]"));
	}
	@Override
	public boolean equals(Object o) {
		return o instanceof java.util.List ? equals((java.util.List<?>)o) : false;
	}
	/**
	 * 指定されたリストがこのリストと等しいかどうかを比較します。
	 * 指定されたリストのサイズが同じで、2つのリストの対応する要素がすべて等しい場合にだけtrueを返します。
	 * 2つの要素e1とe2は、(e1==null ? e2==null : e1.equals(e2))である場合に等しくなります。
	 * つまり2つのリストは、同じ要素が同じ順序で含まれている場合に等しいものとして定義されます。
	 * この定義により、Listインタフェースの実装が異なっても、equalsメソッドが正しく動作することが保証されます。
	 * @param o このリストと等しいかどうかを比較するオブジェクト
	 * @return 指定されたオブジェクトがこのリストと等しい場合はtrue
	 */
	public boolean equals(java.util.List<?> o) {
		int size = size();
		if (size != o.size()) return false;
		java.util.Iterator<?> iter1 = iterator(), iter2 = o.iterator();
		for (int i = 0;i < size;++ i) {
			if (!java.util.Objects.equals(iter1.next(), iter2.next())) return false;
		}
		return true;
	}
	@Override
	public int hashCode() {
		int hashCode = 1;
		for (E e : this) hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}
}
