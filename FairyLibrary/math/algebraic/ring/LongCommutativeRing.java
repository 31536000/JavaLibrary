package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongCommutativeMonoid;

/**
 * 演算が可換環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeRing}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongCommutativeRing<A extends LongAbelian, M extends LongCommutativeMonoid> extends CommutativeRing<Long, A, M>, LongRing<A, M>, LongCommutativeSemiring<A, M>{

}
