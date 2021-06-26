package math.algebraic.operator;

import math.algebraic.group.CommutativeMonoid;
import math.algebraic.group.Idempotent;

public class Minimum<T extends Comparable<T>> implements CommutativeMonoid<T>, Idempotent<T> {

	@Override
	public T apply(T left, T right) {
		if (left == null) return right;
		else if (right == null) return left;
		return left.compareTo(right) < 0 ? left : right;
	}

	@Override
	public T identity() {
		return null;
	}

	@Override
	public T hyper(T element, int repeat) {
		return element;
	}

}
