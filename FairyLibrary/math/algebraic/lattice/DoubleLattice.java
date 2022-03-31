package com._31536000.math.algebraic.lattice;

import com._31536000.math.algebraic.group.DoubleSemilattice;
import com._31536000.math.algebraic.order.DoublePreorder;
import com._31536000.math.algebraic.order.IncomparableException;

/**
 * 演算が束に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link Lattice}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleLattice<A extends DoubleSemilattice, M extends DoubleSemilattice> extends Lattice<Double, A, M>, DoublePreorder{

	@Override
	public default Double plus(Double left, Double right) {
		return plusAsDouble(left, right);
	}
	/**
	 * 和について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left + right
	 */
	public default double plusAsDouble(double left, double right) {
		return getAddition().applyAsDouble(left, right);
	}
	@Override
	public default Double times(Double left, Double right) {
		return timesAsDouble(left, right);
	}
	/**
	 * 積について演算を行います。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left * right
	 */
	public default double timesAsDouble(double left, double right) {
		return getMultiplication().applyAsDouble(left, right);
	}
	@Override
	public default Double additiveIdentity() {
		return additiveIdentityAsDouble();
	}
	/**
	 * 加法単位元を取得します。
	 * @return 加法単位元
	 */
	public default double additiveIdentityAsDouble() {
		return getAddition().identityAsDouble();
	}
	@Override
	public default Double multiplicativeIdentity() {
		return multiplicativeIdentityAsDouble();
	}
	/**
	 * 乗法単位元を取得します。
	 * @return 乗法単位元
	 */
	public default double multiplicativeIdentityAsDouble() {
		return getMultiplication().identityAsDouble();
	}
	@Override
	default int compare(Double o1, Double o2) {
		return compare((double)o1, (double)o2);
	}
	@Override
	default int compare(double o1, double o2) {
		double o3 = getMultiplication().applyAsDouble(o1, o2);
		if (o1 == o3) return o2 == o3 ? 0 : -1;
		if (o2 == o3) return 1;
		throw new IncomparableException();
	}
}
