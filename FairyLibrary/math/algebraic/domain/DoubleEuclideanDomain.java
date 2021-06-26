package math.algebraic.domain;

import math.algebraic.group.DoubleAbelian;
import math.algebraic.group.DoubleCommutativeMonoid;

/**
 * 演算がユークリッド整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EuclideanDomain}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleEuclideanDomain<A extends DoubleAbelian, M extends DoubleCommutativeMonoid> extends EuclideanDomain<Double, A, M>, DoublePrincipalIdealDomain<A, M>{
	@Override
	public default Double reminder(Double left, Double right) {
		return reminderAsDouble(left, right);
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public double reminderAsDouble(double left, double right);
}
