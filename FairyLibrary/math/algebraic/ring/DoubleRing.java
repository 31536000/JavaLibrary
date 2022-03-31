package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.math.algebraic.group.DoubleMonoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Ring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleRing<A extends DoubleAbelian, M extends DoubleMonoid> extends Ring<Double, A, M>, DoubleSemiring<A, M>, DoublePseudoRing<A, M>{

}
