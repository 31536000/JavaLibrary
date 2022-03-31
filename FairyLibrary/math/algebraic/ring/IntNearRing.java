package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntAssociative;
import com._31536000.math.algebraic.group.IntGroup;

/**
 * 演算が近環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link NearRing}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntNearRing<A extends IntGroup, M extends IntAssociative> extends NearRing<Integer, A, M>, IntNearSemiring<A, M>{
	@Override
	public default Integer minus(Integer left, Integer right) {
		return minusAsInt(left, right);
	}
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default int minusAsInt(int left, int right) {
		return getAddition().subtractAsInt(left, right);
	}
	@Override
	public default Integer opposite(Integer element) {
		return oppositeAsInt(element);
	}
	/**
	 * 加法逆元を返します。
	 * @param element 加法逆元を求める値
	 * @return 加法逆元
	 */
	public default int oppositeAsInt(int element) {
		return getAddition().inverseAsInt(element);
	}
}
