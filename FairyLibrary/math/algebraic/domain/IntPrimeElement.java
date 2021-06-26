package math.algebraic.domain;

/**
 * 素元を提供します。
 * これは、{@link PrimeElement}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntPrimeElement extends PrimeElement<Integer> {
	@Override
	public default Integer getPrimeElement() {
		return getPrimeElementAsInt();
	}
	public int getPrimeElementAsInt();
	public static IntPrimeElement create(final int element) {
		return () -> element;
	}
}
