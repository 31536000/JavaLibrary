package math.algebraic.ring;

import math.algebraic.group.Semilattice;
import math.algebraic.group.Monoid;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IdempotentSemiring<T, A extends Semilattice<T>, M extends Monoid<T>> extends Semiring<T, A, M>{

}
