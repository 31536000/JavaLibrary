package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleMonoid;
import com._31536000.math.algebraic.group.DoubleSemilattice;
import com._31536000.math.algebraic.order.DoublePartialOrder;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link IdempotentSemiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleIdempotentSemiring<A extends DoubleSemilattice, M extends DoubleMonoid> extends IdempotentSemiring<Double, A, M>, DoubleLocallyClosedSemiring<A, M>, DoublePartialOrder{
	@Override
	public default Double closure(Double element) {
		return multiplicativeIdentityAsDouble();
	}
	@Override
	public default double closureAsDouble(double element) {
		return multiplicativeIdentityAsDouble();
	}
	@Override
	public default int compare(double o1, double o2) {
		return getAddition().compare(o1, o2);
	}

	@Override
	default int compare(Double o1, Double o2) {
		return compare((double)o1, (double)o2);
	}
}
