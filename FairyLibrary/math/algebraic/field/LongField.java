package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.LongEuclideanDomain;
import com._31536000.math.algebraic.domain.LongPrimeElement;
import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が体であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Field}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongField<A extends LongAbelian, M extends LongAbelian> extends Field<Long, A, M>, LongSkewField<A, M>, LongEuclideanDomain<A, M>{
	@Override
	public default boolean isDivisible(Long left, Long right) {
		return isDivisible((long)left, (long)right);
	}
	@Override
	public default boolean isDivisible(long left, long right) {
		return right != additiveIdentityAsLong();
	}
	@Override
	public default Long divide(Long left, Long right) {
		return divideAsLong(left, right);
	}
	@Override
	public default long divideAsLong(long left, long right) {
		try {
			return timesAsLong(left, getMultiplication().inverseAsLong(right));
		} catch (ArithmeticException e) {
			throw new ArithmeticException("divide by Additive Identify");
		}
	}
	@Override
	public default Long remainder(Long left, Long right) {
		return remainderAsLong(left, right);
	}
	@Override
	public default long remainderAsLong(long left, long right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsLong();
	}
	@Override
	public default Long gcd(Long left, Long right) {
		return gcdAsLong(left, right);
	}
	@Override
	public default long gcdAsLong(long left, long right) {
		return multiplicativeIdentityAsLong();
	}
	@Override
	public default Long lcm(Long left, Long right) {
		return lcmAsLong(left, right);
	}
	@Override
	public default long lcmAsLong(long left, long right) {
		return multiplicativeIdentityAsLong();
	}
	@Override
	public default MultiSet<? extends LongPrimeElement> getPrimeFactorization(Long x) {
		return getPrimeFactorizationAsLong(x);
	}
	@Override
	public default MultiSet<? extends LongPrimeElement> getPrimeFactorizationAsLong(long x) {
		HashMultiSet<LongPrimeElement> ret = HashMultiSet.create(1);
		ret.add(LongPrimeElement.create(x));
		return ret;
	}
}
