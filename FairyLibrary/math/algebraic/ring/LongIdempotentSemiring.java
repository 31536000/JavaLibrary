package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongMonoid;
import com._31536000.math.algebraic.group.LongSemilattice;
import com._31536000.math.algebraic.order.LongPartialOrder;

/**
 * 演算が冪等半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link IdempotentSemiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongIdempotentSemiring<A extends LongSemilattice, M extends LongMonoid> extends IdempotentSemiring<Long, A, M>, LongLocallyClosedSemiring<A, M>, LongPartialOrder{
	@Override
	public default Long closure(Long element) {
		return multiplicativeIdentityAsLong();
	}
	@Override
	public default long closureAsLong(long element) {
		return multiplicativeIdentityAsLong();
	}
	@Override
	public default int compare(long o1, long o2) {
		return getAddition().compare(o1, o2);
	}

	@Override
	default int compare(Long o1, Long o2) {
		return compare((long)o1, (long)o2);
	}
}
