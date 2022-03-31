package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongAssociative;

/**
 * 演算が擬環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PseudoRing}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongPseudoRing<A extends LongAbelian, M extends LongAssociative> extends PseudoRing<Long, A, M>, LongPreSemiring<A, M>, LongNearRing<A, M>{

}
