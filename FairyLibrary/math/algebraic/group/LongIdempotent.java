package com._31536000.math.algebraic.group;

import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.order.LongPartialOrder;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Idempotent}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongIdempotent extends Idempotent<Long>, LongMagma, LongPartialOrder {
	@Override
	public default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}

	@Override
	public default int compare(long o1, long o2) {
		if (o1 == applyAsLong(o1, o2)) { return o2 == applyAsLong(o1, o2) ? 0 : -1; }
		if (o2 == applyAsLong(o1, o2)) return 1;
		throw new IncomparableException();
	}

	@Override
	default int compare(Long o1, Long o2) {
		return compare(o1, o2);
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default long hyperAsLong(long element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default long hyperAsLong(long element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	@Override
	default Long hyper(Long element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}

	@Override
	default Long hyper(Long element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		return element;
	}
}
