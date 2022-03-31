package com._31536000.math.algebraic.domain;

import com._31536000.math.algebraic.group.LongAbelian;
import com._31536000.math.algebraic.group.LongCommutativeMonoid;
import com._31536000.util.collect.MultiSet;

/**
 * 演算が一意分解整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link UniqueFactorizationDomain}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface LongUniqueFactorizationDomain<A extends LongAbelian, M extends LongCommutativeMonoid> extends UniqueFactorizationDomain<Long, A, M>, LongGCDDomain<A, M>{
	@Override
	public default MultiSet<? extends LongPrimeElement> getPrimeFactorization(Long x) {
		return getPrimeFactorizationAsLong(x);
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public MultiSet<? extends LongPrimeElement> getPrimeFactorizationAsLong(long x);
}
