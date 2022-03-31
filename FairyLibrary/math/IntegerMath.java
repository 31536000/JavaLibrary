package com._31536000.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.ring.CommutativeRing;

/**
 * int型に対する様々な演算を提供します。
 * @author 31536000
 *
 */
public class IntegerMath {
	private IntegerMath() {
		throw new AssertionError();
	}

	/**
	 * aとbのうち、小さい方を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち小さい方の値
	 */
	public static int min(int a, int b) {
		return a < b ? a : b;
	}

	/**
	 * 配列の中で最小の値を返します。
	 * @param array 配列
	 * @return 配列の中で最小の値
	 */
	public static int min(int... array) {
		int ret = array[0];
		for (int i = 1; i < array.length; ++i) ret = min(ret, array[i]);
		return ret;
	}

	/**
	 * aとbのうち、大きい方を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち大きい方の値
	 */
	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	/**
	 * 配列の中で最大の値を返します。
	 * @param array 配列
	 * @return 配列の中で最大の値
	 */
	public static int max(int... array) {
		int ret = array[0];
		for (int i = 1; i < array.length; ++i) ret = max(ret, array[i]);
		return ret;
	}

	/**
	 * aとbの最大公約数を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return 最大公約数
	 */
	public static int gcd(int a, int b) {
		while (a != 0) if ((b %= a) != 0) a %= b;
		else return a;
		return b;
	}

