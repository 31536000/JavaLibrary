package com._31536000.util.collect;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Stack;

/**
*
* {@link Deque}インタフェースのサイズ変更可能な配列の実装です。
* 配列の両端キューに容量制限はなく、使用量をサポートするために必要に応じて大きくなります。
* それらはスレッドセーフではありません。
* 外部の同期化がない場合、複数のスレッドによる並行アクセスはサポートされません。
* null要素は禁止されています。
* このクラスは通常、スタックとして使われるときは{@link Stack}よりも高速で、キューとして使われるときは{@link LinkedList}よりも高速です。
* <br>
* ほとんどのArrayDequeオペレーションは、償却定数時間で実行されます。
* 例外には、{@link #remove}、{@link #removeFirstOccurrence}、{@link #removeLastOccurrence}、{@link #contains}、{@link Iterator#remove()
* iterator.remove()}、および一括オペレーションがあり、これらはすべて要素の数に比例する時間で実行されます。
* <br>
* このクラスのiteratorメソッドによって返されるイテレータは、フェイルファストです。
* イテレータの作成後に、イテレータ自体のremoveメソッド以外の方法で両端キューが変更されると、イテレータは通常{@link ConcurrentModificationException}をスローします。
* このように、並行して変更が行われると、イテレータは、将来の予測できない時点において予測できない動作が発生する危険を回避するために、ただちにかつ手際よく例外をスローします。
* <br>
* 通常、非同期の並行変更がある場合、確かな保証を行うことは不可能なので、イテレータのフェイルファストの動作を保証することはできません。
* フェイルファスト・イテレータは、ベスト・エフォート・ベースでConcurrentModificationExceptionをスローします。
* したがって、正確を期すためにこの例外に依存するプログラムを書くことは誤りです。
* イテレータのフェイルファストの動作はバグを検出するためにのみ使用すべきです。
* <br>
* このクラスとそのイテレータは、{@link Collection}および{@link Iterator}インタフェースのオプション・メソッドすべてを実装します。
*
* @param <E> このコレクション内に存在する要素の型
*/
public class ArrayDeque<E> extends AbstractList<E> implements RandomAccess, Deque<E>, Serializable, Cloneable {

	private static final long serialVersionUID = 514786748285690729L;
	private Object[] deque;
	private int first, last;
	private int size = 0, mask;

	/**
	 * 16個の要素を保持するために十分な初期容量を持つ、空の配列両端キューを作成します。
	 */
	public ArrayDeque() {
		this(16);
	}

	/**
	 * 指定された数の要素を保持するために十分な初期容量を持つ、空の配列両端キューを作成します。
	 *
	 * @param numElements 両端キューの初期容量の範囲の下限
	 */
	public ArrayDeque(int numElements) {
		deque = new Object[Math.max(16, Integer.highestOneBit(numElements - 1) << 1)];
		mask = deque.length - 1;
		first = last = deque.length >> 1;
	}

	/**
	 * 指定されたコレクションのイテレータが返す順序で、その要素を格納する両端キューを作成します。
	 * コレクションのイテレータによって返される最初の要素が、最初の要素、つまり両端キューの先頭になります。
	 *
	 * @param c 要素が両端キューに配置されるコレクション
	 * @exception NullPointerException 指定されたコレクションがnullである場合
	 */
	public ArrayDeque(Collection<? extends E> c) {
		deque = new Object[Math.max(16, Integer.highestOneBit(c.size() - 1) << 2)];
		mask = deque.length - 1;
		first = last = deque.length - c.size() >> 1;
		for (E i : c) deque[last++] = i;
	}

	private void resize() {
		if (size >= deque.length) resize(size);
	}

	private void resize(final int size) {
		final int n = Math.max(16, Integer.highestOneBit(size - 1) << 2);
		mask = n - 1;
		final Object[] newdeque = new Object[n];
		final int length = size;
		if (first < last) {
			System.arraycopy(deque, first, newdeque, n >> 2, length);
		} else {
			System.arraycopy(deque, first, newdeque, n >> 2, deque.length - first);
			System.arraycopy(deque, 0, newdeque, (n >> 2) + deque.length - first, last);
		}
		deque = newdeque;
		first = n >> 2;
		last = first + length;
	}

