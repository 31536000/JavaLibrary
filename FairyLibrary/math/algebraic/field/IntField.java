package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.IntEuclideanDomain;
import com._31536000.math.algebraic.domain.IntPrimeElement;
import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が体であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Field}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntField<A extends IntAbelian, M extends IntAbelian> extends Field<Integer, A, M>, IntSkewField<A, M>, IntEuclideanDomain<A, M>{
	@Override
	public default boolean isDivisible(Integer left, Integer right) {
		return isDivisible((int)left, (int)right);
	}
	@Override
	public default boolean isDivisible(int left, int right) {
		return right != additiveIdentityAsInt();
	}
	@Override
	public default Integer divide(Integer left, Integer right) {
		return divideAsInt(left, right);
	}
	@Override
	public default int divideAsInt(int left, int right) {
		try {
			return timesAsInt(left, getMultiplication().inverseAsInt(right));
		} catch (ArithmeticException e) {
			throw new ArithmeticException("divide by Additive Identify");
		}
	}
	@Override
	public default Integer remainder(Integer left, Integer right) {
		return reminderAsInt(left, right);
	}
	@Override
	public default int remainderAsInt(int left, int right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsInt();
	}
	@Override
	public default Integer gcd(Integer left, Integer right) {
		return gcdAsInt(left, right);
	}
	@Override
	public default int gcdAsInt(int left, int right) {
		return multiplicativeIdentityAsInt();
	}
	@Override
	public default Integer lcm(Integer left, Integer right) {
		return lcmAsInt(left, right);
	}
	@Override
	public default int lcmAsInt(int left, int right) {
		return multiplicativeIdentityAsInt();
	}
	@Override
	public default MultiSet<? extends IntPrimeElement> getPrimeFactorization(Integer x) {
		return getPrimeFactorizationAsInt(x);
	}
	@Override
	public default MultiSet<? extends IntPrimeElement> getPrimeFactorizationAsInt(int x) {
		HashMultiSet<IntPrimeElement> ret = HashMultiSet.create(1);
		ret.add(IntPrimeElement.create(x));
		return ret;
	}
}
