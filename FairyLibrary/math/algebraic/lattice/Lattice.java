package math.algebraic.lattice;

import math.algebraic.group.Semilattice;
import math.algebraic.order.IncomparableException;
import math.algebraic.order.Preorder;

/**
 * 演算が束に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Lattice<T, A extends Semilattice<T>, M extends Semilattice<T>> extends Preorder<T>{
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
	@Override
	public default int compare(T o1, T o2) {
		T o3 = getMultiplication().apply(o1, o2);
		if (o1.equals(o3)) return o2.equals(o3) ? 0 : -1;
		if (o2.equals(o3)) return 1;
		throw new IncomparableException();
	}
}
