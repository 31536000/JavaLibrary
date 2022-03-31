package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;

/**
 * 演算がGCD整域であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface GCDDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>> extends IntegrallyClosedDomain<T, A, M>{
	/**
	 * gcdを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最大公約数
	 */
	public T gcd(T left, T right);
	/**
	 * lcmを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最小公倍数
	 */
	public T lcm(T left, T right);
}
