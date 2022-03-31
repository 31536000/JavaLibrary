package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntMonoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Ring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntRing<A extends IntAbelian, M extends IntMonoid> extends Ring<Integer, A, M>, IntSemiring<A, M>, IntPseudoRing<A, M>{
}
