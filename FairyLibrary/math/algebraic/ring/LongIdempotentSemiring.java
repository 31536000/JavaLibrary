package math.algebraic.ring;

import math.algebraic.group.LongMonoid;
import math.algebraic.group.LongSemilattice;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link IdempotentSemiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongIdempotentSemiring<A extends LongSemilattice, M extends LongMonoid> extends IdempotentSemiring<Long, A, M>, LongSemiring<A, M>{

}
