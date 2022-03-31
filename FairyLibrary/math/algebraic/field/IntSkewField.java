package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.IntDomain;
import com._31536000.math.algebraic.domain.IntPrimeElement;
import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntGroup;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が斜体であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link SkewField}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntSkewField<A extends IntAbelian, M extends IntGroup> extends SkewField<Integer, A, M>, IntDomain<A, M>{

	@Override
	public default Integer inverse(Integer element) {
		return inverseAsInt(element);
	}
	/**
	 * 乗法逆元を返します。
	 * @param element 乗法逆元を求める値
	 * @return 乗法逆元
	 */
	public default int inverseAsInt(int element) {
		return getMultiplication().inverseAsInt(element);
	}
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
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public default Integer reminderAsInt(Integer left, Integer right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsInt();
	}
	@Override
	public default Integer gcd(Integer left, Integer right) {
		return gcdAsInt(left, right);
	}
	/**
	 * gcdを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最大公約数
	 */
	public default Integer gcdAsInt(Integer left, Integer right) {
		return multiplicativeIdentityAsInt();
	}
	@Override
	public default Integer lcm(Integer left, Integer right) {
		return lcmAsInt(left, right);
	}
	/**
	 * lcmを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最小公倍数
	 */
	public default Integer lcmAsInt(Integer left, Integer right) {
		return multiplicativeIdentityAsInt();
	}
	@Override
	public default MultiSet<? extends IntPrimeElement> getPrimeFactorization(Integer x) {
		return getPrimeFactorizationAsInt(x);
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public default MultiSet<? extends IntPrimeElement> getPrimeFactorizationAsInt(Integer x) {
		HashMultiSet<IntPrimeElement> ret = HashMultiSet.create(1);
		ret.add(IntPrimeElement.create(x));
		return ret;
	}
}
