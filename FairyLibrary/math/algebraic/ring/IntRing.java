package math.algebraic.ring;

import math.algebraic.group.IntAbelian;
import math.algebraic.group.IntMonoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Ring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntRing<A extends IntAbelian, M extends IntMonoid> extends Ring<Integer, A, M>, IntSemiring<A, M>{
	@Override
	public default Integer subtract(Integer left, Integer right) {
		return subtractAsInt(left, right);
	}
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default int subtractAsInt(int left, int right) {
		return getAddition().subtractAsInt(left, right);
	}
}
