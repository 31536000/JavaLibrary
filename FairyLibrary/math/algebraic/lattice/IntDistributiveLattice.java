package com._31536000.math.algebraic.lattice;

import com._31536000.math.algebraic.group.IntSemilattice;
import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.ring.IntCommutativeSemiring;
import com._31536000.math.algebraic.ring.IntIdempotentSemiring;

/**
 * 演算が分配束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link DistributiveLattice}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntDistributiveLattice<A extends IntSemilattice, M extends IntSemilattice> extends DistributiveLattice<Integer, A, M>, IntIdempotentSemiring<A, M>, IntCommutativeSemiring<A, M>, IntLattice<A, M>{
	@Override
	default Integer plus(Integer left, Integer right) {
		return plusAsInt(left, right);
	}
	@Override
	public default int plusAsInt(int left, int right) {
		return getAddition().applyAsInt(left, right);
	}
	@Override
	default Integer times(Integer left, Integer right) {
		return timesAsInt(left, right);
	}
	@Override
	public default int timesAsInt(int left, int right) {
		return getMultiplication().applyAsInt(left, right);
	}
	@Override
	default Integer additiveIdentity() {
		return additiveIdentityAsInt();
	}
	@Override
	public default int additiveIdentityAsInt() {
		return getAddition().identityAsInt();
	}
	@Override
	default Integer multiplicativeIdentity() {
		return multiplicativeIdentityAsInt();
	}
	@Override
	public default int multiplicativeIdentityAsInt() {
		return getMultiplication().identityAsInt();
	}
	@Override
	default int compare(Integer o1, Integer o2) {
		return compare((int)o1, (int)o2);
	}
	@Override
	default int compare(int o1, int o2) {
		int o3 = getMultiplication().applyAsInt(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
