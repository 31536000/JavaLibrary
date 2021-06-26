package math.algebraic.ring;

import math.algebraic.group.LongAbelian;
import math.algebraic.group.LongMonoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Ring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongRing<A extends LongAbelian, M extends LongMonoid> extends Ring<Long, A, M>, LongSemiring<A, M>{
	@Override
	public default Long subtract(Long left, Long right) {
		return subtractAsLong(left, right);
	}
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default long subtractAsLong(long left, long right) {
		return getAddition().subtractAsLong(left, right);
	}
}
