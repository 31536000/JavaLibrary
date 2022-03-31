package com._31536000.graph;

public interface WeightedGraph<W, E extends IWeightedDirectedMultiGraph.Edge<W>> extends IWeightedDirectedGraph<W, E>, WeightedMultiGraph<W, E> {

}
