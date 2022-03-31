package com._31536000.math.algebraic.group;

/**
 * 演算がアーベル群(可換群)であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Abelian}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntAbelian extends Abelian<Integer>, IntGroup, IntCommutativeMonoid {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
}
