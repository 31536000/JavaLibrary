package com._31536000.math;

import com._31536000.math.algebraic.ring.Ring;

public class SquareMatrix<T extends Ring<T>> extends Matrix<T> implements Ring<SquareMatrix<T>>{

	SquareMatrix(T[][] matrix, Ring<T> ring) {
		super(matrix, ring);
		if (h != w) throw new IllegalArgumentException("行列の大きさが不正です。");
	}

	SquareMatrix(T[][] matrix) {
		this(matrix, (Ring<T>) matrix[0][0]);
	}

	SquareMatrix(T[] matrix, int n, Ring<T> ring) {
		super(matrix, n, n, ring);
	}

	SquareMatrix(T[] matrix, int n) {
		this(matrix, n, (Ring<T>) matrix[0]);
	}

	SquareMatrix(T[] matrix) {
		this(matrix, (int)Math.round(Math.sqrt(matrix.length)), (Ring<T>) matrix[0]);
	}

	public SquareMatrix<T> add(SquareMatrix<T> m) {
		return new SquareMatrix<T>(matrix, h, ring).addEqual(m);
	}

	public SquareMatrix<T> addEqual(SquareMatrix<T> m) {
		if (h != m.h) throw new IllegalArgumentException("行列の大きさが異なります。");
		for (int i = 0;i < matrix.length;++ i) ring.plus(matrix[i], m.matrix[i]);
		return this;
	}

	public SquareMatrix<T> sub(SquareMatrix<T> m) {
		return new SquareMatrix<T>(matrix, h, ring).subEqual(m);
	}

	public SquareMatrix<T> subEqual(SquareMatrix<T> m) {
		if (h != m.h) throw new IllegalArgumentException("行列の大きさが異なります。");
		for (int i = 0;i < matrix.length;++ i) ring.plus(matrix[i], ring.addInverse(m.matrix[i]));
		return this;
	}

	public SquareMatrix<T> mul(T m) {
		return new SquareMatrix<T>(matrix, h, ring).mulEqual(m);
	}

	public SquareMatrix<T> mul(SquareMatrix<T> m) { // TODO 要実装検討
		if (h != m.h) throw new IllegalArgumentException("行列の大きさが異なります。");
		@SuppressWarnings("unchecked")
		T[] ret = (T[]) new Object[h * m.w];
		for (int i = 0;i < ret.length;++ i) {
			int l = i / m.w * w, r = i % w;
			ret[i] = ring.mul(matrix[l], m.matrix[r]);
			for (int j = 1;j < w;++ j) {
				ret[i] = ring.plus(ret[i], ring.mul(matrix[l + j], m.matrix[r + j * m.w]));
			}
		}
		return new SquareMatrix<T>(ret, h, ring);
	}

	public SquareMatrix<T> mulEqual(T m) {
		for (int i = 0;i < matrix.length;++ i) matrix[i] = ring.mul(matrix[i], m);
		return this;
	}

	public SquareMatrix<T> mulEqual(SquareMatrix<T> m) { // TODO 要実装検討
		SquareMatrix<T> mul = mul(m);
		w = mul.w;
		h = mul.h;
		matrix = mul.matrix;
		return this;
	}

	@Override
	public SquareMatrix<T> add(SquareMatrix<T> left, SquareMatrix<T> right) {
		return left.add(right);
	}

	@Override
	public SquareMatrix<T> addUnit() {
		return getZeroMatrix(h, ring);
	}

	@Override
	public SquareMatrix<T> addInverse(SquareMatrix<T> element) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public SquareMatrix<T> mul(SquareMatrix<T> left, SquareMatrix<T> right) {
		return left.mul(right);
	}

	@Override
	public SquareMatrix<T> mulUnit() {
		return getIdentifyMatrix(h, ring);
	}

	@Override
	public SquareMatrix<T> mulInverse(SquareMatrix<T> element) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public SquareMatrix<T> getZeroMatrix(int n, Ring<T> ring) {
		@SuppressWarnings("unchecked")
		T[] matrix = (T[]) new Object[n * n];
		for (int i = 0;i < matrix.length;++ i) matrix[i] = ring.addUnit();
		return new SquareMatrix<T>(matrix, n, ring);
	}

	public SquareMatrix<T> getIdentifyMatrix(int n, Ring<T> ring) {
		@SuppressWarnings("unchecked")
		T[] matrix = (T[]) new Object[n * n];
		for (int i = 0;i < matrix.length;++ i) matrix[i] = i % (h + 1) == 0 ? ring.mulUnit() : ring.addUnit();
		return new SquareMatrix<T>(matrix, n, ring);
	}

}
