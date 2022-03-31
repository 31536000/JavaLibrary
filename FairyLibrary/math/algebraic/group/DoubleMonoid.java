package com._31536000.math.algebraic.group;


/**
 * 演算がモノイドであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Monoid}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleMonoid extends Monoid<Double>, DoubleAssociative, DoubleUnital {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
	@Override
	public default Double hyper(Double element, int repeat) {
		return hyperAsDouble(element, repeat);
	}
	@Override
	public default Double hyper(Double element, long repeat) {
		return hyperAsDouble(element, repeat);
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default double hyperAsDouble(double element, int repeat) {
		if (repeat < 0) throw new IllegalArgumentException("undefined operation");
		double ret = identityAsDouble();
		for (double mul = element;repeat > 0;repeat >>= 1, mul = applyAsDouble(mul, mul)) if ((repeat & 1) != 0) ret = applyAsDouble(ret, mul);
		return ret;
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default double hyperAsDouble(double element, long repeat) {
		if (repeat < 0) throw new IllegalArgumentException("undefined operation");
		double ret = identityAsDouble();
		for (double mul = element;repeat > 0;repeat >>= 1, mul = applyAsDouble(mul, mul)) if ((repeat & 1) != 0) ret = applyAsDouble(ret, mul);
		return ret;
	}
}
