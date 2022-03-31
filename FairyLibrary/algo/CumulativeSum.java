package com._31536000.algo;

import java.util.Arrays;
import com._31536000.math.algebraic.group.Invertible;
import com._31536000.math.algebraic.group.Monoid;
import com._31536000.util.collect.IntRange;

/**
 * 累積和を計算します。
 * @author 31536000
 *
 */
public class CumulativeSum<T> {
	private final Object[] leftSum, rightSum;
	private final Monoid<T> operate;
	private final Invertible<T> inverse;

	@SuppressWarnings("unchecked")
	private CumulativeSum(T[] dat, Monoid<T> operate) {
		this.operate = operate;
		inverse = operate instanceof Invertible ? (Invertible<T>)operate : null;
		leftSum = new Object[dat.length + 1];
		rightSum = new Object[dat.length + 1];
		leftSum[0] = rightSum[dat.length] = operate.identity();
		for (int i = 0;i < dat.length;++ i) {
			leftSum[i + 1] = operate.apply((T)leftSum[i], dat[i]);
			rightSum[dat.length - i - 1] = operate.apply(dat[dat.length - i - 1], (T)rightSum[dat.length - i]);
		}
	}

	/**
	 * [0, index)の値の合計を返します。
	 * @param index 累積和の右辺
	 * @return [0, index)の値の合計
	 */
	public T getSum(int index) {
		@SuppressWarnings("unchecked")
		T ret = (T)leftSum[index];
		return ret;
	}

	/**
	 * [index, size)の値の合計を返します。
	 * @param index 累積和の左辺
	 * @return [index, size)の値の合計
	 */
	public T getLastSum(int index) {
		@SuppressWarnings("unchecked")
		T ret = (T)rightSum[index];
		return ret;
	}

	/**
	 * [0, index) + (index, size)の値の合計を返します。
	 * @param index 除外したい値
	 * @return indexを除く全区間の値の合計
	 */
	public T getAvoidSum(int index) {
		return getAvoidSum(index, index + 1);
	}

	/**
	 * [0, left) + [right, size)の値の合計を返します。
	 * @param left 除外する区間の左閉区間
	 * @param right 除外する区間の右開区間
	 * @return [left, right)を除く全区間の値の合計
	 */
	public T getAvoidSum(int left, int right) {
		return operate.apply(getSum(left), getLastSum(right));
	}

	/**
	 * rangeの区間を除いた値の合計を返します。
	 * @param range 除外する区間
	 * @return rangeの区間を除く全区間の値の合計
	 */
	public T getAvoidSum(IntRange range) {
		return getAvoidSum(range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 区間[left, right)の値の合計を返します。
	 * @param left 左閉区間
	 * @param right 右開区間
	 * @param inverse 逆元
	 * @return 区間[left, right)の値の合計
	 */
	public T getSum(int left, int right) {
		try {
			return operate.apply(inverse.inverse(getSum(left)), getSum(right));
		} catch (NullPointerException e) {
			if (left == 0) return getSum(right);
			if (right == rightSum.length) return getLastSum(left);
			throw e;
		}
	}

	/**
	 * rangeの区間の値の合計を返します。
	 * @param range 求める区間
	 * @param inverse 逆元
	 * @return rangeの区間の値の合計
	 */
	public T getSum(IntRange range) {
		return getSum(range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 累積和を構築します。
	 * @param dat 構築する配列
	 * @param operate 結合法則
	 * @return 累積和
	 */
	public static <E> CumulativeSum<E> create(E[] dat, Monoid<E> operate) {
		return new CumulativeSum<>(dat, operate);
	}

	/**
	 * 累積和を構築します。
	 * @param dat 結合法則を満たす、構築する配列
	 * @return 累積和
	 */
	public static <E extends Monoid<E>> CumulativeSum<E> create(E[] dat) {
		return new CumulativeSum<>(dat, dat[0]);
	}

	@Override
	public String toString() {
		return Arrays.toString(leftSum);
	}
}
