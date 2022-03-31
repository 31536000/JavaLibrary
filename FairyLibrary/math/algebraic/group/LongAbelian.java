package com._31536000.math.algebraic.group;

/**
 * 演算がアーベル群(可換群)であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Abelian}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongAbelian extends Abelian<Long>, LongGroup, LongCommutativeMonoid {
	@Override
	default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}

}
