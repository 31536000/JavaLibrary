package math.algebraic.ring;

import math.algebraic.group.IntCommutativeMonoid;

/**
 * 演算が可換半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeSemiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntCommutativeSemiring<A extends IntCommutativeMonoid, M extends IntCommutativeMonoid> extends CommutativeSemiring<Integer, A, M>, IntSemiring<A, M>{

}
