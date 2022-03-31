package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntAssociative;

/**
 * 演算が擬環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PseudoRing}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntPseudoRing<A extends IntAbelian, M extends IntAssociative> extends PseudoRing<Integer, A, M>, IntPreSemiring<A, M>, IntNearRing<A, M>{

}
