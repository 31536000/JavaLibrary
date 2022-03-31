package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.DoubleEuclideanDomain;
import com._31536000.math.algebraic.domain.DoublePrimeElement;
import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

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
		return isDivisible((double)left, (double)right);
	}
	@Override
	public default boolean isDivisible(double left, double right) {
		return right != additiveIdentityAsDouble();
	}
	@Override
	public default Double divide(Double left, Double right) {
		return divideAsDouble(left, right);
	}
	@Override
	public default double divideAsDouble(double left, double right) {
		try {
			return timesAsDouble(left, getMultiplication().inverseAsDouble(right));
		} catch (ArithmeticException e) {
			throw new ArithmeticException("divide by Additive Identify");
		}
	}
	@Override
	public default Double remainder(Double left, Double right) {
		return remainderAsDouble(left, right);
	}
	@Override
	public default double remainderAsDouble(double left, double right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsDouble();
	}
	@Override
	public default Double gcd(Double left, Double right) {
		return gcdAsDouble(left, right);
	}
	@Override
	public default double gcdAsDouble(double left, double right) {
		return multiplicativeIdentityAsDouble();
	}
	@Override
	public default Double lcm(Double left, Double right) {
		return lcmAsDouble(left, right);
	}
	@Override
	public default double lcmAsDouble(double left, double right) {
		return multiplicativeIdentityAsDouble();
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
