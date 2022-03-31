package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;

/**
 * 演算が可換環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeRing}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleCommutativeRing<A extends DoubleAbelian, M extends DoubleCommutativeMonoid> extends CommutativeRing<Double, A, M>, DoubleRing<A, M>, DoubleCommutativeSemiring<A, M>{

}
