package math.algebraic.ring;

import math.algebraic.group.IntCommutativeMonoid;
import math.algebraic.group.IntMonoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntSemiring<A extends IntCommutativeMonoid, M extends IntMonoid> extends Semiring<Integer, A, M>{
	@Override
	public default Integer add(Integer left, Integer right) {
		return addAsInt(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default int addAsInt(int left, int right) {
		return getAddition().applyAsInt(left, right);
	}
	@Override
	public default Integer multiply(Integer left, Integer right) {
		return multiplyAsInt(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default int multiplyAsInt(int left, int right) {
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
	@Override
	public default Integer multipleIdentity() {
		return multipleIdentityAsInt();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default int multipleIdentityAsInt() {
		return getMultiplication().identityAsInt();
	}
}
