package math.algebraic.ring;

import math.algebraic.group.DoubleMonoid;
import math.algebraic.group.DoubleSemilattice;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link IdempotentSemiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleIdempotentSemiring<A extends DoubleSemilattice, M extends DoubleMonoid> extends IdempotentSemiring<Double, A, M>, DoubleSemiring<A, M>{

}
