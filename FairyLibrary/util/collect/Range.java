package com._31536000.util.collect;

import com._31536000.math.algebraic.group.Semilattice;
import com._31536000.math.algebraic.lattice.Lattice;

public final class Range<E> implements IRange<E, Range<E>, Semilattice<Range<E>>, Semilattice<Range<E>>, Lattice<Range<E>, Semilattice<Range<E>>, Semilattice<Range<E>>>>{

	private final E lower, upper;
	private final BoundType lowerType, upperType;
	private final java.util.Comparator<? super E> comparator;
	private Range(E lower, BoundType lowerType, E upper, BoundType upperType, java.util.Comparator<? super E> comparator) {
		this.lower = lower;
		this.lowerType = lowerType;
		this.upper = upper;
		this.upperType = upperType;
		this.comparator = comparator;
	}

	private static class Addition<E> implements Semilattice<Range<E>>{

		@Override
		public Range<E> apply(Range<E> t, Range<E> u) {
			E l, r;
			BoundType lt, rt;
			if (t.lower == null) {
				if (t.upper == null) {
					return t.lowerType == BoundType.OPEN ? u : t;
				}
				if (u.lower == null) {
					if (u.upper == null) {
						return u.lowerType == BoundType.OPEN ? t : u;
					}
				}
				l = null;
				lt = BoundType.OPEN;
			} else {
				if (u.lower == null) {
					if (u.upper == null) {
						return u.lowerType == BoundType.OPEN ? t : u;
					}
					l = null;
					lt = BoundType.OPEN;
				} else {
					int comp = t.comparator.compare(t.lower, u.lower);
					if (comp < 0) {
						l = t.lower;
						lt = t.lowerType;
					} else if (comp > 0) {
						l = u.lower;
						lt = u.lowerType;
					} else {
						l = t.lower;
						lt = t.lowerType == BoundType.CLOSED || u.lowerType == BoundType.CLOSED ? BoundType.CLOSED : BoundType.OPEN;
					}
				}
			}
			if (t.upper == null || u.upper == null) {
				r = null;
				rt = BoundType.OPEN;
			} else {
				
			}
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public Range<E> identity() {
			return empty();
		}

	}

	public static <E> Range<E> empty() {
		return new Range<>(null, BoundType.OPEN, null, BoundType.OPEN);
	}

	public static <E> Range<E> all() {
		return new Range<>(null, BoundType.CLOSED, null, BoundType.CLOSED);
	}
	@Override
	public Lattice<Range<E>, Semilattice<Range<E>>, Semilattice<Range<E>>> getOperator() { // TODO 自動生成されたメソッド・スタブ
	return null; }

	@Override
	public E lowerEndpoint() {
		return lower;
	}

	@Override
	public BoundType lowerBoundType() {
		return lowerType;
	}

	@Override
	public E upperEndpoint() {
		return upper;
	}

	@Override
	public BoundType upperBoundType() {
		return upperType;
	}

	@Override
	public boolean isEmpty() { // TODO 自動生成されたメソッド・スタブ
	return false; }

	@Override
	public boolean isLess(E value) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isGreater(E value) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean contains(E value) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean containsAll(Iterable<? extends E> values) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean encloses(IRange<? extends E, ?, ?, ?, ?> other) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isIntersect(IRange<? extends E, ?, ?, ?, ?> other) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public IRange<? extends E, ? extends Range<E>, ? extends Semilattice<Range<E>>, ? extends Semilattice<Range<E>>, ? extends Lattice<Range<E>, Semilattice<Range<E>>, Semilattice<Range<E>>>> intersection(
			IRange<? extends E, ?, ?, ?, ?> connectedRange) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public IRange<? extends E, ? extends Range<E>, ? extends Semilattice<Range<E>>, ? extends Semilattice<Range<E>>, ? extends Lattice<Range<E>, Semilattice<Range<E>>, Semilattice<Range<E>>>> span(
			IRange<? extends E, ?, ?, ?, ?> other) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public IRange<? extends E, ? extends Range<E>, ? extends Semilattice<Range<E>>, ? extends Semilattice<Range<E>>, ? extends Lattice<Range<E>, Semilattice<Range<E>>, Semilattice<Range<E>>>> create(
			E lower, BoundType lowerBoundType, E upper, BoundType upperBoundType) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
