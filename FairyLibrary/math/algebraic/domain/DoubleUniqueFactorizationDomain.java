package math.algebraic.domain;

import math.algebraic.group.DoubleAbelian;
import math.algebraic.group.DoubleCommutativeMonoid;
import util.collect.MultiSet;

/**
 * 演算が一意分解整域であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link UniqueFactorizationDomain}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface DoubleUniqueFactorizationDomain<A extends DoubleAbelian, M extends DoubleCommutativeMonoid> extends UniqueFactorizationDomain<Double, A, M>, DoubleGCDDomain<A, M>{
	@Override
	public default MultiSet<? extends DoublePrimeElement> getPrimeFactorization(Double x) {
		return getPrimeFactorizationAsDouble(x);
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public MultiSet<? extends DoublePrimeElement> getPrimeFactorizationAsDouble(double x);
}
