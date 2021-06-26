package math.algebraic.ring;

import math.algebraic.group.DoubleCommutativeMonoid;

/**
 * 演算が可換半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeSemiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleCommutativeSemiring<A extends DoubleCommutativeMonoid, M extends DoubleCommutativeMonoid> extends CommutativeSemiring<Double, A, M>, DoubleSemiring<A, M>{

}
