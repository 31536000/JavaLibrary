package math.algebraic.group;

import java.util.function.IntBinaryOperator;

/**
 * 演算が結合法則を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Associative}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntAssociative extends Associative<Integer>, IntBinaryOperator{
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
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default int hyperAsInt(int element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		int ret = element;
		-- repeat;
		for (int mul = element;repeat > 0;repeat >>= 1, mul = applyAsInt(mul, mul)) if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
		return ret;
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default int hyperAsInt(int element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		int ret = element;
		-- repeat;
		for (int mul = element;repeat > 0;repeat >>= 1, mul = applyAsInt(mul, mul)) if ((repeat & 1) != 0) ret = applyAsInt(ret, mul);
		return ret;
	}
}
