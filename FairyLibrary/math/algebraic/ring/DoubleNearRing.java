package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleAssociative;
import com._31536000.math.algebraic.group.DoubleGroup;

/**
 * 演算が近環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link NearRing}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleNearRing<A extends DoubleGroup, M extends DoubleAssociative> extends NearRing<Double, A, M>, DoubleNearSemiring<A, M>{
	@Override
	public default Double minus(Double left, Double right) {
		return minusAsDouble(left, right);
	}
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default double minusAsDouble(double left, double right) {
		return getAddition().subtractAsDouble(left, right);
	}
	@Override
	public default Double opposite(Double element) {
		return oppositeAsDouble(element);
	}
	/**
	 * 加法逆元を返します。
	 * @param element 加法逆元を求める値
	 * @return 加法逆元
	 */
	public default double oppositeAsDouble(double element) {
		return getAddition().inverseAsDouble(element);
	}
}
