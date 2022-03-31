package com._31536000.util;

import java.util.Comparator;

/**
 * double型に特化した比較関数。
 * @author 31536000
 *
 */
public interface DoubleComparator extends Comparator<Double> {
	int compare(double o1, double o2);

	@Override
	public default int compare(Double o1, Double o2) {
		return compare(o1, o2);
	}

	public static DoubleComparator naturalOrder() {
		return (o1, o2) -> Double.compare(o1, o2);
	}

	@Override
	public default DoubleComparator reversed() {
		return (o1, o2) -> compare(o2, o1);
	}

	public static DoubleComparator reverseOrder() {
		return (o1, o2) -> Double.compare(o2, o1);
	}
}
