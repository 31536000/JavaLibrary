package com._31536000.graph;

public interface WeightedMultiGraph<W, E extends IWeightedDirectedMultiGraph.Edge<W>> extends IWeightedDirectedMultiGraph<W, E>{
	public int degree(int vertex);
	@Override
	public default int indegree(int vertex) {
		return degree(vertex);
	}
	@Override
	public default int outdegree(int vertex) {
		return degree(vertex);
	}
}
