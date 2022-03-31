package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongCommutativeMonoid;

/**
 * 演算が整閉整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IntegrallyClosedDomain}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongIntegrallyClosedDomain<A extends LongAbelian, M extends LongCommutativeMonoid> extends IntegrallyClosedDomain<Long, A, M>, LongIntegralDomain<A, M>{

}
