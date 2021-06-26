package math.algebraic.domain;

import math.algebraic.group.LongAbelian;
import math.algebraic.group.LongCommutativeMonoid;
import math.algebraic.ring.LongCommutativeRing;

/**
 * 演算が整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IntegralDomain}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongIntegralDomain<A extends LongAbelian, M extends LongCommutativeMonoid> extends IntegralDomain<Long, A, M>, LongDomain<A, M>, LongCommutativeRing<A, M>{

}
