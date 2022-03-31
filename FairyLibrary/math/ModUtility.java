package com._31536000.math;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.ring.CommutativeRing;
import com._31536000.util.ArrayUtility;

/**
 * 与えられた数を法とする演算上で、組み合わせの計算を高速に行います。
 * @author 31536000
 *
 */
public class ModUtility implements CommutativeRing<ModInteger, Abelian<ModInteger>, CommutativeMonoid<ModInteger>>{

	private class Mod2Utility {
		public int getMod() {
			return 2;
		}
		public int mod(int n) {
			return n & 1;
		}
		public int mod(long n) {
			return (int)n & 1;
		}
		public int add(int n, int m) {
			return n + m & 1;
		}
		public int add(long n, long m) {
			return mod(n + m);
		}
		public int subtract(int n, int m) {
			return n - m & 1;
		}
		public int subtract(long n, long m) {
			return mod(n - m);
		}
		public int multiply(int n, int m) {
			return n * m & 1;
		}
		public int multiply(long n, long m) {
			return mod(n * m);
		}
		public int divide(int n, int m) {
			return 1;
		}
		public int divide(long n, long m) {
			return 1;
		}
		public int inverse(int n) {
			return 1;
		}
		public int inverse(long n) {
			return 1;
		}
		public int pow(int n, int m) {
			return m == 0 ? 1 : n & 2;
		}
		public int pow(long n, long m) {
			return m == 0 ? 1 : mod(n);
		}
		public int pow(int... n) {
			int m = n[n.length - 1];
			for (int i = n.length - 2;i > 0;-- i) m = n[i] == 0 && m != 0 ? 1 : 0;
			return pow(n[0], m);
		}
		public int pow(long... n) {
			int m = (int)n[n.length - 1];
			for (int i = n.length - 2;i > 0;-- i) m = n[i] == 0 && m != 0 ? 1 : 0;
			return pow(n[0], m);
		}
		public int tetration(int n, int m) {
			return n == 0 ? m + 1 & 1 : n & 1;
		}
		public int tetration(long n, long m) {
			return mod(n == 0 ? m + 1 : n);
		}
		public int factorial(int n) {
			return n <= 1 ? 1 : 0;
		}
		public int factorial(long n) {
			return n <= 1 ? 1 : 0;
		}
		public int permutation(int n, int m) {
			return n == m ? 1 : n == m + 1 ? mod(n) : 0;
		}
		public int permutation(long n, long m) {
			return n == m ? 1 : n == m + 1 ? mod(n) : 0;
		}
		public int combination(int n, int m) {
			return (n & m) == m ? 1 : 0;
		}
		public int combination(long n, long m) {
			return (n & m) == m ? 1 : 0;
		}

		public int totient() {
			return 1;
		}

	}
	private final int mod;
	private int[] fact, inv, invfact;
	private int[] pow;

	/**
	 * modを法として、演算を行います。
	 * @param mod 法とする素数
	 */
	public ModUtility(IntPrime mod) {
		this(mod, 2);
	}

	/**
	 * modを法として、演算を行います。
	 * @param mod 法とする素数
	 * @param calc 予め前計算しておく大きさ
	 */
	public ModUtility(IntPrime mod, int calc) {
		this.mod = mod.prime;
		precalc(calc);
	}

	/**
	 * calcの大きさだけ、前計算を行います。
	 * @param calc 前計算をする大きさ
	 */
	public void precalc(int calc) {
		++ calc;
		if (calc < 2) calc = 2;
		if (calc > mod) calc = mod;
		fact = new int[calc];
		inv = new int[calc];
		invfact = new int[calc];
		fact[0] = invfact[0] = fact[1] = invfact[1] = inv[1] = 1;
		for (int i = 2;i < calc;++ i) {
			fact[i] = (int)((long)fact[i - 1] * i % mod);
			inv[i] = (int)(mod - (long)inv[mod % i] * (mod / i) % mod);
			invfact[i] = (int)((long)invfact[i - 1] * inv[i] % mod);
		}
	}

	/**
	 * modを法とする剰余環上で振舞う整数を返します。
	 * @return modを法とする整数、初期値は0
	 */
	public ModInteger create() {
		return new ModInt();
	}

