package com._31536000.math;

public class Montgomery {
	private final int m;
	private final int inv;
	private final long r2;
	private final long r3;
	final long MAX = 1L << 31;
	final int MASK = (1 << 31) - 1;

	Montgomery(final int mod) {
		m = mod;
		int inv_ = 0;
		long t = 0, r2_ = 0;
		for (int i = 0;i < 31;++ i) {
			if ((t & 1) == 0) {
				t += m;
				inv_ |= 1 << i;
			}
			t >>>= 1;
		}
		inv = inv_;
		r2_ = MAX % m;
		r2 = r2_ * r2_ % m;
		r3 = r2 * MAX % m;
	}
	int generate(final int x) {
		return reduce(x * r2);
	}
	int generateSquare(final int x) {
		return reduce(x * r3);
	}
	int reduce(final long x) {
		final int ans = (int)(x + (x * inv & MASK) * m >>> 31);
		return ans < m ? ans : ans - m;
	}
}
