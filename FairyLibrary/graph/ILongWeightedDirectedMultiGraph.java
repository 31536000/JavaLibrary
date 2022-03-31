package com._31536000.graph;

public interface ILongWeightedDirectedMultiGraph<E extends ILongWeightedDirectedMultiGraph.Edge> extends IWeightedDirectedMultiGraph<Long, E>{
	public static interface Edge extends IWeightedDirectedMultiGraph.Edge<Long> {
		@Override
		public default Long getWeight() {
			return getWeightAsLong();
		}
		public long getWeightAsLong();
	}
}
