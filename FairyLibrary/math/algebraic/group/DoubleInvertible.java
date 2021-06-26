package math.algebraic.group;

import java.util.function.DoubleBinaryOperator;

/**
* この演算が逆元を持つことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Invertible}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleInvertible extends Invertible<Double>, DoubleBinaryOperator{
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
	@Override
	public default Double inverse(Double element) {
		return inverseAsDouble(element);
	}
	/**
	 * 逆元を返します。
	 * @param element 逆元を求める値
	 * @return 逆元
	 */
	public double inverseAsDouble(double element);
}
