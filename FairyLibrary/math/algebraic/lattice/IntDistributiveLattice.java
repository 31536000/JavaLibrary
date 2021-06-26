package math.algebraic.lattice;

import math.algebraic.group.IntSemilattice;
import math.algebraic.ring.IntCommutativeSemiring;
import math.algebraic.ring.IntIdempotentSemiring;

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
	default Integer add(Integer left, Integer right) {
		return addAsInt(left, right);
	}
	@Override
	default Integer multiply(Integer left, Integer right) {
		return multiplyAsInt(left, right);
	}
	@Override
	default Integer additiveIdentity() {
		return additiveIdentityAsInt();
	}
	@Override
	default Integer multipleIdentity() {
		return multipleIdentityAsInt();
	}
}
