package com._31536000.math.algebraic.field;

import com._31536000.math.algebraic.domain.EuclideanDomain;
import com._31536000.math.algebraic.domain.PrimeElement;
import com._31536000.math.algebraic.group.Abelian;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が体であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface Field<T, A extends Abelian<T>, M extends Abelian<T>> extends SkewField<T, A, M>, EuclideanDomain<T, A, M>{
	@Override
	public default T remainder(T left, T right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentity();
	}
	@Override
	public default T gcd(T left, T right) {
		return multiplicativeIdentity();
	}
	@Override
	public default T lcm(T left, T right) {
		return multiplicativeIdentity();
	}
	@Override
	public default MultiSet<? extends PrimeElement<T>> getPrimeFactorization(T x) {
		HashMultiSet<PrimeElement<T>> ret = HashMultiSet.create(1);
		ret.add(PrimeElement.create(x));
		return ret;
	}
}
