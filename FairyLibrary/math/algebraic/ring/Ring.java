package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.Monoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Ring<T, A extends Abelian<T>, M extends Monoid<T>> extends Semiring<T, A, M>, PseudoRing<T, A, M>{

}
