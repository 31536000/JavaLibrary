package math.algebraic.group;

/**
 * 演算が群であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Group}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleGroup extends Group<Double>, DoubleMonoid, DoubleLoop{
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
	@Override
	public default Double hyper(Double element, int repeat) {
		return hyperAsDouble(element, repeat);
	}
	@Override
	public default Double hyper(Double element, long repeat) {
		return hyperAsDouble(element, repeat);
	}
	@Override
	public default Double subtract(Double left, Double right) {
		return subtractAsDouble(left, right);
	}
	/**
	 * 指定された引数のうち、第二引数については逆元を取ってからこの関数を適用します。
	 * この関数の返り値はapplyAsDouble(left, inverseAsDouble(right))と同じです。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return 関数の結果
	 */
	public default double subtractAsDouble(double left, double right) {
		return applyAsDouble(left, inverseAsDouble(right));
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default double hyperAsDouble(double element, int repeat) {
		double ret = identityAsDouble();
		if (repeat < 0) {
			repeat = -repeat;
			for (double mul = element;repeat > 0;repeat >>= 1, mul = applyAsDouble(mul, mul)) if ((repeat & 1) != 0) ret = applyAsDouble(ret, mul);
			return inverseAsDouble(ret);
		}
		for (double mul = element;repeat > 0;repeat >>= 1, mul = applyAsDouble(mul, mul)) if ((repeat & 1) != 0) ret = applyAsDouble(ret, mul);
		return ret;
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default double hyperAsDouble(double element, long repeat) {
		double ret = identityAsDouble();
		if (repeat < 0) {
			repeat = -repeat;
			for (double mul = element;repeat > 0;repeat >>= 1, mul = applyAsDouble(mul, mul)) if ((repeat & 1) != 0) ret = applyAsDouble(ret, mul);
			return inverseAsDouble(ret);
		}
		for (double mul = element;repeat > 0;repeat >>= 1, mul = applyAsDouble(mul, mul)) if ((repeat & 1) != 0) ret = applyAsDouble(ret, mul);
		return ret;
	}
}
