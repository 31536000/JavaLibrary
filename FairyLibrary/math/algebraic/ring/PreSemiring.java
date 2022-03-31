package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.Associative;
import com._31536000.math.algebraic.group.CommutativeMonoid;

/**
 * 演算が前半環であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface PreSemiring<T, A extends CommutativeMonoid<T>, M extends Associative<T>> extends NearSemiring<T, A, M> {

}
