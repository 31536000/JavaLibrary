package math.algebraic.lattice;

import math.algebraic.group.LongSemilattice;
import math.algebraic.order.IncomparableException;
import math.algebraic.order.LongPreorder;

/**
 * 演算が束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link Lattice}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongLattice<A extends LongSemilattice, M extends LongSemilattice> extends Lattice<Long, A, M>, LongPreorder{
	@Override
	default int compare(Long o1, Long o2) {
		return compareAsLong(o1, o2);
	}
	@Override
	default int compareAsLong(long o1, long o2) {
		long o3 = getMultiplication().applyAsLong(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
