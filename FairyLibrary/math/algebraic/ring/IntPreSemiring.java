package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntAssociative;
import com._31536000.math.algebraic.group.IntCommutativeMonoid;

/**
 * 演算が前半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PreSemiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntPreSemiring<A extends IntCommutativeMonoid, M extends IntAssociative> extends IntNearSemiring<A, M>, PreSemiring<Integer, A, M> {

}
