package math.algebraic.lattice;

import math.algebraic.group.DoubleSemilattice;
import math.algebraic.order.DoublePreorder;
import math.algebraic.order.IncomparableException;

/**
 * 演算が束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link Lattice}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleLattice<A extends DoubleSemilattice, M extends DoubleSemilattice> extends Lattice<Double, A, M>, DoublePreorder{
	@Override
	default int compare(Double o1, Double o2) {
		return compareAsDouble(o1, o2);
	}
	@Override
	default int compareAsDouble(double o1, double o2) {
		double o3 = getMultiplication().applyAsDouble(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
