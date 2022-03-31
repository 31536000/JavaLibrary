package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.DoubleDomain;
import com._31536000.math.algebraic.domain.DoublePrimeElement;
import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.math.algebraic.group.DoubleGroup;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が斜体であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link SkewField}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleSkewField<A extends DoubleAbelian, M extends DoubleGroup> extends SkewField<Double, A, M>, DoubleDomain<A, M>{

	@Override
	public default Double inverse(Double element) {
		return inverseAsDouble(element);
	}
	/**
	 * 乗法逆元を返します。
	 * @param element 乗法逆元を求める値
	 * @return 乗法逆元
	 */
	public default double inverseAsDouble(double element) {
		return getMultiplication().inverseAsDouble(element);
	}
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
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public default double remainderAsDouble(double left, double right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentityAsDouble();
	}
	@Override
	public default Double gcd(Double left, Double right) {
		return gcdAsDouble(left, right);
	}
	/**
	 * gcdを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最大公約数
	 */
	public default double gcdAsDouble(double left, double right) {
		return multiplicativeIdentityAsDouble();
	}
	@Override
	public default Double lcm(Double left, Double right) {
		return lcmAsDouble(left, right);
	}
	/**
	 * lcmを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最小公倍数
	 */
	public default double lcmAsDouble(double left, double right) {
		return multiplicativeIdentityAsDouble();
	}
	@Override
	public default MultiSet<? extends DoublePrimeElement> getPrimeFactorization(Double x) {
		return getPrimeFactorizationAsDouble(x);
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public default MultiSet<? extends DoublePrimeElement> getPrimeFactorizationAsDouble(double x) {
		HashMultiSet<DoublePrimeElement> ret = HashMultiSet.create(1);
		ret.add(DoublePrimeElement.create(x));
		return ret;
	}
}
