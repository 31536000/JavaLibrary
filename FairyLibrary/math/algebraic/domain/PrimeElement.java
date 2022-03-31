package com._31536000.math.algebraic.domain;

/**
 * 素元を提供します。
 * @author 31536000
 *
 * @param <T> 演算の型
 */
public interface PrimeElement<T> {
	/**
	 * 自身が保持する素元を返します。
	 * @return 素元
	 */
	public T getPrimeElement();
	/**
	 * elementを保持したPrimeElementを返します。
	 * @param element 素元
	 * @return elementを保持するPrimeElement
	 */
	public static <T> PrimeElement<T> create(final T element) {
		return () -> element;
	}
}
