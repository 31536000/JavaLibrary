package math;

import math.algebraic.domain.LongEuclideanDomain;
import math.algebraic.domain.LongPrimeElement;
import math.algebraic.group.LongAbelian;
import math.algebraic.group.LongCommutativeMonoid;
import util.collect.MultiSet;

public class MathUtil {
	public static class Barrett {
		private final int mod;
		private final long h, l;
		private final long MAX = 1L << 62;
		private final int MASK = (1 << 31) - 1;

		Barrett(final int mod) {
			this.mod = mod;
			final long t = MAX / mod;
			h = t >>> 31;
			l = t & MASK;
		}

		int reduce(final long x) {
			final long xh = x >>> 31, xl = x & MASK;
			long z = xl * l;
			z = xl * h + xh * l + (z >>> 31);
			z = xh * h + (z >>> 31);
			final int ret = (int) (x - z * mod);
			return ret >= mod ? ret - mod : ret;
		}
	}

	public static class BarrettSmall {
		private final int mod;
		final long t;

		BarrettSmall(final int mod) {
			this.mod = mod;
			t = (1L << 42) / mod;
		}

		int reduce(long x) {
			long q = x * t >> 42;
			x -= q * mod;
			return (int) (x >= mod ? x - mod : x);
		}
	}

	private static long safe_mod(long x, final long m) {
		x %= m;
		if (x < 0) x += m;
		return x;
	}

	private static long[] inv_gcd(long a, final long b) {
		a = safe_mod(a, b);
		if (a == 0) return new long[] { b, 0 };

		long s = b, t = a;
		long m0 = 0, m1 = 1;
		while (t > 0) {
			final long u = s / t;
			s -= t * u;
			m0 -= m1 * u;
			long tmp = s;
			s = t;
			t = tmp;
			tmp = m0;
			m0 = m1;
			m1 = tmp;
		}
		if (m0 < 0) m0 += b / s;
		return new long[] { s, m0 };
	}

	public static int pow(long n, long m, final int mod) {
		assert m >= 0 && mod >= 1;
		if (mod == 1) return 0;
		return pow(n, m, new Barrett(mod));
	}

	public static int pow(long n, long m, Barrett mod) {
		assert m >= 0;
		long ans = 1, num = n;
		while (m != 0) {
			if ((m & 1) != 0) ans = mod.reduce(ans * num);
			m >>>= 1;
			num = mod.reduce(num * num);
		}
		return (int) ans;
	}

