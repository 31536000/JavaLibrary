package math.algebraic.lattice;

import math.algebraic.group.IntSemilattice;
import math.algebraic.order.IncomparableException;
import math.algebraic.order.IntPreorder;

/**
 * 演算が束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link Lattice}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntLattice<A extends IntSemilattice, M extends IntSemilattice> extends Lattice<Integer, A, M>, IntPreorder{
	@Override
	default int compare(Integer o1, Integer o2) {
		return compareAsInt(o1, o2);
	}
	@Override
	default int compareAsInt(int o1, int o2) {
		int o3 = getMultiplication().applyAsInt(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
