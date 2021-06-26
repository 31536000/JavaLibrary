package math.algebraic.order;

import util.LongComparator;

/**
 * 演算が全順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link TotalOrder}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongTotalOrder extends TotalOrder<Long>, LongPartialOrder, LongConnexity, LongComparator{
	@Override
	default int compare(Long o1, Long o2) {
		return compareAsLong(o1, o2);
	}
	/**
	 * {@link LongComparator}を受け取り、TotalOrderへと拡張します。
	 * @param comparator TotalOrderへ拡張するComparator
	 * @return Comparatorと同じ比較を提供するTotalOrder
	 */
	public static LongTotalOrder comparing(LongComparator comparator) {
		return (o1, o2) -> comparator.compareAsLong(o1, o2);
	}
	/**
	 * 自然な順序で比較するTotalOrderを返します。
	 * これは、comparingの引数にLong::compareを渡すのと同じです。
	 * @return 自然順序で比較を行うChain
	 */
	public static LongTotalOrder naturalOrder() {
		return (o1, o2) -> Long.compare(o1, o2);
	}
	/**
	 * 自然な順序の逆で比較するTotalOrderを返します。
	 * @return 逆順序で比較を行うTotalOrder
	 */
	public static LongTotalOrder reverseOrder() {
		return (o1, o2) -> Long.compare(o2, o1);
	}
	@Override
	public default LongTotalOrder reversed() {
		return (o1, o2) -> compareAsLong(o2, o1);
	}
}
