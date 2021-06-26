package math.algebraic.field;

import math.algebraic.domain.EuclideanDomain;
import math.algebraic.group.Abelian;

/**
 * 演算が体であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Field<T, A extends Abelian<T>, M extends Abelian<T>> extends SkewField<T, A, M>, EuclideanDomain<T, A, M>{

}
