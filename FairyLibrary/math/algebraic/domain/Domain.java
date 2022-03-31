package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.Monoid;
import com._31536000.math.algebraic.ring.Ring;

/**
 * 演算が域であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Domain<T, A extends Abelian<T>, M extends Monoid<T>> extends Ring<T, A, M>{
	/**
	 * leftをrightで割ることが可能か判定します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / rightが定義されているならtrue
	 */
	public default boolean isDivisible(T left, T right) {
		try {
			divide(left, right);
		} catch (ArithmeticException e) {
			return false;
		}
		return true;
	}
	/**
	 * leftをrightで割った値を返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / right
	 * @exception ArithmeticException left/rightが定義されていない場合
	 */
	public T divide(T left, T right);
}
