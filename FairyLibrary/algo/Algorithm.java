package com._31536000.algo;

import java.util.function.BiFunction;
import java.util.function.Predicate;
import com._31536000.util.collect.IntRange;

public class Algorithm {

	private Algorithm() {
		throw new AssertionError();
	}

	/**
	 * 二分探索を行い、func(x) != func(x+1)となるような数xを発見します。
	 * funcが単調な関数であるとき、発見されるxは一意に定まります。
	 * @param isTrue func(isTrue)=trueとなるような値
	 * @param isFalse func(isFalse)=falseとなるような値
	 * @param func 関数
	 * @complexity O(log(max(isTrue, isFalse) - min(isTrue, isFalse)))
	 * @return func(x) != func(x+1)となるような数x
	 */
	public static int binarySearch(int isTrue, int isFalse, java.util.function.IntPredicate func) {
		if (isTrue <= isFalse) {
			int halfDiff = isFalse - isTrue >> 1, mid = isTrue + halfDiff;
			while(halfDiff != 0) {
				if (func.test(mid)) isTrue = mid;
				else isFalse = mid;
				halfDiff = isFalse - isTrue >> 1;
				mid = isTrue + halfDiff;
			}
			return isTrue;
		} else {
			int halfDiff = isTrue - isFalse >> 1, mid = isFalse + halfDiff;
			while(halfDiff != 0) {
				if (func.test(mid)) isTrue = mid;
				else isFalse = mid;
				halfDiff = isTrue - isFalse >> 1;
				mid = isFalse + halfDiff;
			}
			return isFalse;
		}
	}

	/**
	 * 二分探索を行い、func(x) != func(x+1)となるような数xを発見します。
	 * funcが単調な関数であるとき、発見されるxは一意に定まります。
	 * @param isTrue func(isTrue)=trueとなるような値
	 * @param isFalse func(isFalse)=falseとなるような値
	 * @param func 関数
	 * @complexity O(log(max(isTrue, isFalse) - min(isTrue, isFalse)))
	 * @return func(x) != func(x+1)となるような数x
	 */
	public static long binarySearch(long isTrue, long isFalse, java.util.function.LongPredicate func) {
		if (isTrue <= isFalse) {
			long halfDiff = isFalse - isTrue >> 1, mid = isTrue + halfDiff;
			while(halfDiff != 0) {
				if (func.test(mid)) isTrue = mid;
				else isFalse = mid;
				halfDiff = isFalse - isTrue >> 1;
				mid = isTrue + halfDiff;
			}
			return isTrue;
		} else {
			long halfDiff = isTrue - isFalse >> 1, mid = isFalse + halfDiff;
			while(halfDiff != 0) {
				if (func.test(mid)) isTrue = mid;
				else isFalse = mid;
				halfDiff = isTrue - isFalse >> 1;
				mid = isFalse + halfDiff;
			}
			return isFalse;
		}
	}

	/**
	 * 二分探索を行い、func(x) != func(x+Math.nextUp(x))となるような数xを発見します。
	 * funcが単調な関数であるとき、発見されるxは一意に定まります。
	 * @param isTrue func(isTrue)=trueとなるような値
	 * @param isFalse func(isFalse)=falseとなるような値
	 * @param func 関数
	 * @complexity O(log(max(isTrue, isFalse) - min(isTrue, isFalse)))
	 * @return func(x) != func(x+Math.nextUp(x))となるような数x
	 */
	public static double binarySearch(double isTrue, double isFalse, java.util.function.DoublePredicate func) {
		if (isTrue <= isFalse) {
			double mid = (isTrue + isFalse) / 2;
			while(isTrue < mid && mid < isFalse) {
				if (func.test(mid)) isTrue = mid;
				else isFalse = mid;
				mid = (isTrue + isFalse) / 2;
			}
			return isTrue;
		} else {
			double mid = (isTrue + isFalse) / 2;
			while(isFalse < mid && mid < isTrue) {
				if (func.test(mid)) isTrue = mid;
				else isFalse = mid;
				mid = (isTrue + isFalse) / 2;
			}
			return isFalse;
		}
	}

	/**
	 * 下に凸な関数の極小値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param loop 探索回数
	 * @param func 関数
	 * @complexity O(log(max-min))
	 * @return 極小値
	 */
	public static <T extends Comparable<T>> double find_minimal(double min, double max, int loop, java.util.function.DoubleFunction<T> func) {
		return find_minimal(min, max, loop, func, java.util.Comparator.naturalOrder());
	}

