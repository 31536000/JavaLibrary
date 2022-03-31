package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.order.IntPartialOrder;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Idempotent}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntIdempotent extends Idempotent<Integer>, IntMagma, IntPartialOrder {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}

	@Override
	public default int compare(int o1, int o2) {
		if (o1 == applyAsInt(o1, o2)) { return o2 == applyAsInt(o1, o2) ? 0 : -1; }
		if (o2 == applyAsInt(o1, o2)) return 1;
		throw new IncomparableException();
	}

	@Override
	default int compare(Integer o1, Integer o2) {
		return compare(o1, o2);
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default int hyperAsInt(int element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default int hyperAsInt(int element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	@Override
	default Integer hyper(Integer element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	@Override
	default Integer hyper(Integer element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}
}
