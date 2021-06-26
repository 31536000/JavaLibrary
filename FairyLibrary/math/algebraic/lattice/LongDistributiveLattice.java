package math.algebraic.lattice;

import math.algebraic.group.LongSemilattice;
import math.algebraic.ring.LongCommutativeSemiring;
import math.algebraic.ring.LongIdempotentSemiring;

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
	default Long add(Long left, Long right) {
		return addAsLong(left, right);
	}
	@Override
	default Long multiply(Long left, Long right) {
		return multiplyAsLong(left, right);
	}
	@Override
	default Long additiveIdentity() {
		return additiveIdentityAsLong();
	}
	@Override
	default Long multipleIdentity() {
		return multipleIdentityAsLong();
	}
}
