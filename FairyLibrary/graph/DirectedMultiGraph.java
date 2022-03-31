package com._31536000.graph;

public interface DirectedMultiGraph<E extends DirectedMultiGraph.Edge> extends IIntWeightedDirectedMultiGraph<E> {
	public static interface Edge extends IIntWeightedDirectedMultiGraph.Edge {
		@Override
		public default int getWeightAsInt() {
			return 1;
		}
	}
	public boolean addEdge(int source, int target);
	@Override
	public default boolean addEdgeAsInt(int source, int target, int weight) {
		throw new UnsupportedOperationException("このグラフに重みを設定することはできません。");
	}
}
