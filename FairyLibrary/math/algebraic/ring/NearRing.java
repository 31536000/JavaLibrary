package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.Associative;
import com._31536000.math.algebraic.group.Group;

/**
 * 演算が近環であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface NearRing<T, A extends Group<T>, M extends Associative<T>> extends NearSemiring<T, A, M> {
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default T minus(T left, T right) {
		return getAddition().subtract(left, right);
	}
	/**
	 * 加法逆元を返します。
	 * @param element 加法逆元を求める値
	 * @return 加法逆元
	 */
	public default T opposite(T element) {
		return getAddition().inverse(element);
	}
}
