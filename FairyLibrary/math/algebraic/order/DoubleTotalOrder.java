package math.algebraic.order;

import java.util.Comparator;
import util.DoubleComparator;

/**
 * 演算が全順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link TotalOrder}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleTotalOrder extends TotalOrder<Double>, DoublePartialOrder, DoubleConnexity, DoubleComparator{
	@Override
	public default int compare(Double o1, Double o2) {
		return compareAsDouble(o1, o2);
	}
	/**
	 * {@link Comparator}を受け取り、TotalOrderへと拡張します。
	 * @param comparator Chainへ拡張するComparator
	 * @return Comparatorと同じ比較を提供するTotalOrder
	 */
	public static DoubleTotalOrder comparing(DoubleComparator comparator) {
		return (o1, o2) -> comparator.compareAsDouble(o1, o2);
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
		return (o1, o2) -> compareAsDouble(o2, o1);
	}
}
