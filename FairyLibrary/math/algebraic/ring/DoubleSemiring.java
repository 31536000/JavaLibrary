package math.algebraic.ring;

import math.algebraic.group.DoubleCommutativeMonoid;
import math.algebraic.group.DoubleMonoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleSemiring<A extends DoubleCommutativeMonoid, M extends DoubleMonoid> extends Semiring<Double, A, M>{
	@Override
	public default Double add(Double left, Double right) {
		return addAsDouble(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default double addAsDouble(double left, double right) {
		return getAddition().applyAsDouble(left, right);
	}
	@Override
	public default Double multiply(Double left, Double right) {
		return multiply(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default double multiplyAsDouble(double left, double right) {
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
	@Override
	public default Double multipleIdentity() {
		return multipleIdentityAsDouble();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default double multipleIdentityAsDouble() {
		return getMultiplication().identityAsDouble();
	}
}
