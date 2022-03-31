package com._31536000.math.algebraic.lattice;

import com._31536000.math.algebraic.group.LongSemilattice;
import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.order.LongPreorder;

/**
 * 演算が束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link Lattice}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongLattice<A extends LongSemilattice, M extends LongSemilattice> extends Lattice<Long, A, M>, LongPreorder{

	@Override
	public default Long plus(Long left, Long right) {
		return plusAsLong(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default long plusAsLong(long left, long right) {
		return getAddition().applyAsLong(left, right);
	}
	@Override
	public default Long times(Long left, Long right) {
		return timesAsLong(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default long timesAsLong(long left, long right) {
		return getMultiplication().applyAsLong(left, right);
	}
	@Override
	public default Long additiveIdentity() {
		return additiveIdentityAsLong();
	}
	/**
	 * 加法単位元を取得します。
	 * @return 加法単位元
	 */
	public default long additiveIdentityAsLong() {
		return getAddition().identityAsLong();
	}
	@Override
	public default Long multiplicativeIdentity() {
		return multiplicativeIdentityAsLong();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default long multiplicativeIdentityAsLong() {
		return getMultiplication().identityAsLong();
	}
	@Override
	default int compare(Long o1, Long o2) {
		return compare((long)o1, (long)o2);
	}
	@Override
	default int compare(long o1, long o2) {
		long o3 = getMultiplication().applyAsLong(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
