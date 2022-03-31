package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongCommutativeMonoid;
import com._31536000.math.algebraic.group.LongMonoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongSemiring<A extends LongCommutativeMonoid, M extends LongMonoid> extends Semiring<Long, A, M>, LongPreSemiring<A, M>{

	@Override
	public default Long multiplicativeIdentity() {
		return multiplicativeIdentityAsLong();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default long multiplicativeIdentityAsLong() {
		return getMultiplication().identityAsLong();
	}
}
