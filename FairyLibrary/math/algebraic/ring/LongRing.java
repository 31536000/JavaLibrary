package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongMonoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Ring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongRing<A extends LongAbelian, M extends LongMonoid> extends Ring<Long, A, M>, LongSemiring<A, M>, LongPseudoRing<A, M>{

}
