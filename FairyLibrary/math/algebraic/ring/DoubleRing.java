package math.algebraic.ring;

import math.algebraic.group.DoubleAbelian;
import math.algebraic.group.DoubleMonoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Ring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleRing<A extends DoubleAbelian, M extends DoubleMonoid> extends Ring<Double, A, M>, DoubleSemiring<A, M>{
	@Override
	public default Double subtract(Double left, Double right) {
		return subtractAsDouble(left, right);
	}
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default double subtractAsDouble(double left, double right) {
		return getAddition().subtractAsDouble(left, right);
	}
}
