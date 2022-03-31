package com._31536000.math.algebraic.domain;

/**
 * 素元を提供します。
 * これは、{@link PrimeElement}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntPrimeElement extends PrimeElement<Integer> {
	@Override
	public default Integer getPrimeElement() {
		return getPrimeElementAsInt();
	}
	/**
	 * 自身が保持する素元を返します。
	 * @return 素元
	 */
	public int getPrimeElementAsInt();
	/**
	 * elementを保持したIntPrimeElementを返します。
	 * @param element 素元
	 * @return elementを保持するIntPrimeElement
	 */
	public static IntPrimeElement create(final int element) {
		return () -> element;
	}
}
