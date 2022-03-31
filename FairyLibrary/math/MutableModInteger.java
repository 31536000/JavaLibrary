package com._31536000.math;

import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.ring.CommutativeRing;

public class MutableModInteger extends Number implements CommutativeRing<MutableModInteger, Abelian<MutableModInteger>, CommutativeMonoid<MutableModInteger>>{

	private static final long serialVersionUID = -8595710127161317579L;
	private final int mod;
	private final int num;

	private final Addition add;
	private final Multiplication mul;

	private final MutableModInteger zero, one;

	private class Addition implements Abelian<MutableModInteger> {

		@Override
		public MutableModInteger identity() {
			return zero;
		}

		@Override
		public MutableModInteger inverse(MutableModInteger element) {
			return new MutableModInteger(element, element.mod - element.num);
		}
		@Override
		public MutableModInteger apply(MutableModInteger left, MutableModInteger right) {
			return left.add(right);
		}

		@Override
		public MutableModInteger hyper(MutableModInteger element, int repeat) {
			return element.multiply(repeat);
		}
	}

	private class Multiplication implements Abelian<MutableModInteger> {

		@Override
		public MutableModInteger identity() {
			return one;
		}

		@Override
		public MutableModInteger apply(MutableModInteger left, MutableModInteger right) {
			return left.multiply(right);
		}

		@Override
		public MutableModInteger inverse(MutableModInteger element) {
			return new MutableModInteger(element, element.inverse(element.num));
		}

	}
	
	private MutableModInteger() {
		this.mod = mod;
		this.num = 0;
		add = new Addition();
		mul = new Multiplication();
	}

	public MutableModInteger(int mod, int num) {
		this.mod = mod;
		this.num = validNum(mod, num);
		add = new Addition();
		mul = new Multiplication();
	}

	private MutableModInteger(MutableModInteger n, int num) {
		mod = n.mod;
		this.num = num;
		add = n.add;
		mul = n.mul;
		zero = n.zero;
		one = n.one;
	}

	private static int validNum(int mod, int n) {
		n %= mod;
		if (n < 0) n += mod;
		return n;
	}

	private static int validNum(int mod, long n) {
		n %= mod;
		if (n < 0) n += mod;
		return (int)n;
	}

	protected int inverse(int n) {
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
	}

	public boolean isPrime(int n) {
		if ((n & 1) == 0) return false; // 偶数
		for (int i = 3, j = 8, k = 9;k <= n;i += 2, k += j += 8) if (n % i == 0) return false;
		return true;
	}

	@Override
	public int intValue() {
		return num;
	}

	@Override
	public long longValue() {
		return num;
	}

	@Override
	public float floatValue() {
		return num;
	}

	@Override
	public double doubleValue() {
		return num;
	}

	public MutableModInteger add(int n) {
		return new MutableModInteger(this, validNum(this.num + n));
	}

	public MutableModInteger add(long n) {
		return new MutableModInteger(this, validNum(this.num + n));
	}

	public MutableModInteger add(MutableModInteger n) {
		int sum = this.num + n.num;
		if (sum >= mod) sum -= mod;
		return new MutableModInteger(this, sum);
	}

	public MutableModInteger subtract(int n) {
		return new MutableModInteger(this, validNum(this.num + n));
	}

	public MutableModInteger subtract(long n) {
		return new MutableModInteger(this, validNum(this.num + n));
	}

	public MutableModInteger subtract(MutableModInteger n) {
		int sub = num - n.num;
		if (sub < 0) sub += mod;
		return new MutableModInteger(this, sub);
	}

	public MutableModInteger multiply(int n) {
		return new MutableModInteger(this, validNum(num * n));
	}

	public MutableModInteger multiply(long n) {
		return new MutableModInteger(this, validNum(n % mod * num));
	}

	public MutableModInteger multiply(MutableModInteger n) {
		return new MutableModInteger(this, (int)((long)num * n.num % mod));
	}

	public MutableModInteger divide(int n) {
		return new MutableModInteger(this, (int)((long)num * inverse(validNum(n)) % mod));
	}

	public MutableModInteger divide(long n) {
		return new MutableModInteger(this, (int)((long)num * inverse(validNum(n)) % mod));
	}

	public MutableModInteger divide(MutableModInteger n) {
		return new MutableModInteger(this, (int)((long)num * n.inverse(n.num) % mod));
	}

	public MutableModInteger pow(int n) {
		return new MutableModInteger(this).powEqual(n);
	}

	public MutableModInteger pow(long n) {
		return new MutableModInteger(this).powEqual(n);
	}

	public MutableModInteger pow(MutableModInteger n) {
		return new MutableModInteger(this).powEqual(n);
	}

	public MutableModInteger powEqual(int n) {
		long ans = 1, num = this.num;
		if (n < 0) {
			n = -n;
			while (n != 0) {
				if ((n & 1) != 0) ans = ans * num % mod;
				n >>>= 1;
				num = num * num % mod;
			}
			this.num = inverse((int)ans);
			return this;
		}
		while (n != 0) {
			if ((n & 1) != 0) ans = ans * num % mod;
			n >>>= 1;
			num = num * num % mod;
		}
		this.num = (int)ans;
		return this;
	}
	public MutableModInteger powEqual(long n) {
		return powEqual((int)(n % (mod - 1)));
	}

	public MutableModInteger powEqual(MutableModInteger n) {
		long num = this.num;
		this.num = 1;
		int mul = n.num;
		while (mul != 0) {
			if ((mul & 1) != 0) this.num *= num;
			mul >>>= 1;
			num *= num;
			num %= mod;
		}
		return this;
	}

	public MutableModInteger equal(int n) {
		num = validNum(n);
		return this;
	}

	public MutableModInteger equal(long n) {
		num = validNum(n);
		return this;
	}

	public MutableModInteger equal(MutableModInteger n) {
		num = n.num;
		return this;
	}

	public int toInt() {
		return num;
	}

	public int getMod() {
		return mod;
	}

	@Override
	public boolean equals(Object x) {
		if (x instanceof MutableModInteger) return ((MutableModInteger)x).num == num && ((MutableModInteger)x).mod == mod;
		return false;
	}

	@Override
	public int hashCode() {
		return num ^ mod;
	}

	@Override
	public String toString() {
		return String.valueOf(num);
	}

	@Deprecated
	public String debug() {
		int min = num, ans = 1;
		for (int i = 2;i < min;++ i) {
			int tmp = multiply(i).num;
			if (min > tmp) {
				min = tmp;
				ans = i;
			}
		}
		return min + "/" + ans;
	}

	@Override
	public Addition getAddition() {
		return add;
	}

	@Override
	public Multiplication getMultiplication() {
		return mul;
	}
}
