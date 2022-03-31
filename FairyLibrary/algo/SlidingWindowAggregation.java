package com._31536000.algo;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com._31536000.math.algebraic.group.Monoid;

public class SlidingWindowAggregation<T> extends AbstractQueue<T> implements Deque<T>{
	private final Monoid<T> operator;
	private ArrayList<T> left, right;
	private ArrayList<T> leftSum, rightSum;

	public SlidingWindowAggregation(Monoid<T> operator) {
		this.operator = operator;
		left = new ArrayList<>();
		right = new ArrayList<>();
		leftSum = new ArrayList<>();
		rightSum = new ArrayList<>();
		clearSum();
	}

	@Override
	public void addFirst(T e) {
		leftSum.add(operator.apply(e, leftSum.get(left.size())));
		left.add(e);
	}

	@Override
	public void addLast(T e) {
		rightSum.add(operator.apply(rightSum.get(right.size()), e));
		right.add(e);
	}

	@Override
	public boolean offerFirst(T e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(T e) {
		addLast(e);
		return true;
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		return pollFirst();
	}

	@Override
	public T removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		return pollLast();
	}

	private void clearSum() {
		leftSum.clear();
		rightSum.clear();
		leftSum.add(operator.identity());
		rightSum.add(operator.identity());
	}

	@Override
	public T pollFirst() {
		if (left.isEmpty()) {
			clearSum();
			ArrayList<T> tmp = right;
			right = new ArrayList<>(tmp.size() >> 1);
			left.ensureCapacity(tmp.size() >> 1);
			for (int i = tmp.size() / 2 - 1;i > 0;-- i) addFirst(tmp.get(i));
			for (int i = Math.max(1, tmp.size() >> 1), l = tmp.size();i < l;++ i) addLast(tmp.get(i));
			return tmp.get(0);
		}
		leftSum.remove(left.size());
		return left.remove(left.size() - 1);
	}

	@Override
	public T pollLast() {
		if (right.isEmpty()) {
			clearSum();
			ArrayList<T> tmp = left;
			left = new ArrayList<>(tmp.size() >> 1);
			right.ensureCapacity(tmp.size() >> 1);
			for (int i = tmp.size() / 2 - 1;i > 0;-- i) addLast(tmp.get(i));
			for (int i = Math.max(1, tmp.size() >> 1), l = tmp.size();i < l;++ i) addFirst(tmp.get(i));
			return tmp.get(0);
		}
		rightSum.remove(right.size());
		return right.remove(right.size() - 1);
	}

	@Override
	public T getFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		return peekFirst();
	}

	@Override
	public T getLast() {
		if (isEmpty()) throw new NoSuchElementException();
		return peekLast();
	}

	@Override
	public T peekFirst() {
		return left.get(left.size() - 1);
	}

	@Override
	public T peekLast() {
		return right.get(right.size() - 1);
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean offer(T e) {
		return offerLast(e);
	}

	@Override
	public T poll() {
		return pollFirst();
	}

	@Override
	public T peek() {
		return peekFirst();
	}

	@Override
	public void push(T e) {
		addFirst(e);
	}

	@Override
	public T pop() {
		return removeFirst();
	}

	@Override
	public int size() {
		return left.size() + right.size();
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<T> descendingIterator() {
		throw new UnsupportedOperationException();
	}

	public T foldAll() {
		return operator.apply(leftSum.get(left.size()), rightSum.get(right.size()));
	}
}