	/**
	 * 下に凸な関数の極小値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param loop 探索回数
	 * @param func 関数
	 * @param comparator 比較関数
	 * @complexity O(log(max-min))
	 * @return 極小値
	 */
	public static <T> double find_minimal(double min, double max, int loop, java.util.function.DoubleFunction<T> func, java.util.Comparator<T> comparator) {
		double phi = (1 + Math.sqrt(5)) / 2;
		for (int i = 0;i < loop;++ i) {
			double mid_min = (min * phi + max) / (1 + phi), mid_max = (min + max * phi) / (1 + phi);
			T mid_min_calc = func.apply(mid_min), mid_max_calc = func.apply(mid_max);
			if (comparator.compare(mid_min_calc, mid_max_calc) <= 0) max = mid_max;
			else min = mid_min;
		}
		return min;
	}

	/**
	 * 上に凸な関数の極大値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param loop 探索回数
	 * @param func 関数
	 * @complexity O(log(max-min))
	 * @return 極大値
	 */
	public static <T extends Comparable<T>> double find_maximal(double min, double max, int loop, java.util.function.DoubleFunction<T> func) {
		return find_maximal(min, max, loop, func, java.util.Comparator.naturalOrder());
	}

	/**
	 * 上に凸な関数の極大値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param loop 探索回数
	 * @param func 関数
	 * @param comparator 比較関数
	 * @complexity O(log(max-min))
	 * @return 極大値
	 */
	public static <T> double find_maximal(double min, double max, int loop, java.util.function.DoubleFunction<T> func, java.util.Comparator<T> comparator) {
		if (max <= min) throw new IllegalArgumentException("empty range");
		double phi = (1 + Math.sqrt(5)) / 2;
		for (int i = 0;i < loop;++ i) {
			double mid_min = (min * phi + max) / (1 + phi), mid_max = (min + max * phi) / (1 + phi);
			T mid_min_calc = func.apply(mid_min), mid_max_calc = func.apply(mid_max);
			if (comparator.compare(mid_min_calc, mid_max_calc) >= 0) max = mid_max;
			else min = mid_min;
		}
		return min;
	}

	/**
	 * 下に凸な関数の極小値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @complexity O(log(max-min))
	 * @return 極小値
	 */
	public static <T extends Comparable<T>> int find_minimal(int min, int max, java.util.function.IntFunction<T> func) {
		return find_minimal(min, max, func, java.util.Comparator.naturalOrder());
	}

	/**
	 * 下に凸な関数の極小値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @param comparator 比較関数
	 * @complexity O(log(max-min))
	 * @return 極小値
	 */
	public static <T> int find_minimal(int min, int max, java.util.function.IntFunction<T> func, java.util.Comparator<T> comparator) {
		-- min;
		int range = max - min;
		if (range <= 1) throw new IllegalArgumentException("empty range");
		int fib_small = 1, fib_large = 1;
		while(fib_large < range) {
			fib_large += fib_small;
			fib_small = fib_large - fib_small;
		}
		T mid_min_calc = null, mid_max_calc = null;
		int last_calc = -1;
		final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
		while(max - min > 2) {
			fib_small = fib_large - fib_small;
			fib_large -= fib_small;
			int mid_min = min + fib_small, mid_max = min + fib_large;
			if (mid_max >= max) {
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
				continue;
			}
			if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
			if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
			if (comparator.compare(mid_min_calc, mid_max_calc) <= 0) {
				max = mid_max;
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
			} else {
				min = mid_min;
				mid_min_calc = mid_max_calc;
				last_calc = LAST_CALC_IS_MIN;
			}
		}
		return min + 1;
	}

	/**
	 * 上に凸な関数の極大値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @complexity O(log(max-min))
	 * @return 極大値
	 */
	public static <T extends Comparable<T>> int find_maximal(int min, int max, java.util.function.IntFunction<T> func) {
		return find_maximal(min, max, func, java.util.Comparator.naturalOrder());
	}

