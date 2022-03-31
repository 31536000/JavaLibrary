package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.group.Monoid;

/**
 * 演算が閉半環に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface ClosedSemiring<T, A extends CommutativeMonoid<T>, M extends Monoid<T>> extends Semiring<T, A, M> {
	/**
	 * 閉包、すなわちclosure(a)=∑[k=0, ∞]a^kを返します。
	 * @param element 閉包を求める値
	 * @return 閉包
	 */
	public T closure(T element);
}
