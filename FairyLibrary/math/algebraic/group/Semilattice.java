package com._31536000.math.algebraic.group;

/**
 * 演算が半束であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Semilattice<T> extends Band<T>, CommutativeMonoid<T> {
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default T hyper(T element, int repeat) {
		if (repeat < 1) {
			if (repeat == 0) return identity();
			throw new IllegalArgumentException("undefined operation");
		}
		return element;
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default T hyper(T element, long repeat) {
		if (repeat < 1) {
			if (repeat == 0) return identity();
			throw new IllegalArgumentException("undefined operation");
		}
		return element;
	}
}
