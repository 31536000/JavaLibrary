package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleAssociative;
import com._31536000.math.algebraic.group.DoubleMonoid;

/**
 * 演算が近半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link NearSemiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleNearSemiring<A extends DoubleMonoid, M extends DoubleAssociative> extends NearSemiring<Double, A, M>{
	@Override
	public default Double plus(Double left, Double right) {
		return plusAsDouble(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default double plusAsDouble(double left, double right) {
		return getAddition().applyAsDouble(left, right);
	}
	@Override
	public default Double times(Double left, Double right) {
		return timesAsDouble(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default double timesAsDouble(double left, double right) {
		return getMultiplication().applyAsDouble(left, right);
	}
	@Override
	public default Double additiveIdentity() {
		return additiveIdentityAsDouble();
	}
	/**
	 * 加法単位元を取得します。
	 * @return 加法単位元
	 */
	public default double additiveIdentityAsDouble() {
		return getAddition().identityAsDouble();
	}
}
