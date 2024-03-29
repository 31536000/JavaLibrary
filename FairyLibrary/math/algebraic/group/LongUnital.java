package com._31536000.math.algebraic.group;

/**
 * 演算が単位元を持つことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Unital}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongUnital extends Unital<Long>, LongMagma {
	@Override
	public default Long identity() {
		return identityAsLong();
	}

	/**
	 * 単位元を返します。
	 * @return 単位元
	 */
	public long identityAsLong();
}
