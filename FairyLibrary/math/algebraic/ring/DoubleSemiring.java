package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;
import com._31536000.math.algebraic.group.DoubleMonoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleSemiring<A extends DoubleCommutativeMonoid, M extends DoubleMonoid> extends Semiring<Double, A, M>, DoublePreSemiring<A, M>{

	@Override
	public default Double multiplicativeIdentity() {
		return multiplicativeIdentityAsDouble();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default double multiplicativeIdentityAsDouble() {
		return getMultiplication().identityAsDouble();
	}
}
