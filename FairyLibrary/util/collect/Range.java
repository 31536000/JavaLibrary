package util.collect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Range<C> implements Serializable{

	private static final long serialVersionUID = -4702828934863023392L;
	protected C lower;
	protected C upper;
	protected BoundType lowerType;
	protected BoundType upperType;
	private Comparator<? super C> comparator;

	protected Range(C lower, BoundType lowerType, C upper, BoundType upperType) {
		this(lower, lowerType, upper, upperType, null);
	}

	protected Range(C lower, BoundType lowerType, C upper, BoundType upperType, Comparator<? super C> comparator) {
		this.lower = lower;
		this.upper = upper;
		this.lowerType = lowerType;
		this.upperType = upperType;
		this.comparator = comparator;
	}

	public static <C extends Comparable<? super C>> Range<C> range(C lower, BoundType lowerType, C upper, BoundType upperType) {
		if (lower != null && upper != null) {
			int comp = lower.compareTo(upper);
			if (comp > 0) return new Range<C>(null, BoundType.CLOSED, null, BoundType.CLOSED);
			else if (comp == 0 && (lowerType == BoundType.OPEN || upperType == BoundType.OPEN))return new Range<C>(null, BoundType.CLOSED, null, BoundType.CLOSED);
		}
		return new Range<C>(lower, lowerType, upper, upperType);
	}

	public static <C> Range<C> range(C lower, BoundType lowerType, C upper, BoundType upperType, Comparator<? super C> comparator) {
		if (lower != null && upper != null) {
			int comp = comparator.compare(lower, upper);
			if (comp > 0) return new Range<C>(null, BoundType.CLOSED, null, BoundType.CLOSED, comparator);
			else if (comp == 0 && (lowerType == BoundType.OPEN || upperType == BoundType.OPEN)) return new Range<C>(null, BoundType.CLOSED, null, BoundType.CLOSED, comparator);
		}
		return new Range<C>(lower, lowerType, upper, upperType, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> all() {
		return range((C)null, BoundType.OPEN, null, BoundType.OPEN);
	}

	public static <C> Range<C> all(Comparator<? super C> comparator) {
		return range(null, BoundType.OPEN, null, BoundType.OPEN, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> atMost(C upper) {
		return range(null, BoundType.OPEN, upper, BoundType.CLOSED);
	}

	public static <C> Range<C> atMost(C upper, Comparator<? super C> comparator) {
		return range(null, BoundType.OPEN, upper, BoundType.CLOSED, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> lessThan(C upper) {
		return range(null, BoundType.OPEN, upper, BoundType.OPEN);
	}

	public static <C> Range<C> lessThan(C upper, Comparator<? super C> comparator) {
		return range(null, BoundType.OPEN, upper, BoundType.OPEN, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> downTo(C upper, BoundType boundType) {
		return range(null, BoundType.OPEN, upper, boundType);
	}

	public static <C> Range<C> downTo(C upper, BoundType boundType, Comparator<? super C> comparator) {
		return range(null, BoundType.OPEN, upper, boundType, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> atLeast(C lower) {
		return range(lower, BoundType.CLOSED, null, BoundType.OPEN);
	}

	public static <C> Range<C> atLeast(C lower, Comparator<? super C> comparator) {
		return range(lower, BoundType.CLOSED, null, BoundType.OPEN, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> greaterThan(C lower) {
		return range(lower, BoundType.OPEN, null, BoundType.OPEN);
	}

	public static <C> Range<C> greaterThan(C lower, Comparator<? super C> comparator) {
		return range(lower, BoundType.OPEN, null, BoundType.OPEN, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> upTo(C lower, BoundType boundType) {
		return range(lower, boundType, null, BoundType.OPEN);
	}

	public static <C> Range<C> upTo(C lower, BoundType boundType, Comparator<? super C> comparator) {
		return range(lower, boundType, null, BoundType.OPEN, comparator  );
	}

	public static <C extends Comparable<? super C>> Range<C> open(C lower, C upper) {
		return range(lower, BoundType.OPEN, upper, BoundType.OPEN);
	}

	public static <C> Range<C> open(C lower, C upper, Comparator<? super C> comparator) {
		return range(lower, BoundType.OPEN, upper, BoundType.OPEN, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> openClosed(C lower, C upper) {
		return range(lower, BoundType.OPEN, upper, BoundType.CLOSED);
	}

	public static <C> Range<C> openClosed(C lower, C upper, Comparator<? super C> comparator) {
		return range(lower, BoundType.OPEN, upper, BoundType.CLOSED, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> closedOpen(C lower, C upper) {
		return range(lower, BoundType.CLOSED, upper, BoundType.OPEN);
	}

	public static <C> Range<C> closedOpen(C lower, C upper, Comparator<? super C> comparator) {
		return range(lower, BoundType.CLOSED, upper, BoundType.OPEN, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> closed(C lower, C upper) {
		return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
	}

	public static <C> Range<C> closed(C lower, C upper, Comparator<? super C> comparator) {
		return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> singleton(C value) {
		return range(value, BoundType.CLOSED, value, BoundType.CLOSED);
	}

	public static <C> Range<C> singleton(C value, Comparator<? super C> comparator) {
		return range(value, BoundType.CLOSED, value, BoundType.CLOSED, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> empty() {
		return range((C)null, BoundType.CLOSED, null, BoundType.CLOSED);
	}

	public static <C> Range<C> empty(Comparator<? super C> comparator) {
		return range(null, BoundType.CLOSED, null, BoundType.CLOSED, comparator);
	}

	public static <C extends Comparable<? super C>> Range<C> encloseAll(Iterable<C> values) {
		C lower = values.iterator().next();
		C upper = lower;
		for (C i : values) {
			if (lower.compareTo(i) > 0) lower = i;
			if (upper.compareTo(i) < 0) upper = i;
		}
		return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED);
	}

	public static <C> Range<C> encloseAll(Iterable<C> values, Comparator<? super C> comparator) {
		C lower = values.iterator().next();
		C upper = lower;
		for (C i : values) {
			if (comparator.compare(lower, i) > 0) lower = i;
			if (comparator.compare(upper, i) < 0) upper = i;
		}
		return range(lower, BoundType.CLOSED, upper, BoundType.CLOSED, comparator);
	}

	protected int compareLower(C value) {
		return compareLower(value, BoundType.CLOSED);
	}

	protected int compareLower(C value, BoundType boundType) {
		return compareLower(lower, lowerType, value, boundType);
	}

	protected int compareLower(C lower, BoundType lowerType, C value) {
		return compareLower(lower, lowerType, value, BoundType.CLOSED);
	}

	protected int compareLower(C lower, BoundType lowerType, C value, BoundType boundType) {
		if (lower == null) return value == null ? 0 : -1;
		else if (value == null) return 1;
		int compare;
		if (comparator == null) {
			@SuppressWarnings("unchecked")
			Comparable<C> comp = (Comparable<C>)lower;
			compare = comp.compareTo(value);
		} else compare = comparator.compare(lower, value);
		if (compare == 0) {
			if (lowerType == BoundType.CLOSED) -- compare;
			if (boundType == BoundType.CLOSED) ++ compare;
		}
		return compare;
	}

	protected int compareUpper(C value) {
		return compareUpper(value, BoundType.CLOSED);
	}

	protected int compareUpper(C value, BoundType boundType) {
		return compareUpper(upper, upperType, value, boundType);
	}

	protected int compareUpper(C upper, BoundType upperType, C value) {
		return compareUpper(upper, upperType, value, BoundType.CLOSED);
	}

	protected int compareUpper(C upper, BoundType upperType, C value, BoundType boundType) {
		if (upper == null) return value == null ? 0 : 1;
		if (value == null) return -1;
		int compare;
		if (comparator == null) {
			@SuppressWarnings("unchecked")
			Comparable<C> comp = (Comparable<C>)upper;
			compare = comp.compareTo(value);
		} else compare = comparator.compare(upper, value);
		if (compare == 0) {
			if (upperType == BoundType.CLOSED) ++ compare;
			if (boundType == BoundType.CLOSED) -- compare;
		}
		return compare;
	}

	public boolean hasLowerBound() {
		return lower != null;
	}

	public C lowerEndpoint() {
		if (hasLowerBound()) return lower;
		throw new IllegalStateException();
	}

	public BoundType lowerBoundType() {
		if (hasLowerBound()) return lowerType;
		throw new IllegalStateException();
	}

	public boolean hasUpperBound() {
		return upper != null;
	}

	public C upperEndpoint() {
		if (hasUpperBound()) return upper;
		throw new IllegalStateException();
	}

	public BoundType upperBoundType() {
		if (hasUpperBound()) return upperType;
		throw new IllegalStateException();
	}

	/**
	 * この区間が空集合か判定します。
	 * @return 空集合ならばtrue
	 */
	public boolean isEmpty() {
		return lower == null && upper == null && lowerType == BoundType.CLOSED;
	}

	/**
	 * 与えられた引数が区間の左側に位置するか判定します。<br>
	 * 接する場合は区間の左側ではないと判定します。
	 * @param value 調べる引数
	 * @return 区間の左側に位置するならtrue
	 */
	public boolean isLess(C value) {
		return isLess(value, BoundType.CLOSED);
	}

	protected boolean isLess(C value, BoundType boundType) {
		return compareLower(value, boundType) > 0;
	}

	/**
	 * 与えられた引数が区間の右側に位置するか判定します。<br>
	 * 接する場合は区間の右側ではないと判定します。
	 * @param value 調べる引数
	 * @return 区間の右側に位置するならtrue
	 */
	public boolean isGreater(C value) {
		return isGreater(value, BoundType.CLOSED);
	}

	private boolean isGreater(C value, BoundType boundType) {
		return compareUpper(value, boundType) < 0;
	}

	/**
	 * 与えられた引数が区間内に位置するか判定します。<br>
	 * 接する場合も区間内に位置すると判定します。
	 * @param value 調べる引数
	 * @return 区間内に位置するならtrue
	 */
	public boolean contains(C value) {
		return !isLess(value) && !isGreater(value) && !isEmpty();
	}

	/**
	 * 与えられた引数すべてが区間内に位置するか判定します。<br>
	 * 接する場合も区間内に位置すると判定します。
	 * @param value 調べる要素
	 * @return 全ての要素が区間内に位置するならtrue
	 */
	public boolean containsAll(Iterable<? extends C> values) {
		for (C i : values) if (!contains(i)) return false;
		return true;
	}

	/**
	 * 与えられた区間がこの区間に内包されるか判定します。<br>
	 *
	 * @param other
	 * @return 与えられた区間がこの区間に内包されるならtrue
	 */
	public boolean encloses(Range<C> other) {
		return !isLess(other.lower, other.lowerType) && !isGreater(other.upper, other.upperType);
	}

	/**
	 * 与えられた区間がこの区間と公差するか判定します。<br>
	 * 接する場合は公差するものとします。
	 * @param value 調べる引数
	 * @return 区間が交差するならtrue
	 */
	public boolean isConnected(Range<C> other) {
		if (this.isEmpty() || other.isEmpty()) return false;
		C lower, upper;
		BoundType lowerType, upperType;
		if (isLess(other.lower, other.lowerType)) {
			lower = other.lower;
			lowerType = other.lowerType;
		} else {
			lower = this.lower;
			lowerType = this.lowerType;
		}
		if (isGreater(other.upper, other.upperType)) {
			upper = other.upper;
			upperType = other.upperType;
		} else {
			upper = this.upper;
			upperType = this.upperType;
		}
		if (lower == null || upper == null) return true;
		int comp = compareLower(lower, lowerType, upper, upperType);
		return comp <= 0;
	}
	/**
	 * この区間との積集合を返します。
	 * @param connectedRange 積集合を求める区間
	 * @return 積集合
	 */
	public Range<C> intersection(Range<C> connectedRange) {
		if (this.isEmpty() || connectedRange.isEmpty()) {
			if (comparator == null) return new Range<C>(null, BoundType.CLOSED, null, BoundType.CLOSED);
			return empty(comparator);
		}
		C lower, upper;
		BoundType lowerType, upperType;
		if (isLess(connectedRange.lower, connectedRange.lowerType)) {
			lower = connectedRange.lower;
			lowerType = connectedRange.lowerType;
		} else {
			lower = this.lower;
			lowerType = this.lowerType;
		}
		if (isGreater(connectedRange.upper, connectedRange.upperType)) {
			upper = connectedRange.upper;
			upperType = connectedRange.upperType;
		} else {
			upper = this.upper;
			upperType = this.upperType;
		}
		if (comparator == null) {
			return new Range<C>(lower, lowerType, upper, upperType);
		}
		return range(lower, lowerType, upper, upperType, comparator);
	}

	/**
	 * この区間との和集合を返します。
	 * @param other 和集合を求める区間
	 * @return 和集合
	 */
	public Range<C> span(Range<C> other) {
		if (other.isEmpty()) return new Range<C>(lower, lowerType, upper, upperType);
		C lower, upper;
		BoundType lowerType, upperType;
		if (isLess(other.lower, other.lowerType)) {
			lower = this.lower;
			lowerType = this.lowerType;
		} else {
			lower = other.lower;
			lowerType = other.lowerType;
		}
		if (isGreater(other.upper, other.upperType)) {
			upper = this.upper;
			upperType = this.upperType;
		} else {
			upper = other.upper;
			upperType = other.upperType;
		}
		return new Range<C>(lower, lowerType, upper, upperType, comparator);
	}

	/**
	 * 区間スケジューリングを行います。<br>
	 * 計算量は要素数Nに対してO(NlogN)です。
	 * @param ranges 区間の集合
	 * @return 区間スケジューリングを行った際の一つの解
	 */
	public static <C> List<Range<C>> scheduling(List<Range<C>> ranges) {
		PriorityQueue<Range<C>> pq = new PriorityQueue<Range<C>>((l, r) -> l.compareUpper(r.upper, r.upperType));
		List<Range<C>> ret = new ArrayList<>();
		Range<C> last = pq.poll();
		if (pq.isEmpty()) return ret;
		ret.add(last);
		while(!pq.isEmpty()) {
			Range<C> tmp = pq.poll();
			if (tmp.compareLower(last.upper, last.upperType) > 0) {
				ret.add(tmp);
				last = tmp;
			}
		}
		return ret;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object instanceof Range) {
			@SuppressWarnings("unchecked")
			Range<C> comp = (Range<C>) object;
			return compareLower(comp.lower, comp.lowerType) == 0 && compareUpper(comp.upper, comp.upperType) == 0 && lowerType == comp.lowerType && upperType == comp.upperType;
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (lower == null && upper == null) return 0;
		else if (lower == null) return upper.hashCode();
		else if (upper == null) return lower.hashCode();
		return lower.hashCode() ^ upper.hashCode();
	}

	@Override
	public String toString() {
		if (isEmpty()) return "()";
		return (lowerType == BoundType.OPEN ? "(" : "[") + (lower == null ? "" : lower.toString()) + ".." + (upper == null ? "" : upper.toString()) + (upperType == BoundType.OPEN ? ")" : "]");
	}
}
