package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntCommutativeMonoid;
import com._31536000.math.algebraic.group.IntMonoid;

/**
 * 演算が局所閉半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link LocallyClosedSemiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntLocallyClosedSemiring<A extends IntCommutativeMonoid, M extends IntMonoid> extends LocallyClosedSemiring<Integer, A, M>, IntClosedSemiring<A, M>{

}
