package math.algebraic.domain;

import math.algebraic.group.IntAbelian;
import math.algebraic.group.IntCommutativeMonoid;
import util.collect.MultiSet;

/**
 * 演算が一意分解整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link UniqueFactorizationDomain}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntUniqueFactorizationDomain<A extends IntAbelian, M extends IntCommutativeMonoid> extends UniqueFactorizationDomain<Integer, A, M>, IntGCDDomain<A, M>{
	@Override
	public default MultiSet<? extends IntPrimeElement> getPrimeFactorization(Integer x) {
		return getPrimeFactorizationAsInt(x);
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public MultiSet<? extends IntPrimeElement> getPrimeFactorizationAsInt(int x);
}
