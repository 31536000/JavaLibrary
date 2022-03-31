package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.morphism.EndoMorphism;
import com._31536000.math.algebraic.morphism.LongEndoMorphism;

/**
 * 演算がマグマであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Magma}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongMagma extends Magma<Long>, java.util.function.LongBinaryOperator {
	@Override
	public default EndoMorphism<Long> partial(Long bind) {
		return partialAsLong(bind);
	}

	/**
	 * 演算の第一引数に部分適用を行った関数を返します。
	 * @param bind 第一引数
	 * @return bindを第一引数として部分適用を行った関数
	 */
	public default LongEndoMorphism partialAsLong(long bind) {
		return t -> applyAsLong(bind, t);
	}
}
