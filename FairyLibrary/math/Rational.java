package com._31536000.math;

import com._31536000.math.algebraic.domain.LongEuclideanDomain;
import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongCommutativeMonoid;
import com._31536000.math.algebraic.order.TotalOrder;

public class Rational extends
		Fraction<Long, LongAbelian, LongCommutativeMonoid, LongEuclideanDomain<LongAbelian, LongCommutativeMonoid>, Rational>
			implements TotalOrder<Rational>, Comparable<Rational>{
	private final long numerator, denominator;
	public static final Rational ZERO = new Rational(0, 1), ONE = new Rational(1, 1), HALF = new Rational(1, 2);

	Rational(long numerator, long denominator) {
		if (denominator == 0) throw new IllegalArgumentException("divide by zero.");
		if (denominator < 0) {
			numerator = -numerator;
			denominator = -denominator;
		}
		long gcd = gcd(Math.abs(numerator), denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}

	public static long gcd(long a, long b) {
		while (a != 0) if ((b %= a) != 0) a %= b;
		else return a;
		return b;
	}

	@Override
	public Long getNumerator() { return numerator; }

	public long getNumeratorAsLong() { return numerator; }

	@Override
	public Long getDenominator() { return denominator; }

	public long getDenominatorAsLong() { return denominator; }

	/**
	 * 値が(this+val)である有理数を返します。
	 * @param val この有理数に加算する値
	 * @return this + val
	 */
	@Override
	public Rational add(Rational val) {
		return plus(this, val);
	}

	/**
	 * 値が(left+right)である有理数を返します。
	 * @param left 有理数
	 * @param right 有理数
	 * @return left + right
	 */
	@Override
	public Rational plus(Rational left, Rational right) {
		return new Rational(left.numerator * right.denominator + left.denominator * right.numerator,
				left.denominator * right.denominator);
	}

	/**
	 * 値が(this-val)である有理数を返します。
	 * @param val この有理数から減算する値
	 * @return this - val
	 */
	@Override
	public Rational subtract(Rational val) {
		return minus(this, val);
	}

	/**
	 * 値が(left-right)である有理数を返します。
	 * @param left 有理数
	 * @param right 有理数
	 * @return left - right
	 */
	@Override
	public Rational minus(Rational left, Rational right) {
		return new Rational(left.numerator * right.denominator - left.denominator * right.numerator,
				left.denominator * right.denominator);
	}

	/**
	 * 値が(this*val)である有理数を返します。
	 * @param val この有理数で乗算する値
	 * @return this * val
	 */
	@Override
	public Rational multiply(Rational val) {
		return times(this, val);
	}

	/**
	 * 値が(left*right)である有理数を返します。
	 * @param left 有理数
	 * @param right 有理数
	 * @return left * right
	 */
	@Override
	public Rational times(Rational left, Rational right) {
		return new Rational(left.numerator * right.numerator, left.denominator * right.denominator);
	}

	/**
	 * 値が(this/val)である有理数を返します。
	 * @param val この有理数を除算する値
	 * @return this / val
	 */
	@Override
	public Rational divide(Rational val) {
		return divide(this, val);
	}

	/**
	 * 値が(left/right)である有理数を返します。
	 * @param left 有理数
	 * @param right 有理数
	 * @return left / right
	 */
	@Override
	public Rational divide(Rational left, Rational right) {
		return new Rational(left.numerator * right.denominator, left.denominator * right.numerator);
	}

	/**
	 * この有理数の逆数を返します。
	 * @return 1 / this
	 */
	@Override
	public Rational inverse() {
		return new Rational(denominator, numerator);
	}

	/**
	 * 与えられた有理数の逆数を返します。
	 * @param val 有理数
	 * @return 1 / val
	 */
	@Override
	public Rational inverse(Rational val) {
		return new Rational(val.denominator, val.numerator);
	}

	@Override
	public int hashCode() {
		return Long.hashCode(numerator ^ denominator);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Rational) {
			return ((Rational) o).numerator == numerator && ((Rational) o).denominator == denominator;
		}
		return false;
	}

	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	private static class Addition implements Abelian<Rational> {

		@Override
		public Rational apply(Rational t, Rational u) {
			return t.add(u);
		}

		@Override
		public Rational identity() {
			return ZERO;
		}

		@Override
		public Rational inverse(Rational element) {
			return new Rational(-element.numerator, element.denominator);
		}
	}

	private static class Multiplication implements Abelian<Rational> {
		@Override
		public Rational apply(Rational t, Rational u) {
			return t.multiply(u);
		}

		@Override
		public Rational identity() {
			return ONE;
		}

		@Override
		public Rational inverse(Rational element) {
			return element.inverse();
		}
	}

	@Override
	protected LongEuclideanDomain<LongAbelian, LongCommutativeMonoid> getOperator() {
		return MathUtility.getLongOperator();
	}

	public static Rational create(long numerator, long denominator) {
		return new Rational(numerator, denominator);
	}

	private Addition addition;
	private Multiplication multiplication;
	@Override
	public Abelian<Rational> getAddition() { return addition == null ? addition = new Addition() : addition; }

	@Override
	public Abelian<Rational> getMultiplication() { return multiplication == null ? multiplication = new Multiplication() : multiplication; }

	@Override
	public int compareTo(Rational o) {
		final long TOP = 1L << 32;
		long a = numerator / TOP, b = numerator % TOP;
		long c = o.denominator / TOP, d = o.denominator % TOP;
		long l1 = a * c, l2 = b * d;
		long tmp = a * d;
		l1 += tmp / TOP;
		l2 += tmp % TOP;
		tmp = b * c;
		l1 += tmp / TOP;
		l2 += tmp % TOP;
		a = denominator / TOP;
		b = denominator % TOP;
		c = o.numerator / TOP;
		d = o.numerator % TOP;
		long r1 = a * c, r2 = b * d;
		tmp = a * d;
		r1 += tmp / TOP;
		r2 += tmp % TOP;
		tmp = b * c;
		r1 += tmp / TOP;
		r2 += tmp % TOP;
		int comp = Long.compare(l1, r1);
		if (comp == 0) comp = Long.compare(l2, r2);
		return comp;
	}

	@Override
	public int compare(Rational o1, Rational o2) {
		return o1.compareTo(o2);
	}
}
