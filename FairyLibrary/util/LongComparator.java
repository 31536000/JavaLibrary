package com._31536000.util;

import java.util.Comparator;

/**
 * long型に特化した比較関数。
 * @author 31536000
 *
 */
public interface LongComparator extends Comparator<Long> {
	int compare(long o1, long o2);

	@Override
	public default int compare(Long o1, Long o2) {
		return compare(o1, o2);
	}

	public static LongComparator naturalOrder() {
		return (o1, o2) -> Long.compare(o1, o2);
	}

	@Override
	public default LongComparator reversed() {
		return (o1, o2) -> compare(o2, o1);
	}

	public static LongComparator reverseOrder() {
		return (o1, o2) -> Long.compare(o2, o1);
	}
}
