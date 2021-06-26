package math.algebraic.domain;

import math.algebraic.group.LongAbelian;
import math.algebraic.group.LongCommutativeMonoid;

/**
 * 演算がGCD整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link GCDDomain}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongGCDDomain<A extends LongAbelian, M extends LongCommutativeMonoid> extends GCDDomain<Long, A, M>, LongIntegrallyClosedDomain<A, M>{
	@Override
	public default Long gcd(Long left, Long right) {
		return gcdAsLong(left, right);
	}
	public long gcdAsLong(long left, long right);
	@Override
	public default Long lcm(Long left, Long right) {
		return lcmAsLong(left, right);
	}
	public long lcmAsLong(long left, long right);
}
