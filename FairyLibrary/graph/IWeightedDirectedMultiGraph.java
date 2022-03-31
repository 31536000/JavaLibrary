package com._31536000.graph;

/**
 * 有向多重重み付きグラフです。
 * <p>
 * グラフの実装には、追加の制約がある場合があります。例えば、多重辺を認めない場合や、重みが存在しないグラフの場合があります。
 * このような場合における挙動は任意です。従って、辺を追加、あるいは削除する際には実体をよく理解した上で実行してください。
 * </p>
 *
 * @author 31536000
 *
 * @param <W> 辺の重み
 * @param <E> 辺
 */
public interface IWeightedDirectedMultiGraph<W, E extends IWeightedDirectedMultiGraph.Edge<W>>
		extends java.util.Collection<E> {
	/**
	 * 重み付き辺です。
	 * 重み付き辺は、その始点と終点及び重みを管理します。
	 *
	 * @author 31536000
	 *
	 * @param <W> 辺の重み
	 */
	public static interface Edge<W> {
		/**
		 * この辺の始点を返します。
		 * @return 始点
		 */
		public int getSource();

		/**
		 * この辺の終点を返します。
		 * @return 終点
		 */
		public int getTarget();

		/**
		 * この辺の重みを返します。
		 * @return 重み
		 */
		public W getWeight();
	}

	/**
	 * 指定された辺を追加します(オプションの操作)。これにより、グラフが変更された場合はtrueが返ります。
	 * <p>
	 * グラフによっては、指定された辺を追加することを拒否する場合があります。
	 * グラフの各実装では、グラフが受け入れる辺についての制約を明記するようにしてください。
	 * </p>
	 * @param edge 追加する辺
	 * @return 辺を追加した場合はtrue
	 * @exception NullPointerException 指定された要素がnullの場合
	 * @exception UnsupportedOperationException addEdgeオペレーションがこのグラフでサポートされない場合
	 */
	public boolean addEdge(E edge);

	/**
	 * 指定された辺を追加します(オプションの操作)。これにより、グラフが変更された場合はtrueが返ります。
	 * <p>このメソッドは、{@link #addEdge(E)}と同等です。</p>
	 *
	 * <p>
	 * グラフによっては、指定された辺を追加することを拒否する場合があります。
	 * グラフの各実装では、グラフが受け入れる辺についての制約を明記するようにしてください。
	 * </p>
	 * @param edge 追加する辺
	 * @return 辺を追加した場合はtrue
	 * @exception NullPointerException 指定された要素がnullの場合
	 * @exception UnsupportedOperationException addEdgeオペレーションがこのグラフでサポートされない場合
	 */
	@Override
	public default boolean add(E edge) {
		return addEdge(edge);
	}

	/**
	 * 指定されたコレクションのすべての辺を追加します(オプションの操作)。
	 * これにより、グラフが変更された場合はtrueが返ります。
	 * <p>このメソッドは、{@link #addEdge(E)}と同等です。</p>
	 * <p>
	 * グラフによっては、指定された辺を追加することを拒否する場合があります。
	 * グラフの各実装では、グラフが受け入れる辺についての制約を明記するようにしてください。
	 * </p>
	 * @param edges 追加する辺のコレクション
	 * @return 辺を追加した場合はtrue
	 * @exception NullPointerException 指定された要素がnullの場合
	 * @exception UnsupportedOperationException addEdgesオペレーションがこのグラフでサポートされない場合
	 */
	public default boolean addEdges(java.util.Collection<? extends E> edges) {
		boolean ret = false;
		for (E e : edges) ret |= addEdge(e);
		return ret;
	}

	/**
	 * 指定されたコレクションのすべての辺を追加します(オプションの操作)。
	 * これにより、グラフが変更された場合はtrueが返ります。
	 * <p>
	 * グラフによっては、指定された辺を追加することを拒否する場合があります。
	 * グラフの各実装では、グラフが受け入れる辺についての制約を明記するようにしてください。
	 * </p>
	 * @param edges 追加する辺のコレクション
	 * @return 辺を追加した場合はtrue
	 * @exception NullPointerException 指定された要素がnullの場合
	 * @exception UnsupportedOperationException addEdgesオペレーションがこのグラフでサポートされない場合
	 */
	@Override
	public default boolean addAll(java.util.Collection<? extends E> edges) {
		return addEdges(edges);
	}

	/**
	 * このグラフに含まれる全ての辺のコレクションを返します。これは、このグラフ自身に等しいです。
	 * @return グラフに含まれる全ての辺のコレクション
	 */
	public default java.util.Collection<E> getEdges() {
		return this;
	}

	/**
	 * sourceを始点とする辺のコレクションを返します。
	 * @param source 始点
	 * @return sourceを始点とする辺のコレクション
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(source &lt; 0 || source &gt;= order())
	 */
	public java.util.Collection<E> getEdges(int source);

	/**
	 * sourceを始点、targetを終点とする辺のコレクションを返します。
	 * @param source 始点
	 * @param target 終点
	 * @return sourceを始点、targetを終点とする辺のコレクション
	 * @exception IndexOutOfBoundsException インデックスが範囲外の場合(source &lt; 0 || source &gt;= order() || target &lt; 0 || target &gt;= order())
	 */
	public java.util.Collection<E> getEdges(int source, int target);

	/**
	 * 指定された要素がグラフに含まれている場合にtrueを返します。つまり、このグラフに、(o==null ? e==null : o.equals(e))となる要素eが含まれている場合にのみtrueを返します。
	 * @param o このグラフ内にあるかどうかが判定される要素
	 * @return 指定された要素がこのグラフに含まれている場合はtrue
	 * @exception ClassCastException 指定された要素の型が、このグラフと互換でない場合
	 * @exception NullPointerException 指定された要素がnullの場合
	 */
	@Override
	public boolean contains(Object o);

	/**
	 * 指定されたコレクションのすべての要素がこのグラフに含まれている場合にtrueを返します。
	 * @param c このグラフに含まれているかどうかがチェックされるコレクション
	 * @return 指定されたコレクションのすべての要素がこのグラフに含まれている場合はtrue
	 * @exception ClassCastException 指定されたコレクションの1つ以上の要素の型が、このグラフと互換でない場合
	 * @exception NullPointerException 指定されたコレクションに1つ以上のnull要素が含まれている場合、または指定されたコレクションがnullの場合
	 */
	@Override
	public default boolean containsAll(java.util.Collection<?> c) {
		for (Object o : c) if (!contains(o)) return false;
		return true;
	}

	/**
	 * このグラフに辺が1つも含まれていない場合にtrueを返します。
	 * @return このグラフに辺が1つも含まれていない場合はtrue
	 */
	@Override
	public default boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * sourceを始点、targetを終点とする辺を全て削除します(オプションの操作)。
	 * 呼出しの結果としてこのグラフが変更された場合はtrueを返します。
	 * @param source 始点
	 * @param target 終点
	 * @return グラフから一つ以上の辺が削除されたならtrue
	 * @exception UnsupportedOperationException removeEdgesオペレーションがこのグラフでサポートされない場合
	 */
	public int removeEdges(int source, int target);

	/**
	 * 指定された辺のうち最初に出現したものを、このグラフから削除します。
	 * その辺がグラフにない場合、変更はありません。
	 * つまり、(edge==null ? e==null : edge.equals(e))となる最初の辺eが存在する場合は、その辺を削除します。
	 * 指定された辺がこのグラフに含まれていた場合(すなわち、呼出しの結果としてこのグラフが変更された場合)はtrueを返します。
	 * @param edge このグラフから削除する辺(その辺が存在する場合)
	 * @return このグラフが指定された辺を保持していた場合はtrue
	 * @exception UnsupportedOperationException removeEdgeオペレーションがこのグラフでサポートされない場合
	 */
	public boolean removeEdge(E edge);

	/**
	 * 指定された要素のうち最初に出現したものを、このグラフから削除します。
	 * その要素がグラフにない場合、変更はありません。
	 * つまり、(edge==null ? e==null : edge.equals(e))となる最初の辺eが存在する場合は、その辺を削除します。
	 * 指定された要素がこのグラフに含まれていた場合(すなわち、呼出しの結果としてこのグラフが変更された場合)はtrueを返します。
	 * <p>このメソッドは、{@link #removeEdge(E)}と同等です。</p>
	 * 
	 * @param edge このグラフから削除する要素(その要素が存在する場合)
	 * @return このグラフが指定された要素を保持していた場合はtrue
	 * @exception ClassCastException 指定された要素の型が、このグラフと互換でない場合
	 * @exception UnsupportedOperationException removeオペレーションがこのグラフでサポートされない場合
	 */
	@Override
	public default boolean remove(Object o) {
		@SuppressWarnings("unchecked")
		E e = (E) o;
		return removeEdge(e);
	}

	@Override
	public default boolean removeAll(java.util.Collection<?> c) {
		boolean ret = false;
		for (Object o : c) ret |= remove(o);
		return ret;
	}

	@Override
	public default boolean retainAll(java.util.Collection<?> c) {
		boolean ret = false;
		for (java.util.Iterator<E> iter = iterator();iter.hasNext();) {
			E e = iter.next();
			if (!c.contains(e)) {
				iter.remove();
				ret = true;
			}
		}
		return ret;
	}

	public int removeEdges(int source);

	public boolean hasEdge(int source, int target);

	public boolean hasEdge(E edge);

	public int size(int source, int target);

	@Override
	public void clear();

	@Override
	public int size();

	public int order();

	public int indegree(int vertex);

	public int outdegree(int vertex);

	public default boolean adjacent(int v1, int v2) {
		return hasEdge(v1, v2) || hasEdge(v2, v1);
	}

	public java.util.Collection<Integer> adjacentVertices(int source);

	public IWeightedDirectedMultiGraph<W, E> reverse();

	public void build(Adjacent adjacent);

	@Override
	public boolean equals(Object o);

	@Override
	public int hashCode();

	@Override
	public String toString();

	@Override
	public default java.util.Iterator<E> iterator() {
		return getEdges().iterator();
	}

	@Override
	public default java.util.Spliterator<E> spliterator() {
		return getEdges().spliterator();
	}

	@Override
	public default java.util.stream.Stream<E> stream() {
		return getEdges().stream();
	}

	@Override
	public default Object[] toArray() {
		return getEdges().toArray();
	}

	@Override
	public default <T> T[] toArray(T[] a) {
		return getEdges().toArray(a);
	}
}
