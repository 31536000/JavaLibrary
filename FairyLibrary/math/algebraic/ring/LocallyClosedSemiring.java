package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.group.Monoid;

/**
 * 演算が局所閉半環に属することを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LocallyClosedSemiring<T, A extends CommutativeMonoid<T>, M extends Monoid<T>> extends ClosedSemiring<T, A, M>{
	/**
	 * 任意の元aに対して、∑[i=0,k]a^i = ∑[i=0, k+1]a^iを満たす最小の値kを返します。
	 * @return この局所閉半環の次数
	 */
	public int degree();
}
