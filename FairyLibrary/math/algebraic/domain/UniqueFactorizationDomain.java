package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が一意分解整域であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface UniqueFactorizationDomain<T, A extends Abelian<T>, M extends CommutativeMonoid<T>> extends GCDDomain<T, A, M>{
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public MultiSet<? extends PrimeElement<T>> getPrimeFactorization(T x);
}
