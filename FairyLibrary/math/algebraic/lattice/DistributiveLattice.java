package math.algebraic.lattice;

import math.algebraic.group.Semilattice;
import math.algebraic.ring.CommutativeSemiring;
import math.algebraic.ring.IdempotentSemiring;

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
	public default T add(T left, T right) {
		return getAddition().apply(left, right);
	}
	@Override
	public default T multiply(T left, T right) {
		return getMultiplication().apply(left, right);
	}
	@Override
	public default T additiveIdentity() {
		return getAddition().identity();
	}
	@Override
	public default T multipleIdentity() {
		return getMultiplication().identity();
	}
}