	/**
	 * 配列全ての値の最大公約数を返します。
	 * @param array 配列
	 * @return 最大公約数
	 */
	public static int gcd(int... array) {
		int ret = array[0];
		for (int i = 1; i < array.length; ++i) ret = gcd(ret, array[i]);
		return ret;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return 最小公倍数
	 */
	public static int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @param array 配列
	 * @return 最小公倍数
	 */
	public static int lcm(int... array) {
		int ret = array[0];
		for (int i = 1; i < array.length; ++i) ret = lcm(ret, array[i]);
		return ret;
	}

	/**
	 * 素因数分解した結果を返します。
	 * @param n 素因数分解したい値
	 * @return 素因数分解の結果
	 */
	public static Map<Integer, Integer> primeFactor(int n) {
		Map<Integer, Integer> ret = new TreeMap<>();
		for (int i = 2, l = root(n, 2, java.math.RoundingMode.UP); i <= l; ++i) {
			while (n % i == 0) {
				ret.compute(i, (k, v) -> v == null ? 1 : v + 1);
				n /= i;
			}
		}
		if (n != 1) ret.put(n, 1);
		return ret;
	}

	/**
	 * 約数を返します。
	 * @param n 約数を知りたい値
	 * @return nの約数
	 */
	public static List<Integer> divisor(int n) {
		List<Integer> ret = new ArrayList<>();
		for (int i = 1, l = root(n, 2, java.math.RoundingMode.UP); i <= l; ++i) {
			if (n % i == 0) {
				ret.add(i);
				ret.add(n / i);
			}
		}
		{
			int last = ret.size() - 1;
			if (last > 0 && ret.get(last) == ret.get(last - 1)) ret.remove(last);
		}
		Collections.sort(ret);
		return ret;
	}

	/**
	 * aのb乗根を返します。
	 * @param a 整数
	 * @param b 整数
	 * @param mode 端数の処理方法
	 * @return aのb乗根
	 * @throws ArithmeticException 不正引数
	 */
	public static int root(int a, int b, java.math.RoundingMode mode) {

	}

	/**
	 * aのb乗を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return aのb乗
	 */
	public static int pow(int a, int b) {
		int ans = 1;
		for (int mul = a; b > 0; b >>= 1, mul *= mul) if ((b & 1) != 0) ans *= mul;
		return ans;
	}

	/**
	 * aのb乗をmodを法として計算したものを返します。
	 * @param a 整数
	 * @param b 整数
	 * @param mod 法
	 * @return aのb乗をmodを法として計算したもの
	 */
	public static int pow(int a, int b, int mod) {
		a %= mod;
		if (a < 0) a += mod;
		b %= mod;
		if (b < 0) b += mod - 1;
		long ans = 1;
		for (long mul = a; b > 0; b >>= 1, mul = mul * mul % mod) if ((b & 1) != 0) ans = ans * mul % mod;
		return (int) ans;
	}

	/**
	 * aをbで割った値を、端数に関してはRoundingModeに従って計算します。
	 * @param a 整数
	 * @param b 整数
	 * @param mode 端数の処理方法
	 * @return a/b
	 * @throws ArithmeticException 不正引数
	 */
	public static int divide(int a, int b, java.math.RoundingMode mode) {
		int t, p;
		switch (mode) {
			case UP:
				t = (a >>> 31 << 1) - 1;
				return (a - t) / b + t;
			case DOWN:
				return a / b;
			case CEILING:
				t = ~a >>> 31;
				return (a - t) / b + t;
			case FLOOR:
				return Math.floorDiv(a, b);
			case HALF_UP:
				t = a / b;
				p = a - t * b << 1;
				if (b <= p) ++t;
				return t;
			case HALF_EVEN:
				t = a / b;
				p = a - t * b << 1;
				if (b < p) ++t;
				else if (b == p && (t & 1) != 0) ++t;
				return t;
			case HALF_DOWN:
				t = a / b;
				p = a - t * b << 1;
				if (b < p) ++t;
				return t;
			case UNNECESSARY:
				if (a % b != 0) throw new ArithmeticException();
				return a / b;
		}
		throw new AssertionError("undefined mode");
	}

	/*
	public static boolean isPerfectPower(int n, int pow) { // TODO 未実装
		if (n == 1) return true;
		if (pow >= 32) return false;
		long max = 1L << divide(32, pow, RoundingMode.UP);

	}*/

	public static int sum(int... array) {
		int ret = 0;
		for (int i : array) ret += i;
		return ret;
	}

	public static int multiply(int... array) {
		int ret = 0;
		for (int i : array) ret *= i;
		return ret;
	}

	public static int nextMultiple(int a, int b) {
		return divide(a, b, java.math.RoundingMode.CEILING) * b;
	}

	public static int mod(int a, int mod) {
		return (a %= mod) < 0 ? a + mod : a;
	}

	public enum Addition implements Abelian<Integer> {
		INSTANCE;

		@Override
		public Integer apply(Integer left, Integer right) {
			return left + right;
		}

		@Override
		public Integer identity() {
			return 0;
		}

		@Override
		public Integer inverse(Integer element) {
			return -element;
		}

		@Override
		public Integer hyper(Integer element, int repeat) {
			return element * repeat;
		}
	}

	public enum Multiplication implements CommutativeMonoid<Integer> {
		INSTANCE;

		@Override
		public Integer apply(Integer left, Integer right) {
			return left * right;
		}

		@Override
		public Integer identity() {
			return 1;
		}

		@Override
		public Integer hyper(Integer element, int repeat) {
			return IntegerMath.pow(element, repeat);
		}
	}

	public enum OR implements CommutativeMonoid<Integer> {
		INSTANCE;

		@Override
		public Integer apply(Integer left, Integer right) {
			return left | right;
		}

		@Override
		public Integer identity() {
			return 0;
		}

		@Override
		public Integer hyper(Integer element, int repeat) {
			return element;
		}
	}

	public enum AND implements CommutativeMonoid<Integer> {
		INSTANCE;

		@Override
		public Integer apply(Integer left, Integer right) {
			return left & right;
		}

		@Override
		public Integer identity() {
			return 1;
		}

		@Override
		public Integer hyper(Integer element, int repeat) {
			return element;
		}
	}

	public enum XOR implements Abelian<Integer> {
		INSTANCE;

		@Override
		public Integer apply(Integer left, Integer right) {
			return left ^ right;
		}

		@Override
		public Integer identity() {
			return 0;
		}

		@Override
		public Integer inverse(Integer element) {
			return element;
		}

		@Override
		public Integer hyper(Integer element, int repeat) {
			return (repeat & 1) == 0 ? 0 : element;
		}
	}

	public static Abelian<Integer> getAddition() { return Addition.INSTANCE; }

	public static CommutativeMonoid<Integer> getMultiplication() { return Multiplication.INSTANCE; }

	public static CommutativeRing<Integer, Abelian<Integer>, CommutativeMonoid<Integer>> getAlgebraic() {
		return new CommutativeRing<Integer, Abelian<Integer>, CommutativeMonoid<Integer>>() {

			@Override
			public Abelian<Integer> getAddition() { return IntegerMath.getAddition(); }

			@Override
			public CommutativeMonoid<Integer> getMultiplication() { return IntegerMath.getMultiplication(); }

		};
	}

	public static CommutativeMonoid<Integer> getOR() { return OR.INSTANCE; }

	public static CommutativeMonoid<Integer> getAND() { return AND.INSTANCE; }

	public static Abelian<Integer> getXOR() { return XOR.INSTANCE; }
}
