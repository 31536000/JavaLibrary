package math.algebraic.domain;

import math.algebraic.group.IntAbelian;
import math.algebraic.group.IntCommutativeMonoid;

/**
 * 演算がGCD整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link GCDDomain}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntGCDDomain<A extends IntAbelian, M extends IntCommutativeMonoid> extends GCDDomain<Integer, A, M>, IntIntegrallyClosedDomain<A, M>{
	@Override
	public default Integer gcd(Integer left, Integer right) {
		return gcdAsInt(left, right);
	}
	public int gcdAsInt(int left, int right);
	@Override
	public default Integer lcm(Integer left, Integer right) {
		return lcmAsInt(left, right);
	}
	public int lcmAsInt(int left, int right);
}
