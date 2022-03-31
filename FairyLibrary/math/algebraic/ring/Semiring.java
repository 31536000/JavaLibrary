package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.group.Monoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Semiring<T, A extends CommutativeMonoid<T>, M extends Monoid<T>> extends PreSemiring<T, A, M> {
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default T multiplicativeIdentity() {
		return getMultiplication().identity();
	}
	/**
	 * 標数を取得します。
	 * @return 標数
	 */
	public default int characteristic() {
		return 0;
	}
}
