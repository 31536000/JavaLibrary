package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.LongDomain;
import com._31536000.math.algebraic.domain.LongPrimeElement;
import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongGroup;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が斜体であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link SkewField}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongSkewField<A extends LongAbelian, M extends LongGroup> extends SkewField<Long, A, M>, LongDomain<A, M>{

	@Override
	public default Long inverse(Long element) {
		return inverseAsLong(element);
	}
	/**
	 * 乗法逆元を返します。
	 * @param element 乗法逆元を求める値
	 * @return 乗法逆元
	 */
	public default long inverseAsLong(long element) {
		return getMultiplication().inverseAsLong(element);
	}
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
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public default long remainderAsLong(long left, long right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsLong();
	}
	@Override
	public default Long gcd(Long left, Long right) {
		return gcdAsLong(left, right);
	}
	/**
	 * gcdを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最大公約数
	 */
	public default long gcdAsLong(long left, long right) {
		return multiplicativeIdentityAsLong();
	}
	@Override
	public default Long lcm(Long left, Long right) {
		return lcmAsLong(left, right);
	}
	/**
	 * lcmを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最小公倍数
	 */
	public default long lcmAsLong(long left, long right) {
		return multiplicativeIdentityAsLong();
	}
	@Override
	public default MultiSet<? extends LongPrimeElement> getPrimeFactorization(Long x) {
		return getPrimeFactorizationAsLong(x);
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public default MultiSet<? extends LongPrimeElement> getPrimeFactorizationAsLong(long x) {
		HashMultiSet<LongPrimeElement> ret = HashMultiSet.create(1);
		ret.add(LongPrimeElement.create(x));
		return ret;
	}
}
