package math.algebraic.field;

import math.algebraic.domain.Domain;
import math.algebraic.domain.PrimeElement;
import math.algebraic.group.Abelian;
import math.algebraic.group.Group;
import util.collect.HashMultiSet;
import util.collect.MultiSet;

/**
 * 演算が斜体であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface SkewField<T, A extends Abelian<T>, M extends Group<T>> extends Domain<T, A, M>{
	@Override
	public default boolean isDivisible(T left, T right) {
		return !right.equals(additiveIdentity());
	}
	/**
	 * 逆元を返します。
	 * @param element 逆元を求める値
	 * @return 逆元
	 */
	public default T inverse(T element) {
		return getMultiplication().inverse(element);
	}
	@Override
	public default T divide(T left, T right) {
		try {
			return multiply(left, inverse(right));
		} catch (ArithmeticException e) {
			throw new ArithmeticException("divide by Additive Identify");
		}
	}
	/**
	 * leftをrightで割った余りを返します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return left % right
	 */
	public default T reminder(T left, T right) {
		if (isDivisible(left, right)) throw new ArithmeticException("divide by Additive Identify");
		return additiveIdentity();
	}
	/**
	 * gcdを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最大公約数
	 */
	public default T gcd(T left, T right) {
		return multipleIdentity();
	}
	/**
	 * lcmを計算します。
	 * @param left 関数の第一引数
	 * @param right 関数の第二引数
	 * @return leftとrightの最小公倍数
	 */
	public default T lcm(T left, T right) {
		return multipleIdentity();
	}
	/**
	 * 素因数分解した結果を返します。
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public default MultiSet<? extends PrimeElement<T>> getPrimeFactorization(T x) {
		HashMultiSet<PrimeElement<T>> ret = HashMultiSet.create(1);
		ret.add(PrimeElement.create(x));
		return ret;
	}
}
