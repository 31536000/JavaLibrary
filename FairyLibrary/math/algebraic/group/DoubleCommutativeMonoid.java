package com._31536000.math.algebraic.group;

/**
 * 演算が可換モノイドであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeMonoid}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleCommutativeMonoid extends CommutativeMonoid<Double>, DoubleMonoid, DoubleCommutative {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
}
