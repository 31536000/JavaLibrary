package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.order.PartialOrder;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Idempotent<T> extends Magma<T>, PartialOrder<T> {
	@Override
	public default int compare(T o1, T o2) {
		if (java.util.Objects.equals(o1, apply(o1, o2))) {
			return java.util.Objects.equals(o2, apply(o1, o2)) ? 0 : -1;
		}
		if (java.util.Objects.equals(o2, apply(o1, o2))) return 1;
		throw new IncomparableException();
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default T hyper(T element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default T hyper(T element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}
}
