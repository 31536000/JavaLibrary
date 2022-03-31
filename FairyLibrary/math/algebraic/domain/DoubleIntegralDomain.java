package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;
import com._31536000.math.algebraic.ring.DoubleCommutativeRing;

/**
 * 演算が整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IntegralDomain}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleIntegralDomain<A extends DoubleAbelian, M extends DoubleCommutativeMonoid> extends IntegralDomain<Double, A, M>, DoubleDomain<A, M>, DoubleCommutativeRing<A, M>{

}
