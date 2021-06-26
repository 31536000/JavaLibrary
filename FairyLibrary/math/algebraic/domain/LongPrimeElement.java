package math.algebraic.domain;

/**
 * 素元を提供します。
 * これは、{@link PrimeElement}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongPrimeElement extends PrimeElement<Long> {
	@Override
	public default Long getPrimeElement() {
		return getPrimeElementAsLong();
	}
	public long getPrimeElementAsLong();
	public static LongPrimeElement create(final long element) {
		return () -> element;
	}
}