	/**
	 * 上に凸な関数の極大値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @param comparator 比較関数
	 * @complexity O(log(max-min))
	 * @return 極大値
	 */
	public static <T> int find_maximal(int min, int max, java.util.function.IntFunction<T> func, java.util.Comparator<T> comparator) {
		-- min;
		int range = max - min;
		if (range <= 1) throw new IllegalArgumentException("empty range");
		int fib_small = 1, fib_large = 1;
		while(fib_large < range) {
			fib_large += fib_small;
			fib_small = fib_large - fib_small;
		}
		T mid_min_calc = null, mid_max_calc = null;
		int last_calc = -1;
		final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
		while(max - min > 2) {
			fib_small = fib_large - fib_small;
			fib_large -= fib_small;
			int mid_min = min + fib_small, mid_max = min + fib_large;
			if (mid_max >= max) {
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
				continue;
			}
			if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
			if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
			if (comparator.compare(mid_min_calc, mid_max_calc) >= 0) {
				max = mid_max;
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
			} else {
				min = mid_min;
				mid_min_calc = mid_max_calc;
				last_calc = LAST_CALC_IS_MIN;
			}
		}
		return min + 1;
	}

	/**
	 * 下に凸な関数の極小値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @complexity O(log(max-min))
	 * @return 極小値
	 */
	public static <T extends Comparable<T>> long find_minimal(long min, long max, java.util.function.LongFunction<T> func) {
		return find_minimal(min, max, func, java.util.Comparator.naturalOrder());
	}

	/**
	 * 下に凸な関数の極小値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @param comparator 比較関数
	 * @complexity O(log(max-min))
	 * @return 極小値
	 */
	public static <T> long find_minimal(long min, long max, java.util.function.LongFunction<T> func, java.util.Comparator<T> comparator) {
		-- min;
		long range = max - min;
		if (range <= 1) throw new IllegalArgumentException("empty range");
		long fib_small = 1, fib_large = 1;
		while(fib_large < range) {
			fib_large += fib_small;
			fib_small = fib_large - fib_small;
		}
		T mid_min_calc = null, mid_max_calc = null;
		int last_calc = -1;
		final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
		while(max - min > 2) {
			fib_small = fib_large - fib_small;
			fib_large -= fib_small;
			long mid_min = min + fib_small, mid_max = min + fib_large;
			if (mid_max >= max) {
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
				continue;
			}
			if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
			if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
			if (comparator.compare(mid_min_calc, mid_max_calc) <= 0) {
				max = mid_max;
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
			} else {
				min = mid_min;
				mid_min_calc = mid_max_calc;
				last_calc = LAST_CALC_IS_MIN;
			}
		}
		return min + 1;
	}

	/**
	 * 上に凸な関数の極大値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @complexity O(log(max-min))
	 * @return 極大値
	 */
	public static <T extends Comparable<T>> long find_maximal(long min, long max, java.util.function.LongFunction<T> func) {
		return find_maximal(min, max, func, java.util.Comparator.naturalOrder());
	}

	/**
	 * 上に凸な関数の極大値を発見します。
	 * @param <T> 関数の終域
	 * @param min 関数の定義域の下界(これを含む)
	 * @param max 関数の定義域の上界(これを含まない)
	 * @param func 関数
	 * @param comparator 比較関数
	 * @complexity O(log(max-min))
	 * @return 極大値
	 */
	public static <T> long find_maximal(long min, long max, java.util.function.LongFunction<T> func, java.util.Comparator<T> comparator) {
		-- min;
		long range = max - min;
		if (range <= 1) throw new IllegalArgumentException("empty range");
		long fib_small = 1, fib_large = 1;
		while(fib_large < range) {
			fib_large += fib_small;
			fib_small = fib_large - fib_small;
		}
		T mid_min_calc = null, mid_max_calc = null;
		int last_calc = -1;
		final int LAST_CALC_IS_MIN = 0, LAST_CALC_IS_MAX = 1;
		while(max - min > 2) {
			fib_small = fib_large - fib_small;
			fib_large -= fib_small;
			long mid_min = min + fib_small, mid_max = min + fib_large;
			if (mid_max >= max) {
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
				continue;
			}
			if (last_calc != LAST_CALC_IS_MIN) mid_min_calc = func.apply(mid_min);
			if (last_calc != LAST_CALC_IS_MAX) mid_max_calc = func.apply(mid_max);
			if (comparator.compare(mid_min_calc, mid_max_calc) >= 0) {
				max = mid_max;
				mid_max_calc = mid_min_calc;
				last_calc = LAST_CALC_IS_MAX;
			} else {
				min = mid_min;
				mid_min_calc = mid_max_calc;
				last_calc = LAST_CALC_IS_MIN;
			}
		}
		return min + 1;
	}

