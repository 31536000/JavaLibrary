package math;

import math.algebraic.domain.LongPrimeElement;

/**
 * 素数を渡すためのクラスです。<br>
 * 中身が確実に素数であることを保証するときに使ってください。
 *
 * @author 31536000
 *
 */
public class LongPrime extends Number implements LongPrimeElement {

	private static final long serialVersionUID = -3536248438247649787L;
	public final long prime;

	/**
	 * 素数を設定します。
	 *
	 * @param prime 素数
	 * @throws IllegalArgumentException 素数以外を渡した時
	 */
	public LongPrime(final long prime) {
		if (!MathUtility.isPrime(prime)) throw new IllegalArgumentException(prime + " is not prime");
		this.prime = prime;
	}

	@Override
	public int intValue() {
		return (int)prime;
	}

	@Override
	public long longValue() {
		return prime;
	}

	@Override
	public float floatValue() {
		return prime;
	}

	@Override
	public double doubleValue() {
		return prime;
	}

	@Override
	public String toString() {
		return String.valueOf(prime);
	}

	@Override
	public int hashCode() {
		return Long.hashCode(prime);
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof LongPrime) return prime == ((LongPrime) o).prime;
		return false;
	}

	@Override
	public long getPrimeElementAsLong() { return prime; }
}
