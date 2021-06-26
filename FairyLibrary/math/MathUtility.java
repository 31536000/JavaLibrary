package math;

import math.algebraic.domain.IntEuclideanDomain;
import math.algebraic.domain.IntPrimeElement;
import math.algebraic.domain.LongEuclideanDomain;
import math.algebraic.domain.LongPrimeElement;
import math.algebraic.group.IntAbelian;
import math.algebraic.group.IntCommutativeMonoid;
import math.algebraic.group.LongAbelian;
import math.algebraic.group.LongCommutativeMonoid;
import util.collect.HashMultiSet;
import util.collect.MultiSet;

/**
 * このクラスは、数学的な様々な演算を提供します。
 * @author 31536000
 *
 */
public class MathUtility {
	/**
	 * modで割った余りを高速に求めるクラスです。
	 * @author 31536000
	 *
	 */
	public static class Barrett {
		private final int mod;
		private final long h, l;
		private final long MAX = 1L << 62;
		private final int MASK = (1 << 31) - 1;

		/**
		 * 法を保持します。
		 * @complexity O(1)
		 * @param mod 法
		 */
		Barrett(final int mod) {
			this.mod = mod;
			final long t = MAX / mod;
			h = t >>> 31;
			l = t & MASK;
		}

		/**
		 * xをmodで割った余りを求めます。
		 * @complexity O(1)
		 * @param x 割られる値
		 * @return x % mod
		 */
		int reduce(final long x) {
			final long xh = x >>> 31, xl = x & MASK;
			long z = xl * l;
			z = xl * h + xh * l + (z >>> 31);
			z = xh * h + (z >>> 31);
			final int ret = (int) (x - z * mod);
			return ret >= mod ? ret - mod : ret;
		}
	}

	/**
	 * modで割った余りを高速に求めるクラスです。
	 * このクラスはmod &lt; 2^21=2194304のときにしか動作しません。
	 * @author 31536000
	 *
	 */
	public static class BarrettSmall {
		private final int mod;
		final long t;

		/**
		 * 法を保持します。
		 * @complexity O(1)
		 * @param mod 法
		 */
		BarrettSmall(final int mod) {
			this.mod = mod;
			t = (1L << 42) / mod;
		}

		/**
		 * xをmodで割った余りを求めます。
		 * @complexity O(1)
		 * @param x 割られる値
		 * @return x % mod
		 */
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

	/**
	 * nのm乗をmodで割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @param mod 法
	 * @return modを法として、nのm乗
	 */
	public static int pow(long n, long m, final int mod) {
		if (mod == 1) return 0;
		return pow(n, m, new Barrett(mod));
	}

	/**
	 * nのm乗をmodで割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @param mod 法
	 * @return modを法として、nのm乗
	 */
	public static int pow(long n, long m, Barrett mod) {
		n = mod.reduce(n);
		int ans = 1;
		while (m != 0) {
			if ((m & 1) != 0) ans = mod.reduce(ans * n);
			m >>>= 1;
			n = mod.reduce(n * n);
		}
		return ans;
	}

	/**
	 * nのm乗をmodで割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @param mod 法
	 * @return modを法として、nのm乗
	 */
	public static long pow(long n, long m, long mod) {
		long ans = 1;
		while (m != 0) {
			if ((m & 1) != 0) ans = multiply(ans, n, mod);
			m >>>= 1;
			n = multiply(n, n, mod);
		}
		return ans;
	}

	/**
	 * n * m % mod を求めます。
	 * @complexity O(log m)
	 * @param n 左辺
	 * @param m 右辺
	 * @param mod 法
	 * @return n * m % mod
	 */
	public static long multiply(long n, long m, long mod) {
		long ans = 0;
		if (mod > 1L << 62) {
			if (n >= mod) n -= mod;
			while(m != 0) {
				if ((m & 1) != 0) {
					ans += n;
					if (ans < 0 || ans >= mod) ans -= mod;
				}
				m >>>= 1;
				n <<= 1;
				if (n < 0 || n >= mod) n -= mod;
			}
			return ans;
		} else {
			n %= mod;
			while(m != 0) {
				if ((m & 1) != 0) {
					ans += n;
					if (ans >= mod) ans -= mod;
				}
				m >>>= 1;
				n <<= 1;
				if (n >= mod) n -= mod;
			}
		}
		return ans;
	}