	/**
	 * この両端キューに要素が含まれていない場合にtrueを返します。
	 *
	 * @return この両端キューに要素が含まれていない場合はtrue
	 */
	@Override
	public boolean isEmpty() {
		return first == last;
	}

	/**
	 * 両端キュー内のすべての要素を適切な順序(最初の要素から最後の要素へ)で格納している配列を返します。
	 * <br>
	 * 返される配列への参照を両端キューが維持しないという点で、この配列は安全です。(つまり、このメソッドは新しい配列を割り当てます。)
	 * このため、呼出し側は、返された配列を自由に変更できます。
	 * <br>
	 * このメソッドは、配列ベースのAPIとコレクションベースのAPIの間の橋渡し役として機能します。
	 *
	 * @return 両端キューのすべての要素が格納されている配列
	 */
	@Override
	public Object[] toArray() {
		Object[] ret = new Object[size()];
		if (first < last) {
			System.arraycopy(deque, first, ret, 0, ret.length);
		} else {
			System.arraycopy(deque, first, ret, 0, deque.length - first);
			System.arraycopy(deque, 0, ret, deque.length - first, last);
		}
		return ret;
	}

	/**
	 * 両端キュー内のすべての要素を適切な順序(最初の要素から最後の要素へ)で格納している配列を返します。
	 * 返される配列の実行時の型は、指定された配列の型になります。
	 * 指定された配列に両端キューが収まる場合は、その中に返されます。
	 * そうでない場合は、指定された配列の実行時の型と両端キューのサイズを持つ新しい配列が割り当てられます。
	 * <br>
	 * 両端キューが指定された配列に収まり、その配列にさらに余裕がある場合(つまり、配列が両端キューより多くの要素を持つ場合)、その配列内で両端キューの終端よりあとの要素はnullに設定されます。
	 * <br>
	 * {@link #toArray()}メソッドと同じように、このメソッドは、配列ベースのAPIとコレクションベースのAPIの間の橋渡し役として機能します。
	 * さらに、このメソッドでは、出力配列の実行時の型を正確に制御できるため、環境によっては割当ての手間を抑えることができます。
	 * <br>
	 * xが、文字列だけからなる両端キューであることがわかっていると仮定します。
	 * 次のコードを使うと、新しく割り当てられたStringの配列に両端キューをダンプできます。
	 * <br>
	 * {@code String[] y = x.toArray(new String[0]);}
	 * <br>
	 * toArray(new Object[0])は、機能の点でtoArray()と同一です。
	 *
	 * @param   <T> コレクションを含む配列の実行時の型
	 * @param a 配列が十分な大きさを持つ場合は、両端キューの要素が格納される配列。
	 *          そうでない場合は、要素を格納するために同じ実行時の型の新しい配列が割り当てられる
	 * @return 両端キューのすべての要素が格納されている配列
	 * @exception ArrayStoreException  指定された配列の実行時の型が、この両端キュー内のすべての要素の実行時の型のスーパー・タイプでない場合
	 * @exception NullPointerException 指定された配列がnullである場合
	 *
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		int n = size();
		if (a.length < n) {
			@SuppressWarnings("unchecked")
			T[] copy = (T[])java.lang.reflect.Array.newInstance(a.getClass(), n);
			a = copy;
		}
		if (first < last) {
			System.arraycopy(deque, first, a, 0, a.length);
		} else {
			System.arraycopy(deque, first, a, 0, deque.length - first);
			System.arraycopy(deque, 0, a, deque.length - first, last);
		}
		Arrays.fill(a, n, a.length, null);
		return a;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean ret = true;
		for (Object i : c) ret &= contains(i);
		return ret;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean ret = false;
		for (E i : c) ret |= add(i);
		return ret;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean ret = false;
		for (Object i : c) ret |= remove(i);
		return ret;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		int update = first, check = first;
		while (check != last) {
			boolean retain = false;
			for (Object o : c)
				retain |= Objects.equals(deque[check], o);
			if (retain) {
				deque[update] = check;
				update = update + 1 & mask;
			}
			check = check + 1 & mask;
		}
		if (last == update) return false;
		last = update;
		resize();
		return true;
	}

	/**
	 * すべての要素を両端キューから削除します。
	 * この呼出しが戻ると、両端キューは空になります。
	 */
	@Override
	public void clear() {
		deque = new Object[16];
		first = 8;
		last = first;
		size = 0;
		++ modCount;
	}

