package com._31536000.math.algebraic.group;


/**
 * 演算がモノイドであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Monoid}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntMonoid extends Monoid<Integer>, IntAssociative, IntUnital {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
	@Override
	public default Integer hyper(Integer element, int repeat) {
		return hyperAsInt(element, repeat);
	}
	@Override
	public default Integer hyper(Integer element, long repeat) {
		return hyperAsInt(element, repeat);
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default int hyperAsInt(int element, int repeat) {
		if (repeat < 0) throw new IllegalArgumentException("undefined operation");
		int ret = identityAsInt();
		for (int mul = element;repeat > 0;repeat >>= 1, mul = applyAsInt(mul, mul)) if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
		return ret;
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default int hyperAsInt(int element, long repeat) {
		if (repeat < 0) throw new IllegalArgumentException("undefined operation");
		int ret = identityAsInt();
		for (int mul = element;repeat > 0;repeat >>= 1, mul = applyAsInt(mul, mul)) if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
		return ret;
	}
}
