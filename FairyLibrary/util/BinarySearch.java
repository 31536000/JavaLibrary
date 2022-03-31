package com._31536000.util;

import java.util.function.Predicate;
import com._31536000.util.collect.IntRange;

public interface BinarySearch<T> {

	public int size();

	/**
	 * 指定した範囲で二分探索を行います。
	 * @param left 範囲の左区間
	 * @param right 範囲の右区間
	 * @param f 単調性を持つ関数
	 * @return 半開区間[left, right)において関数fがtrueを返す最大の値(無ければleft-1)<br>
	 * ただしleft>rightなら、半開区間[right, left)において関数fがtrueを返す最小の値(無ければleft)
	 */
	public int binarySearch(int left, int right, Predicate<T> f);

	/**
	 * 指定した範囲で二分探索を行います。
	 * @param range 二分探索を行う区間
	 * @param f 単調性を持つ関数
	 * @return 区間rangeにおいて関数fがtrueを返す最大の値(無ければrangeの左区間より小さい最大の値)
	 */
	public default int binarySearch(IntRange range, Predicate<T> f) {
		return binarySearch(range.getClosedLower(), range.getOpenUpper(), f);
	}

	/**
	 * 全区間で二分探索を行います。
	 * @param f 単調性を持つ関数
	 * @return 半開区間[0, size)において関数fがtrueを返す最大の値(無ければleft-1)
	 */
	public default int binarySearch(Predicate<T> f) {
		return binarySearch(0, size(), f);
	}

	/**
	 * 指定した範囲で二分探索を行います。
	 * @param left 範囲の左区間
	 * @param f 単調性を持つ関数
	 * @return 半開区間[left, size)において関数fがtrueを返す最大の値(無ければleft-1)
	 */
	public default int binarySearch(int left, Predicate<T> f) {
		return binarySearch(left, size(), f);
	}
}
