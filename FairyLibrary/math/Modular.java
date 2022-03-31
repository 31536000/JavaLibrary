package com._31536000.math;

import java.lang.ref.WeakReference;
import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntCommutativeMonoid;
import com._31536000.math.algebraic.ring.IntCommutativeRing;

public class Modular implements IntCommutativeRing<IntAbelian, IntCommutativeMonoid> {

	public final int mod;
	private final Barrett barrett;

	private Modular(int mod) {
		this.mod = mod;
		barrett = new Barrett(mod);
	}

	private static final java.util.HashMap<Integer, WeakReference<Modular>> multiton = new java.util.HashMap<>(1);

	public synchronized static Modular create(int mod) {
		if (mod <= 0) throw new IllegalArgumentException("mod " + mod + " undefined");
		Modular ret = null;
		WeakReference<Modular> ref = multiton.get(mod);
		if (ref == null || (ret = ref.get()) == null) {
			ret = new Modular(mod); // TODO: mod値によって特殊化する
			multiton.put(mod, new WeakReference<>(ret));
		}
		return ret;
	}

	public int mod(int n) {
		return barrett.reduce(n);
	}

	public int plus(int n, int m) {
		return addition.applyAsInt(n, m);
	}

	public int minus(int n, int m) {
		int ret = n - m;
		return ret < 0 ? ret + mod : ret;
	}

	@Override
	public int minusAsInt(int n, int m) {
		return minus(n, m);
	}

	public int times(int n, int m) {
		return multiplication.applyAsInt(n, m);
	}

	private final IntAbelian addition = new IntAbelian() {
		@Override
		public int applyAsInt(int left, int right) {
			int ret = left + right;
			return ret >= mod ? ret - mod : ret;
		}

		@Override
		public int identityAsInt() {
			return 0;
		}

		@Override
		public int inverseAsInt(int element) {
			return element == 0 ? element : mod - element;
		}
	};

	@Override
	public IntAbelian getAddition() { return addition; }

	private final IntCommutativeMonoid multiplication = new IntCommutativeMonoid() {

		@Override
		public int applyAsInt(int left, int right) {
			return barrett.reduce((long) left * right);
		}

		@Override
		public int identityAsInt() {
			return 1;
		}

	};

	@Override
	public IntCommutativeMonoid getMultiplication() { return multiplication; }
}
