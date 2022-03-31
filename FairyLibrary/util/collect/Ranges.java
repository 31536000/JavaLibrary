package com._31536000.util.collect;

public class Ranges {
	private Ranges() {
		throw new AssertionError();
	}

	public static <E, R extends IRange<? extends E>> java.util.Comparator<R> getLexicographicalOrder(java.util.Comparator<? super E> comparator) {
		return (l, r) -> {
			int comp = comparator.compare(l.lowerEndpoint(), r.lowerEndpoint());
			if (comp != 0) return comp;
			if (l.lowerBoundType() != r.lowerBoundType()) return l.lowerBoundType() == BoundType.OPEN ? 1 : -1;
			comp = comparator.compare(l.upperEndpoint(), r.upperEndpoint());
			if (comp != 0) return comp;
			return l.upperBoundType() != r.upperBoundType() ? l.upperBoundType() == BoundType.OPEN ? -1 : 1 : 0;
		};
	}

	public static <E extends Comparable<? super E>, R extends IRange<? extends E>> java.util.Comparator<R> getLexicographicalOrder() {
		return getLexicographicalOrder(java.util.Comparator.naturalOrder());
	}

	public static <E, R extends IRange<? extends E>> java.util.Comparator<R> getReflectedLexicographicalOrder(java.util.Comparator<? super E> comparator) {
		return (l, r) -> {
			int comp = comparator.compare(l.upperEndpoint(), r.upperEndpoint());
			if (comp != 0) return comp;
			if (l.upperBoundType() != r.upperBoundType()) return l.upperBoundType() == BoundType.OPEN ? -1 : 1;
			comp = comparator.compare(r.lowerEndpoint(), r.lowerEndpoint());
			if (comp != 0) return comp;
			return l.lowerBoundType() != r.lowerBoundType() ? l.lowerBoundType() == BoundType.OPEN ? 1 : -1 : 0;
		};
	}

	public static <E extends Comparable<? super E>, R extends IRange<? extends E>> java.util.Comparator<R> getReflectedLexicographicalOrder() {
		return getReflectedLexicographicalOrder(java.util.Comparator.naturalOrder());
	}
}
