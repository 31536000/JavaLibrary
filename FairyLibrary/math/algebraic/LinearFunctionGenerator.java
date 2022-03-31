package com._31536000.math.algebraic;

import com._31536000.math.algebraic.group.Monoid;
import com._31536000.math.algebraic.ring.NearRing;

public class LinearFunctionGenerator<T> {
	private final Monoid<T> addition, multiplication;
	private final LinearFunction<T> identity;

	public LinearFunctionGenerator(NearRing<T, ? extends Monoid<T>, ? extends Monoid<T>> ring) {
		this(ring.getAddition(), ring.getMultiplication());
	}

	public LinearFunctionGenerator(Monoid<T> addition, Monoid<T> multiplication) {
		this.addition = addition;
		this.multiplication = multiplication;
		identity = new LinearFunction<>(addition, multiplication, multiplication.identity(), addition.identity());
	}

	public LinearFunction<T> linearFunction(T a, T b) {
		return new LinearFunction<>(addition, multiplication, a, b);
	}

	public Monoid<LinearFunction<T>> composition() {
		return new Monoid<LinearFunction<T>>() {
			@Override
			public LinearFunction<T> apply(LinearFunction<T> t, LinearFunction<T> u) {
				return new LinearFunction<>(addition, multiplication, multiplication.apply(t.a, u.a),
						addition.apply(multiplication.apply(t.a, u.b), t.b));
			}

			@Override
			public LinearFunction<T> identity() {
				return identity;
			}
		};
	}
}
