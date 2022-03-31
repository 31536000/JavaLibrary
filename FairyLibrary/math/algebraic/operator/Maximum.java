package com._31536000.math.algebraic.operator;

import com._31536000.math.algebraic.group.Semilattice;

public class Maximum<T extends Comparable<T>> implements Semilattice<T>{

	@Override
	public T apply(T left, T right) {
		if (left == null) return right;
		else if (right == null) return left;
		return left.compareTo(right) > 0 ? left : right;
	}

	@Override
	public T identity() {
		return null;
	}

}
