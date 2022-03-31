package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongCommutativeMonoid;

/**
 * 演算が可換半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeSemiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongCommutativeSemiring<A extends LongCommutativeMonoid, M extends LongCommutativeMonoid> extends CommutativeSemiring<Long, A, M>, LongSemiring<A, M>{

}
