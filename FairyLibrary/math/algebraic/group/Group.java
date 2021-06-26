package math.algebraic.group;

/**
 * 演算が群であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Group<T> extends Monoid<T>, Loop<T>{
	/**
	 * 指定された引数のうち、第二引数については逆元を取ってからこの関数を適用します。
	 * この関数の返り値はapply(left, inverse(right))と同じです。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return 関数の結果
	 */
	public default T subtract(T left, T right) {
		return apply(left, inverse(right));
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default T hyper(T element, int repeat) {
		T ret = identity();
		if (repeat < 0) {
			repeat = -repeat;
			for (T mul = element;repeat > 0;repeat >>= 1, mul = apply(mul, mul)) if ((repeat & 1) != 0) ret = apply(ret, mul);
			return inverse(ret);
		}
		for (T mul = element;repeat > 0;repeat >>= 1, mul = apply(mul, mul)) if ((repeat & 1) != 0) ret = apply(ret, mul);
		return ret;
	}
}
