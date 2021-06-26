package math.algebraic.domain;

import math.algebraic.group.DoubleAbelian;
import math.algebraic.group.DoubleCommutativeMonoid;

/**
 * 演算がGCD整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link GCDDomain}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleGCDDomain<A extends DoubleAbelian, M extends DoubleCommutativeMonoid> extends GCDDomain<Double, A, M>, DoubleIntegrallyClosedDomain<A, M>{
	@Override
	public default Double gcd(Double left, Double right) {
		return gcdAsDouble(left, right);
	}
	public double gcdAsDouble(double left, double right);
	@Override
	public default Double lcm(Double left, Double right) {
		return lcmAsDouble(left, right);
	}
	public double lcmAsDouble(double left, double right);
}
