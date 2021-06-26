package math.algebraic.field;

import math.algebraic.domain.LongEuclideanDomain;
import math.algebraic.domain.LongPrimeElement;
import math.algebraic.group.LongAbelian;
import util.collect.HashMultiSet;
import util.collect.MultiSet;

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
		return isDivisibleAsLong(left, right);
	}
	@Override
	public default boolean isDivisibleAsLong(long left, long right) {
		return right != additiveIdentityAsLong();
	}
	@Override
	public default Long divide(Long left, Long right) {
		return divideAsLong(left, right);
	}
	@Override
	public default long divideAsLong(long left, long right) {
		try {
			return multiplyAsLong(left, getMultiplication().inverseAsLong(right));
		} catch (ArithmeticException e) {
			throw new ArithmeticException("divide by Additive Identify");
		}
	}
	@Override
	public default Long reminder(Long left, Long right) {
		return reminderAsLong(left, right);
	}
	@Override
	public default long reminderAsLong(long left, long right) {
		if (isDivisibleAsLong(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsLong();
	}
	@Override
	public default Long gcd(Long left, Long right) {
		return gcdAsLong(left, right);
	}
	@Override
	public default long gcdAsLong(long left, long right) {
		return multipleIdentityAsLong();
	}
	@Override
	public default Long lcm(Long left, Long right) {
		return lcmAsLong(left, right);
	}
	@Override
	public default long lcmAsLong(long left, long right) {
		return multipleIdentityAsLong();
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
