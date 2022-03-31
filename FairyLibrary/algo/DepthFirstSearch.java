package com._31536000.algo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;

public class DepthFirstSearch<T> implements Iterable<T>, Iterator<T>{
	Deque<T> stack;
	HashSet<T> hash;
	public DepthFirstSearch() {
		stack = new ArrayDeque<T>();
		hash = new HashSet<T>();
	}

	public DepthFirstSearch(T t) {
		this();
		push(t);
	}

	public boolean push(T t) {
		if (!hash.add(t)) return false;
		stack.addFirst(t);
		return true;
	}

	public boolean contains(T t) {
		return hash.contains(t);
	}

	public void clear() {
		stack.clear();
		hash.clear();
	}
	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public T next() {
		return stack.pollFirst();
	}
}
