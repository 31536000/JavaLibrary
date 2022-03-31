package com._31536000.math.algebraic.lattice;

import com._31536000.math.algebraic.group.Semilattice;
import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.ring.CommutativeSemiring;
import com._31536000.math.algebraic.ring.IdempotentSemiring;

/**
 * 演算が分配束に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DistributiveLattice<T, A extends Semilattice<T>, M extends Semilattice<T>> extends IdempotentSemiring<T, A, M>, CommutativeSemiring<T, A, M>, Lattice<T, A, M>{
	@Override
	public default T plus(T left, T right) {
		return getAddition().apply(left, right);
	}
	@Override
	public default T times(T left, T right) {
		return getMultiplication().apply(left, right);
	}
	@Override
	public default T additiveIdentity() {
		return getAddition().identity();
	}
	@Override
	public default T multiplicativeIdentity() {
		return getMultiplication().identity();
	}
	@Override
	public default int compare(T o1, T o2) {
		T o3 = getMultiplication().apply(o1, o2);
		if (o1.equals(o3)) return o2.equals(o3) ? 0 : -1;
		if (o2.equals(o3)) return 1;
		throw new IncomparableException();
	}
}
