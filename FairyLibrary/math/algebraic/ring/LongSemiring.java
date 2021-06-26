package math.algebraic.ring;

import math.algebraic.group.LongCommutativeMonoid;
import math.algebraic.group.LongMonoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongSemiring<A extends LongCommutativeMonoid, M extends LongMonoid> extends Semiring<Long, A, M>{
	@Override
	public default Long add(Long left, Long right) {
		return addAsLong(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default long addAsLong(long left, long right) {
		return getAddition().applyAsLong(left, right);
	}
	@Override
	public default Long multiply(Long left, Long right) {
		return multiplyAsLong(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default long multiplyAsLong(long left, long right) {
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
	@Override
	public default Long multipleIdentity() {
		return multipleIdentityAsLong();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default long multipleIdentityAsLong() {
		return getMultiplication().identityAsLong();
	}
}
