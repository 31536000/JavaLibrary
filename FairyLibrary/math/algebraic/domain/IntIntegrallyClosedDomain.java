package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntCommutativeMonoid;

/**
 * 演算が整閉整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IntegrallyClosedDomain}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntIntegrallyClosedDomain<A extends IntAbelian, M extends IntCommutativeMonoid> extends IntegrallyClosedDomain<Integer, A, M>, IntIntegralDomain<A, M>{

}
