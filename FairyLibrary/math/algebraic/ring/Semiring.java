package math.algebraic.ring;

import math.algebraic.group.CommutativeMonoid;
import math.algebraic.group.Monoid;

/**
 * 演算が半環であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Semiring<T, A extends CommutativeMonoid<T>, M extends Monoid<T>> {
	/**
	 * 和をなす演算を取り出します。
	 * @return 和に関する演算
	 */
	public A getAddition();
	/**
	 * 積をなす演算を取り出します。
	 * @return 積に関する演算
	 */
	public M getMultiplication();
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default T add(T left, T right) {
		return getAddition().apply(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default T multiply(T left, T right) {
		return getMultiplication().apply(left, right);
	}
	/**
	 * 加法単位元を取得します。
	 * @return 加法単位元
	 */
	public default T additiveIdentity() {
		return getAddition().identity();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default T multipleIdentity() {
		return getMultiplication().identity();
	}
	/**
	 * 標数を取得します。
	 * @return 標数
	 */
	public default int characteristic() {
		return 0;
	}
}
