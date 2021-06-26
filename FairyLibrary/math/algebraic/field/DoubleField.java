package math.algebraic.field;

import math.algebraic.domain.DoubleEuclideanDomain;
import math.algebraic.domain.DoublePrimeElement;
import math.algebraic.group.DoubleAbelian;
import util.collect.HashMultiSet;
import util.collect.MultiSet;

/**
 * 演算が体であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Field}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleField<A extends DoubleAbelian, M extends DoubleAbelian> extends Field<Double, A, M>, DoubleSkewField<A, M>, DoubleEuclideanDomain<A, M>{
	@Override
	public default boolean isDivisible(Double left, Double right) {
		return isDivisibleAsDouble(left, right);
	}
	@Override
	public default boolean isDivisibleAsDouble(double left, double right) {
		return right != additiveIdentityAsDouble();
	}
	@Override
	public default Double divide(Double left, Double right) {
		return divideAsDouble(left, right);
	}
	@Override
	public default double divideAsDouble(double left, double right) {
		try {
			return multiplyAsDouble(left, getMultiplication().inverseAsDouble(right));
		} catch (ArithmeticException e) {
			throw new ArithmeticException("divide by Additive Identify");
		}
	}
	@Override
	public default Double reminder(Double left, Double right) {
		return reminderAsDouble(left, right);
	}
	@Override
	public default double reminderAsDouble(double left, double right) {
		if (isDivisibleAsDouble(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsDouble();
	}
	@Override
	public default Double gcd(Double left, Double right) {
		return gcdAsDouble(left, right);
	}
	@Override
	public default double gcdAsDouble(double left, double right) {
		return multipleIdentityAsDouble();
	}
	@Override
	public default Double lcm(Double left, Double right) {
		return lcmAsDouble(left, right);
	}
	@Override
	public default double lcmAsDouble(double left, double right) {
		return multipleIdentityAsDouble();
	}
	@Override
	public default MultiSet<? extends DoublePrimeElement> getPrimeFactorization(Double x) {
		return getPrimeFactorizationAsDouble(x);
	}
	@Override
	public default MultiSet<? extends DoublePrimeElement> getPrimeFactorizationAsDouble(double x) {
		HashMultiSet<DoublePrimeElement> ret = HashMultiSet.create(1);
		ret.add(DoublePrimeElement.create(x));
		return ret;
	}
}
