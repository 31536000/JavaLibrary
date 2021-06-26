package math.algebraic.order;

/**
 * 演算が全順序であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface TotalOrder<T> extends PartialOrder<T>, WeakOrder<T>, java.util.Comparator<T>{
	/**
	 * {@link java.util.Comparator}を受け取り、Chainへと拡張します。
	 * @param comparator Chainへ拡張するComparator
	 * @return Comparatorと同じ比較を提供するChain
	 */
	public static <T> TotalOrder<T> comparing(java.util.Comparator<T> comparator) {
		return (o1, o2) -> comparator.compare(o1, o2);
	}
}
