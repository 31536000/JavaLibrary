package math.algebraic.group;

import java.util.function.DoubleBinaryOperator;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Idempotent}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleIdempotent extends Idempotent<Double>, DoubleBinaryOperator{
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
}
