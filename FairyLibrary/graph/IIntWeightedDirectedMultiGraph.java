package com._31536000.graph;

public interface IIntWeightedDirectedMultiGraph<E extends IIntWeightedDirectedMultiGraph.Edge> extends IWeightedDirectedMultiGraph<Integer, E>{
	public static interface Edge extends IWeightedDirectedMultiGraph.Edge<Integer> {
		@Override
		public default Integer getWeight() {
			return getWeightAsInt();
		}
		public int getWeightAsInt();
	}
}
