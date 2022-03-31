package com._31536000.math.algebraic.domain;

/**
 * 素元を提供します。
 * これは、{@link PrimeElement}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoublePrimeElement extends PrimeElement<Double> {
	@Override
	public default Double getPrimeElement() {
		return getPrimeElementAsDouble();
	}
	/**
	 * 自身が保持する素元を返します。
	 * @return 素元
	 */
	public double getPrimeElementAsDouble();
	/**
	 * elementを保持したDoublePrimeElementを返します。
	 * @param element 素元
	 * @return elementを保持するDoublePrimeElement
	 */
	public static DoublePrimeElement create(final double element) {
		return () -> element;
	}
}
