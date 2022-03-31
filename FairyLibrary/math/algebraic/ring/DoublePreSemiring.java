package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleAssociative;
import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;

/**
 * 演算が前半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PreSemiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoublePreSemiring<A extends DoubleCommutativeMonoid, M extends DoubleAssociative> extends DoubleNearSemiring<A, M>, PreSemiring<Double, A, M> {

}
