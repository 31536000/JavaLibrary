package com._31536000.algo;

import java.util.Arrays;

/**
 * 素集合データ構造です。<br>
 * 連結成分の個数や大きさを取得することができます。
 * @author 31536000
 */
public class UnionFind {
	protected int[] parent; // 負ならばデータ数、正ならば親のindex
	protected int size;

	/**
	 * size個の頂点を持つ森を作ります。
	 * @param size 頂点数
	 */
	public UnionFind(int size) {
		this.size = size;
		this.parent = new int[size];
		Arrays.fill(this.parent, -1);
	}

	/**
	 * その頂点がどの頂点を根とする連結成分に属しているかを求めます。<br>
	 * 計算量はO(α(N))です。
	 * @param n 根を求めたい頂点
	 * @return 根となっている頂点
	 */
	public int find(int n) {
		while(parent[n] >= 0 && parent[parent[n]] >= 0) n = parent[n] = parent[parent[n]]; // 経路圧縮
		return parent[n] >= 0 ? parent[n] : n;
	}

	/**
	 * 指定した頂点同士を連結します。<br>
	 * 計算量はO(α(N))です。
	 * @param l 連結したい頂点
	 * @param r 連結したい頂点
	 * @return この関数によって連結されたならtrue、既に連結だったならfalse
	 */
	public boolean unite(int l, int r) {
		if ((l = find(l)) == (r = find(r))) return false; // 最初から連結
		if (parent[l] >= parent[r]) { // rの方が大きいなら入れ替える
			int swap = l;
			l = r;
			r = swap;
		}
		parent[l] += parent[r]; // 大きい方へ小さい方をマージする
		parent[r] = l;
		-- size;
		return true;
	}

	/**
	 * 指定した頂点が根か判定します。<br>
	 * 計算量はO(1)です。
	 * @param n 判定したい頂点
	 * @return nが根ならばtrue
	 */
	public boolean isRoot(int n) {
		return parent[n] < 0;
	}

	/**
	 * 指定した2個の頂点が連結か判定します。<br>
	 * 計算量はO(α(N))です。
	 * @param l 判定したい頂点
	 * @param r 判定したい頂点
	 * @return lとrが連結ならばtrue
	 */
	public boolean isUnion(int l, int r) {
		return find(l) == find(r);
	}

	/**
	 * 指定した頂点を含む連結成分の要素数を求めます。<br>
	 * 計算量はO(α(N))です。
	 * @param n 要素数を求めたい連結成分の要素
	 * @return 頂点nを含む連結成分の要素数
	 */
	public int getSize(int n) {
		return -parent[find(n)];
	}

	/**
	 * 連結成分の個数を求めます。<br>
	 * 計算量はO(1)です。
	 * @return 連結成分の個数
	 */
	public int getUnion() {
		return size;
	}
}
