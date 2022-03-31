package com._31536000.math.algebraic.domain;

/**
 * 素元を提供します。
 * これは、{@link PrimeElement}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongPrimeElement extends PrimeElement<Long> {
	@Override
	public default Long getPrimeElement() {
		return getPrimeElementAsLong();
	}
	/**
	 * 自身が保持する素元を返します。
	 * @return 素元
	 */
	public long getPrimeElementAsLong();
	/**
	 * elementを保持したLongPrimeElementを返します。
	 * @param element 素元
	 * @return elementを保持するLongPrimeElement
	 */
	public static LongPrimeElement create(final long element) {
		return () -> element;
	}
}