	/**
	 * 尺取り法を計算します。
	 * @param l 区間の左端(これを含む)
	 * @param r 区間の右端(これを含まない)
	 * @param empty 空集合に対する値
	 * @param f 与えられた区間が尺取り法で数え上げる条件を満たすならtrue
	 * @param addRight 区間に当該の値を追加する処理
	 * @param removeLeft 区間から当該の値を取り除く処理
	 * @param def 答えが存在しなかったときの答え
	 * @param ans 今までの答えと条件を満たす区間幅が与えられた時の次の答え
	 * @return 尺取り法の答え
	 */
	public static <T, E> E syakutori(int l, int r, T empty, Predicate<T> f, BiFunction<T, Integer, T> addRight, BiFunction<T, Integer, T> removeLeft, E def, BiFunction<E, Integer, E> ans) {
		for (int i = l, j = l;j < r;def = ans.apply(def, j - i)) {
			empty = addRight.apply(empty, j++);
			while (!f.test(empty) && i < j) empty = removeLeft.apply(empty, i++);
		}
		return def;
	}

	/**
	 * 尺取り法を計算します。
	 * @param l 計算をする区間
	 * @param empty 空集合に対する値
	 * @param f 与えられた区間が尺取り法で数え上げる条件を満たすならtrue
	 * @param addRight 区間に当該の値を追加する処理
	 * @param removeLeft 区間から当該の値を取り除く処理
	 * @param def 答えが存在しなかったときの答え
	 * @param ans 今までの答えと条件を満たす区間幅が与えられた時の次の答え
	 * @return 尺取り法の答え
	 */
	public static <T, E> E syakutori(IntRange range, T empty, Predicate<T> f, BiFunction<T, Integer, T> addRight, BiFunction<T, Integer, T> removeLeft, E def, BiFunction<E, Integer, E> ans) {
		return syakutori(range.getClosedLower(), range.getOpenUpper(), empty, f, addRight, removeLeft, def, ans);
	}

	public static interface IntBiFunction<T> extends java.util.function.BiFunction<Integer, Integer, T>{
		@Override
		public default T apply(Integer left, Integer right) {
			return apply((int)left, (int)right);
		}
		public T apply(int left, int right);
	}

	public static interface IntToLongBiFunction extends IntBiFunction<Long>{
		@Override
		public default Long apply(int left, int right) {
			return applyAsLong(left, right);
		}
		public long applyAsLong(int left, int right);
	}

	public static interface IntToDoubleBiFunction extends IntBiFunction<Double>{
		@Override
		public default Double apply(int left, int right) {
			return applyAsDouble(left, right);
		}
		public double applyAsDouble(int left, int right);
	}

	/**
	 * 行列fが任意のi\<jについてargmin(f(i, *)) < argmin(f(j, *))を満たすとき、各iに対してargmin(f(i, *))を求めます。
	 * @complexity O(h log w)
	 * @param h fの高さ
	 * @param w fの幅
	 * @param f 行列
	 * @param comparator 値の大小比較
	 * @return 各iについてargmin(f(i, *))を格納した、長さhの配列
	 */
	public static <T> int[] monotoneMinima(int h, int w, IntBiFunction<T> f, java.util.Comparator<T> comparator) {
		int[] ret = new int[h];
		monotoneMinima(0, 0, h, w, f, comparator, ret);
		return ret;
	}

	private static <T> void monotoneMinima(int y, int x, int h, int w, IntBiFunction<T> f, java.util.Comparator<T> comparator, int[] ans) {
		if (y >= h) return;
		if (y == h - 1) {
			int argmin = x;
			if (x != w - 1) {
				T min = f.apply(y, x);
				for (int i = x + 1;x < w;++ x) {
					T tmp = f.apply(y, i);
					if (comparator.compare(min, tmp) > 0) {
						min = tmp;
						argmin = i;
					}
				}
			}
			ans[y] = argmin;
			return;
		}
		int half = y + (h - y >> 1);
		monotoneMinima(half, x, half + 1, w, f, comparator, ans);
		monotoneMinima(y, x, half, ans[half], f, comparator, ans);
		monotoneMinima(half + 1, ans[half], h, w, f, comparator, ans);
	}

