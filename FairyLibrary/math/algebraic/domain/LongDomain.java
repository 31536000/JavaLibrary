package math.algebraic.domain;

import math.algebraic.group.LongAbelian;
import math.algebraic.group.LongMonoid;
import math.algebraic.ring.LongRing;

/**
 * 演算が域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Domain}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongDomain<A extends LongAbelian, M extends LongMonoid> extends Domain<Long, A, M>, LongRing<A, M>{
	@Override
	public default boolean isDivisible(Long left, Long right) {
		return isDivisibleAsLong(left, right);
	}
	/**
	 * leftをrightで割ることが可能か判定します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / rightが定義されているならtrue
	 */
	public default boolean isDivisibleAsLong(long left, long right) {
		try {
			divideAsLong(left, right);
		} catch (ArithmeticException e) {
			return false;
		}
		return true;
	}
	/**
	 * leftをrightで割った値を返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / right
	 * @exception ArithmeticException left/rightが定義されていない場合
	 */
	@Override
	public default Long divide(Long left, Long right) {
		return divideAsLong(left, right);
	}
	/**
	 * leftをrightで割った値を返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / right
	 * @exception ArithmeticException left/rightが定義されていない場合
	 */
	public long divideAsLong(long left, long right);
}
