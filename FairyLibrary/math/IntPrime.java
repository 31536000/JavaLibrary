package math;

import math.algebraic.domain.IntPrimeElement;

/**
 * 素数を渡すためのクラスです。<br>
 * 中身が確実に素数であることを保証するときに使ってください。
 *
 * @author 31536000
 *
 */
public class IntPrime extends Number implements IntPrimeElement {

	private static final long serialVersionUID = 6704437279075128425L;
	public final int prime;

	/**
	 * 素数を設定します。
	 *
	 * @param prime 素数
	 * @throws IllegalArgumentException 素数以外を渡した時
	 */
	public IntPrime(final int prime) {
		if (!MathUtility.isPrime(prime)) throw new IllegalArgumentException(prime + " is not prime");
		this.prime = prime;
	}

	@Override
	public int intValue() {
		return prime;
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
		return prime;
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof IntPrime) return prime == ((IntPrime) o).prime;
		return false;
	}

	@Override
	public int getPrimeElementAsInt() { return prime; }
}
