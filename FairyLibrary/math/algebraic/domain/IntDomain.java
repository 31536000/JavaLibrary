package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntMonoid;
import com._31536000.math.algebraic.ring.IntRing;

/**
 * 演算が域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Domain}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntDomain<A extends IntAbelian, M extends IntMonoid> extends Domain<Integer, A, M>, IntRing<A, M>{
	@Override
	public default boolean isDivisible(Integer left, Integer right) {
		return isDivisible((int)left, (int)right);
	}
	/**
	 * leftをrightで割ることが可能か判定します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / rightが定義されているならtrue
	 */
	public default boolean isDivisible(int left, int right) {
		try {
			divideAsInt(left, right);
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
	@Override
	public default Integer divide(Integer left, Integer right) {
		return divideAsInt(left, right);
	}
	/**
	 * leftをrightで割った値を返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / right
	 * @exception ArithmeticException left/rightが定義されていない場合
	 */
	public int divideAsInt(int left, int right);
}
