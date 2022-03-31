package com._31536000.algo;

import com._31536000.util.collect.IntRange;

/**
 * Mo's Algorithmを用いて、クエリ平方分割に対する問題を解くことができます。<br>
 * このアルゴリズムを実行する時には、Moを継承したデータ構造に対してcalcを呼ぶことで実行を行います。<br>
 * Moを継承するデータ構造は次の操作を行うことができなければなりません。
 * <ul>
 * 	<li>{@link #add(i)} i番目の要素を追加します。</li>
 * 	<li>{@link #remove(i)} i番目の要素を削除します。</li>
 * 	<li>{@link #get(IntRange range)} 現在の区間における要素を返します。ここで、この区間はrangeに等しいです。</li>
 * </ul>
 * addとremoveにかかる計算量がf(x)、getにかかる計算量がg(x)であり、区間長がN、クエリ数がQの場合における計算量はO(Q(logN + g(x)) + Nsqrt(Q)f(x))です。
 * <br>もし削除ができないデータ構造であって、巻き戻し操作ができる場合は、代わりに{@link RollbackMo}を用いてください。
 * @author 31536000
 *
 * @param <T> クエリに対する答えの型
 */
public interface Mo<T> {
	/**
	 * i番目の要素を追加します。
	 * @param i 追加する要素のindex
	 */
	void add(int i);
	/**
	 * i番目の要素を削除します。
	 * @param i 削除する要素のindex
	 */
	void remove(int i);
	/**
	 * 現在、このデータ構造が保持している値を返します。<br>
	 * これはrangeで与えられる区間が保持している値に等しいです。
	 * @param range 現在の区間
	 * @return rangeで指定された区間が保持している値
	 */
	T get(IntRange range);
	/**
	 * 与えられたデータ構造を用いて、クエリに対する答えを返します。
	 * @param mo 用いるデータ構造
	 * @param query 求めたい区間クエリ
	 * @return 各クエリに対する答え
 * @complexity addとremoveにかかる計算量がf(x)、getにかかる計算量がg(x)であり、区間長がN、クエリ数がQの場合における計算量はO(Q(logN + g(x)) + Nsqrt(Q)f(x))
	 */
	static <T> java.util.List<T> calc(Mo<T> mo, IntRange[] query) {
		return calc(mo, java.util.Arrays.asList(query));
	}
	/**
	 * 与えられたデータ構造を用いて、クエリに対する答えを返します。
	 * @param mo 用いるデータ構造
	 * @param query 求めたい区間クエリ
	 * @return 各クエリに対する答え
 * @complexity addとremoveにかかる計算量がf(x)、getにかかる計算量がg(x)であり、区間長がN、クエリ数がQの場合における計算量はO(Q(logN + g(x)) + Nsqrt(Q)f(x))
	 */
	static <T> java.util.List<T> calc(Mo<T> mo, java.util.List<IntRange> query) {
		class Query{
			final int l, r, i;
			final long ord;
			Query(IntRange range, int index) {
				l = range.getClosedLower();
				r = range.getOpenUpper();
				i = index;
				ord = triangleorder(l, r, 32 - Integer.numberOfLeadingZeros(r));
			}
			long triangleorder(int x, int y, final int log) {
				long d = 0;
				for (int s = 1 << log - 1; s != 1; s >>= 1) {
					if (x >= s) {
						d += 36L * s * s >> 4;
						x -= s;
						y -= s;
					} else if (x + y > s << 1) {
						d += 24L * s * s >> 4;
						++ x;
						y = (s << 1) - y;
						x ^= y;
						y ^= x;
						x ^= y;
					} else if (y > s) {
						d += 12L * s * s >> 4;
						x = s - x;
						y -= s + 1;
						x ^= y;
						y ^= x;
						x ^= y;
					}
				}
				return d + x + y - 1;
			}
		}
		Query[] q = new Query[query.size()];
		for (int i = 0;i < q.length;++ i) q[i] = new Query(query.get(i), i);
		java.util.Arrays.sort(q, (l, r) -> Long.compare(l.ord, r.ord));
		Object[] ans = new Object[q.length];
		int left = q[0].l, right = q[0].l;
		for (Query i : q) {
			while(left > i.l) mo.add(-- left);
			while(right < i.r) mo.add(right ++);
			while(left < i.l) mo.remove(left ++);
			while(right > i.r) mo.remove(-- right);
			ans[i.i] = mo.get(query.get(i.i));
		}
		return java.util.Arrays.stream(ans).map(i -> {
			@SuppressWarnings("unchecked")
			T ret = (T)i;
			return ret;
		}).collect(java.util.stream.Collectors.toList());
	}
}
