package com._31536000.math.algebraic.group;

/**
 * 演算がアーベル群(可換群)であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Abelian}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleAbelian extends Abelian<Double>, DoubleGroup, DoubleCommutativeMonoid {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
}
