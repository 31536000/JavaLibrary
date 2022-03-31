package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongAssociative;
import com._31536000.math.algebraic.group.LongGroup;

/**
 * 演算が近環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link NearRing}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongNearRing<A extends LongGroup, M extends LongAssociative> extends NearRing<Long, A, M>, LongNearSemiring<A, M>{
	@Override
	public default Long minus(Long left, Long right) {
		return minusAsLong(left, right);
	}
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default long minusAsLong(long left, long right) {
		return getAddition().subtractAsLong(left, right);
	}
	@Override
	public default Long opposite(Long element) {
		return oppositeAsLong(element);
	}
	/**
	 * 加法逆元を返します。
	 * @param element 加法逆元を求める値
	 * @return 加法逆元
	 */
	public default long oppositeAsLong(long element) {
		return getAddition().inverseAsLong(element);
	}
}
