package math.algebraic.domain;

/**
 * 素元を提供します。
 * @author 31536000
 *
 * @param <T> 演算の型
 */
public interface PrimeElement<T> {
	public T getPrimeElement();
	public static <T> PrimeElement<T> create(final T element) {
		return () -> element;
	}
}
