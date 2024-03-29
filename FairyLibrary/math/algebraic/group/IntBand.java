package com._31536000.math.algebraic.group;

/**
 * 演算が帯であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Band}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntBand extends Band<Integer>, IntAssociative, IntIdempotent {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}

	@Override
	public default int hyperAsInt(int element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	@Override
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
