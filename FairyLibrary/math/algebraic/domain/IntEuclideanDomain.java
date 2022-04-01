package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntCommutativeMonoid;
import com._31536000.util.collect.Array;

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
	public default Integer remainder(Integer left, Integer right) {
		return remainderAsInt(left, right);
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public int remainderAsInt(int left, int right);

	@Override
	public default Array<Integer> divideAndRemainder(Integer left, Integer right) {
		int remain = remainderAsInt(left, right);
		int div = divideAsInt(minusAsInt(left, remain), right);
		Array<Integer> ret = Array.create(2);
		ret.set(0, div);
		ret.set(1, remain);
		return ret;
	}
	/**
	 * right * x + y = leftとなるようなx及びyを値として持つ配列を返します。
	 * ここでyは{@link #remainderAsInt(int, int) #remainderAsInt(left, right)}に一致します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return x, yと続く二つの要素を持つ配列
	 */
	public default int[] divideAndRemainderAsInt(int left, int right) {
		int remain = remainderAsInt(left, right);
		int div = divideAsInt(minusAsInt(left, remain), right);
		return new int[] {div, remain};
	}
}
