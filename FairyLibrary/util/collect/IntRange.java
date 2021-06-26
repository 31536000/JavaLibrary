package util.collect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.UnaryOperator;

public class IntRange extends IterableRange<Integer>{

	private static final long serialVersionUID = 5623995336491967216L;
	private final boolean useFastIter;

	private static class Next implements UnaryOperator<Integer> {

		@Override
		public Integer apply(Integer value) {
			return value + 1;
		}
	}

	protected IntRange() {
		super(null, BoundType.CLOSED, null, BoundType.CLOSED, new Next());
		useFastIter = true;
	}

	protected IntRange(UnaryOperator<Integer> func) {
		super(null, BoundType.CLOSED, null, BoundType.CLOSED, func);
		useFastIter = false;
	}

	protected IntRange(int lower, BoundType lowerType, int upper, BoundType upperType) {
		super(lower, lowerType, upper, upperType, new Next());
		useFastIter = true;
	}

	protected IntRange(int lower, BoundType lowerType, int upper, BoundType upperType, UnaryOperator<Integer> func) {
		super(lower, lowerType, upper, upperType, func);
		useFastIter = false;
	}

	public static IntRange range(int lower, BoundType lowerType, int upper, BoundType upperType) {
		if (lower > upper) return new IntRange();
		if (lowerType == BoundType.OPEN) ++ lower;
		if (upperType == BoundType.OPEN) -- upper;
		return new IntRange(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
	}

	public static IntRange range(int lower, BoundType lowerType, int upper, BoundType upperType, UnaryOperator<Integer> func) {
		if (lower > upper) return new IntRange(func);
		if (lowerType == BoundType.OPEN) ++ lower;
		if (upperType == BoundType.OPEN) -- upper;
		return new IntRange(lower, BoundType.CLOSED, upper, BoundType.CLOSED, func);
	}

	public static IntRange open(int lower, int upper) {
		return range(lower, BoundType.OPEN, upper, BoundType.OPEN);
	}

	public static IntRange open(int lower, int upper, UnaryOperator<Integer> func) {
		return range(lower, BoundType.OPEN, upper, BoundType.OPEN, func);
	}

	public static IntRange open(int upper) {
		return range(0, BoundType.CLOSED, upper, BoundType.OPEN);
	}

	public static IntRange open(int upper, UnaryOperator<Integer> func) {
		return range(0, BoundType.CLOSED, upper, BoundType.OPEN, func);
	}

	public static IntRange openClosed(int lower, int upper) {
		return range(lower, BoundType.OPEN, upper, BoundType.CLOSED);
	}

	public static IntRange openClosed(int lower, int upper, UnaryOperator<Integer> func) {
		return range(lower, BoundType.OPEN, upper, BoundType.CLOSED, func);
	}

	public static IntRange closedOpen(int lower, int upper) {
		return range(lower, BoundType.CLOSED, upper, BoundType.OPEN);
	}

	public static IntRange closedOpen(int lower, int upper, UnaryOperator<Integer> func) {
		return range(lower, BoundType.CLOSED, upper, BoundType.OPEN, func);
	}

	public static IntRange closed(int lower, int upper) {
		return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
	}

	public static IntRange closed(int lower, int upper, UnaryOperator<Integer> func) {
		return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, func);
	}

	public static IntRange closed(int upper) {
		return range(0, BoundType.CLOSED, upper, BoundType.CLOSED);
	}

	public static IntRange closed(int upper, UnaryOperator<Integer> func) {
		return range(0, BoundType.CLOSED, upper, BoundType.CLOSED, func);
	}

	public static IntRange singleton(int value) {
		return range(value, BoundType.CLOSED, value, BoundType.CLOSED);
	}

	public static IntRange singleton(int value, UnaryOperator<Integer> func) {
		return range(value, BoundType.CLOSED, value, BoundType.CLOSED, func);
	}

	private class FastIter implements Iterator<Integer> {
		int now;
		public FastIter() {
			now = lower;
		}
		@Override
		public final boolean hasNext() {
			return now <= upper;
		}

		@Override
		public final Integer next() {
			return now++;
		}

		@Override
		public final void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class Iter implements Iterator<Integer> {
		int now;
		public Iter() {
			now = lower;
		}
		@Override
		public final boolean hasNext() {
			return now <= upper;
		}

		@Override
		public final Integer next() {
			int ret = now;
			now = func.apply(now);
			return ret;
		}

		@Override
		public final void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return lower == null || upper == null ? new EmptyIter() : useFastIter ? new FastIter() : new Iter();
	}

	@Override
	public int getDistance() {
		int ret = upper - lower;
		if (upperType == BoundType.CLOSED) ++ ret;
		return ret;
	}

	public int getClosedLower() {
		return lower;
	}

	public int getOpenLower() {
		return lower - 1;
	}

	public int getClosedUpper() {
		return upperType == BoundType.CLOSED ? upper : upper - 1;
	}

	public int getOpenUpper() {
		return upperType == BoundType.CLOSED ? upper + 1 : upper;
	}



	/**
	 * 区間スケジューリングを行います。<br>
	 * 計算量は要素数Nに対してO(NlogN)です。
	 * @param ranges 区間の集合
	 * @return 区間スケジューリングを行った際の一つの解
	 */
	public static List<IntRange> intScheduling(List<IntRange> ranges) {
		PriorityQueue<IntRange> pq = new PriorityQueue<IntRange>((l, r) -> l.compareUpper(r.upper, r.upperType));
		pq.addAll(ranges);
		List<IntRange> ret = new ArrayList<>();
		if (pq.isEmpty()) return ret;
		IntRange last = pq.poll();
		ret.add(last);
		while(!pq.isEmpty()) {
			IntRange tmp = pq.poll();
			System.err.println(tmp);
			if (tmp.compareLower(last.upper, last.upperType) > 0) {
				ret.add(tmp);
				last = tmp;
			}
		}
		return ret;
	}
}