package com._31536000.util.collect;


public class MultiDimensionalArray<T> extends AbstractArray<T>{
	final int[] dimension;

	private static int getLength(int[] dimension) {
		long length = 1;
		for (int i : dimension) {
			if (i < 0) throw new IllegalArgumentException("negative dimension");
			length *= i;
			if (length > Integer.MAX_VALUE) throw new IllegalArgumentException("too big array");
		}
		return (int)length;
	}

	public MultiDimensionalArray(int... dimension) {
		super(getLength(dimension));
		this.dimension = dimension;
	}

	public T set(T element, int...index) {
		
	}



}
