package com._31536000.primitive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * int型に対する様々な操作を提供するユーティリティクラスです。
 * @author 31536000
 *
 */
public class Ints {

	private Ints() {
		throw new AssertionError();
	}

	/**
	 * 整数型配列を整数型リストに変換します。<br>
	 * これはArrays.asList(backingArray)のInteger型に特化した形です。
	 * @param backingArray int型配列
	 * @return int型リスト
	 */
	public static List<Integer> asList(int... array) {
		List<Integer> ret = new ArrayList<Integer>();
		for (int i : array) ret.add(i);
		return ret;
	}

	/**
	 * long型の整数がint型の整数に変換できるならその値を、変換できないなら例外を返します。
	 * @param value int型に変換する整数
	 * @return int型に変換した整数
	 * @throws IllegalArgumentException int型の範囲ではない時
	 */
	public static int checkedCast(long value) {
		if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) throw new IllegalArgumentException();
		return (int)value;
	}

	/**
	 * 複数のint型配列を結合して、一つの配列にします。
	 * @param arrays int型配列の配列
	 * @return 結合した配列
	 */
	public static int[] concat(int[]... arrays) {
		int len = 0;
		for (int[] i : arrays) len += i.length;
		int[] ret = new int[len];
		len = 0;
		for (int[] i : arrays) {
			System.arraycopy(i, 0, ret, len, i.length);
			len += i.length;
		}
		return ret;
	}

	/**
	 * 配列内にtargetと同じ値があるか調べます。<br>
	 * 計算量は先頭からtargetと同じ値のインデックスの距離に等しいです。
	 * @param array 調べる配列
	 * @param target 調べる値
	 * @return targetがあればtrue
	 */
	public static boolean contains(int[] array, int target) {
		for (int i : array) if (i == target) return true;
		return false;
	}

	/**
	 * 配列内にtargetと同じ値があるか調べ、そのインデックスを返します。<br>
	 * 複数ある場合は先頭のインデックスが返ります。<br>
	 * 計算量は先頭からtargetと同じ値のインデックスの距離に等しいです。
	 * @param array 調べる配列
	 * @param target 調べる値
	 * @return targetがあればそのインデックス、無ければ-1
	 */
	public static int indexOf(int[] array, int target) {
		for (int i = 0;i < array.length;++ i) if (array[i] == target) return i;
		return -1;
	}

	/**
	 * 配列内にtargetと同じ値があるか調べ、そのインデックスを返します。<br>
	 * 複数ある場合は末尾のインデックスが返ります。<br>
	 * 計算量は末尾からtargetと同じ値のインデックスの距離に等しいです。
	 * @param array 調べる配列
	 * @param target 調べる値
	 * @return targetがあればそのインデックス、無ければ-1
	 */
	public static int lastIndexOf(int[] array, int target) {
		for (int i = array.length - 1;i >= 0;-- i) if (array[i] == target) return i;
		return -1;
	}

	/**
	 * 配列をseparator区切りで文字列に変換します。
	 * @param separator 区切り文字
	 * @param array 出力する配列
	 * @return 配列をseparator区切りで変換した文字列
	 */
	public static String join(String separator, int... array) {
		StringBuilder ret = new StringBuilder(String.valueOf(array[0]));
		for (int i = 1;i < array.length;++ i) ret.append(separator).append(array[i]);
		return ret.toString();
	}

	/**
	 * リストからint型の配列を生成します。
	 * @param collection 配列にしたいリスト
	 * @return int型配列
	 */
	public static int[] toArray(Collection<? extends Number> collection) {
		int[] ret = new int[collection.size()];
		int c = -1;
		for (Number i : collection) ret[++c] = i.intValue();
		return ret;
	}

}
