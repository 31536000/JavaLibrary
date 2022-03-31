package com._31536000.algo;

import java.util.Deque;

public class UnionFindUndo extends UnionFind{

	private class Edge {
		private int index, value;
		private Edge(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
	Deque<Edge> history;

	public UnionFindUndo(int size) {
		super(size);
	}

	@Override
	/**
	 * その頂点がどの頂点を根とする連結成分に属しているかを求めます。<br>
	 * 計算量はO(log(N))です。
	 * @param n 根を求めたい頂点
	 * @return 根となっている頂点
	 */
	public int find(int n) {
		while(parent[n] >= 0) n = parent[n]; // 経路圧縮を行わない
		return n;
	}

	@Override
	/**
	 * 指定した頂点同士を連結します。<br>
	 * 計算量はO(log(N))です。
	 * @param l 連結したい頂点
	 * @param r 連結したい頂点
	 * @return この関数によって連結されたならtrue、既に連結だったならfalse
	 */
	public boolean unite(int l, int r) {
		if ((l = find(l)) == (r = find(r))) return false; // 最初から連結
		history.add(new Edge(l, parent[l]));
		history.add(new Edge(r, parent[r]));
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
	 * 連結状態を1個分巻き戻します。<br>
	 * 計算量はO(1)です。
	 */
	public void undo() {
		Edge e = history.pollLast();
		parent[e.index] = e.value;
		e = history.pollLast();
		parent[e.index] = e.value;
	}
}
