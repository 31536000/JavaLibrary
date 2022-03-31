package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;

/**
 * 演算が主イデアル整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PrincipalIdealDomain}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoublePrincipalIdealDomain<A extends DoubleAbelian, M extends DoubleCommutativeMonoid> extends PrincipalIdealDomain<Double, A, M>, DoubleUniqueFactorizationDomain<A, M> {

}
