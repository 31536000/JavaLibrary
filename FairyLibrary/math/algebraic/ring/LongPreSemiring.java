package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongAssociative;
import com._31536000.math.algebraic.group.LongCommutativeMonoid;

/**
 * 演算が前半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PreSemiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongPreSemiring<A extends LongCommutativeMonoid, M extends LongAssociative> extends LongNearSemiring<A, M>, PreSemiring<Long, A, M> {

}
