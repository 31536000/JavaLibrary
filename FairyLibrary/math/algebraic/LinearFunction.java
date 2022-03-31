package com._31536000.math.algebraic;

import com._31536000.math.algebraic.group.Magma;
import com._31536000.math.algebraic.morphism.EndoMorphism;

public class LinearFunction<T> implements EndoMorphism<T> {
	private final Magma<T> addition, multiplication;
	final T a, b;
	LinearFunction(Magma<T> addition, Magma<T> multiplication, T a, T b) {
		this.addition = addition;
		this.multiplication = multiplication;
		this.a = a;
		this.b = b;
	}
	@Override
	public T apply(T t) {
		return addition.apply(multiplication.apply(a, t), b);
	}

}
