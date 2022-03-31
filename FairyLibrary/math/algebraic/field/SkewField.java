package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.Domain;
import com._31536000.math.algebraic.domain.PrimeElement;
import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.Group;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が斜体であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface SkewField<T, A extends Abelian<T>, M extends Group<T>> extends Domain<T, A, M>{
	@Override
	public default boolean isDivisible(T left, T right) {
		return !right.equals(additiveIdentity());
	}
	/**
	 * 乗法逆元を返します。
	 * @param element 乗法逆元を求める値
	 * @return 乗法逆元
	 */
	public default T inverse(T element) {
		return getMultiplication().inverse(element);
	}
	@Override
	public default T divide(T left, T right) {
		try {
			return times(left, inverse(right));
		} catch (ArithmeticException e) {
			throw new ArithmeticException("divide by Additive Identify");
		}
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public default T remainder(T left, T right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentity();
	}
	/**
	 * gcdを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最大公約数
	 */
	public default T gcd(T left, T right) {
		return multiplicativeIdentity();
	}
	/**
	 * lcmを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最小公倍数
	 */
	public default T lcm(T left, T right) {
		return multiplicativeIdentity();
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public default MultiSet<? extends PrimeElement<T>> getPrimeFactorization(T x) {
		HashMultiSet<PrimeElement<T>> ret = HashMultiSet.create(1);
		ret.add(PrimeElement.create(x));
		return ret;
	}
}
