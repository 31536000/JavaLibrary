package com._31536000.graph;

public interface Graph<E extends DirectedMultiGraph.Edge> extends WeightedGraph<Integer, E>, DirectedGraph<E>, MultiGraph<E> {

}
