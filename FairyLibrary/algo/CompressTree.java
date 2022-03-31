package com._31536000.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 座標圧縮をするライブラリです。
 * @author 31536000
 *
 * @param <T> 座標圧縮を行う型
 */
public class CompressTree<T extends Comparable<T>>{
	private TreeMap<T, Integer> zip;
	private Object[] unzip;

	/**
	 * datを用いて座圧します。
	 * @param dat 座圧する値
	 */
	public CompressTree(T[] dat) {
		Arrays.sort(dat);
		zip = new TreeMap<T, Integer>();
		List<T> unzip = new ArrayList<T>();
		for (int i = 0, j = 0;i < dat.length;++ i) {
			if (zip.put(dat[i], j++) != null) {
				zip.put(dat[i], --j-1);
			} else unzip.add(dat[i]);
		}
		this.unzip = unzip.toArray();
	}

	/**
	 * datを用いて座圧します。
	 * @param dat 座圧する値
	 */
	public CompressTree(List<T> dat) {
		dat.sort(null);
		Object[] dats = dat.toArray();
		zip = new TreeMap<T, Integer>();
		List<T> unzip = new ArrayList<T>();
		for (int i = 0, j = 0;i < dats.length;++ i) {
			@SuppressWarnings("unchecked")
			T push = (T) dats[i];
			if (zip.put(push, j++) != null) {
				zip.put(push, --j-1);
			} else unzip.add(push);
		}
		this.unzip = unzip.toArray();
	}

	/**
	 * 座圧した値を求めます。
	 * @param dat 座圧前の値
	 * @return 座圧後の値
	 */
	public int zip(T dat) {
		return zip.get(dat);
	}

	/**
	 * 座圧した値を求めます。
	 * @param dat 座圧前の値
	 * @return 座圧後の値
	 */
	public int floorZip(T dat) {
		return zip.floorEntry(dat).getValue();
	}

	/**
	 * 座圧した値を求めます。
	 * @param dat 座圧前の値
	 * @return 座圧後の値
	 */
	public int ceilingZip(T dat) {
		return zip.ceilingEntry(dat).getValue();
	}

	/**
	 * 座圧前の値を求めます。
	 * @param index 座圧後の値
	 * @return 座圧前の値
	 */
	public T unzip(int index) {
		@SuppressWarnings("unchecked")
		T ret = (T)unzip[index];
		return ret;
	}

	/**
	 * 要素数を求めます。
	 * @return ユニークな要素数
	 */
	public int size() {
		return unzip.length;
	}
}
