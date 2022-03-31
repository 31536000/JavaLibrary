package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.Monoid;
import com._31536000.math.algebraic.group.Semilattice;
import com._31536000.math.algebraic.order.PartialOrder;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IdempotentSemiring<T, A extends Semilattice<T>, M extends Monoid<T>> extends LocallyClosedSemiring<T, A, M>, PartialOrder<T>{
	@Override
	public default T closure(T element) {
		return multiplicativeIdentity();
	}
	@Override
	public default int degree() {
		return 0;
	}
	@Override
	public default int compare(T o1, T o2) {
		return getAddition().compare(o1, o2);
	}
}