	public static int pow998_244_353(long n, long m) {
		assert m >= 0;
		long ans = 1, num = n;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * num % 998_244_353;
			m >>>= 1;
			num = num * num % 998_244_353;
		}
		return (int) ans;
	}

	public static int pow167_772_161(long n, long m) {
		assert m >= 0;
		long ans = 1, num = n;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * num % 167_772_161;
			m >>>= 1;
			num = num * num % 167_772_161;
		}
		return (int) ans;
	}

	public static int pow469_762_049(long n, long m) {
		assert m >= 0;
		long ans = 1, num = n;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * num % 469_762_049;
			m >>>= 1;
			num = num * num % 469_762_049;
		}
		return (int) ans;
	}

	public static int pow1_000_000_007(long n, long m) {
		assert m >= 0;
		long ans = 1, num = n;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * num % 1_000_000_007;
			m >>>= 1;
			num = num * num % 1_000_000_007;
		}
		return (int) ans;
	}

	public static int pow(long n, long m, BarrettSmall mod) {
		assert m >= 0;
		long ans = 1, num = n;
		while (m != 0) {
			if ((m & 1) != 0) ans = mod.reduce(ans * num);
			m >>>= 1;
			num = mod.reduce(num * num);
		}
		return (int) ans;
	}

	public static long[] crt(final long[] r, final long[] m) {
		assert r.length == m.length;
		final int n = r.length;

		long r0 = 0, m0 = 1;
		for (int i = 0; i < n; i++) {
			assert 1 <= m[i];
			long r1 = safe_mod(r[i], m[i]), m1 = m[i];
			if (m0 < m1) {
				long tmp = r0;
				r0 = r1;
				r1 = tmp;
				tmp = m0;
				m0 = m1;
				m1 = tmp;
			}
			if (m0 % m1 == 0) {
				if (r0 % m1 != r1) return new long[] { 0, 0 };
				continue;
			}

			final long[] ig = inv_gcd(m0, m1);
			final long g = ig[0], im = ig[1];

			final long u1 = m1 / g;
			if ((r1 - r0) % g != 0) return new long[] { 0, 0 };

			final long x = (r1 - r0) / g % u1 * im % u1;

			r0 += x * m0;
			m0 *= u1;
			if (r0 < 0) r0 += m0;
			// System.err.printf("%d %d\n", r0, m0);
		}
		return new long[] { r0, m0 };
	}

	public static long floor_sum(final long n, final long m, long a, long b) {
		long ans = 0;
		if (a >= m) {
			ans += (n - 1) * n * (a / m) / 2;
			a %= m;
		}
		if (b >= m) {
			ans += n * (b / m);
			b %= m;
		}

		final long y_max = (a * n + b) / m;
		final long x_max = y_max * m - b;
		if (y_max == 0) return ans;
		ans += (n - (x_max + a - 1) / a) * y_max;
		ans += floor_sum(y_max, a, m, (a - x_max % a) % a);
		return ans;
	}

	/**
	 * aとbの最大公約数を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return 最大公約数
	 */
	public static int gcd(int a, int b) {
		while (a != 0) if ((b %= a) != 0) a %= b; else return a;
		return b;
	}

	/**
	 * 配列全ての値の最大公約数を返します。
	 * @param array 配列
	 * @return 最大公約数
	 */
	public static int gcd(int... array) {
		int ret = array[0];
		for (int i = 1;i < array.length;++ i) ret = gcd(ret, array[i]);
		return ret;
	}

	/**
	 * aとbの最大公約数を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return 最大公約数
	 */
	public static long gcd(long a, long b) {
		while (a != 0) if ((b %= a) != 0) a %= b; else return a;
		return b;
	}

	/**
	 * 配列全ての値の最大公約数を返します。
	 * @param array 配列
	 * @return 最大公約数
	 */
	public static long gcd(long... array) {
		long ret = array[0];
		for (int i = 1;i < array.length;++ i) ret = gcd(ret, array[i]);
		return ret;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return 最小公倍数
	 */
	public static long lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return 最小公倍数
	 */
	public static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @param array 配列
	 * @return 最小公倍数
	 */
	public static long lcm(int... array) {
		long ret = array[0];
		for (int i = 1;i < array.length;++ i) ret = lcm(ret, array[i]);
		return ret;
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
		for (int i = 1;i < array.length;++ i) ret = min(ret, array[i]);
		return ret;
	}

	/**
	 * aとbのうち、小さい方を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち小さい方の値
	 */
	public static long min(long a, long b) {
		return a < b ? a : b;
	}

	/**
	 * 配列の中で最小の値を返します。
	 * @param array 配列
	 * @return 配列の中で最小の値
	 */
	public static long min(long... array) {
		long ret = array[0];
		for (int i = 1;i < array.length;++ i) ret = min(ret, array[i]);
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
		for (int i = 1;i < array.length;++ i) ret = max(ret, array[i]);
		return ret;
	}

	/**
	 * aとbのうち、大きい方を返します。
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち大きい方の値
	 */
	public static long max(long a, long b) {
		return a > b ? a : b;
	}

	/**
	 * 配列の中で最大の値を返します。
	 * @param array 配列
	 * @return 配列の中で最大の値
	 */
	public static long max(long... array) {
		long ret = array[0];
		for (int i = 1;i < array.length;++ i) ret = max(ret, array[i]);
		return ret;
	}

	/**
	 * 配列の値の合計を返します。
	 * @param array 配列
	 * @return 配列の値の総和
	 */
	public static long sum(int... array) {
		long ret = 0;
		for (int i : array) ret += i;
		return ret;
	}

	/**
	 * 配列の値の合計を返します。
	 * @param array 配列
	 * @return 配列の値の総和
	 */
	public static long sum(long... array) {
		long ret = 0;
		for (long i : array) ret += i;
		return ret;
	}

	/**
	 * 二項係数を列挙した配列を返します。
	 * @param l 左辺
	 * @param r 右辺
	 * @return 0≦i≦l及び0≦j≦rを満たす全てのi, jに対してi choose jを求めた配列
	 */
	public static long[][] combination(int l, int r) {
		long[][] pascal = new long[l + 1][r + 1];
		pascal[0][0] = 1;
		for (int i = 1;i <= l;++ i) {
			pascal[i][0] = 1;
			for (int j = 1;j <= r;++ j) {
				pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
			}
		}
		return pascal;
	}

	private static class AdditionLong implements LongAbelian {
		@Override
		public long applyAsLong(long left, long right) {
			return left + right;
		}
		@Override
		public long identityAsLong() {
			return 0;
		}
		@Override
		public long inverseAsLong(long element) {
			return -element;
		}
	}
	private static class MultiplicationLong implements LongCommutativeMonoid {
		@Override
		public long applyAsLong(long left, long right) {
			return left * right;
		}
		@Override
		public long identityAsLong() {
			return 1;
		}
	}
	private static class AlgebraicLong implements LongEuclideanDomain<LongAbelian, LongCommutativeMonoid> {

		private static LongAbelian addition = new AdditionLong();
		private static LongCommutativeMonoid multiplication = new MultiplicationLong();
		@Override
		public LongAbelian getAddition() {return addition;}

		@Override
		public LongCommutativeMonoid getMultiplication() {return multiplication;}

		@Override
		public long gcdAsLong(long left, long right) {
			return MathUtil.gcd(left, right);
		}

		@Override
		public long lcmAsLong(long left, long right) {
			return MathUtil.lcm(left, right);
		}

		@Override
		public long divideAsLong(long left, long right) {
			return left / right;
		}

		@Override
		public MultiSet<? extends LongPrimeElement> getPrimeFactorizationAsLong(long x) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public long reminderAsLong(long left, long right) {
			return left % right;
		}
	}

	private static AlgebraicLong algebraicLong;
	public static LongEuclideanDomain<LongAbelian, LongCommutativeMonoid> getLongOperator() {
		return algebraicLong == null ? algebraicLong = new AlgebraicLong() : algebraicLong;
	}
}
