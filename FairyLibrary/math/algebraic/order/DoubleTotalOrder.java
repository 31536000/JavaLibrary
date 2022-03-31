package com._31536000.math.algebraic.order;

import com._31536000.util.DoubleComparator;

/**
 * 演算が全順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link TotalOrder}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleTotalOrder extends TotalOrder<Double>, DoublePartialOrder, DoubleConnexity, DoubleComparator{
	@Override
	public default int compare(Double o1, Double o2) {
		return compare((double)o1, (double)o2);
	}
	/**
	 * {@link DoubleComparator}を受け取り、TotalOrderへと拡張します。
	 * @param comparator TotalOrderへ拡張するComparator
	 * @return Comparatorと同じ比較を提供するTotalOrder
	 */
	public static DoubleTotalOrder comparing(DoubleComparator comparator) {
		return (o1, o2) -> comparator.compare(o1, o2);
	}
	/**
	 * 自然な順序で比較するTotalOrderを返します。
	 * これは、comparingの引数にDouble::compareを渡すのと同じです。
	 * @return 自然順序で比較を行うTotalOrder
	 */
	public static DoubleTotalOrder naturalOrder() {
		return (o1, o2) -> Double.compare(o1, o2);
	}
	/**
	 * 自然な順序の逆で比較するChainを返します。
	 * @return 自然順序で比較を行うChain
	 */
	public static DoubleTotalOrder reverseOrder() {
		return (o1, o2) -> Double.compare(o2, o1);
	}
	@Override
	public default DoubleTotalOrder reversed() {
		return (o1, o2) -> compare(o2, o1);
	}
}
