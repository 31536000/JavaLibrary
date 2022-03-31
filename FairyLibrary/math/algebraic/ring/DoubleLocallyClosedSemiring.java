package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;
import com._31536000.math.algebraic.group.DoubleMonoid;

/**
 * 演算が局所閉半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link LocallyClosedSemiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleLocallyClosedSemiring<A extends DoubleCommutativeMonoid, M extends DoubleMonoid> extends LocallyClosedSemiring<Double, A, M>, DoubleClosedSemiring<A, M>{

}
