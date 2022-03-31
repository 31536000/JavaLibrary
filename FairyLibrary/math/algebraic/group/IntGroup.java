package com._31536000.math.algebraic.group;

/**
 * 演算が群であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Group}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntGroup extends Group<Integer>, IntMonoid, IntLoop {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}

	@Override
	public default Integer hyper(Integer element, int repeat) {
		return hyperAsInt(element, repeat);
	}

	@Override
	public default Integer hyper(Integer element, long repeat) {
		return hyperAsInt(element, repeat);
	}

	@Override
	public default Integer subtract(Integer left, Integer right) {
		return subtractAsInt(left, right);
	}

	/**
	 * 指定された引数のうち、第二引数については逆元を取ってからこの関数を適用します。
	 * この関数の返り値はapplyAsInt(left, inverseAsInt(right))と同じです。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return 関数の結果
	 */
	public default int subtractAsInt(int left, int right) {
		return applyAsInt(left, inverseAsInt(right));
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default int hyperAsInt(int element, int repeat) {
		int ret = identityAsInt();
		if (repeat < 0) {
			repeat = -repeat;
			for (int mul = element; repeat > 0; repeat >>= 1, mul = applyAsInt(mul, mul))
				if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
			return inverseAsInt(ret);
		}
		for (int mul = element; repeat > 0; repeat >>= 1, mul = applyAsInt(mul, mul))
			if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
		return ret;
	}

	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default int hyperAsInt(int element, long repeat) {
		int ret = identityAsInt();
		if (repeat < 0) {
			repeat = -repeat;
			for (int mul = element; repeat > 0; repeat >>= 1, mul = applyAsInt(mul, mul))
				if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
			return inverseAsInt(ret);
		}
		for (int mul = element; repeat > 0; repeat >>= 1, mul = applyAsInt(mul, mul))
			if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
		return ret;
	}
}
