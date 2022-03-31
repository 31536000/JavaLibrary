package com._31536000.math.algebraic.lattice;

import com._31536000.math.algebraic.group.IntSemilattice;
import com._31536000.math.algebraic.order.IncomparableException;
import com._31536000.math.algebraic.order.IntPreorder;

/**
 * 演算が束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link Lattice}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntLattice<A extends IntSemilattice, M extends IntSemilattice> extends Lattice<Integer, A, M>, IntPreorder{

	@Override
	public default Integer plus(Integer left, Integer right) {
		return plusAsInt(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default int plusAsInt(int left, int right) {
		return getAddition().applyAsInt(left, right);
	}
	@Override
	public default Integer times(Integer left, Integer right) {
		return timesAsInt(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default int timesAsInt(int left, int right) {
		return getMultiplication().applyAsInt(left, right);
	}
	@Override
	public default Integer additiveIdentity() {
		return additiveIdentityAsInt();
	}
	/**
	 * 加法単位元を取得します。
	 * @return 加法単位元
	 */
	public default int additiveIdentityAsInt() {
		return getAddition().identityAsInt();
	}
	@Override
	public default Integer multiplicativeIdentity() {
		return multiplicativeIdentityAsInt();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default int multiplicativeIdentityAsInt() {
		return getMultiplication().identityAsInt();
	}
	@Override
	default int compare(Integer o1, Integer o2) {
		return compare((int)o1, (int)o2);
	}
	@Override
	default int compare(int o1, int o2) {
		int o3 = getMultiplication().applyAsInt(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
