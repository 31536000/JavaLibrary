package com._31536000.math.algebraic.group;

/**
 * 演算が単位元を持つことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Unital}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleUnital extends Unital<Double>, DoubleMagma {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}

	@Override
	public default Double identity() {
		return identityAsDouble();
	}

	/**
	 * 単位元を返します。
	 * @return 単位元
	 */
	public double identityAsDouble();
}
