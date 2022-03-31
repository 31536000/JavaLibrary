package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.util.collect.Array;

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
	public T remainder(T left, T right);

	/**
	 * right * x + y = leftとなるようなx及びyを値として持つ配列を返します。
	 * ここでyは{@link #remainder(left, right)}に一致します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return x, yと続く二つの要素を持つ配列
	 */
	public default Array<T> divideAndRemainder(T left, T right) {
		T remain = remainder(left, right);
		T div = divide(minus(left, remain), right);
		Array<T> ret = Array.create(2);
		ret.set(0, div);
		ret.set(1, remain);
		return ret;
	}
}
