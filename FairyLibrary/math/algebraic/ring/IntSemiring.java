package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntCommutativeMonoid;
import com._31536000.math.algebraic.group.IntMonoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntSemiring<A extends IntCommutativeMonoid, M extends IntMonoid> extends Semiring<Integer, A, M>, IntPreSemiring<A, M>{

	@Override
	public default Integer multiplicativeIdentity() {
		return multiplicativeIdentityAsInt();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default int multiplicativeIdentityAsInt() {
		return getMultiplication().identityAsInt();
	}
}
