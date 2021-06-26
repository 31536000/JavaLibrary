package math.algebraic.domain;

import math.algebraic.group.DoubleAbelian;
import math.algebraic.group.DoubleCommutativeMonoid;

/**
 * 演算が整閉整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IntegrallyClosedDomain}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleIntegrallyClosedDomain<A extends DoubleAbelian, M extends DoubleCommutativeMonoid> extends IntegrallyClosedDomain<Double, A, M>, DoubleIntegralDomain<A, M>{

}
