package math.algebraic.order;

/**
 * 順序関係を意味する演算を提供します。
 * これは、{@link Order}に対してプリミティブ型特殊化(double向け)を行ったものです。
 *
 * @author 31536000
 *
 */
public interface DoubleOrder extends Order<Double>{
	@Override
	public default int compare(Double o1, Double o2) {
		return compareAsDouble(o1, o2);
	}
	/**
	 * 順序付けのために2つの引数を比較します。
	 * 最初の引数が2番目の引数より小さい場合は負の整数、両方が等しい場合は0、
	 * 最初の引数が2番目の引数より大きい場合は正の整数を返します。
	 * @param o1 比較対象の最初のオブジェクト。
	 * @param o2 比較対象の2番目のオブジェクト。
	 * @return 最初の引数が2番目の引数より小さい場合は負の整数、両方が等しい場合は0、最初の引数が2番目の引数より大きい場合は正の整数。
	 * @exception IncomparableException 二つのオブジェクトが比較不能な場合。
	 */
	public int compareAsDouble(double o1, double o2);
	@Override
	public default boolean isComparable(Double o1, Double o2) {
		return isComparableAsDouble(o1, o2);
	}
	/**
	 * 二つの要素o1とo2が比較不能か判定します。
	 * @param o1 比較対象の最初のオブジェクト。
	 * @param o2 比較対象の2番目のオブジェクト。
	 * @return 比較可能ならばtrue
	 */
	public default boolean isComparableAsDouble(double o1, double o2) {
		try {
			compareAsDouble(o1, o2);
		} catch (IncomparableException e) {
			return false;
		}
		return true;
	}
}
