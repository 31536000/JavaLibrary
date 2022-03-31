package com._31536000.math.algebraic.group;

/**
* この演算が逆元を持つことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Invertible}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongInvertible extends Invertible<Long>, LongMagma {
	@Override
	public default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}

	@Override
	public default Long inverse(Long element) {
		return inverseAsLong(element);
	}

	/**
	 * 逆元を返します。
	 * @param element 逆元を求める値
	 * @return 逆元
	 */
	public long inverseAsLong(long element);
}
