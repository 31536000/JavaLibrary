package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongAssociative;
import com._31536000.math.algebraic.group.LongMonoid;

/**
 * 演算が近半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link NearSemiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongNearSemiring<A extends LongMonoid, M extends LongAssociative> extends NearSemiring<Long, A, M>{
	@Override
	public default Long plus(Long left, Long right) {
		return plusAsLong(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default long plusAsLong(long left, long right) {
		return getAddition().applyAsLong(left, right);
	}
	@Override
	public default Long times(Long left, Long right) {
		return timesAsLong(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default long timesAsLong(long left, long right) {
		return getMultiplication().applyAsLong(left, right);
	}
	@Override
	public default Long additiveIdentity() {
		return additiveIdentityAsLong();
	}
	/**
	 * 加法単位元を取得します。
	 * @return 加法単位元
	 */
	public default long additiveIdentityAsLong() {
		return getAddition().identityAsLong();
	}
}
