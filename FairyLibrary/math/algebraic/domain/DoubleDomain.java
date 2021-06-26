package math.algebraic.domain;

import math.algebraic.group.DoubleAbelian;
import math.algebraic.group.DoubleMonoid;
import math.algebraic.ring.DoubleRing;

/**
 * 演算が域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Domain}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleDomain<A extends DoubleAbelian, M extends DoubleMonoid> extends Domain<Double, A, M>, DoubleRing<A, M>{
	@Override
	public default boolean isDivisible(Double left, Double right) {
		return isDivisibleAsDouble(left, right);
	}
	/**
	 * leftをrightで割ることが可能か判定します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / rightが定義されているならtrue
	 */
	public default boolean isDivisibleAsDouble(double left, double right) {
		try {
			divideAsDouble(left, right);
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
	public default Double divide(Double left, Double right) {
		return divideAsDouble(left, right);
	}
	/**
	 * leftをrightで割った値を返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left / right
	 * @exception ArithmeticException left/rightが定義されていない場合
	 */
	public double divideAsDouble(double left, double right);
}
