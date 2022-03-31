package com._31536000.math.algebraic.lattice;

import com._31536000.math.algebraic.group.LongSemilattice;
import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.ring.LongCommutativeSemiring;
import com._31536000.math.algebraic.ring.LongIdempotentSemiring;

/**
 * 演算が分配束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link DistributiveLattice}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongDistributiveLattice<A extends LongSemilattice, M extends LongSemilattice> extends DistributiveLattice<Long, A, M>, LongIdempotentSemiring<A, M>, LongCommutativeSemiring<A, M>, LongLattice<A, M>{
	@Override
	default Long plus(Long left, Long right) {
		return plusAsLong(left, right);
	}
	@Override
	public default long plusAsLong(long left, long right) {
		return getAddition().applyAsLong(left, right);
	}
	@Override
	default Long times(Long left, Long right) {
		return timesAsLong(left, right);
	}
	@Override
	public default long timesAsLong(long left, long right) {
		return getMultiplication().applyAsLong(left, right);
	}
	@Override
	default Long additiveIdentity() {
		return additiveIdentityAsLong();
	}
	@Override
	public default long additiveIdentityAsLong() {
		return getAddition().identityAsLong();
	}
	@Override
	default Long multiplicativeIdentity() {
		return multiplicativeIdentityAsLong();
	}
	@Override
	public default long multiplicativeIdentityAsLong() {
		return getMultiplication().identityAsLong();
	}
	@Override
	default int compare(Long o1, Long o2) {
		return compare((long)o1, (long)o2);
	}
	@Override
	default int compare(long o1, long o2) {
		long o3 = getMultiplication().applyAsLong(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
