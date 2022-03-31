package com._31536000.graph;

public interface ILongWeightedDirectedGraph<E extends ILongWeightedDirectedMultiGraph.Edge> extends IWeightedDirectedGraph<Long, E>, ILongWeightedDirectedMultiGraph<E>{
	@Override
	public default boolean addEdge(int source, int target, Long weight) {
		return addEdgeAsLong(source, target, weight);
	}
}
