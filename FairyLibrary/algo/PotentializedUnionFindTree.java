package com._31536000.algo;

import java.util.Arrays;
import com._31536000.math.algebraic.group.Abelian;
import com._31536000.math.algebraic.group.Associative;

/**
 * ポテンシャル付きの素集合データ構造です。<br>
 * 連結成分の個数や大きさ、その距離を取得することができます。
 * @author 31536000
 *
 * @param <T> 連結成分と一緒にマージしたい要素があれば
 * @param <W> 各辺の重み
 */
public class PotentializedUnionFindTree<T, W> {
	private int[] parent; // 負ならばデータ数、正ならば親のindex
	private T[] data;
	private Object[] potential;
	private Associative<T> merge;
	private Abelian<W> distance;

	/**
	 * size個の頂点を持つ森を作ります。
	 * @param size 頂点数
	 */
	public PotentializedUnionFindTree(int size, Abelian<W> potential) {
		this.parent = new int[size];
		Arrays.fill(this.parent, -1);
		distance = potential;
		this.potential = new Object[size];
		for (int i = 0;i < size;++ i) this.potential[i] = potential.identity();
	}

	/**
	 * 初期状態がdataで定義される森を作ります。
	 * @param data 各頂点の初期状態
	 * @param merge 連結時にマージする計算式
	 */
	public PotentializedUnionFindTree(T[] data, Associative<T> merge, Abelian<W> weight) {
		this(data.length, weight);
		this.data = data;
		this.merge = merge;
	}


	/**
	 * 初期状態がdataで定義される森を作ります。
	 * @param data 各頂点の初期状態
	 */
	@SuppressWarnings("unchecked")
	public PotentializedUnionFindTree(T[] data, Abelian<W> weight) {
		this(data, (Associative<T>) data[0], weight);
	}

	/**
	 * その頂点がどの頂点を根とする連結成分に属しているかを求めます。<br>
	 * 計算量はO(logN)です。
	 * @param n 根を求めたい頂点
	 * @return 根となっている頂点
	 */
	public int find(int n) {
		while(parent[n] >= 0) n = parent[n];
		return n;
	}

	/**
	 * 指定した頂点同士をlからrへ繋ぐように連結します。<br>
	 * 計算量はO(logN)です。
	 * @param l 連結したい頂点
	 * @param r 連結したい頂点
	 * @param w 繋ぐ辺の重み
	 * @return この関数によって連結されたならtrue、既に連結だったならfalse
	 */
	public boolean unite(int l, int r, W w) {
		if (isEqual(l, r)) return false;
		if (parent[l] >= parent[r]) { // rの方が大きい
			parent[r] += parent[l]; // 大きい方へ小さい方をマージする
			parent[l] = r;
			if (merge != null) data[r] = merge.apply(data[r], data[l]);
			@SuppressWarnings("unchecked")
			W t = (W)potential[l];
			potential[l] = distance.apply(t, w);
		} else {
			parent[l] += parent[r]; // 大きい方へ小さい方をマージする
			parent[r] = l;
			if (merge != null) data[l] = merge.apply(data[l], data[r]);
			@SuppressWarnings("unchecked")
			W t = (W)potential[r];
			potential[r] = distance.apply(t, distance.inverse(w));
		}
		return true;
	}

	/**
	 * 指定した2個の頂点が連結か判定します。<br>
	 * 計算量はO(logN)です。
	 * @param l 判定したい頂点
	 * @param r 判定したい頂点
	 * @return lとrが連結ならばtrue
	 */
	public boolean isEqual(int l, int r) {
		return find(l) == find(r);
	}

	/**
	 * 指定した頂点を含む連結成分の要素数を求めます。<br>
	 * 計算量はO(logN)です。
	 * @param n 要素数を求めたい連結成分の要素
	 * @return 頂点nを含む連結成分の要素数
	 */
	public int getSize(int n) {
		return -parent[find(n)];
	}

	/**
	 * 2つの頂点間の差を求めます。
	 * @param l 求めたい頂点
	 * @param r 求めたい頂点
	 * @return lからrへの頂点の差
	 */
	public W getDistance(int l, int r) {
		if (!isEqual(l, r)) throw new IllegalStateException("lとrは非連結です。");
		@SuppressWarnings("unchecked")
		W t = (W)potential[r];
		@SuppressWarnings("unchecked")
		W element = (W)potential[l];
		return distance.apply(t, distance.inverse(element));
	}

	/**
	 * 指定した頂点の要素を求めます。<br>
	 * 計算量はO(logN)です。
	 * @param n 求めたい頂点
	 * @return その頂点を含む連結成分の要素
	 */
	public T getData(int n) {
		return data[find(n)];
	}

	/**
	 * 連結成分の個数を求めます。<br>
	 * 計算量はO(logN)です。
	 * @return 連結成分の個数
	 */
	public int getUnion() {
		int ret = 0;
		for (int i : parent) if (i < 0) ++ ret;
		return ret;
	}
}
