package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.LongCommutativeMonoid;
import com._31536000.math.algebraic.group.LongMonoid;

/**
 * 演算が閉半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link ClosedSemiring}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongClosedSemiring<A extends LongCommutativeMonoid, M extends LongMonoid> extends ClosedSemiring<Long, A, M>, LongSemiring<A, M> {
	@Override
	public default Long closure(Long element) {
		return closureAsLong(element);
	}
	/**
	 * 閉包、すなわちclosure(a)=∑[k=0, ∞]a^kを返します。
	 * @param element 閉包を求める値
	 * @return 閉包
	 */
	public long closureAsLong(long element);
}