	/**
	 * nのm乗を998_244_353で割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @return 998_244_353を法として、nのm乗
	 */
	public static int pow998_244_353(long n, long m) {
		n %= 998_244_353;
		long ans = 1;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * n % 998_244_353;
			m >>>= 1;
			n = n * n % 998_244_353;
		}
		return (int) ans;
	}

	/**
	 * nのm乗を167_772_161で割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @return 167_772_161を法として、nのm乗
	 */
	public static int pow167_772_161(long n, long m) {
		n %= 167_772_161;
		long ans = 1;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * n % 167_772_161;
			m >>>= 1;
			n = n * n % 167_772_161;
		}
		return (int) ans;
	}

	/**
	 * nのm乗を469_762_049で割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @return 469_762_049を法として、nのm乗
	 */
	public static int pow469_762_049(long n, long m) {
		n %= 469_762_049;
		long ans = 1;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * n % 469_762_049;
			m >>>= 1;
			n = n * n % 469_762_049;
		}
		return (int) ans;
	}

	/**
	 * nのm乗を1_000_000_007で割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @return 1_000_000_007を法として、nのm乗
	 */
	public static int pow1_000_000_007(long n, long m) {
		n %= 1_000_000_007;
		long ans = 1;
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * n % 1_000_000_007;
			m >>>= 1;
			n = n * n % 1_000_000_007;
		}
		return (int) ans;
	}

	/**
	 * nのm乗をmodで割った余りを求めます。
	 * @complexity O(log m)
	 * @param n 底
	 * @param m 冪指数
	 * @param mod 法
	 * @return modを法として、nのm乗
	 */
	public static int pow(long n, long m, BarrettSmall mod) {
		n = mod.reduce(n);
		int ans = 1;
		while (m != 0) {
			if ((m & 1) != 0) ans = mod.reduce(ans * n);
			m >>>= 1;
			n = mod.reduce(n * n);
		}
		return ans;
	}

	/**
	 * 中国剰余定理を用いて、x ≡ r[i] mod m[i] となるような最小のxを求めます。
	 * このようなxはy=lcm(m[i])を用いてx mod yと表せるので、{x, y}を返します。
	 * x ≡ r[i] mod m[i]を満たすxが存在しない場合は{0, 0}が返ります。
	 * @complexity 配列の長さをnとして、 O(n log(lcm m))
	 * @param r 法で割った余り
	 * @param m 法
	 * @return x ≡ r[i] mod m[i]を満たす最小のxと、その巡回群の大きさ
	 */
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
		}
		return new long[] { r0, m0 };
	}

	/**
	 * Σ[i=0, n-1] ⌊(ai + b)/m⌋ を求めます。
	 * @complexity O(log m)
	 * @param n
	 * @param m
	 * @param a
	 * @param b
	 * @return Σ[i=0, n-1] ⌊(ai + b)/m⌋
	 */
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
	 * @complexity O(log(min(a, b)))
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
	 * @complexity n=array.lengthとして、 O(n + log(min(array)))
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
	 * @complexity O(log(min(a, b)))
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
	 * @complexity n=array.lengthとして、 O(n + log(min(array)))
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
	 * @complexity O(log(min(a, b)))
	 * @param a 整数
	 * @param b 整数
	 * @return 最小公倍数
	 */
	public static long lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @complexity O(log(min(a, b)))
	 * @param a 整数
	 * @param b 整数
	 * @return 最小公倍数
	 */
	public static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @complexity n=array.lengthとして、 O(n + log(min(array)))
	 * @param array 配列
	 * @return 最小公倍数
	 */
	public static long lcm(int... array) {
		long ret = array[0];
		for (int i = 1;i < array.length;++ i) ret = lcm(ret, array[i]);
		return ret;
	}

	/**
	 * 配列全ての値の最小公倍数を返します。
	 * @complexity n=array.lengthとして、 O(n + log(min(array)))
	 * @param array 配列
	 * @return 最小公倍数
	 */
	public static long lcm(long... array) {
		long ret = array[0];
		for (int i = 1;i < array.length;++ i) ret = lcm(ret, array[i]);
		return ret;
	}

	/**
	 * aとbのうち、小さい方を返します。
	 * @complexity O(1)
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち小さい方の値
	 */
	public static int min(int a, int b) {
		return a < b ? a : b;
	}

	/**
	 * 配列の中で最小の値を返します。
	 * @complexity O(array.length)
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
	 * @complexity O(1)
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち小さい方の値
	 */
	public static long min(long a, long b) {
		return a < b ? a : b;
	}

	/**
	 * 配列の中で最小の値を返します。
	 * @complexity O(array.length)
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
	 * @complexity O(1)
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち大きい方の値
	 */
	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	/**
	 * 配列の中で最大の値を返します。
	 * @complexity O(array.length)
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
	 * @complexity O(1)
	 * @param a 整数
	 * @param b 整数
	 * @return aとbのうち大きい方の値
	 */
	public static long max(long a, long b) {
		return a > b ? a : b;
	}

	/**
	 * 配列の中で最大の値を返します。
	 * @complexity O(array.length)
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
	 * @complexity O(array.length)
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
	 * @complexity O(array.length)
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
	 * @complexity O(lr)
	 * @param l 左辺
	 * @param r 右辺
	 * @return 0≦i≦l及び0≦j≦rを満たす全てのi, jに対してi choose jを求めた配列
	 */
	public static long[][] combination(int l, int r) {
		long[][] pascal = new long[l + 1][r + 1];
		pascal[0][0] = 1;
		for (int i = 1;i <= l;++ i) {
			pascal[i][0] = 1;
			for (int j = 1;j <= r;++ j) pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
		}
		return pascal;
	}

	private static boolean isSPRP(Barrett n, int a) {
		int d = n.mod - 1, s = Integer.numberOfTrailingZeros(d);
		d >>= s;
		int cur = 1, pw = d;
		do {
			if ((pw & 1) != 0) cur = n.reduce((long)cur * a);
			a = n.reduce((long)a * a);
			pw >>= 1;
		} while(pw != 0);
		if (cur == 1) return true;
		while(s --> 0) {
			if (cur == n.mod - 1) return true;
			cur = n.reduce((long)cur * cur);
		}
		return false;
	}

	private static boolean isSPRP(long n, long a) {
		long d = n - 1;
		int s = Long.numberOfTrailingZeros(d);
		d >>= s;
		long cur = 1, pw = d;
		do {
			if ((pw & 1) != 0) cur = multiply(cur, a, n);
			a = multiply(a, a, n);
			pw >>= 1;
		} while(pw != 0);
		if (cur == 1) return true;
		while(s --> 0) {
			if (cur == n - 1) return true;
			cur = multiply(cur, cur, n);
		}
		return false;
	}

	private static final int bases[] = {15591, 2018, 166, 7429, 8064, 16045, 10503, 4399, 1949, 1295, 2776, 3620, 560, 3128, 5212, 2657, 2300, 2021, 4652, 1471, 9336, 4018, 2398, 20462, 10277, 8028, 2213, 6219, 620, 3763, 4852, 5012, 3185, 1333, 6227, 5298, 1074, 2391, 5113, 7061, 803, 1269, 3875, 422, 751, 580, 4729, 10239, 746, 2951, 556, 2206, 3778, 481, 1522, 3476, 481, 2487, 3266, 5633, 488, 3373, 6441, 3344, 17, 15105, 1490, 4154, 2036, 1882, 1813, 467, 3307, 14042, 6371, 658, 1005, 903, 737, 1887, 7447, 1888, 2848, 1784, 7559, 3400, 951, 13969, 4304, 177, 41, 19875, 3110, 13221, 8726, 571, 7043, 6943, 1199, 352, 6435, 165, 1169, 3315, 978, 233, 3003, 2562, 2994, 10587, 10030, 2377, 1902, 5354, 4447, 1555, 263, 27027, 2283, 305, 669, 1912, 601, 6186, 429, 1930, 14873, 1784, 1661, 524, 3577, 236, 2360, 6146, 2850, 55637, 1753, 4178, 8466, 222, 2579, 2743, 2031, 2226, 2276, 374, 2132, 813, 23788, 1610, 4422, 5159, 1725, 3597, 3366, 14336, 579, 165, 1375, 10018, 12616, 9816, 1371, 536, 1867, 10864, 857, 2206, 5788, 434, 8085, 17618, 727, 3639, 1595, 4944, 2129, 2029, 8195, 8344, 6232, 9183, 8126, 1870, 3296, 7455, 8947, 25017, 541, 19115, 368, 566, 5674, 411, 522, 1027, 8215, 2050, 6544, 10049, 614, 774, 2333, 3007, 35201, 4706, 1152, 1785, 1028, 1540, 3743, 493, 4474, 2521, 26845, 8354, 864, 18915, 5465, 2447, 42, 4511, 1660, 166, 1249, 6259, 2553, 304, 272, 7286, 73, 6554, 899, 2816, 5197, 13330, 7054, 2818, 3199, 811, 922, 350, 7514, 4452, 3449, 2663, 4708, 418, 1621, 1171, 3471, 88, 11345, 412, 1559, 194};
	private static final byte wheel[] = { 10, 2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2 };

	/**
	 * 1から与えられた数までの素数の集合を返します。
	 * このとき、返り値をsetとするとset.get(p)はpが素数の時にtrueとなります。
	 * @complexity O(n loglog n)
	 * @param n 素数を求める範囲
	 * @return 素数の集合
	 */
	public static java.util.BitSet primeSet(int n) {
		if (n < 0) throw new IllegalArgumentException("negative number");
		final java.util.BitSet prime = new java.util.BitSet(++n);
		prime.set(2, n);
		for (int i = 4; i < n && i > 0; i += 2) prime.set(i, false);
		for (int i = 9; i < n && i > 0; i += 6) prime.set(i, false);
		for (int i = 25; i < n && i > 0; i += 10) prime.set(i, false);
		for (int i = 49; i < n && i > 0; i += 14) prime.set(i, false);
		final int sqrt = (int) Math.sqrt(n);
		for (int i = 11, j = 0; i <= sqrt; i += wheel[++j % wheel.length]) {
			if (prime.get(i)) for (int k = i * i; k < n && k > 0; k += i << 1) prime.set(k, false);
		}
		return prime;
	}

	/**
	 * 与えられた値が素数か否かを判定します。<br>
	 * この実装はhttp://ceur-ws.org/Vol-1326/020-Forisek.pdfに基づきます。
	 * @complexity O(log x)
	 * @param x 判定したい値
	 * @return xが素数ならtrue
	 */
	public static boolean isPrime(final int x) {
		if (x == 2 || x == 3 || x == 5 || x == 7) return true;
		if ((x & 1) == 0 || x % 3 == 0 || x % 5 == 0 || x % 7 == 0) return false;
		if (x < 121) return x > 1;
		return checkPrime(x);
	}

	private static boolean checkPrime(final int x) {
		if (x < 121) return x > 1;
		long h = x;
		h = (h >> 16 ^ h) * 0x45d9f3b;
		h = (h >> 16 ^ h) * 0x45d9f3b;
		h = (h >> 16 ^ h) & 0xFF;
		return isSPRP(new Barrett(x), bases[(int)h]);
	}

	/**
	 * 与えられた値が素数か否かを判定します。
	 * @complexity O(log^2 x)
	 * @param x 判定したい値
	 * @return xが素数ならtrue
	 */
	public static boolean isPrime(final long x) {
		if (x == 2 || x == 3 || x == 5 || x == 7) return true;
		if ((x & 1) == 0 || x % 3 == 0 || x % 5 == 0 || x % 7 == 0) return false;
		return checkPrime(x);
	}

	private static boolean checkPrime(final long x) {
		if (x < 121) return x > 1;
		if (!isSPRP(x, 2)) return false;
		if (!isSPRP(x, 325)) return false;
		if (!isSPRP(x, 9375)) return false;
		if (!isSPRP(x, 28178)) return false;
		if (!isSPRP(x, 450775)) return false;
		if (!isSPRP(x, 9780504)) return false;
		return isSPRP(x, 1795265022);
	}

	private static int pollardRho(final int x, final int c) {
		final Barrett barrett = new Barrett(x);
		return pollardRho(x, c, barrett);
	}

	private static int pollardRho(final int x, final int c, final Barrett barrett) {
		int next = 4, i = 1;
		int n = 2, m = 2, d = 1;
		do {
			if ( ++i == next) {
				m = n;
				next <<= 1;
			}
			if ((n = barrett.reduce((long)n * n + c)) == m) return pollardRho(x, c+1, barrett);
		} while ((d = gcd(Math.abs(n - m), x)) == 1);
		return d;
	}

	/**
	 * 与えられた値を素因数分解した結果を返します。
	 * @complexity O(x^(1/4) log x)
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public static MultiSet<IntPrime> getPrimeFactorization(int x) {
		if (x <= 0) throw new IllegalArgumentException("non positive number: " + x);
		final MultiSet<IntPrime> ret = HashMultiSet.create();
		int c;
		if((x & 1) == 0) {
			c = 1;
			for (x >>= 1;(x & 1) == 0;x >>= 1) ++ c;
			ret.add(new IntPrime(2), c);
		}
		if(x % 3 == 0) {
			c = 1;
			for (x /= 3;x % 3 == 0;x /= 3) ++ c;
			ret.add(new IntPrime(3), c);
		}
		if(x % 5 == 0) {
			c = 1;
			for (x /= 5;x % 5 == 0;x /= 5) ++ c;
			ret.add(new IntPrime(5), c);
		}
		if(x % 7 == 0) {
			c = 1;
			for (x /= 7;x % 7 == 0;x /= 7) ++ c;
			ret.add(new IntPrime(7), c);
		}
		int p, count;
		while(x != 1) {
			for (p = x;!checkPrime(p);p = pollardRho(p, 1));
			Barrett barrett = new Barrett(p);
			count = 1;
			for (x /= p;barrett.reduce(x) == 0;x /= p)++ count;
			ret.add(new IntPrime(p), count);
		}
		return ret;
	}

	private static long pollardRho(final long x, final int c) {
		int next = 4, i = 1;
		long n = 2, m = 2, d = 1;
		do {
			if ( ++i == next) {
				m = n;
				next <<= 1;
			}
			n = multiply(n, n, x);
			n += c;
			if (n < 0 || n >= x) n -= x;
			if (n == m) return pollardRho(x, c+1);
		} while ((d = gcd(Math.abs(n - m), x)) == 1);
		return d;
	}

	/**
	 * 与えられた値を素因数分解した結果を返します。
	 * @complexity O(x^(1/4) log x)
	 * @param x 素因数分解する値
	 * @return 素因数分解した結果
	 */
	public static MultiSet<LongPrime> getPrimeFactorization(long x) {
		if (x <= 0) throw new IllegalArgumentException("non positive number: " + x);
		final MultiSet<LongPrime> ret = HashMultiSet.create();
		int c;
		if((x & 1) == 0) {
			c = 1;
			for (x >>= 1;(x & 1) == 0;x >>= 1) ++ c;
			ret.add(new LongPrime(2L), c);
		}
		if(x % 3 == 0) {
			c = 1;
			for (x /= 3;x % 3 == 0;x /= 3) ++ c;
			ret.add(new LongPrime(3L), c);
		}
		if(x % 5 == 0) {
			c = 1;
			for (x /= 5;x % 5 == 0;x /= 5) ++ c;
			ret.add(new LongPrime(5L), c);
		}
		if(x % 7 == 0) {
			c = 1;
			for (x /= 7;x % 7 == 0;x /= 7) ++ c;
			ret.add(new LongPrime(7L), c);
		}
		long p;
		int count;
		while(x != 1) {
			for (p = x;!checkPrime(p);p = pollardRho(p, 1));
			count = 1;
			for (x /= p;x % p == 0;x /= p)++ count;
			ret.add(new LongPrime(p), count);
		}
		return ret;
	}

	private static class AdditionInt implements IntAbelian {
		@Override
		public int applyAsInt(int left, int right) {
			return left + right;
		}
		@Override
		public int identityAsInt() {
			return 0;
		}
		@Override
		public int inverseAsInt(int element) {
			return -element;
		}
	}

	private static class MultiplicationInt implements IntCommutativeMonoid {
		@Override
		public int applyAsInt(int left, int right) {
			return left * right;
		}
		@Override
		public int identityAsInt() {
			return 1;
		}
	}

	private static class AlgebraicInt implements IntEuclideanDomain<IntAbelian, IntCommutativeMonoid> {

		private static IntAbelian addition = new AdditionInt();
		private static IntCommutativeMonoid multiplication = new MultiplicationInt();
		@Override
		public IntAbelian getAddition() {return addition;}

		@Override
		public IntCommutativeMonoid getMultiplication() {return multiplication;}

		@Override
		public int gcdAsInt(int left, int right) {
			return MathUtility.gcd(left, right);
		}

		@Override
		public int lcmAsInt(int left, int right) {
			return (int)MathUtility.lcm(left, right);
		}

		@Override
		public int divideAsInt(int left, int right) {
			return left / right;
		}

		@Override
		public MultiSet<? extends IntPrimeElement> getPrimeFactorizationAsInt(int x) {
			return MathUtility.getPrimeFactorization(x);
		}

		@Override
		public int reminderAsInt(int left, int right) {
			return left % right;
		}
	}

	private static AlgebraicInt algebraicInt;
	/**
	 * 通常の意味で整数を演算する整域(int, +, *)を返します。
	 * @complexity O(1)
	 * @return 整域
	 */
	public static IntEuclideanDomain<IntAbelian, IntCommutativeMonoid> getIntOperator() {
		return algebraicInt == null ? algebraicInt = new AlgebraicInt() : algebraicInt;
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
			return MathUtility.gcd(left, right);
		}

		@Override
		public long lcmAsLong(long left, long right) {
			return MathUtility.lcm(left, right);
		}

		@Override
		public long divideAsLong(long left, long right) {
			return left / right;
		}

		@Override
		public MultiSet<? extends LongPrimeElement> getPrimeFactorizationAsLong(long x) {
			return MathUtility.getPrimeFactorization(x);
		}

		@Override
		public long reminderAsLong(long left, long right) {
			return left % right;
		}
	}

	private static AlgebraicLong algebraicLong;
	/**
	 * 通常の意味で整数を演算する整域(long, +, *)を返します。
	 * @complexity O(1)
	 * @return 整域
	 */
	public static LongEuclideanDomain<LongAbelian, LongCommutativeMonoid> getLongOperator() {
		return algebraicLong == null ? algebraicLong = new AlgebraicLong() : algebraicLong;
	}
}
