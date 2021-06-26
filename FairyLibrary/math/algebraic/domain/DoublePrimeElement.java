package math.algebraic.domain;

/**
 * 素元を提供します。
 * これは、{@link PrimeElement}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoublePrimeElement extends PrimeElement<Double> {
	@Override
	public default Double getPrimeElement() {
		return getPrimeElementAsDouble();
	}
	public double getPrimeElementAsDouble();
	public static DoublePrimeElement create(final double element) {
		return () -> element;
	}
}
