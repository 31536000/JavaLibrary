package math.algebraic.domain;

import math.algebraic.group.Abelian;
import math.algebraic.group.CommutativeMonoid;

/**
 * 演算がユークリッド整域であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface EuclideanDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>> extends PrincipalIdealDomain<T, A, M>{
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public T reminder(T left, T right);
}
