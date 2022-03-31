package com._31536000.math.algebraic.group;

/**
 * 演算が結合法則を満たす、すなわち半群であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Associative<T> extends Magma<T> {
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default T hyper(T element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		T ret = element;
		--repeat;
		for (T mul = element; repeat > 0; repeat >>= 1, mul = apply(mul, mul))
			if ((repeat & 1) != 0) ret = apply(ret, mul);
		return ret;
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default T hyper(T element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		T ret = element;
		--repeat;
		for (T mul = element; repeat > 0; repeat >>= 1, mul = apply(mul, mul))
			if ((repeat & 1) != 0) ret = apply(ret, mul);
		return ret;
	}
}
