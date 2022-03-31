package com._31536000.algo;

import java.util.function.Predicate;

public interface SegmentTreeInterface<T, E> {
	/**
	 * データを更新します。
	 * @param dat 更新するデータ
	 * @param index 更新する場所
	 */
	public void update(E dat, int index);
	/**
	 * 区間[left, right)のデータを更新します。
	 * @param dat 更新するデータ
	 * @param left 更新する場所の左区間
	 * @param right 更新する場所の右区間
	 */
	public void update(E dat, int left, int right);
	/**
	 * 全ての値を取得します。
	 * @return 現在の値
	 */
	public Object[] get();
	/**
	 * 全ての値を取得します。
	 * @param array データを格納する配列
	 * @return 現在の値
	 */
	public T[] get(T[] array);
	/**
	 * 指定した場所の値を取得します。
	 * @param index 取得したい場所
	 * @return その場所の値
	 */
	public T get(int index);
	/**
	 * 指定した値を除く場所の値を取得します。
	 * @param index 取得しない場所
	 * @return 全体からindexを取り除いた値
	 */
	public default T getAvoid(int index) {
		return getAvoid(index, index + 1);
	}
	/**
	 * 指定した範囲の合計を取得します。
	 * @param left 範囲の左区間
	 * @param right 範囲の右区間
	 * @return 半開区間[left, right)の合計
	 */
	public T get(int left, int right);
	/**
	 * 指定した範囲を除く区間の合計を取得します。
	 * @param left 範囲の左区間
	 * @param right 範囲の右区間
	 * @return 半開区間[0, left)+[right, size)の合計
	 */
	public T getAvoid(int left, int right);
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
	 * 要素数を返します。
	 * @return 要素数
	 */
	public int size();
}
