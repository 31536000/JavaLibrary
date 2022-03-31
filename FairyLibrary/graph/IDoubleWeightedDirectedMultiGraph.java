package com._31536000.graph;

public interface IDoubleWeightedDirectedMultiGraph<E extends IDoubleWeightedDirectedMultiGraph.Edge> extends IWeightedDirectedMultiGraph<Double, E>{
	public static interface Edge extends IWeightedDirectedMultiGraph.Edge<Double> {
		@Override
		public default Double getWeight() {
			return getWeightAsDouble();
		}
		public double getWeightAsDouble();
	}
}
