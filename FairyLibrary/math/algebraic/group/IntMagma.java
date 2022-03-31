package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.morphism.EndoMorphism;
import com._31536000.math.algebraic.morphism.IntEndoMorphism;

/**
 * 演算がマグマであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Magma}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 */
public interface IntMagma extends Magma<Integer>, java.util.function.IntBinaryOperator {
	@Override
	public default EndoMorphism<Integer> partial(Integer bind) {
		return partialAsInt(bind);
	}

	/**
	 * 演算の第一引数に部分適用を行った関数を返します。
	 * @param bind 第一引数
	 * @return bindを第一引数として部分適用を行った関数
	 */
	public default IntEndoMorphism partialAsInt(int bind) {
		return t -> applyAsInt(bind, t);
	}
}
