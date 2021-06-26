package math.algebraic.group;

import java.util.function.LongBinaryOperator;

/**
 * 演算が結合法則を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Associative}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongAssociative extends Associative<Long>, LongBinaryOperator{
	@Override
	public default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}
	@Override
	public default Long hyper(Long element, int repeat) {
		return hyperAsLong(element, repeat);
	}
	@Override
	public default Long hyper(Long element, long repeat) {
		return hyperAsLong(element, repeat);
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default long hyperAsLong(long element, int repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		long ret = element;
		-- repeat;
		for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
		return ret;
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、1以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	public default long hyperAsLong(long element, long repeat) {
		if (repeat < 1) throw new IllegalArgumentException("undefined operation");
		long ret = element;
		-- repeat;
		for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
		return ret;
	}
}