	/**
	 * modを法とする剰余環上で振舞う整数を返します。
	 * @param n 初期値
	 * @return modを法とする整数
	 */
	public ModInteger create(int n) {
		return new ModInt(n);
	}

	private class ModInt extends ModInteger {

		private static final long serialVersionUID = -2435281861935422575L;

		public ModInt() {
			super(mod);
		}

		public ModInt(int n) {
			super(mod, n);
		}

		public ModInt(ModInteger mod) {
			super(mod);
		}

		@Override
		protected ModInteger getNewInstance(ModInteger mod) {
			return new ModInt(mod);
		}

		@Override
		protected int inverse(int n) {
			return ModUtility.this.inverse(n);
		}
	}

	/**
	 * 法として扱う値を返します。
	 * @return 法
	 */
	public int getMod() {
		return mod;
	}

	/**
	 * modを法として、nの逆元を返します。<br>
	 * 計算量はO(log n)です。
	 * @param n 逆元を求めたい値
	 * @return 逆元
	 */
	public int inverse(int n) {
		try {
			if (inv.length > n) return inv[n];
			 int m = mod, u = 0, v = 1, t;
			 while(n != 0) {
				 t = m / n;
				 m -= t * n;
				 u -= t * v;
				 if (m != 0) {
					 t = n / m;
					 n -= t * m;
					 v -= t * u;
				 } else {
					 v %= mod;
					 if (v < 0) v += mod;
					 return v;
				 }
			 }
			 u %= mod;
			 if (u < 0) u += mod;
			 return u;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * n!を、modを法として求めた値を返します。<br>
	 * 計算量はO(n)です。
	 * @param n 階乗を求めたい値
	 * @return nの階乗をmodで割った余り
	 */
	public int factorial(int n) {
		try {
			if (fact.length > n) return fact[n];
			long ret = fact[fact.length - 1];
			for (int i = fact.length;i <= n;++ i) ret = ret * i % mod;
			return (int)ret;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * nPkをmodで割った余りを求めます。<br>
	 * 計算量はO(n-k)です。
	 * @param n 左辺
	 * @param k 右辺
	 * @return nPkをmodで割った余り
	 */
	public int permutation(int n, int k) {
		if (n < 0) throw new IllegalArgumentException();
		if (n < k) return 0;
		if (fact.length > n) return (int)((long)fact[n] * invfact[n - k] % mod);
		long ret = 1;
		for (int i = n - k + 1;i <= n;++ i) ret = ret * i % mod;
		return (int)ret;
	}

	/**
	 * nCkをmodで割った余りを求めます。<br>
	 * 計算量はO(min(plogn, n-k))です。
	 * @param n 左辺
	 * @param k 右辺
	 * @return nCkをmodで割った余り
	 */
	public int combination(int n, int k) {
		if (n < 0) throw new IllegalArgumentException();
		if (n < k) return 0;
		if (fact.length > n) return (int)((long)fact[n] * invfact[k] % mod * invfact[n - k] % mod);
		long ret = 1;
		if (n >= mod) {
			if (mod == 2) return (~n & k) == 0 ? 1 : 0;
			while(n > 0) {
				ret = ret * combination(n % mod, k % mod) % mod;
				n /= mod;
				k /= mod;
			}
			return (int)ret;
		}
		if (n < 2 * k) k = n - k;
		ret = invfact.length > k ? invfact[k] : inverse(factorial(k));
		for (int i = n - k + 1;i <= n;++ i) ret = ret * i % mod;
		return (int)ret;
	}

	/**
	 * 他項係数をmodで割った余りを求めます。<br>
	 * 計算量はO(n)です。
	 * @param n 左辺
	 * @param k 右辺、合計がn以下である必要がある
	 * @return 他項係数
	 */
	public int multinomial(int n, int... k) {
		int sum = 0;
		for (int i : k) sum += i;
		long ret = factorial(n);
		if (fact.length > n) {
			for (int i : k) {
				if (i < 0) throw new IllegalArgumentException();
				ret = ret * invfact[i] % mod;
				sum += i;
			}
			if (sum > n) return 0;
			ret = ret * invfact[n - sum] % mod;
		} else {
			for (int i : k) {
				if (i < 0) throw new IllegalArgumentException();
				if (invfact.length > i) ret = ret * invfact[i] % mod;
				else ret = ret * inverse(factorial(i)) % mod;
				sum += i;
			}
			if (sum > n) return 0;
			if (invfact.length > n - sum) ret = ret * invfact[n - sum] % mod;
			else ret = ret * inverse(factorial(n - sum)) % mod;
		}
		return (int)ret;
	}

	/**
	 * n個からk個を選ぶ重複組み合わせnHkをmodで割った余りを求めます。<br>
	 * 計算量はO(min(n, k))です。
	 * @param n 左辺
	 * @param k 右辺
	 * @return nHkをmodで割った余り
	 */
	public int multichoose(int n, int k) {
		return combination(mod(n + k - 1), k);
	}

	/**
	 * カタラン数C(n)をmodで割った余りを求めます。<br>
	 * 計算量はO(n)です。
	 * @param n 求めたいカタラン数の番号
	 * @return カタラン数
	 */
	public int catalan(int n) {
		return divide(combination(mod(2 * n), n), mod(n + 1));
	}

	/**
	 * 第一種スターリング数S(n, k)をmodで割った余りを求めます。<br>
	 * 計算量はO(nk)です。 // TODO NTTを使うとO(n log n)、未実装
	 * @param n 左辺
	 * @param k 右辺
	 * @return S(n, k)をmodで割った余り
	 */
	public int firstStirling(int n, int k) {
		int[] stirling = new int[(n + 1) * (k + 1)];
		stirling[0] = 1;
		int h = k + 1;
		for (int i = 0;i < n;++ i) {
			for (int j = 0;j < k;++ j) {
				int tmp = stirling[i * h + j] + (int)((long)i * stirling[i * h + j + 1] % mod);
				stirling[(i + 1) * h + j + 1] = tmp >= mod ? tmp - mod : tmp;
			}
		}
		return stirling[stirling.length - 1];
	}

	/**
	 * 第二種スターリング数S(n, k)をmodで割った余りを求めます。<br>
	 * 計算量はO(k)です。
	 * @param n 左辺
	 * @param k 右辺
	 * @return S(n, k)をmodで割った余り
	 */
	public int secondStirling(int n, int k) {
		if (k == 0) return n == 0 ? 1 : 0;
		int[] sieve = new int[k + 1], prime = new int[k + 1];
		int size = 0;
		sieve[1] = 1;
		for (int i = 2;i <= k;++ i) {
			if (sieve[i] == 0) prime[size++] = sieve[i] = i;
			for (int j = 0, s;j < size && prime[j] <= sieve[i] && (s = i * prime[j]) <= k;++ j) sieve[s] = prime[j];
		}
		long ans = 0;
		for (int i = 1, s;i <= k;++ i) {
			long tmp = (long)combination(k, i) * (prime[i] = (s = sieve[i]) == i ? pow(i, n) : (int)((long)prime[s] * prime[i / s] % mod)) % mod;
			ans += (k - i & 1) != 0 ? -tmp : tmp;
		}
		return (int)((long)mod(ans) * invfact[k] % mod);
	}

	/**
	 * ベル数B(n, k)をmodで割った余りを求めます。<br>
	 * 計算量はO(k)です。
	 * @param n 左辺
	 * @param k 右辺
	 * @return B(n, k)をmodで割った余り
	 */
	public int bell(int n, int k) {
		if (k == 0) return n == 0 ? 1 : 0;
		int[] sieve = new int[k + 1], prime = new int[k + 1];
		int size = 0;
		sieve[1] = 1;
		long sum = 0;
		for (int i = 2;i <= k;++ i) {
			if (sieve[i] == 0) prime[size++] = sieve[i] = i;
			for (int j = 0, s;j < size && prime[j] <= sieve[i] && (s = i * prime[j]) <= k;++ j) sieve[s] = prime[j];
			sum += (i & 1) != 0 ? -invfact[i] : invfact[i];
		}
		sum = mod(sum);
		long ans = 0;
		for (int i = 0, s;i <= k;++ i) {
			long tmp = (long)(prime[i] = (s = sieve[i]) == i ? pow(i, n) : (int)((long)prime[s] * prime[i / s] % mod)) * invfact[i] % mod;
			ans += tmp * sum % mod;
			if ((sum -= (k - i & 1) != 0 ? -invfact[k - i] : invfact[k - i]) < 0) sum += mod;
		}
		return mod(ans);
	}

	/**
	 * ベル数B(n)をmodで割った余りを求めます。<br>
	 * 計算量はO(n)です。
	 * @param n 求めたいベル数の番号
	 * @return B(n)
	 */
	public int bell(int n) {
		return bell(n, n);
	}

	/**
	 * 分割数P(n, k)をmodで割った余りを求めます。<br>
	 * 計算量はO(nk)です。 // TODO NTTを使うとO(n log n)、未実装
	 * @param n 左辺
	 * @param k 右辺
	 * @return P(n, k)をmodで割った余り
	 */
	public int pertition(int n, int k) {
		int[] pertition = new int[(n + 1) * (k + 1)];
		pertition[0] = 1;
		int h = k + 1;
		for (int i = 0;i <= n;++ i) {
			for (int j = 1, l = Math.min(i, k);j <= l;++ j) pertition[i * h + j] = pertition[i * h + j - 1] + pertition[(i - j) * h + j];
			for (int j = i;j < k;++ j) pertition[i * h + j + 1] = pertition[i * h + j];
		}
		return pertition[n * h + k];
	}

	/**
	 * 分割数P(n)をmodで割った余りを求めます。<br>
	 * 計算量はO(n sqrt(n))です。 // TODO NTTを使うとO(n log n)、未実装
	 * @param n 求めたい分割数の番号
	 * @return P(n)
	 */
	public int pertition(int n) {
		long[] pertition = new long[n + 1];
		pertition[0] = 1;
		for (int i = 1;i <= n;++ i) {
			for (int j = 1, t;(t = i - (j * (3 * j - 1) >> 1)) >= 0; ++ j) {
				pertition[i] += (j & 1) != 0 ? pertition[t] : -pertition[t];
			}
			for (int j = 1, t;(t = i - (j * (3 * j + 1) >> 1)) >= 0; ++ j) {
				pertition[i] += (j & 1) != 0 ? pertition[t] : -pertition[t];
			}
			pertition[i] %= mod;
		}
		return (int)pertition[n];
	}

	/**
	 * nのm乗をmodで割った余りを求めます。<br>
	 * 計算量はO(log m)です。
	 * @param n 床
	 * @param m 冪指数
	 * @return n^mをmodで割った余り
	 */
	public int pow(int n, int m) {
		long ans = 1, num = n;
		if (m < 0) {
			m = -m;
			while (m != 0) {
				if ((m & 1) != 0) ans = ans * num % mod;
				m >>>= 1;
				num = num * num % mod;
			}
			return inverse((int)ans);
		}
		while (m != 0) {
			if ((m & 1) != 0) ans = ans * num % mod;
			m >>>= 1;
			num = num * num % mod;
		}
		return (int)ans;
	}

	/**
	 * nのm乗をmodで割った余りを求めます。<br>
	 * 計算量はO(log m)です。
	 * @param n 床
	 * @param m 冪指数
	 * @return n^mをmodで割った余り
	 */
	public int pow(long n, long m) {
		return pow((int)(n % mod), (int)(m % (mod - 1)));
	}

	/**
	 * 現在のmod値のトーシェント数を返します。<br>
	 * なお、これはmod-1に等しいです。
	 * @return トーシェント数
	 */
	public int totient() {
		return mod - 1;
	}

	/**
	 * nのトーシェント数を返します。<br>
	 * 計算量はO(sqrt n)です。
	 * @param n トーシェント数を求めたい値
	 * @return nのトーシェント数
	 */
	public static int totient(int n) {
		int totient = n;
		for (int i = 2;i * i <= n;++ i) {
			if (n % i == 0) {
				totient = totient / i * (i - 1);
				while ((n %= i) % i == 0);
			}
		}
		if (n != 1) totient = totient / n * (n - 1);
		return totient;
	}

	/**
	 * nをmodで割った余りを返します。
	 * @param n 演算する値
	 * @return nをmodで割った余り
	 */
	public int mod(int n) {
		return (n %= mod) < 0 ? n + mod : n;
	}

	/**
	 * nをmodで割った余りを返します。
	 * @param n 演算する値
	 * @return nをmodで割った余り
	 */
	public int mod(long n) {
		return (int)((n %= mod) < 0 ? n + mod : n);
	}

	/**
	 * n+mをmodで割った余りを返します。
	 * @param n 足される値
	 * @param m 足す値
	 * @return n+mをmodで割った余り
	 */
	public int add(int n, int m) {
		return mod(n + m);
	}

	/**
	 * n-mをmodで割った余りを返します。
	 * @param n 引かれる値
	 * @param m 引く値
	 * @return n-mをmodで割った余り
	 */
	public int subtract(int n, int m) {
		return mod(n - m);
	}

	/**
	 * n*mをmodで割った余りを返します。
	 * @param n 掛けられる値
	 * @param m 掛ける値
	 * @return n*mをmodで割った余り
	 */
	public int multiply(int n, int m) {
		int ans = (int)((long)n * m % mod);
		return ans < 0 ? ans + mod : ans;
	}

	/**
	 * n/mをmodで割った余りを返します。
	 * @param n 割られる値
	 * @param m 割る値
	 * @return n/mをmodで割った余り
	 */
	public int divide(int n, int m) {
		return multiply(n, inverse(m));
	}

	/**
	 * fを通ることが分かっているfの要素数-1次の関数について、xの位置における値をmodで割った余りを返します。<br>
	 * 計算量はO(f)です。
	 * @param f 関数の形
	 * @param x 求める位置
	 * @return 求めたい値をmodで割った余り
	 */
	public ModInteger lagrangePolynomial(ModInteger[] f, int x) {
		if (f.length > x) return f[x];
		if (x > fact.length) precalc(x);
		ModInteger ret = create(0);
		ModInteger[] dp = new ModInteger[f.length], dp2 = new ModInteger[f.length];
		dp[0] = create(1);
		dp2[f.length - 1] = create(1);
		for (int i = 1;i < f.length;++ i) {
			dp[i] = dp[i - 1].multiply(x - i - 1);
			dp2[f.length - i - 1] = dp2[f.length - i].multiply(x - f.length + i);
		}
		for (int i = 0;i < f.length;++ i) {
			ModInteger tmp = f[i].multiply(dp[i]).multiplyEqual(dp2[i]).multiplyEqual(inv[i]).multiplyEqual(inv[f.length - 1 - i]);
			if ((f.length - i & 1) == 0) ret.addEqual(tmp);
			else ret.subtractEqual(tmp);
		}
		return ret;
	}

	/**
	 * 与えられた配列に対し、その配列を並び替えることで構成できる配列の集合をSとします。
	 * このとき、arrayがSを辞書順に並べると何番目かを求めます。
	 * @complexity N=array.length として O(N log N)
	 * @param array 辞書順で何番目か求めたい配列
	 * @return arrayが辞書順で何番目か
	 */
	public ModInteger permutationNumber(int[] array) {
		int[] compress = ArrayUtility.compress(array);
		int[] bucket = new int[array.length];
		for (int i : compress) ++ bucket[i];
		int sum = multinomial(array.length, bucket);
		int[] bit = new int[array.length + 1];
		for (int i = 0;i < array.length;++ i) for (int j = i + 1, add = bucket[i];j < bit.length;j += j & -j) bit[j] += add;
		int ans = 1;
		for (int i = 0;i < array.length;++ i) {
			sum = divide(sum, array.length - i);
			int comp = compress[i];
			int min = 0;
			for (int j = comp;j != 0;j -= j & -j) min += bit[j];
			ans = add(ans, multiply(sum, min));
			sum = multiply(sum, bucket[comp]--);
			for (int j = comp + 1;j < bit.length;j += j & -j) -- bit[j];
		}
		return create(ans);
	}

	@Override
	public Abelian<ModInteger> getAddition() { // TODO 自動生成されたメソッド・スタブ
	return null; }

	@Override
	public CommutativeMonoid<ModInteger> getMultiplication() { // TODO 自動生成されたメソッド・スタブ
	return null; }
}

