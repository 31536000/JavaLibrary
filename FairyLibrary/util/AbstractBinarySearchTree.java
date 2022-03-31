package com._31536000.util;


import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Queue;

public abstract class AbstractBinarySearchTree<E> extends AbstractCollection<E> implements Tree<E>, Queue<E> {

	Comparator<? super E> comparator;

	@SuppressWarnings("unchecked")
	public int compare(E e, E e2) {
		if (comparator == null) return ((Comparable<E>)e).compareTo(e2);
		else return comparator.compare(e, e2);
	}

	public AbstractBinarySearchTree() {
	}

	public AbstractBinarySearchTree(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	public AbstractBinarySearchTree(Comparator<? super E> comparator) {
		this();
		this.comparator = comparator;
	}



	@SuppressWarnings("unchecked")
	public E[] toArray() {
		return (E[]) super.toArray();
	}

	@Override
	public boolean offer(E e) {
		return add(e);
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
		return getFirst();
	}

	@Override
	public E peek() {
		return peekFirst();
	}

	public E pollFirst() {
		try {
			return removeFirst();
		} catch(Exception e) {
			return null;
		}
	}

	public E pollLast() {
		try {
			return removeLast();
		} catch(Exception e) {
			return null;
		}
	}

	public abstract E getFirst();

	public abstract E getLast();

	public E peekFirst() {
		try {
			return getFirst();
		} catch(Exception e) {
			return null;
		}
	}

	public E peekLast() {
		try {
			return getLast();
		} catch(Exception e) {
			return null;
		}
	}

	public E pop() {
		return removeFirst();
	}

	public void push(E e) {
		add(e);
	}

	public abstract E removeFirst();

	public abstract E removeLast();

	@Override
	public Comparator<? super E> comparator() {
		return comparator;
	}

	@Override
	public E first() {
		return getFirst();
	}

	@Override
	public E last() {
		return getLast();
	}

}
