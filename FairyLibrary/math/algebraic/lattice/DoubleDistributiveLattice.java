package math.algebraic.lattice;

import math.algebraic.group.DoubleSemilattice;
import math.algebraic.ring.DoubleCommutativeSemiring;
import math.algebraic.ring.DoubleIdempotentSemiring;

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
	default Double add(Double left, Double right) {
		return addAsDouble(left, right);
	}
	@Override
	default Double multiply(Double left, Double right) {
		return multiplyAsDouble(left, right);
	}
	@Override
	default Double additiveIdentity() {
		return additiveIdentityAsDouble();
	}
	@Override
	default Double multipleIdentity() {
		return multipleIdentityAsDouble();
	}
}
