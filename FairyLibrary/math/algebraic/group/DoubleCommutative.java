package com._31536000.math.algebraic.group;

/**
 * 演算が交換法則を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Commutative}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleCommutative extends Commutative<Double>, DoubleMagma {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
}
