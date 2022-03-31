package com._31536000.math;

public class Barrett {
	private final int m;
	private final long h, l;
	private final long MAX = 1L << 62;
	private final int MASK = (1 << 31) - 1;
	Barrett(final int mod) {
		m = mod;
		final long t = MAX / m;
		h = t >>> 31;
		l = t & MASK;
	}
	int reduce(final long x) {
		final long xh = x >>> 31, xl = x & MASK;
		long z = xl * l;
		z = xl * h + xh * l + (z>>>31);
		z = xh * h + (z>>>31);
		final int ret = (int)(x - z * m);
		return ret >= m ? ret - m : ret;
	}
}
