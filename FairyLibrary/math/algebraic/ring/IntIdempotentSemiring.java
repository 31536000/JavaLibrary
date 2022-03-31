package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntMonoid;
import com._31536000.math.algebraic.group.IntSemilattice;
import com._31536000.math.algebraic.order.IntPartialOrder;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link IdempotentSemiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntIdempotentSemiring<A extends IntSemilattice, M extends IntMonoid> extends IdempotentSemiring<Integer, A, M>, IntLocallyClosedSemiring<A, M>, IntPartialOrder{
	@Override
	public default Integer closure(Integer element) {
		return multiplicativeIdentityAsInt();
	}
	@Override
	public default int closureAsInt(int element) {
		return multiplicativeIdentityAsInt();
	}
	@Override
	public default int compare(int o1, int o2) {
		return getAddition().compare(o1, o2);
	}

	@Override
	default int compare(Integer o1, Integer o2) {
		return compare((int)o1, (int)o2);
	}
}
