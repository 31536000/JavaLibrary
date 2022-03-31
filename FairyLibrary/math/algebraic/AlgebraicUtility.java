package com._31536000.math.algebraic;

import java.util.function.BinaryOperator;
import com._31536000.math.algebraic.group.Associative;
import com._31536000.math.algebraic.group.Monoid;

public class AlgebraicUtility {
	private AlgebraicUtility() {
		throw new AssertionError();
	}
	
	

	@SafeVarargs
	public static <T> T apply(BinaryOperator<T> operate, T... element) {
		T ret = element[0];
		for (int i = 1;i < element.length;++ i) ret = operate.apply(ret, element[i]);
		return ret;
	}

	public static <T> Monoid<T> getMonoid(Associative<T> operate) {
		class NullMonoid implements Monoid<T> {
			private final Associative<T> operate;

			private NullMonoid(Associative<T> operate) {
				this.operate = operate;
			}

			@Override
			public T apply(T t, T u) {
				if (t == null) return u;
				if (u == null) return t;
				return operate.apply(t, u);
			}

			@Override
			public T identity() {
				return null;
			}
		}
		return new NullMonoid(operate);
	}

	public static <T> Monoid<T> getMonoid(Associative<T> operate, T unit) {
		if (unit == null) return getMonoid(operate);
		class AddMonoid implements Monoid<T> {
			private final Associative<T> operate;
			private final T unit;

			private AddMonoid(Associative<T> operate, T unit) {
				this.operate = operate;
				this.unit = unit;
			}

			@Override
			public T apply(T t, T u) {
				if (unit.equals(t)) return u;
				if (unit.equals(u)) return t;
				return operate.apply(t, u);
			}

			@Override
			public T identity() {
				return unit;
			}
		}
		return new AddMonoid(operate, unit);
	}
}
