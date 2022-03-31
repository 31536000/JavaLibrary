package com._31536000.math.algebraic.order;

import java.util.function.IntBinaryOperator;
import com._31536000.util.IntComparator;

/**
 * 演算が全順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link TotalOrder}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntTotalOrder extends TotalOrder<Integer>, IntPartialOrder, IntConnexity, IntComparator{
	@Override
	default int compare(Integer o1, Integer o2) {
		return compare((int)o1, (int)o2);
	}
	/**
	 * {@link IntComparator}を受け取り、TotalOrderへと拡張します。
	 * @param comparator TotalOrderへ拡張するComparator
	 * @return IntComparatorと同じ比較を提供するTotalOrder
	 */
	public static IntTotalOrder comparing(IntComparator comparator) {
		return (o1, o2) -> comparator.compare(o1, o2);
	}
	/**
	 * 比較関数を受け取り、TotalOrderへと拡張します。
	 * @param comparator 比較関数、全順序を満たす必要がある
	 * @return comparatorの規則で比較を行うTotalOrder
	 */
	public static IntTotalOrder comparing(IntBinaryOperator comparator) {
		return (o1, o2) -> comparator.applyAsInt(o1, o2);
	}
	/**
	 * 自然な順序で比較するTotalOrderを返します。
	 * これは、comparingの引数にInteger::compareを渡すのと同じです。
	 * @return 自然順序で比較を行うTotalOrder
	 */
	public static IntTotalOrder naturalOrder() {
		return (o1, o2) -> Integer.compare(o1, o2);
	}
	/**
	 * 自然な順序の逆で比較するTotalOrderを返します。
	 * @return 逆順序で比較を行うTotalOrder
	 */
	public static IntTotalOrder reverseOrder() {
		return (o1, o2) -> Integer.compare(o2, o1);
	}
	@Override
	public default IntTotalOrder reversed() {
		return (o1, o2) -> compare(o2, o1);
	}
}
