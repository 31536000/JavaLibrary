package com._31536000.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compress<T extends Comparable<T>> {

	private Map<T, Integer> zip;
	private Object[] unzip;

	public Compress(T[] dat) {
		Arrays.sort(dat);
		zip = new HashMap<T, Integer>();
		List<T> unzip = new ArrayList<T>();
		for (int i = 0, j = 0; i < dat.length; ++i) {
			if (zip.put(dat[i], j++ ) != null) {
				zip.put(dat[i], --j - 1);
			} else unzip.add(dat[i]);
		}
		this.unzip = unzip.toArray();
	}

	public Compress(List<T> dat) {
		dat.sort(null);
		Object[] dats = dat.toArray();
		zip = new HashMap<T, Integer>();
		List<T> unzip = new ArrayList<T>();
		for (int i = 0, j = 0; i < dats.length; ++i) {
			@SuppressWarnings("unchecked")
			T push = (T)dats[i];
			if (zip.put(push, j++ ) != null) {
				zip.put(push, --j - 1);
			} else unzip.add(push);
		}
		this.unzip = unzip.toArray();
	}

	public int zip(T dat) {
		return zip.get(dat);
	}

	public T unzip(int index) {
		@SuppressWarnings("unchecked")
		T ret = (T)unzip[index];
		return ret;
	}

	public int size() {
		return unzip.length;
	}
}
