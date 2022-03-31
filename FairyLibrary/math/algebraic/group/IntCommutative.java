package com._31536000.math.algebraic.group;

/**
 * 演算が交換法則を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Commutative}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntCommutative extends Commutative<Integer>, IntMagma {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
}
