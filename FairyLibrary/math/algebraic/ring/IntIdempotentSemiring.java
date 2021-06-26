package math.algebraic.ring;

import math.algebraic.group.IntMonoid;
import math.algebraic.group.IntSemilattice;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link IdempotentSemiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntIdempotentSemiring<A extends IntSemilattice, M extends IntMonoid> extends IdempotentSemiring<Integer, A, M>, IntSemiring<A, M>{

}
