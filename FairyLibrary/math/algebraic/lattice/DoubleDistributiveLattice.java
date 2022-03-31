package com._31536000.math.algebraic.lattice;

import com._31536000.math.algebraic.group.DoubleSemilattice;
import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.ring.DoubleCommutativeSemiring;
import com._31536000.math.algebraic.ring.DoubleIdempotentSemiring;

/**
 * 演算が分配束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link DistributiveLattice}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleDistributiveLattice<A extends DoubleSemilattice, M extends DoubleSemilattice> extends DistributiveLattice<Double, A, M>, DoubleIdempotentSemiring<A, M>, DoubleCommutativeSemiring<A, M>, DoubleLattice<A, M>{
	@Override
	default Double plus(Double left, Double right) {
		return plusAsDouble(left, right);
	}
	@Override
	default double plusAsDouble(double left, double right) {
		return getAddition().applyAsDouble(left, right);
	}
	@Override
	default Double times(Double left, Double right) {
		return timesAsDouble(left, right);
	}
	@Override
	public default double timesAsDouble(double left, double right) {
		return getMultiplication().applyAsDouble(left, right);
	}
	@Override
	default Double additiveIdentity() {
		return additiveIdentityAsDouble();
	}
	@Override
	public default double additiveIdentityAsDouble() {
		return getAddition().identityAsDouble();
	}
	@Override
	default Double multiplicativeIdentity() {
		return multiplicativeIdentityAsDouble();
	}
	@Override
	public default double multiplicativeIdentityAsDouble() {
		return getMultiplication().identityAsDouble();
	}
	@Override
	default int compare(Double o1, Double o2) {
		return compare((double)o1, (double)o2);
	}
	@Override
	default int compare(double o1, double o2) {
		double o3 = getMultiplication().applyAsDouble(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
