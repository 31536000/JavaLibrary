package com._31536000.algo.string;

public class RollingHash {
	private final long[] hash;
	private final long[] pow;
	
	public static final long MOD = (1L << 61) - 1;
	private static final long MASK0 = (1L << 30) - 1;
	private static final long MASK1 = (1L << 31) - 1;
	private static long base = -1;
	private RollingHash(final char[] s) {
		this(s, base == -1 ? base() : base);
	}
	private RollingHash(final char[] s, final long base) {
		RollingHash.base = base;
		hash = new long[s.length + 1];
		pow = new long[s.length + 1];
		pow[0] = 1;
		for (int i = 0;i < s.length;++ i) {
			hash[i + 1] = mod(mul(hash[i], base) + s[i]);
			pow[i + 1] = mul(pow[i], base);
		}
	}
	long hash() {
		return hash[hash.length - 1];
	}
	long hash(final int l, final int r) {
		return mod(hash[r] - mul(hash[l], pow[r - l]) + MOD);
	}
	
	private static long mul(final long a, final long b) {
		final long au = a >>> 31;
		final long ad = a & MASK1;
		final long bu = b >>> 31;
		final long bd = b & MASK1;
		final long mid = ad * bu + au * bd;
		final long midu = mid >>> 30;
		final long midd = mid & MASK0;
		return (au * bu << 1) + midu + (midd << 31) + ad * bd;
	}
	
	private static long mod(final long x) {
		final long xu = x >>> 61;
		final long xd = x & MOD;
		long res = xu + xd;
		if (res >= MOD) res -= MOD;
		return res;
	}
	
	public static long base() {
		return base(new java.util.Random());
	}
	 
	public static long base(final java.util.Random rnd) {
		while(true) {
			long e = mod(rnd.nextLong());
			if (gcd(e, MOD - 1) == 1) {
				long ans = 1;
				for (long mul = 37; e > 0; e >>= 1, mul = mul(mul, mul))
					if ((e & 1) != 0) ans = mul(ans, mul);
				return ans;
			}
		}
	}
	private static long gcd(long a, long b) {
		while (a != 0)
			if ((b %= a) != 0) a %= b;
			else return a;
		return b;
	}
}
