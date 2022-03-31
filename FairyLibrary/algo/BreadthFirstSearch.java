package com._31536000.algo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com._31536000.util.collect.CircularArray;

/**
 * 幅優先探索を自動的に行うライブラリです。
 * @author 31536000
 *
 * @param <T> 幅優先探索の対象となる型
 */
public class BreadthFirstSearch<T> implements Iterable<T>, Iterator<T>{
	CircularArray<Deque<T>> queue;
	HashSet<T> hash;
	int size;

	/**
	 * キューを1個用いるBFSを用意します。
	 */
	public BreadthFirstSearch() {
		this(1);
	}

	/**
	 * キューをn個用いるBFSを用意します。
	 * @param n 使うキューの数
	 */
	public BreadthFirstSearch(int n) {
		queue = new CircularArray<Deque<T>>(n);
		for (int i = 0;i < n;++ i) queue.set(i, new ArrayDeque<T>());
		hash = new HashSet<T>();
		size = 0;
	}

	/**
	 * キューを1個用いて、最初に調べる場所がtのBFSを作成します。
	 * @param t 最初に調べる値
	 */
	public BreadthFirstSearch(T t) {
		this(1, t);
	}

	/**
	 * キューをn個用いて、最初に調べる場所がtのBFSを作成します。
	 * @param n 使うキューの数
	 * @param t 最初に調べる値
	 */
	public BreadthFirstSearch(int n, T t) {
		this(n);
		push(t);
	}

	/**
	 * 第n番目のキューに新しくtを調べる対象に追加します。
	 * @param n 使うキューの場所(0はstackとして処理される)
	 * @param t 調べる値
	 * @return 既にtを調べたことがあるならfalse、まだ調べてないならtrue
	 */
	public boolean push(int n, T t) {
		if (n < 0 || n > queue.size()) throw new IndexOutOfBoundsException();
		if (n == 0) { // stackとして使う
			Deque<T> que = queue.front();
			if (!hash.add(t)) return false;
			que.addFirst(t);
		} else { // queueとして使う
			Deque<T> que = queue.get(n - 1);
			if (!hash.add(t)) return false;
			que.addLast(t);
		}
		++ size;
		return true;
	}

	/**
	 * 1番目のキューに新しくtを調べる対象に追加します。
	 * @param t 調べる値
	 * @return 既にtを調べたことがあるならfalse、まだ調べてないならtrue
	 */
	public boolean push(T t) {
		if (!hash.add(t)) return false;
		queue.front().addLast(t);
		++ size;
		return true;
	}

	/**
	 * 既にその要素を調べているか返します。
	 * @param t 調べる値
	 * @return 既に調べていたらtrue
	 */
	public boolean contains(T t) {
		return hash.contains(t);
	}

	/**
	 * BFSを初期化します。
	 */
	public void clear() {
		for (Deque<T> i : queue) i.clear();
		hash.clear();
		size = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return size > 0;
	}

	@Override
	public T next() {
		if (size <= 0) throw new NoSuchElementException();
		while (queue.front().isEmpty()) queue.rotateNext();
		-- size;
		return queue.front().pollFirst();
	}
}
