package math.algebraic.group;


/**
 * 演算がモノイドであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Monoid}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongMonoid extends Monoid<Long>, LongAssociative, LongUnital {
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default long hyperAsLong(long element, int repeat) {
		if (repeat < 0) throw new IllegalArgumentException("undefined operation");
		long ret = identityAsLong();
		for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
		return ret;
	}
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default long hyperAsLong(long element, long repeat) {
		if (repeat < 0) throw new IllegalArgumentException("undefined operation");
		long ret = identityAsLong();
		for (long mul = element;repeat > 0;repeat >>= 1, mul = applyAsLong(mul, mul)) if ((repeat & 1) != 0) ret = applyAsLong(ret, mul);
		return ret;
	}
	@Override
	default Long hyper(Long element, int repeat) {
		return hyperAsLong(element, repeat);
	}
	@Override
	default Long hyper(Long element, long repeat) {
		return hyperAsLong(element, repeat);
	}
}
