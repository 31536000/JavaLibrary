package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.DoubleAbelian;
import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;
import com._31536000.util.collect.Array;

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
	public default Double remainder(Double left, Double right) {
		return remainderAsDouble(left, right);
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public double remainderAsDouble(double left, double right);

	@Override
	public default Array<Double> divideAndRemainder(Double left, Double right) {
		double remain = remainderAsDouble(left, right);
		double div = divideAsDouble(minusAsDouble(left, remain), right);
		Array<Double> ret = Array.create(2);
		ret.set(0, div);
		ret.set(1, remain);
		return ret;
	}
	/**
	 * right * x + y = leftとなるようなx及びyを値として持つ配列を返します。
	 * ここでyは{@link #remainderAsDouble(left, right)}に一致します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return x, yと続く二つの要素を持つ配列
	 */
	public default double[] divideAndRemainderAsDouble(double left, double right) {
		double remain = remainderAsDouble(left, right);
		double div = divideAsDouble(minusAsDouble(left, remain), right);
		return new double[] {div, remain};
	}
}
