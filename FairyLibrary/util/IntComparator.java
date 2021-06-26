package util;

import java.util.Comparator;

/**
 * int型に特化した比較関数。
 * @author 31536000
 *
 */
public interface IntComparator extends Comparator<Integer> {
	int compareAsInt(int o1, int o2);

	@Override
	public default int compare(Integer o1, Integer o2) {
		return compareAsInt(o1, o2);
	}

	public static IntComparator naturalOrder() {
		return (o1, o2) -> Integer.compare(o1, o2);
	}

	@Override
	public default IntComparator reversed() {
		return (o1, o2) -> compareAsInt(o2, o1);
	}

	public static IntComparator reverseOrder() {
		return (o1, o2) -> Integer.compare(o2, o1);
	}
}
