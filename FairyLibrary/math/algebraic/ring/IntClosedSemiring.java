package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntCommutativeMonoid;
import com._31536000.math.algebraic.group.IntMonoid;

/**
 * 演算が閉半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link ClosedSemiring}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntClosedSemiring<A extends IntCommutativeMonoid, M extends IntMonoid> extends ClosedSemiring<Integer, A, M>, IntSemiring<A, M> {
	@Override
	public default Integer closure(Integer element) {
		return closureAsInt(element);
	}
	/**
	 * 閉包、すなわちclosure(a)=∑[k=0, ∞]a^kを返します。
	 * @param element 閉包を求める値
	 * @return 閉包
	 */
	public int closureAsInt(int element);
}