	/**
	 * 指定された要素をこの両端キューの先頭に挿入します。
	 *
	 * @param e 追加する要素
	 * @exception NullPointerException 指定された要素がnullである場合
	 */
	@Override
	public void addFirst(E e) {
		if (e == null) throw new NullPointerException();
		deque[first = first - 1 & mask] = e;
		++ modCount;
		++ size;
		resize();
	}

	/**
	 * 指定された要素をこの両端キューの末尾に挿入します。
	 * <br>
	 * このメソッドは、{@link #add(E)}と同等です。
	 *
	 * @param e 追加する要素
	 * @exception NullPointerException 指定された要素がnullである場合
	 */
	@Override
	public void addLast(E e) {
		if (e == null) throw new NullPointerException();
		deque[last] = e;
		last = last + 1 & mask;
		++ modCount;
		++ size;
		resize();
	}

	/**
	 * 指定された要素をこの両端キューの先頭に挿入します。
	 *
	 * @param e 追加する要素
	 * @return true ({@link Deque#offerFirst(E)}で指定されているとおり)
	 * @exception NullPointerException 指定された要素がnullである場合
	 */
	@Override
	public boolean offerFirst(E e) {
		if (e == null) throw new NullPointerException();
		deque[first = first - 1 & mask] = e;
		++ modCount;
		++ size;
		resize();
		return true;
	}


	/**
	 * 指定された要素をこの両端キューの末尾に挿入します。
	 *
	 * @param e 追加する要素
	 * @return true ({@link Deque#offerLast(E)}で指定されているとおり)
	 * @exception NullPointerException 指定された要素がnullである場合
	 */
	@Override
	public boolean offerLast(E e) {
		if (e == null) throw new NullPointerException();
		deque[last] = e;
		last = last + 1 & mask;
		++ modCount;
		++ size;
		resize();
		return true;
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		deque[first] = null;
		first = first + 1 & mask;
		-- size;
		++ modCount;
		resize();
		return ret;
	}

