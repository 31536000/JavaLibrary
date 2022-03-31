package com._31536000.util.collect;

import java.util.function.IntFunction;

public interface Collection<E> extends java.util.Collection<E>{
	public default E getFirst() {
		return iterator().next();
	}
	public default E getLast() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		E ret = null;
		for (E i : this) ret = i;
		return ret;
	}
	public default <T> T[] toArray(IntFunction<T[]> generator) {
		return toArray(generator.apply(0));
	}
}
