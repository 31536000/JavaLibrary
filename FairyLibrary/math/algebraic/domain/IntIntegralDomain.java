package math.algebraic.domain;

import math.algebraic.group.IntAbelian;
import math.algebraic.group.IntCommutativeMonoid;
import math.algebraic.ring.IntCommutativeRing;

/**
 * 演算が整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IntegralDomain}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntIntegralDomain<A extends IntAbelian, M extends IntCommutativeMonoid> extends IntegralDomain<Integer, A, M>, IntDomain<A, M>, IntCommutativeRing<A, M>{

}
