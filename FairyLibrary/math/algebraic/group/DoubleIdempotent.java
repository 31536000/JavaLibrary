package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.order.DoublePartialOrder;
import com._31536000.math.algebraic.order.IncomparableException;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Idempotent}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleIdempotent extends Idempotent<Double>, DoubleMagma, DoublePartialOrder {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}

	@Override
	public default int compare(double o1, double o2) {
		if (o1 == applyAsDouble(o1, o2)) { return o2 == applyAsDouble(o1, o2) ? 0 : -1; }
		if (o2 == applyAsDouble(o1, o2)) return 1;
		throw new IncomparableException();
	}

	@Override
	default int compare(Double o1, Double o2) {
		return compare(o1, o2);
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default double hyperAsDouble(double element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default double hyperAsDouble(double element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	@Override
	default Double hyper(Double element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	@Override
	default Double hyper(Double element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}
}
