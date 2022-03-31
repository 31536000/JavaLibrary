package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntAssociative;
import com._31536000.math.algebraic.group.IntMonoid;

/**
 * 演算が近半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link NearSemiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntNearSemiring<A extends IntMonoid, M extends IntAssociative> extends NearSemiring<Integer, A, M>{
	@Override
	public default Integer plus(Integer left, Integer right) {
		return plusAsInt(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default int plusAsInt(int left, int right) {
		return getAddition().applyAsInt(left, right);
	}
	@Override
	public default Integer times(Integer left, Integer right) {
		return timesAsInt(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default int timesAsInt(int left, int right) {
		return getMultiplication().applyAsInt(left, right);
	}
	@Override
	public default Integer additiveIdentity() {
		return additiveIdentityAsInt();
	}
	/**
	 * 加法単位元を取得します。
	 * @return 加法単位元
	 */
	public default int additiveIdentityAsInt() {
		return getAddition().identityAsInt();
	}
}
