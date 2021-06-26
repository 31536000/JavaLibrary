package util.collect;

import java.util.Iterator;
import java.util.function.UnaryOperator;

public class IterableRange<C> extends Range<C> implements Iterable<C>{

	private static final long serialVersionUID = 9065915259748260688L;
	protected UnaryOperator<C> func;

	protected IterableRange(C lower, BoundType lowerType, C upper, BoundType upperType, UnaryOperator<C> func) {
		super(lower, lowerType, upper, upperType);
		this.func = func;
	}

	public static <C extends Comparable<? super C>> IterableRange<C> range(C lower, BoundType lowerType, C upper, BoundType upperType, UnaryOperator<C> func) {
		if (lower == null || upper == null) return new IterableRange<C>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
		int comp = lower.compareTo(upper);
		if (comp > 0) return new IterableRange<C>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
		else if (comp == 0 && (lowerType == BoundType.OPEN || upperType == BoundType.OPEN)) return new IterableRange<C>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
		return new IterableRange<C>(lower, lowerType, upper, upperType, func);
	}

	public static <C extends Comparable<? super C>> IterableRange<C> open(C lower, C upper, UnaryOperator<C> func) {
		if (lower == null) return new IterableRange<C>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
		return range(func.apply(lower), BoundType.CLOSED, upper, BoundType.OPEN, func);
	}

	public static <C extends Comparable<? super C>> IterableRange<C> openClosed(C lower, C upper, UnaryOperator<C> func) {
		if (lower == null) return new IterableRange<C>(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
		return range(func.apply(lower), BoundType.CLOSED, upper, BoundType.CLOSED, func);
	}

	public static <C extends Comparable<? super C>> IterableRange<C> closedOpen(C lower, C upper, UnaryOperator<C> func) {
		return range(lower, BoundType.CLOSED, upper, BoundType.OPEN, func);
	}

	public static <C extends Comparable<? super C>> IterableRange<C> closed(C lower, C upper, UnaryOperator<C> func) {
		return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, func);
	}

	public static <C extends Comparable<? super C>> IterableRange<C> singleton(C value, UnaryOperator<C> func) {
		return range(value, BoundType.CLOSED, value, BoundType.CLOSED, func);
	}

	protected class Iter implements Iterator<C> {
		C now;
		Iter() {
			now = lower;
		}
		@Override
		public final boolean hasNext() {
			return !isGreater(now);
		}

		@Override
		public final C next() {
			C ret = now;
			now = func.apply(now);
			return ret;
		}

		@Override
		public final void remove() {
			throw new UnsupportedOperationException();
		}
	}

	protected class EmptyIter implements Iterator<C> {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public C next() {
			return null;
		}

		@Override
		public final void remove() {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public Iterator<C> iterator() {
		return lower == null || upper == null ? new EmptyIter() : new Iter();
	}

	public int getDistance() {
		C check = upper;
		int ret = 0;
		while (lower != check) {
			check = func.apply(check);
			++ ret;
		}
		return ret;
	}
}
