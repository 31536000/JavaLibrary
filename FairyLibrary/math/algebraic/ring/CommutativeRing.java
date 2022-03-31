package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;

/**
 * 演算が可換環に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface CommutativeRing<T, A extends Abelian<T>, M extends CommutativeMonoid<T>> extends Ring<T, A, M>, CommutativeSemiring<T, A, M>{

}
