package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.DoubleCommutativeMonoid;
import com._31536000.math.algebraic.group.DoubleMonoid;

/**
 * 演算が閉半環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link ClosedSemiring}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleClosedSemiring<A extends DoubleCommutativeMonoid, M extends DoubleMonoid> extends ClosedSemiring<Double, A, M>, DoubleSemiring<A, M> {
	@Override
	public default Double closure(Double element) {
		return closureAsDouble(element);
	}
	/**
	 * 閉包、すなわちclosure(a)=∑[k=0, ∞]a^kを返します。
	 * @param element 閉包を求める値
	 * @return 閉包
	 */
	public double closureAsDouble(double element);
}
