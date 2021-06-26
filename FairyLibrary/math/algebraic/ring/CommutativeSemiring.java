package math.algebraic.ring;

import math.algebraic.group.CommutativeMonoid;

/**
 * 演算が可換半環に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface CommutativeSemiring<T, A extends CommutativeMonoid<T>, M extends CommutativeMonoid<T>> extends Semiring<T, A, M>{

}
