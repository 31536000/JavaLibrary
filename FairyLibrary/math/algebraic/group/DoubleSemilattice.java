package com._31536000.math.algebraic.group;

/**
 * 演算が半束であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semilattice}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleSemilattice extends Semilattice<Double>, DoubleBand, DoubleCommutativeMonoid {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default double hyperAsDouble(double element, int repeat) {
		if (repeat < 1) {
			if (repeat == 0) return identityAsDouble();
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
	public default double hyperAsDouble(double element, long repeat) {
		if (repeat < 1) {
			if (repeat == 0) return identityAsDouble();
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
	default Double hyper(Double element, int repeat) {
		if (repeat < 1) {
			if (repeat == 0) return identityAsDouble();
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
	default Double hyper(Double element, long repeat) {
		if (repeat < 1) {
			if (repeat == 0) return identityAsDouble();
			throw new IllegalArgumentException("undefined operation");
		}
		return element;
	}
}
