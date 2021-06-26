package math.algebraic.ring;

import math.algebraic.group.Abelian;
import math.algebraic.group.Monoid;

/**
 * 演算が環であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Ring<T, A extends Abelian<T>, M extends Monoid<T>> extends Semiring<T, A, M>{
	/**
	 * 差について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left - right
	 */
	public default T subtract(T left, T right) {
		return getAddition().subtract(left, right);
	}
}
