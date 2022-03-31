package com._31536000.math.algebraic.group;

/**
* この演算が逆元を持つことを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Invertible<T> extends Magma<T> {
	/**
	 * 逆元を返します。
	 * @param element 逆元を求める値
	 * @return 逆元
	 */
	public T inverse(T element);
}
