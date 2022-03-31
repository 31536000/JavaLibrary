package com._31536000.graph;

public interface IWeightedDirectedGraph<W, E extends IWeightedDirectedMultiGraph.Edge<W>> extends IWeightedDirectedMultiGraph<W, E>{
	public E getEdge(int source, int target);
	@Override
	public default java.util.Collection<E> getEdges(int source, int target) {
		return java.util.Arrays.asList(getEdge(source, target));
	}
	public boolean removeEdge(int source, int target);
	@Override
	public default int removeEdges(int source, int target) {
		return removeEdge(source, target) ? 1 : 0;
	}
	@Override
	public default boolean removeEdge(E edge) {
		if (!hasEdge(edge)) return false;
		removeEdge(edge.getSource(), edge.getTarget());
		return true;
	}
	@Override
	public default int size(int source, int target) {
		return hasEdge(source, target) ? 1 : 0;
	}
}
