package math.algebraic.domain;

import math.algebraic.group.IntAbelian;
import math.algebraic.group.IntCommutativeMonoid;

/**
 * 演算が主イデアル整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PrincipalIdealDomain}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntPrincipalIdealDomain<A extends IntAbelian, M extends IntCommutativeMonoid> extends PrincipalIdealDomain<Integer, A, M>, IntUniqueFactorizationDomain<A, M> {

}
