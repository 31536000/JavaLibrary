package com._31536000.algo;

import com._31536000.math.algebraic.group.Associative;

public class DisjointSparseTable<T> {
	private final Associative<T> operate;
	private final Object[] table;
	
	public DisjointSparseTable(T[] array, Associative<T> operate) {
		this.operate = operate;
		
	}

	private T operate(T left, T right) {
		if (left == null) return right;
		if (right == null) return left;
		return operate.operate(left, right);
	}
}
