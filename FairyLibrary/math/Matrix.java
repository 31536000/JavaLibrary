package com._31536000.math;

import com._31536000.math.algebraic.ring.Ring;

public class Matrix<T extends Ring<T>>{
	int w, h;
	T[] matrix;
	Ring<T> ring;
	Matrix(T[][] matrix, Ring<T> ring) {
		if ((h = matrix.length) <= 0) throw new IllegalArgumentException("行列の大きさが不正です。");
		w = matrix[0].length;
		setMatrix(matrix);
	}

	Matrix(T[][] matrix) {
		this(matrix, (Ring<T>) matrix[0][0]);
	}

	Matrix(T[] matrix, int h, int w, Ring<T> ring) {
		if (matrix.length != h * w) throw new IllegalArgumentException("行列の大きさが不正です。");
		this.h = h;
		this.w = w;
		this.matrix = matrix;
		this.ring = ring;
	}

	Matrix(T[] matrix, int h, int w) {
		this(matrix, h, w, (Ring<T>) matrix[0]);
	}

	@SuppressWarnings("unchecked")
	private void setMatrix(T[][] matrix) {
		this.matrix = (T[]) new Object[h * w];
		for (int i = 0;i < matrix.length;++ i) {
			if (matrix[i].length != w) throw new IllegalArgumentException("行列の大きさが不正です。");
			for (int j = 0;j < matrix[i].length;++ j) {
				this.matrix[i * h + j] = matrix[i][j];
			}
		}
	}

	public Matrix<T> add(Matrix<T> m) {
		Matrix<T> ret = new Matrix<T>(matrix, h, w, ring);
		ret.addEqual(m);
		return ret;
	}

	public Matrix<T> addEqual(Matrix<T> m) {
		if (w != m.w || h != m.h) throw new IllegalArgumentException("行列の大きさが異なります。");
		for (int i = 0;i < matrix.length;++ i) {
			ring.plus(matrix[i], m.matrix[i]);
		}
		return this;
	}

	public Matrix<T> sub(Matrix<T> m) {
		Matrix<T> ret = new Matrix<T>(matrix, h, w, ring);
		ret.subEqual(m);
		return ret;
	}

	public Matrix<T> subEqual(Matrix<T> m) {
		if (w != m.w || h != m.h) throw new IllegalArgumentException("行列の大きさが異なります。");
		for (int i = 0;i < matrix.length;++ i) {
			ring.plus(matrix[i], ring.addInverse(m.matrix[i]));
		}
		return this;
	}

	public Matrix<T> mul(T m) {
		Matrix<T> ret = new Matrix<T>(matrix, h, w, ring);
		ret.mulEqual(m);
		return ret;
	}

	public Matrix<T> mul(Matrix<T> m) {
		if (w != m.h) throw new IllegalArgumentException("行列の大きさが異なります。");
		@SuppressWarnings("unchecked")
		T[] ret = (T[]) new Object[h * m.w];
		for (int i = 0;i < ret.length;++ i) {
			int l = i / m.w * w, r = i % w;
			ret[i] = ring.mul(matrix[l], m.matrix[r]);
			for (int j = 1;j < w;++ j) {
				ret[i] = ring.plus(ret[i], ring.mul(matrix[l + j], m.matrix[r + j * m.w]));
			}
		}
		return new Matrix<T>(ret, h, m.w, ring);
	}

	public Matrix<T> mulEqual(T m) {
		for (int i = 0;i < matrix.length;++ i) matrix[i] = ring.mul(matrix[i], m);
		return this;
	}

	public Matrix<T> mulEqual(Matrix<T> m) {
		Matrix<T> mul = mul(m);
		w = mul.w;
		h = mul.h;
		matrix = mul.matrix;
		return this;
	}

	public Matrix<T> getTransposeMatrix() {

	}

	public Matrix<T> getZeroMatrix(int h, int w, Ring<T> ring) {
		@SuppressWarnings("unchecked")
		T[] matrix = (T[]) new Object[h * w];
		for (int i = 0;i < matrix.length;++ i) matrix[i] = ring.addUnit();
		return new Matrix<T>(matrix, h, w, ring);
	}
}
