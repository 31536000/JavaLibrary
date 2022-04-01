package com._31536000.math.algebraic.order;

/**
 * 演算が全順序であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface TotalOrder<T> extends PartialOrder<T>, WeakOrder<T>, java.util.Comparator<T> {
	/**
	 * {@link java.util.Comparator}を受け取り、TotalOrderへと拡張します。
	 * @param <T> 二項演算の型
	 * @param comparator TotalOrderへ拡張するComparator
	 * @return Comparatorと同じ比較を提供するTotalOrder
	 */
	public static <T> TotalOrder<T> comparing(java.util.Comparator<T> comparator) {
		return (o1, o2) -> comparator.compare(o1, o2);
	}

	@Override
	public default TotalOrder<T> reversed() {
		return (o1, o2) -> compare(o2, o1);
	}
}
