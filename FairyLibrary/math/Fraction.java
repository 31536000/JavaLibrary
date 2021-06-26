package math;

import math.algebraic.domain.IntegralDomain;
import math.algebraic.field.Field;
import math.algebraic.group.Abelian;
import math.algebraic.group.CommutativeMonoid;

public abstract class Fraction<T, A extends Abelian<T>, M extends CommutativeMonoid<T>, D extends IntegralDomain<T, A, M>, E extends Fraction<T, A, M, D, E>>
		implements Field<E, Abelian<E>, Abelian<E>> {
	/**
	 * 分子を取得します。
	 * @return 分子
	 */
	public abstract T getNumerator();

	/**
	 * 分母を取得します。
	 * @return 分母
	 */
	public abstract T getDenominator();

	/**
	 * 演算を取得します。
	 * @return 演算
	 */
	protected abstract D getOperator();

	/**
	 * 値が(this+val)であるFractionを返します。
	 * @param val このFractionに加算する値
	 * @return this + val
	 */
	public abstract E add(E val);

	@Override
	public abstract E add(E left, E right);

	/**
	 * 値が(this-val)であるFractionを返します。
	 * @param val このFractionから減算する値
	 * @return this - val
	 */
	public abstract E subtract(E val);

	@Override
	public abstract E subtract(E left, E right);

	/**
	 * 値が(this*val)であるFractionを返します。
	 * @param val このFractionで乗算する値
	 * @return this * val
	 */
	public abstract E multiply(E val);

	@Override
	public abstract E multiply(E left, E right);

	/**
	 * 値が(this/val)であるFractionを返します。
	 * @param val このFractionを除算する値
	 * @return this / val
	 */
	public abstract E divide(E val);

	@Override
	public E divide(E left, E right) {
		return multiply(left, inverse(right));
	}

	/**
	 * このFractionの逆数を返します。
	 * @return 1 / this
	 */
	public abstract E inverse();

	@Override
	public abstract E inverse(E val);

	@Override
	public int hashCode() {
		return getNumerator().hashCode() ^ getDenominator().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Fraction) {
			try {
				@SuppressWarnings("unchecked")
				E t = (E) o;
				D operator = getOperator();
				Abelian<T> add = operator.getAddition();
				CommutativeMonoid<T> mul = operator.getMultiplication();
				return add.subtract(mul.apply(getNumerator(), t.getDenominator()),
						mul.apply(getDenominator(), t.getNumerator())).equals(add.identity());
			} catch (ClassCastException e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return getNumerator().toString() + "/" + getDenominator().toString();
	}

	@Override
	public abstract Abelian<E> getAddition();

	@Override
	public abstract Abelian<E> getMultiplication();

	private static class ObjectFraction<T, A extends Abelian<T>, M extends CommutativeMonoid<T>, D extends IntegralDomain<T, A, M>>
			extends Fraction<T, A, M, D, ObjectFraction<T, A, M, D>> {
		private final T numerator, denominator;
		private final D operator;

		public ObjectFraction(T numerator, T denominator, D operator) {
			this.numerator = numerator;
			this.denominator = denominator;
			this.operator = operator;
		}

		@Override
		public T getNumerator() {
			return numerator;
		}

		@Override
		public T getDenominator() {
			return denominator;
		}

		@Override
		protected D getOperator() {
			return operator;
		}

		/**
		 * 値が(this+val)であるFractionを返します。
		 * @param val このFractionに加算する値
		 * @return this + val
		 */
		@Override
		public ObjectFraction<T, A, M, D> add(ObjectFraction<T, A, M, D> val) {
			return add(this, val);
		}

		@Override
		public ObjectFraction<T, A, M, D> add(ObjectFraction<T, A, M, D> left, ObjectFraction<T, A, M, D> right) {
			D operator = left.getOperator();
			Abelian<T> add = operator.getAddition();
			CommutativeMonoid<T> mul = operator.getMultiplication();
			return new ObjectFraction<>(
					add.apply(mul.apply(left.getNumerator(), right.getDenominator()),
							mul.apply(left.getDenominator(), right.getNumerator())),
					mul.apply(left.getDenominator(), right.getDenominator()), operator);
		}

		/**
		 * 値が(this-val)であるFractionを返します。
		 * @param val このFractionから減算する値
		 * @return this - val
		 */
		@Override
		public ObjectFraction<T, A, M, D> subtract(ObjectFraction<T, A, M, D> val) {
			return subtract(this, val);
		}

		@Override
		public ObjectFraction<T, A, M, D> subtract(ObjectFraction<T, A, M, D> left, ObjectFraction<T, A, M, D> right) {
			D operator = left.getOperator();
			Abelian<T> add = operator.getAddition();
			CommutativeMonoid<T> mul = operator.getMultiplication();
			return new ObjectFraction<>(
					add.subtract(mul.apply(left.getNumerator(), right.getDenominator()),
							mul.apply(left.getDenominator(), right.getNumerator())),
					mul.apply(left.getDenominator(), right.getDenominator()), operator);
		}

		/**
		 * 値が(this*val)であるFractionを返します。
		 * @param val このFractionで乗算する値
		 * @return this * val
		 */
		@Override
		public ObjectFraction<T, A, M, D> multiply(ObjectFraction<T, A, M, D> val) {
			return multiply(this, val);
		}

		@Override
		public ObjectFraction<T, A, M, D> multiply(ObjectFraction<T, A, M, D> left, ObjectFraction<T, A, M, D> right) {
			D operator = left.getOperator();
			CommutativeMonoid<T> mul = operator.getMultiplication();
			return new ObjectFraction<>(mul.apply(left.getNumerator(), right.getNumerator()),
					mul.apply(left.getDenominator(), right.getNumerator()), operator);
		}

		/**
		 * 値が(this/val)であるFractionを返します。
		 * @param val このFractionを除算する値
		 * @return this / val
		 */
		@Override
		public ObjectFraction<T, A, M, D> divide(ObjectFraction<T, A, M, D> val) {
			return divide(this, val);
		}

		@Override
		public ObjectFraction<T, A, M, D> divide(ObjectFraction<T, A, M, D> left, ObjectFraction<T, A, M, D> right) {
			return multiply(left, inverse(right));
		}

		/**
		 * このFractionの逆数を返します。
		 * @return 1 / this
		 */
		@Override
		public ObjectFraction<T, A, M, D> inverse() {
			return inverse(this);
		}

		@Override
		public ObjectFraction<T, A, M, D> inverse(ObjectFraction<T, A, M, D> val) {
			return new ObjectFraction<>(val.getDenominator(), val.getNumerator(), getOperator());
		}

		private class Addition implements Abelian<ObjectFraction<T, A, M, D>> {

			@Override
			public ObjectFraction<T, A, M, D> apply(ObjectFraction<T, A, M, D> t, ObjectFraction<T, A, M, D> u) {
				return add(t, u);
			}

			@Override
			public ObjectFraction<T, A, M, D> identity() {
				D operator = getOperator();
				return new ObjectFraction<>(operator.getAddition().identity(),
						operator.getMultiplication().identity(), operator);
			}

			@Override
			public ObjectFraction<T, A, M, D> inverse(ObjectFraction<T, A, M, D> element) {
				D operator = element.getOperator();
				return new ObjectFraction<>(operator.getAddition().inverse(element.getNumerator()),
						element.getDenominator(), operator);
			}
		}

		private class Multiplication implements Abelian<ObjectFraction<T, A, M, D>> {
			@Override
			public ObjectFraction<T, A, M, D> apply(ObjectFraction<T, A, M, D> t, ObjectFraction<T, A, M, D> u) {
				return multiply(t, u);
			}

			@Override
			public ObjectFraction<T, A, M, D> identity() {
				D operator = getOperator();
				return new ObjectFraction<>(operator.getMultiplication().identity(),
						operator.getMultiplication().identity(), operator);
			}

			@Override
			public ObjectFraction<T, A, M, D> inverse(ObjectFraction<T, A, M, D> element) {
				return new ObjectFraction<>(element.getDenominator(), element.getNumerator(), element.getOperator());
			}
		}

		@Override
		public Abelian<ObjectFraction<T, A, M, D>> getAddition() { return new Addition(); }

		@Override
		public Abelian<ObjectFraction<T, A, M, D>> getMultiplication() { return new Multiplication(); }
	}

	public static <T, A extends Abelian<T>, M extends CommutativeMonoid<T>, D extends IntegralDomain<T, A, M>> Fraction<T, A, M, D, ObjectFraction<T,A,M,D>> create(T numerator,
			T denominator, D operator) {
		return new ObjectFraction<>(numerator, denominator, operator);
	}
}
