package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.morphism.DoubleEndoMorphism;
import com._31536000.math.algebraic.morphism.EndoMorphism;

/**
 * 演算がマグマであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Magma}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleMagma extends Magma<Double>, java.util.function.DoubleBinaryOperator {
	@Override
	public default EndoMorphism<Double> partial(Double bind) {
		return partialAsDouble(bind);
	}

	/**
	 * 演算の第一引数に部分適用を行った関数を返します。
	 * @param bind 第一引数
	 * @return bindを第一引数として部分適用を行った関数
	 */
	public default DoubleEndoMorphism partialAsDouble(double bind) {
		return t -> applyAsDouble(bind, t);
	}
}
