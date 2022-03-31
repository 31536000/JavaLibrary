package com._31536000.math;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import com._31536000.math.algebraic.group.CommutativeMonoid;
import com._31536000.math.algebraic.group.Semilattice;
import com._31536000.util.collect.HashMultiSet;
import com._31536000.util.collect.MultiSet;

/**
 * 整数の素因数分解表現を保持します。
 *
 * @author 31536000
 *
 */
public class IntPrimeFactor extends Number implements MultiSet<IntPrime>, Cloneable{
	private final MultiSet<IntPrime> primeFactor;
	public IntPrimeFactor(MultiSet<IntPrime> primeFactor) {
		this.primeFactor = HashMultiSet.create(primeFactor);
	}
	public IntPrimeFactor(int n) {
		primeFactor = MathUtility.getPrimeFactorization(n);
	}
	@Override
	public int size() {
		return primeFactor.size();
	}
	@Override
	public boolean isEmpty() {return primeFactor.isEmpty();}
	@Override
	public boolean contains(Object o) {
		return primeFactor.contains(o);
	}
	@Override
	public Iterator<IntPrime> iterator() {
		return primeFactor.iterator();
	}
	@Override
	public Object[] toArray() {
		return primeFactor.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return primeFactor.toArray(a);
	}
	@Override
	public boolean add(IntPrime e) {
		return primeFactor.add(e);
	}
	@Override
	public boolean remove(Object o) {
		return primeFactor.remove(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return primeFactor.containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends IntPrime> c) {
		return primeFactor.addAll(c);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return primeFactor.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return primeFactor.retainAll(c);
	}
	@Override
	public void clear() {
		primeFactor.clear();
	}
	@Override
	public int add(IntPrime element, int occurrences) {
		return primeFactor.add(element, occurrences);
	}
	@Override
	public int count(Object element) {
		return primeFactor.count(element);
	}
	@Override
	public Set<? extends IntPrime> keySet() {
		return primeFactor.keySet();
	}
	@Override
	public int remove(Object element, int occurrences) {
		return primeFactor.remove(element, occurrences);
	}
	@Override
	public int setCount(IntPrime element, int count) {
		return primeFactor.setCount(element, count);
	}
	@Override
	public boolean setCount(IntPrime element, int oldCount, int newCount) {
		return primeFactor.setCount(element, oldCount, newCount);
	}
	@Override
	public int intValue() {
		int ret = 1;
		for (IntPrime i : primeFactor) ret *= i.prime;
		return ret;
	}
	@Override
	public long longValue() {
		long ret = 1;
		for (IntPrime i : primeFactor) ret *= i.prime;
		return ret;
	}
	@Override
	public float floatValue() {
		float ret = 1;
		for (IntPrime i : primeFactor) ret *= i.prime;
		return ret;
	}
	@Override
	public double doubleValue() {
		double ret = 1;
		for (IntPrime i : primeFactor) ret *= i.prime;
		return ret;
	}
	private static class Multiplication implements CommutativeMonoid<IntPrimeFactor> {
		@Override
		public IntPrimeFactor apply(IntPrimeFactor t, IntPrimeFactor u) {
			IntPrimeFactor ret = t.clone();
			for (IntPrime i : u.keySet()) ret.add(i, u.count(i));
			return ret;
		}
		@Override
		public IntPrimeFactor identity() {
			return new IntPrimeFactor(1);
		}
	}

	@Override
	public IntPrimeFactor clone() {
		IntPrimeFactor ret = new IntPrimeFactor(1);
		for (IntPrime i : keySet()) ret.add(i, count(i));
		return ret;
	}

	private static Multiplication multiplication;

	public CommutativeMonoid<IntPrimeFactor> getMultiplication() {
		return multiplication == null ? multiplication = new Multiplication() : multiplication;
	}

	private static class GCD implements Semilattice<IntPrimeFactor> {

		@Override
		public IntPrimeFactor apply(IntPrimeFactor t, IntPrimeFactor u) {
			IntPrimeFactor ret = t.clone();
			for (IntPrime i : t) ret.setCount(i, Math.min(t.count(i), u.count(i)));
			return ret;
		}

		@Override
		public IntPrimeFactor identity() {

		}

	}

	@Override
	public Set<? extends Entry<IntPrime>> entrySet() {
		return primeFactor.entrySet();
	}
	@Override
	public boolean removeAll(Object element) {
		return primeFactor.removeAll(element);
	}
}
