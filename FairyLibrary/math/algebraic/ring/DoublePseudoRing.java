package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.math.algebraic.group.DoubleAssociative;

/**
 * 演算が擬環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PseudoRing}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoublePseudoRing<A extends DoubleAbelian, M extends DoubleAssociative> extends PseudoRing<Double, A, M>, DoublePreSemiring<A, M>, DoubleNearRing<A, M>{

}
