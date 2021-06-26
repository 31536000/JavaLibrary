package math.algebraic.domain;

import math.algebraic.group.IntAbelian;
import math.algebraic.group.IntCommutativeMonoid;

/**
 * 演算がユークリッド整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EuclideanDomain}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntEuclideanDomain<A extends IntAbelian, M extends IntCommutativeMonoid> extends EuclideanDomain<Integer, A, M>, IntPrincipalIdealDomain<A, M>{
	@Override
	public default Integer reminder(Integer left, Integer right) {
		return reminderAsInt(left, right);
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public int reminderAsInt(int left, int right);
}
