package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongCommutativeMonoid;
import com._31536000.util.collect.Array;

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
	public default Long remainder(Long left, Long right) {
		return remainderAsLong(left, right);
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public long remainderAsLong(long left, long right);


	@Override
	public default Array<Long> divideAndRemainder(Long left, Long right) {
		long remain = remainderAsLong(left, right);
		long div = divideAsLong(minusAsLong(left, remain), right);
		Array<Long> ret = Array.create(2);
		ret.set(0, div);
		ret.set(1, remain);
		return ret;
	}
	/**
	 * right * x + y = leftとなるようなx及びyを値として持つ配列を返します。
	 * ここでyは{@link #remainderAsLong(long, long) #remainderAsLong(left, right)}に一致します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return x, yと続く二つの要素を持つ配列
	 */
	public default long[] divideAndRemainderAsLong(long left, long right) {
		long remain = remainderAsLong(left, right);
		long div = divideAsLong(minusAsLong(left, remain), right);
		return new long[] {div, remain};
	}
}
