package com._31536000.math;

import com._31536000.math.algebraic.group.Abelian;

public class ModInteger extends Number implements Cloneable{

	private static final long serialVersionUID = -146632793619565203L;
	private final int mod;
	private final int num;

	private final Addition add;
	private final Multiplication mul;

	private class Addition implements Abelian<ModInteger> {

		@Override
		public ModInteger identity() {
			return new ModInteger(mod, 0);
		}

		@Override
		public ModInteger inverse(ModInteger element) {
			return new ModInteger(element, element.mod - element.num);
		}
		@Override
		public ModInteger apply(ModInteger left, ModInteger right) {
			return left.add(right);
		}

		@Override
		public ModInteger hyper(ModInteger element, int repeat) {
			return element.multiply(repeat);
		}
	}

	private class Multiplication implements Abelian<ModInteger> {

		@Override
		public ModInteger identity() {
			return new ModInteger(mod, 1);
		}

		@Override
		public ModInteger apply(ModInteger left, ModInteger right) {
			return left.multiply(right);
		}

		@Override
		public ModInteger inverse(ModInteger element) {
			return new ModInteger(element, element.inverse(element.num));
		}

	}

	public ModInteger(int mod) {
		this.mod = mod;
		num = 0;
		add = new Addition();
		mul = new Multiplication();
	}

	public ModInteger(int mod, int num) {
		this.mod = mod;
		this.num = validNum(num);
		add = new Addition();
		mul = new Multiplication();
	}

	public ModInteger(int mod, long num) {
		this.mod = mod;
		this.num = validNum(num);
		add = new Addition();
		mul = new Multiplication();
	}

	public ModInteger(ModInteger n) {
		this(n.mod, n.num, n.add, n.mul);
	}

	private ModInteger(ModInteger n, int num) {
		this(n.mod, num, n.add, n.mul);
	}

	protected ModInteger(int mod, int num, Addition add, Multiplication mul) {
		this.mod = mod;
		this.num = num;
		this.add = add;
		this.mul = mul;
	}

	private int validNum(int n) {
		n %= mod;
		if (n < 0) n += mod;
		return n;
	}

	private int validNum(long n) {
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

	public ModInteger add(int n) {
		return new ModInteger(this, validNum(num + n));
	}

	public ModInteger add(long n) {
		return new ModInteger(this, validNum(num + n));
	}

	public ModInteger add(ModInteger n) {
		int m = num + n.num;
		if (m >= mod) m -= mod;
		return new ModInteger(this, m);
	}

	public ModInteger subtract(int n) {
		return new ModInteger(this, validNum(num - n));
	}

	public ModInteger subtract(long n) {
		return new ModInteger(this, validNum(num - n));
	}

	public ModInteger subtract(ModInteger n) {
		int m = num - n.num;
		if (m < 0) m += mod;
		return new ModInteger(this, m);
	}

	public ModInteger multiply(int n) {
		return new ModInteger(this, validNum((long)num * n));
	}

	public ModInteger multiply(long n) {
		return new ModInteger(this, validNum(num * n));
	}

	public ModInteger multiply(ModInteger n) {
		return new ModInteger(this, validNum((long)num * n.num));
	}

	public ModInteger divide(int n) {
		return new ModInteger(this, validNum((long)num * inverse(n)));
	}

	public ModInteger divide(long n) {
		return new ModInteger(this, validNum((long)num * inverse(validNum(n))));
	}

	public ModInteger divide(ModInteger n) {
		return new ModInteger(this, validNum((long)num * inverse(n.num)));
	}

	private int pow(int n, int m) {
		long val = 1 % mod, mul = n;
		while(m != 0) {
			if ((m & 1) != 0) val = val * mul % mod;
			m >>= 1;
			mul = mul * mul % mod;
		}
		return (int)val;
	}

	public ModInteger pow(int n) {
		return new ModInteger(this, pow(num, n));
	}

	public ModInteger pow(long n) {
		return new ModInteger(this, pow(num, (int)(n % (mod - 1))));
	}

	public ModInteger pow(ModInteger n) {
		return new ModInteger(this, pow(num, n.num));
	}

	public int getMod() {
		return mod;
	}

	@Override
	public boolean equals(Object x) {
		if (x instanceof ModInteger) return ((ModInteger)x).num == num && ((ModInteger)x).mod == mod;
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

	public Addition getAddition() {
		return add;
	}

	public Multiplication getMultiplication() {
		return mul;
	}

	@Override
	public ModInteger clone() {
		return new ModInteger(this);
	}
}
