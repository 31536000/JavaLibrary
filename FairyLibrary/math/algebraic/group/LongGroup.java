package com._31536000.math.algebraic.group;

/**
 * 演算が群であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Group}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongGroup extends Group<Long>, LongMonoid, LongLoop{
	@Override
	default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}
	@Override
	default Long hyper(Long element, int repeat) {
		return hyperAsLong(element, repeat);
	}
	@Override
	default Long hyper(Long element, long repeat) {
		return hyperAsLong(element, repeat);
	}
	@Override
	public default Long subtract(Long left, Long right) {
		return subtractAsLong(left, right);
	}
	/**
	 * 指定された引数のうち、第二引数については逆元を取ってからこの関数を適用します。
	 * この関数の返り値はapplyAsLong(left, inverseAsLong(right))と同じです。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return 関数の結果
	 */
	public default long subtractAsLong(long left, long right) {
		return applyAsLong(left, inverseAsLong(right));
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default long hyperAsLong(long element, int repeat) {
		long ret = identityAsLong();
		if (repeat < 0) {
			repeat = -repeat;
			for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
			return inverseAsLong(ret);
		}
		for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
		return ret;
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default long hyperAsLong(long element, long repeat) {
		long ret = identityAsLong();
		if (repeat < 0) {
			repeat = -repeat;
			for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
			return inverseAsLong(ret);
		}
		for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
		return ret;
	}
}
