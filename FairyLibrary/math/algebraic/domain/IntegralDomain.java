package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.ring.CommutativeRing;

/**
 * 演算が整域であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntegralDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>> extends Domain<T, A, M>, CommutativeRing<T, A, M>{

}
