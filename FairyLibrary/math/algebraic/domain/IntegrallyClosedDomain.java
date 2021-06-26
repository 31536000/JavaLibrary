package math.algebraic.domain;

import math.algebraic.group.Abelian;
import math.algebraic.group.CommutativeMonoid;

/**
 * 演算が整閉整域であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntegrallyClosedDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>> extends IntegralDomain<T, A, M>{

}