	@Override
	public E removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		E ret = (E)deque[last = last - 1 & mask];
		deque[last] = null;
		-- size;
		++ modCount;
		resize();
		return ret;
	}

	@Override
	public E pollFirst() {
		if (isEmpty()) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		deque[first] = null;
		first = first + 1 & mask;
		-- size;
		++ modCount;
		resize();
		return ret;
	}

	@Override
	public E pollLast() {
		if (isEmpty()) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)deque[last = last - 1 & mask];
		deque[last] = null;
		-- size;
		++ modCount;
		resize();
		return ret;
	}

	@Override
	public E getFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		return ret;
	}

	@Override
	public E getLast() {
		if (isEmpty()) throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		E ret = (E)deque[last - 1];
		return ret;
	}

	@Override
	public E peekFirst() {
		if (isEmpty()) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		return ret;
	}

	@Override
	public E peekLast() {
		if (isEmpty()) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)deque[last - 1];
		return ret;
	}

	/**
	 * 両端キューを先頭から末尾までトラバースするときに、この両端キューの指定された要素のうち最初に出現したものを削除します。
	 * その要素が両端キューにない場合、変更はありません。
	 * つまり、o.equals(e)となる最初の要素eが存在する場合は、その要素を削除します。
	 * 指定された要素がこの両端キューに含まれていた場合(すなわち、呼出しの結果としてこの両端キューが変更された場合)はtrueを返します。
	 *
	 * @param o この両端キューから削除される要素(その要素が存在する場合)
	 * @return 指定された要素がこの両端キューに含まれていた場合はtrue
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		int check = first;
		while (check != last) {
			if (Objects.equals(deque[check], o)) {
				localRemove(check);
				return true;
			}
			check = check + 1 & mask;
		}
		return false;
	}

	/**
	 * 両端キューを先頭から末尾までトラバースするときに、この両端キューの指定された要素のうち最後に出現したものを削除します。
	 * その要素が両端キューにない場合、変更はありません。
	 * つまり、o.equals(e)となる最後の要素eが存在する場合は、その要素を削除します。
	 * 指定された要素がこの両端キューに含まれていた場合(すなわち、呼出しの結果としてこの両端キューが変更された場合)はtrueを返します。
	 *
	 * @param o この両端キューから削除される要素(その要素が存在する場合)
	 * @return 指定された要素がこの両端キューに含まれていた場合はtrue
	 */
	@Override
	public boolean removeLastOccurrence(Object o) {
		int check = last;
		while (check != first) {
			if (Objects.equals(deque[check], o)) {
				localRemove(check);
				return true;
			}
			check = check - 1 & mask;
		}
		return false;
	}

	/**
	 * 指定された要素をこの両端キューの末尾に挿入します。
	 * <br>
	 * このメソッドは、{@link #addLast(E)}と同等です。
	 *
	 * @param e 追加する要素
	 * @return true ({@link Collection#add(E)}で指定されているとおり)
	 * @exception NullPointerException 指定された要素がnullである場合
	 */
	@Override
	public boolean add(E e) {
		if (e == null) throw new NullPointerException();
		deque[last] = e;
		last = last + 1 & mask;
		++ modCount;
		++ size;
		resize();
		return true;
	}


	/**
	 * 指定された要素をこの両端キューの末尾に挿入します。
	 * <br>
	 * このメソッドは、{@link #offerLast(E)}と同等です。
	 *
	 * @param e 追加する要素
	 * @return true ({@link Queue#offer(E)}で指定されているとおり)
	 * @exception NullPointerException 指定された要素がnullである場合
	 */
	@Override
	public boolean offer(E e) {
		if (e == null) throw new NullPointerException();
		deque[last] = e;
		last = last + 1 & mask;
		++ modCount;
		++ size;
		resize();
		return true;
	}

	/**
	 * この両端キューが表すキューの先頭を取得して削除します。
	 * このメソッドは、両端キューが空の場合に例外をスローする点のみが{@link #poll}メソッドと異なります。
	 * <br>
	 * このメソッドは、{@link #removeFirst()}と同等です。
	 *
	 * @return この両端キューで表されるキューの先頭
	 * @exception NoSuchElementException この両端キューが空の場合
	 */
	@Override
	public E remove() {
		if (isEmpty()) throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		deque[first] = null;
		first = first + 1 & mask;
		-- size;
		++ modCount;
		resize();
		return ret;
	}

	/**
	 * この両端キューによって表されるキューの先頭(つまり、この両端キューの最初の要素)を取得および削除します。
	 * 両端キューが空の場合は、nullを返します。
	 * <br>
	 * このメソッドは、{@link #pollFirst()}と同等です。
	 *
	 * @return この両端キューで表されるキューの先頭。この両端キューが空の場合はnull
	 */
	@Override
	public E poll() {
		if (isEmpty()) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		deque[first] = null;
		first = first + 1 & mask;
		-- size;
		++ modCount;
		resize();
		return ret;
	}

	/**
	 * この両端キューで表されるキューの先頭を取得しますが、削除しません。
	 * このメソッドは、両端キューが空の場合に例外をスローする点のみが{@link #peek}メソッドと異なります。
	 * <br>
	 * このメソッドは、{@link #getFirst()}と同等です。
	 *
	 * @return この両端キューで表されるキューの先頭
	 * @exception NoSuchElementException この両端キューが空の場合
	 */
	@Override
	public E element() {
		if (isEmpty()) throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		return ret;
	}

	/**
	 * この両端キューによって表されるキューの先頭を取得しますが、削除しません。
	 * 両端キューが空の場合はnullを返します。
	 * <br>
	 * このメソッドは、{@link #peekFirst()}と同等です。
	 *
	 * @return この両端キューで表されるキューの先頭。この両端キューが空の場合はnull
	 */
	@Override
	public E peek() {
		if (isEmpty()) return null;
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		return ret;
	}

	/**
	 * この両端キューで表されるスタックに要素を入れます。
	 * つまり、要素をこの両端キューの先頭に挿入します。
	 * <br>
	 * このメソッドは、{@link #addFirst(E)}と同等です。
	 *
	 * @param e プッシュする要素
	 * @exception NullPointerException 指定された要素がnullである場合
	 */
	@Override
	public void push(E e) {
		if (e == null) throw new NullPointerException();
		deque[first = first - 1 & mask] = e;
		++ modCount;
		++ size;
		resize();
	}

	/**
	 * この両端キューで表されるスタックに要素をポップします。
	 * つまり、この両端キューの最初の要素を削除して返します。
	 * <br>
	 * このメソッドは、{@link #removeFirst()}と同等です。
	 *
	 * @return この両端キューの先頭の要素(この両端キューによって表されるスタックの上部)
	 * @exception NoSuchElementException この両端キューが空の場合
	 */
	@Override
	public E pop() {
		if (isEmpty()) throw new NoSuchElementException();
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first];
		deque[first] = null;
		first = first + 1 & mask;
		-- size;
		++ modCount;
		resize();
		return ret;
	}

	/**
	 * 指定された要素の単一のインスタンスを、この両端キューから削除します。
	 * その要素が両端キューにない場合、変更はありません。
	 * つまり、o.equals(e)となる最初の要素eが存在する場合は、その要素を削除します。
	 * 指定された要素がこの両端キューに含まれていた場合(すなわち、呼出しの結果としてこの両端キューが変更された場合)はtrueを返します。
	 * <br>
	 * このメソッドは、{@link #removeFirstOccurrence(Object)}と同等です。
	 *
	 * @param o この両端キューから削除される要素(その要素が存在する場合)
	 * @return 指定された要素がこの両端キューに含まれていた場合はtrue
	 */
	@Override
	public boolean remove(Object o) {
		int check = first;
		while (check != last) {
			if (Objects.equals(deque[check], o)) {
				localRemove(check);
				return true;
			}
			check = check + 1 & mask;
		}
		return false;
	}

	/**
	 * 指定された要素がこの両端キューに含まれている場合にtrueを返します。
	 * つまり、この両端キューに、o.equals(e)となる要素eが1つ以上含まれている場合にだけtrueを返します。
	 *
	 * @param e この両端キューに含まれているかどうかを調べるオブジェクト
	 * @return 指定された要素がこの両端キューに含まれている場合はtrue
	 */
	@Override
	public boolean contains(Object o) {
		boolean ret = false;
		for (Object i : deque)
			ret |= Objects.equals(i, o);
		return ret;
	}

	/**
	 * この両端キュー内の要素の数を返します。
	 *
	 * @return この両端キュー内の要素の数
	 */
	@Override
	public int size() {
		return size;
	}

	private final class Iter implements Iterator<E> {

		private int index = first, check = modCount;

		@Override
		public boolean hasNext() {
			return index != last;
		}

		@Override
		public E next() {
			if (check != modCount) throw new ConcurrentModificationException();
			@SuppressWarnings("unchecked")
			E ret = (E)deque[index];
			index = index + 1 & mask;
			return ret;
		}

		@Override
		public void remove() {
			localRemove(index);
			check = modCount;
		}
	}

	private final class DescendingIter implements Iterator<E> {

		private int index = last, check = modCount;

		@Override
		public boolean hasNext() {
			return index != first;
		}

		@Override
		public E next() {
			if (check != modCount) throw new ConcurrentModificationException();
			@SuppressWarnings("unchecked")
			E ret = (E)deque[index];
			index = index - 1 & mask;
			return ret;
		}

		@Override
		public void remove() {
			localRemove(index);
			check = modCount;
		}
	}

	/**
	 * 両端キューの要素のイテレータを返します。
	 * 要素の順序は最初(先頭)から最後(末尾)になります。
	 * これは、要素が{@link #remove()}の連続する呼出しによってキューから取り出される順序、または{@link #pop()}の連続する呼出しによってポップされる順序と同じです。
	 *
	 * @return 両端キューの要素のイテレータ
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new DescendingIter();
	}

	/**
	 * この両端キュー内の指定された位置にある要素を返します。
	 *
	 * @param index 返される要素のインデックス
	 * @return この両端キュー内の指定された位置にある要素
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(index < 0||index>= size())
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) throw new IndexOutOfBoundsException(String.valueOf(index));
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first + index & mask];
		return ret;
	}

	/**
	 * この両端キューの指定された位置にある要素を、指定された要素で置き換えます。
	 *
	 * @param index   置換される要素のインデックス
	 * @param element 指定された位置に格納される要素
	 * @return 指定された位置に以前あった要素
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(index < 0||index>= size())
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size()) throw new IndexOutOfBoundsException(String.valueOf(index));
		int n = first + index & mask;
		@SuppressWarnings("unchecked")
		E ret = (E)deque[n];
		deque[n] = element;
		++ modCount;
		return ret;
	}

	/**
	 * この両端キューの指定された位置にある要素を削除します。
	 * 後続の要素は左に移動します(インデックス値から1を減算)。
	 *
	 * @param index 削除される要素のインデックス
	 * @return 両端キューから削除した要素
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(index < 0||index>= size())
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) throw new IndexOutOfBoundsException(String.valueOf(index));
		@SuppressWarnings("unchecked")
		E ret = (E)deque[first + index & mask];
		localRemove(first + index & mask);
		return ret;
	}

	private void localRemove(int index) {
		if (first <= index) System.arraycopy(deque, first, deque, first + 1, index - first);
		else System.arraycopy(deque, index + 1, deque, index, first - index - 1);
		last = last - 1 & mask;
		-- size;
		++ modCount;
	}

	/**
	 * この両端キューの複製を返します。
	 *
	 * @return この両端キューのコピー
	 * @see Cloneable
	 */
	@Override
	public Object clone() {
		try {
			@SuppressWarnings("unchecked")
			ArrayDeque<E> ret = (ArrayDeque<E>)super.clone();
			ret.deque = Arrays.copyOf(deque, deque.length);
			return ret;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * この両端キュー内の要素に対する遅延バインディングおよびフェイルファスト{@link Spliterator}を作成します。
	 * <br>
	 * Spliteratorは、{@link Spliterator#SIZED}、{@link Spliterator#SUBSIZED}、{@link Spliterator#ORDERED}、および{@link Spliterator#NONNULL}を報告します。
	 * オーバーライドする実装は、追加の特性値の報告をドキュメント化する必要があります。
	 *
	 * @return この両端キュー内の要素に対するSpliterator
	 */
	@Override
	public Spliterator<E> spliterator() {
		return Spliterators.spliterator(this, Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.ORDERED | Spliterator.NONNULL);
	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void add(int index, E element) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int indexOf(Object o) {
		if (first < last) {
			for (int i = first;i < last;++ i) if (o.equals(deque[i])) return i - first;
		} else {
			for (int i = first;i < deque.length;++ i) if (o.equals(deque[i])) return i - first;
			for (int i = 0;i < last;++ i) if (o.equals(deque[i])) return deque.length - first + i;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (first < last) {
			for (int i = last - 1;i >= first;-- i) if (o.equals(deque[i])) return i - first;
		} else {
			for (int i = last - 1;i >= 0;-- i) if (o.equals(deque[i])) return deque.length - first + i;
			for (int i = deque.length - 1;i >= first;-- i) if (o.equals(deque[i])) return i - first;
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void ensureCapacity(int minCapacity) {
		resize(minCapacity);
	}

	public void trimToSize() {
		resize(size);
	}
}