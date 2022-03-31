package com._31536000.util;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.function.Consumer;

public class CircularDeque<E> extends AbstractCollection<E> implements Deque<E>, Serializable {

	private static final long serialVersionUID = -833492169956601730L;
	transient final Object[] elements;
	transient int head = 0, tail = 0, size = 0;

	public CircularDeque() {
		this(32);
	}

	public CircularDeque(int size) {
		elements = new Object[32];
	}

	public CircularDeque(Collection<? extends E> coll) {
		elements = coll.toArray();
	}

	public boolean add(E e) {
		return offer(e);
	}

	public E get(int index) {
		if (0 > index || index < size) throw new NoSuchElementException();
		if ((index += head) >= elements.length) index -= elements.length;
		@SuppressWarnings("unchecked")
		E ret = (E)elements[index];
		return ret;
	}

	@Override
	public boolean offer(E e) {
		return offerLast(e);
	}

	@Override
	public E remove() {
		return removeFirst();
	}

	@Override
	public E poll() {
		return pollFirst();
	}

	@Override
	public E element() {
		E ret = peek();
		if (ret == null) throw new NoSuchElementException();
		return ret;
	}

	@Override
	public E peek() {
		return peekFirst();
	}

	@Override
	public int size() {
		return size;
	}

	public int maxSize() {
		return elements.length;
	}

	public boolean isAtFullCapacity() {
		return size == elements.length;
	}

	public void rotate(int index) {
		if (size != elements.length) throw new IllegalStateException();
		if ((head += index) >= elements.length) head -= elements.length;
		if ((tail += index) >= elements.length) tail -= elements.length;
	}

	@Override
	public Iterator<E> iterator() {
		return new DeqIterator();
	}

	private class DeqIterator implements Iterator<E> {

		private int cursor = head;

		private int fence = tail;

		public boolean hasNext() {
			return cursor != fence;
		}

		public E next() {
			if (cursor == fence) throw new NoSuchElementException();
			@SuppressWarnings("unchecked")
			E result = (E)elements[cursor];
			if (++ cursor >= elements.length) cursor = 0;
			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public void forEachRemaining(Consumer<? super E> action) {
			while (hasNext()) action.accept(next());
		}
	}

	@Override
	public void addFirst(E e) {
		if (e == null) throw new NullPointerException();
		elements[head--] = e;
		if (head < 0) {
			if (tail == elements.length - 1) ++ head;
			else {
				head = elements.length - 1;
				++ size;
			}
		} else if (head == tail) ++ head;
		else ++ size;
	}

	@Override
	public void addLast(E e) {
		if (e == null) throw new NullPointerException();
		elements[tail++] = e;
		if (tail >= elements.length) {
			if (head == 0) -- tail;
			else {
				tail = 0;
				++ size;
			}
		} else if (tail == head) -- tail;
		else ++ size;
	}

	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	@Override
	public E removeFirst() {
		E ret = pollFirst();
		if (ret == null) throw new NoSuchElementException();
		return ret;
	}

	@Override
	public E removeLast() {
		E ret = pollLast();
		if (ret == null) throw new NoSuchElementException();
		return ret;
	}

	@Override
	public E pollFirst() {
		if (size == 0) return null;
		-- size;
		@SuppressWarnings("unchecked")
		E ret = (E)elements[head];
		elements[head++] = null;
		if (head >= elements.length) head = 0;
		return ret;
	}

	@Override
	public E pollLast() {
		if (size == 0) return null;
		-- size;
		@SuppressWarnings("unchecked")
		E ret = (E)elements[tail];
		elements[tail--] = null;
		if (tail < 0) tail = elements.length - 1;
		return ret;
	}

	@Override
	public E getFirst() {
		E ret = peekFirst();
		if (ret == null) throw new NoSuchElementException();
		return ret;
	}

	@Override
	public E getLast() {
		E ret = peekLast();
		if (ret == null) throw new NoSuchElementException();
		return ret;
	}

	@Override
	public E peekFirst() {
		if (size == 0) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)elements[head];
		return ret;
	}

	@Override
	public E peekLast() {
		if (size == 0) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)elements[tail];
		return ret;
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
	public void push(E e) {
		addFirst(e);
	}

	@Override
	public E pop() {
		return removeFirst();
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
