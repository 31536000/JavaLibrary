package math.algebraic.domain;

import math.algebraic.group.LongAbelian;
import math.algebraic.group.LongCommutativeMonoid;

/**
 * 演算がユークリッド整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EuclideanDomain}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongEuclideanDomain<A extends LongAbelian, M extends LongCommutativeMonoid> extends EuclideanDomain<Long, A, M>, LongPrincipalIdealDomain<A, M>{
	@Override
	public default Long reminder(Long left, Long right) {
		return reminderAsLong(left, right);
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public long reminderAsLong(long left, long right);
}
