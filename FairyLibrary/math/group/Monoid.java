package math.group;


/**
 * 演算がモノイドであることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Monoid<T> extends Associative<T>, Unit<T> {
	/**
	 * repeat個のelementを順次演算した値を返します。
	 * @param element 演算する値
	 * @param repeat 繰り返す回数、0以上であること
	 * @return 演算を+として、element + element + ... + elementと演算をrepeat-1回行った値
	 */
	@Override
	public default T hyper(T element, int repeat) {
		if (repeat < 0) throw new IllegalArgumentException("undefined operation");
		T ret = unit();
		for (T mul = element;repeat > 0;repeat >>= 1, mul = apply(mul, mul)) if ((repeat & 1) != 0) ret = apply(ret, mul);
		return ret;
	}
}
