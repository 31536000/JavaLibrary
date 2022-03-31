package com._31536000.math.algebraic.group;

/**
* この演算が逆元を持つことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Invertible}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntInvertible extends Invertible<Integer>, IntMagma {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}

	@Override
	public default Integer inverse(Integer element) {
		return inverseAsInt(element);
	}

	/**
	 * 逆元を返します。
	 * @param element 逆元を求める値
	 * @return 逆元
	 */
	public int inverseAsInt(int element);
}