	/**
	 * 行列fが任意のi\<jについてargmin(f(i, *)) < argmin(f(j, *))を満たすとき、各iに対してargmin(f(i, *))を求めます。
	 * @complexity O(h log w)
	 * @param h fの高さ
	 * @param w fの幅
	 * @param f 行列
	 * @return 各iについてargmin(f(i, *))を格納した、長さhの配列
	 */
	public static <T> int[] monotoneMinima(int h, int w, java.util.function.IntBinaryOperator f) {
		int[] ret = new int[h];
		monotoneMinima(0, 0, h, w, f, ret);
		return ret;
	}

	private static <T> void monotoneMinima(int y, int x, int h, int w, java.util.function.IntBinaryOperator f, int[] ans) {
		if (y >= h) return;
		if (y == h - 1) {
			int argmin = x;
			if (x != w - 1) {
				int min = f.applyAsInt(y, x);
				for (int i = x + 1;x < w;++ x) {
					int tmp = f.applyAsInt(y, i);
					if (min > tmp) {
						min = tmp;
						argmin = i;
					}
				}
			}
			ans[y] = argmin;
			return;
		}
		int half = y + (h - y >> 1);
		monotoneMinima(half, x, half + 1, w, f, ans);
		monotoneMinima(y, x, half, ans[half], f, ans);
		monotoneMinima(half + 1, ans[half], h, w, f, ans);
	}

	/**
	 * 行列fが任意のi\<jについてargmin(f(i, *)) < argmin(f(j, *))を満たすとき、各iに対してargmin(f(i, *))を求めます。
	 * @complexity O(h log w)
	 * @param h fの高さ
	 * @param w fの幅
	 * @param f 行列
	 * @return 各iについてargmin(f(i, *))を格納した、長さhの配列
	 */
	public static <T> int[] monotoneMinima(int h, int w, IntToLongBiFunction f) {
		int[] ret = new int[h];
		monotoneMinima(0, 0, h, w, f, ret);
		return ret;
	}

	private static <T> void monotoneMinima(int y, int x, int h, int w, IntToLongBiFunction f, int[] ans) {
		if (y >= h) return;
		if (y == h - 1) {
			int argmin = x;
			if (x != w - 1) {
				long min = f.applyAsLong(y, x);
				for (int i = x + 1;x < w;++ x) {
					long tmp = f.applyAsLong(y, i);
					if (min > tmp) {
						min = tmp;
						argmin = i;
					}
				}
			}
			ans[y] = argmin;
			return;
		}
		int half = y + (h - y >> 1);
		monotoneMinima(half, x, half + 1, w, f, ans);
		monotoneMinima(y, x, half, ans[half], f, ans);
		monotoneMinima(half + 1, ans[half], h, w, f, ans);
	}

	/**
	 * 行列fが任意のi\<jについてargmin(f(i, *)) < argmin(f(j, *))を満たすとき、各iに対してargmin(f(i, *))を求めます。
	 * @complexity O(h log w)
	 * @param h fの高さ
	 * @param w fの幅
	 * @param f 行列
	 * @return 各iについてargmin(f(i, *))を格納した、長さhの配列
	 */
	public static <T> int[] monotoneMinima(int h, int w, IntToDoubleBiFunction f) {
		int[] ret = new int[h];
		monotoneMinima(0, 0, h, w, f, ret);
		return ret;
	}

	private static <T> void monotoneMinima(int y, int x, int h, int w, IntToDoubleBiFunction f, int[] ans) {
		if (y >= h) return;
		if (y == h - 1) {
			int argmin = x;
			if (x != w - 1) {
				double min = f.applyAsDouble(y, x);
				for (int i = x + 1;x < w;++ x) {
					double tmp = f.applyAsDouble(y, i);
					if (min > tmp) {
						min = tmp;
						argmin = i;
					}
				}
			}
			ans[y] = argmin;
			return;
		}
		int half = y + (h - y >> 1);
		monotoneMinima(half, x, half + 1, w, f, ans);
		monotoneMinima(y, x, half, ans[half], f, ans);
		monotoneMinima(half + 1, ans[half], h, w, f, ans);
	}
}
