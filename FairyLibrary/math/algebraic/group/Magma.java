package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.morphism.EndoMorphism;

/**
 * 演算がマグマであることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Magma<T> extends java.util.function.BinaryOperator<T>{
	/**
	 * 演算の第一引数に部分適用を行った関数を返します。
	 * @param bind 第一引数
	 * @return bindを第一引数として部分適用を行った関数
	 */
	public default EndoMorphism<T> partial(T bind) {
		return t -> apply(bind, t);
	}
}
