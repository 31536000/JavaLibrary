package com._31536000.graph;

import com._31536000.graph.ILongWeightedDirectedMultiGraph.Edge;
import com._31536000.util.collect.Array;

public class LongWeightedDirectedGraph extends java.util.AbstractCollection<ILongWeightedDirectedMultiGraph.Edge>
		implements ILongWeightedDirectedGraph<ILongWeightedDirectedMultiGraph.Edge> {
	private abstract class Adjacency {
		public abstract boolean addEdge(ILongWeightedDirectedMultiGraph.Edge edge);

		public abstract java.util.Collection<ILongWeightedDirectedMultiGraph.Edge> getEdges();

		public abstract java.util.Collection<ILongWeightedDirectedMultiGraph.Edge> getEdges(int source);

		public abstract ILongWeightedDirectedMultiGraph.Edge getEdge(int source, int target);

		public abstract boolean removeEdge(int source, int target);

		public abstract boolean removeEdge(ILongWeightedDirectedMultiGraph.Edge edge);

		public abstract int removeEdges(int source);

		public abstract boolean hasEdge(int source, int target);

		public abstract boolean hasEdge(ILongWeightedDirectedMultiGraph.Edge edge);

		public abstract void clear();

		public abstract int size();

		public abstract int indegree(int vertex);

		public abstract int outdegree(int vertex);

		public abstract java.util.Collection<Integer> adjacentVertices(int source);

		public abstract Adjacency reverse();
	}

	private class ObjectEdge implements ILongWeightedDirectedMultiGraph.Edge {
		final int source, target;
		final long weight;

		ObjectEdge(int source, int target, long weight) {
			this.source = source;
			this.target = target;
			this.weight = weight;
		}

		@Override
		public int getSource() { return source; }

		@Override
		public int getTarget() { return target; }

		@Override
		public long getWeightAsLong() { return weight; }

		@Override
		public boolean equals(Object o) {
			if (o instanceof ILongWeightedDirectedMultiGraph.Edge) {
				ILongWeightedDirectedMultiGraph.Edge e = (ILongWeightedDirectedMultiGraph.Edge) o;
				return source == e.getSource() && target == e.getTarget() && weight == e.getWeightAsLong();
			} else if (o instanceof WeightedDirectedMultiGraph.Edge) {
				WeightedDirectedMultiGraph.Edge<?> e = (WeightedDirectedMultiGraph.Edge<?>) o;
				return source == e.getSource() && target == e.getTarget()
						&& java.util.Objects.equals(weight, e.getWeight());
			}
			return false;
		}

		@Override
		public int hashCode() {
			return source ^ target ^ Long.hashCode(weight);
		}

		@Override
		public String toString() {
			return "{" + source + "->" + target + ": " + weight + "}";
		}
	}

	private class AdjacencyArray extends Adjacency {
		final ILongWeightedDirectedMultiGraph.Edge[][] adjacent;
		int size;

		AdjacencyArray(int order, java.util.Collection<? extends ILongWeightedDirectedMultiGraph.Edge> edge) {
			adjacent = new ILongWeightedDirectedMultiGraph.Edge[order][];
			int[] count = new int[order];
			for (ILongWeightedDirectedMultiGraph.Edge e : edge) ++count[e.getSource()];
			for (int i = 0; i < order; ++i) {
				adjacent[i] = new ILongWeightedDirectedMultiGraph.Edge[count[i]];
				count[i] = 0;
			}
			for (ILongWeightedDirectedMultiGraph.Edge e : edge) adjacent[e.getSource()][count[e.getSource()]++] = e;
			size = edge.size();
		}

		@Override
		public boolean addEdge(ILongWeightedDirectedMultiGraph.Edge edge) {
			int source = edge.getSource(), target = edge.getTarget();
			ILongWeightedDirectedMultiGraph.Edge[] array = adjacent[source];
			for (int i = 0; i < array.length; ++i) {
				if (array[i].getTarget() == target) return false;
			}
			adjacent[source] = java.util.Arrays.copyOf(array, array.length + 1);
			array[array.length - 1] = edge;
			++size;
			return true;
		}

		@Override
		public java.util.Collection<ILongWeightedDirectedMultiGraph.Edge> getEdges() {
			class Edges extends java.util.AbstractCollection<ILongWeightedDirectedMultiGraph.Edge> {
				@Override
				public int size() {
					return size;
				}

				class Iter implements java.util.Iterator<ILongWeightedDirectedMultiGraph.Edge> {
					int v = 0, e = -1;
					int lastV = 0, lastE = -1;

					Iter() {
						getNext();
					}

					@Override
					public boolean hasNext() {
						return v < adjacent.length;
					}

					@Override
					public ILongWeightedDirectedMultiGraph.Edge next() {
						try {
							ILongWeightedDirectedMultiGraph.Edge ret = adjacent[v][e];
							lastV = v;
							lastE = e;
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						while (++e >= adjacent[v].length) {
							if (++v >= adjacent.length) return;
							e = -1;
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						ILongWeightedDirectedMultiGraph.Edge[] array = adjacent[lastV];
						array[lastE] = array[array.length - 1];
						adjacent[lastV] = java.util.Arrays.copyOf(array, array.length - 1);
						if (v == lastV) --e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<ILongWeightedDirectedMultiGraph.Edge> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public java.util.Collection<ILongWeightedDirectedMultiGraph.Edge> getEdges(int source) {
			class Edges extends java.util.AbstractCollection<ILongWeightedDirectedMultiGraph.Edge> {
				@Override
				public int size() {
					return adjacent[source].length;
				}

				class Iter implements java.util.Iterator<ILongWeightedDirectedMultiGraph.Edge> {
					int e = 0;
					int lastE = -1;

					@Override
					public boolean hasNext() {
						return e < adjacent[source].length;
					}

					@Override
					public ILongWeightedDirectedMultiGraph.Edge next() {
						try {
							lastE = e;
							return adjacent[source][e++];
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						ILongWeightedDirectedMultiGraph.Edge[] array = adjacent[source];
						array[lastE] = array[array.length - 1];
						adjacent[source] = java.util.Arrays.copyOf(array, array.length - 1);
						--e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<ILongWeightedDirectedMultiGraph.Edge> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public ILongWeightedDirectedMultiGraph.Edge getEdge(int source, int target) {
			for (ILongWeightedDirectedMultiGraph.Edge o : adjacent[source]) if (o.getTarget() == target) return o;
			return null;
		}

		@Override
		public boolean removeEdge(int source, int target) {
			ILongWeightedDirectedMultiGraph.Edge[] array = adjacent[source];
			for (int i = 0; i < array.length; ++i) {
				if (array[i].getTarget() == target) {
					array[i] = array[array.length - 1];
					adjacent[source] = java.util.Arrays.copyOf(array, array.length - 1);
					--size;
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean removeEdge(ILongWeightedDirectedMultiGraph.Edge edge) {
			ILongWeightedDirectedMultiGraph.Edge[] array = adjacent[edge.getSource()];
			for (int i = 0; i < array.length; ++i) {
				if (java.util.Objects.equals(edge, array[i])) {
					array[i] = array[array.length - 1];
					adjacent[edge.getSource()] = java.util.Arrays.copyOf(array, array.length - 1);
					--size;
					return true;
				}
			}
			return false;
		}

		@Override
		public int removeEdges(int source) {
			int count = adjacent[source].length;
			adjacent[source] = new ILongWeightedDirectedMultiGraph.Edge[0];
			size -= count;
			return count;
		}

		@Override
		public boolean hasEdge(int source, int target) {
			for (ILongWeightedDirectedMultiGraph.Edge i : adjacent[source])
				if (i.getTarget() == target) return true;
			return false;
		}

		@Override
		public boolean hasEdge(ILongWeightedDirectedMultiGraph.Edge edge) {
			for (ILongWeightedDirectedMultiGraph.Edge i : adjacent[edge.getSource()]) if (java.util.Objects.equals(i, edge)) return true;
			return false;
		}

		@Override
		public void clear() {
			for (int i = 0; i < adjacent.length; ++i) adjacent[i] = new ILongWeightedDirectedMultiGraph.Edge[0];
			size = 0;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public int indegree(int vertex) {
			return adjacent[vertex].length;
		}

		@Override
		public int outdegree(int vertex) {
			return reverse().indegree(vertex);
		}

		@Override
		public java.util.Collection<Integer> adjacentVertices(int source) {
			class Vertices extends java.util.AbstractCollection<Integer> {

				@Override
				public java.util.Iterator<Integer> iterator() {
					class Iter implements java.util.Iterator<Integer> {
						int lastE = -1, e = 0;

						@Override
						public boolean hasNext() {
							return e < adjacent[source].length;
						}

						@Override
						public Integer next() {
							try {
								lastE = e;
								return adjacent[source][e++].getTarget();
							} catch (Exception e) {
								throw new java.util.NoSuchElementException();
							}
						}

						@Override
						public void remove() {
							if (lastE == -1) throw new IllegalStateException();
							ILongWeightedDirectedMultiGraph.Edge[] array = adjacent[source];
							array[lastE] = array[array.length - 1];
							adjacent[source] = java.util.Arrays.copyOf(array, array.length - 1);
							--size;
							--e;
							lastE = -1;
						}
					}
					return new Iter();
				}

				@Override
				public int size() {
					return adjacent[source].length;
				}

			}
			return new Vertices();
		}

		@Override
		public Adjacency reverse() {
			return new AdjacencyArray(size(),
					getEdges().stream().map(e -> new ObjectEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}
	}
	


	private class AdjacencyList extends Adjacency {
		Array<java.util.ArrayList<ILongWeightedDirectedMultiGraph.Edge>> adjacent;
		int size;

		AdjacencyList(int order, java.util.Collection<? extends ILongWeightedDirectedMultiGraph.Edge> edge) {
			adjacent = Array.create(order);
			for (int i = 0; i < order; ++i) adjacent.set(i, new java.util.ArrayList<ILongWeightedDirectedMultiGraph.Edge>());
			for (ILongWeightedDirectedMultiGraph.Edge e : edge) {
				adjacent.get(e.getSource()).add(e);
			}
			size = edge.size();
		}

		@Override
		public boolean addEdge(ILongWeightedDirectedMultiGraph.Edge edge) {
			adjacent.get(edge.getSource()).add(edge);
			++size;
			return true;
		}

		@Override
		public java.util.Collection<Edge> getEdges() {
			class Edges extends java.util.AbstractCollection<Edge> {
				@Override
				public int size() {
					return size;
				}

				class Iter implements java.util.Iterator<Edge> {
					int v = 0, e = -1;
					int lastV = 0, lastE = -1;

					Iter() {
						getNext();
					}

					@Override
					public boolean hasNext() {
						return v < adjacent.size();
					}

					@Override
					public Edge next() {
						try {
							Edge ret = adjacent.get(v).get(e);
							lastV = v;
							lastE = e;
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						while (++e >= adjacent.get(v).size()) {
							if (++v >= adjacent.size()) return;
							e = -1;
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						java.util.ArrayList<Edge> array = adjacent.get(lastV);
						array.set(lastE, array.get(array.size() - 1));
						array.remove(array.size() - 1);
						if (v == lastV) --e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public java.util.Collection<Edge> getEdges(int source) {
			class Edges extends java.util.AbstractCollection<Edge> {
				@Override
				public int size() {
					return adjacent.get(source).size();
				}

				class Iter implements java.util.Iterator<Edge> {
					int e = 0;
					int lastE = -1;

					@Override
					public boolean hasNext() {
						return e < adjacent.get(source).size();
					}

					@Override
					public Edge next() {
						try {
							lastE = e;
							return adjacent.get(source).get(e++);
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						java.util.ArrayList<Edge> array = adjacent.get(source);
						array.set(lastE, array.get(array.size() - 1));
						array.remove(array.size() - 1);
						--e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public java.util.Collection<Edge> getEdges(int source, int target) {
			class Edges extends java.util.AbstractCollection<Edge> {
				@Override
				public int size() {
					return AdjacencyList.this.size(source, target);
				}

				class Iter implements java.util.Iterator<Edge> {
					int e = 0;
					int lastE = -1;

					@Override
					public boolean hasNext() {
						return e < adjacent.get(source).size();
					}

					@Override
					public Edge next() {
						try {
							lastE = e;
							Edge ret = adjacent.get(source).get(e++);
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						while (hasNext() && adjacent.get(source).get(e).getTarget() != target) ++e;
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						java.util.ArrayList<Edge> array = adjacent.get(source);
						array.set(lastE, array.get(array.size() - 1));
						array.remove(array.size() - 1);
						e = lastE;
						getNext();
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public int removeEdges(int source, int target) {
			int count = 0;
			java.util.ArrayList<Edge> array = adjacent.get(source);
			for (int i = 0; i < array.size(); ++i) {
				if (array.get(i).getTarget() == target) {
					array.set(i, array.get(array.size() - 1));
					array.remove(array.size() - 1);
					++count;
				}
			}
			size -= count;
			return count;
		}

		@Override
		public boolean removeEdge(Edge edge) {
			boolean ret = adjacent.get(edge.getSource()).remove(edge);
			if (ret) --size;
			return ret;
		}

		@Override
		public int removeEdges(int source) {
			int count = adjacent.get(source).size();
			adjacent.get(source).clear();
			size -= count;
			return count;
		}

		@Override
		public boolean hasEdge(int source, int target) {
			for (Edge e : adjacent.get(source)) if (e.getTarget() == target) return true;
			return false;
		}

		@Override
		public boolean hasEdge(Edge edge) {
			return adjacent.get(edge.getSource()).contains(edge);
		}

		@Override
		public int size(int source, int target) {
			int size = 0;
			for (Edge e : adjacent.get(source)) if (e.getTarget() == target) ++size;
			return size;
		}

		@Override
		public void clear() {
			for (java.util.ArrayList<Edge> i : adjacent) i.clear();
			size = 0;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public int indegree(int vertex) {
			return adjacent.get(vertex).size();
		}

		@Override
		public int outdegree(int vertex) {
			return reverse().indegree(vertex);
		}

		@Override
		public java.util.Collection<Integer> adjacentVertices(int source) {
			class Vertices extends java.util.AbstractCollection<Integer> {

				@Override
				public java.util.Iterator<Integer> iterator() {
					class Iter implements java.util.Iterator<Integer> {
						java.util.HashSet<Integer> set = new java.util.HashSet<>();
						int lastE = -1, e = 0;

						@Override
						public boolean hasNext() {
							return e < adjacent.get(source).size();
						}

						@Override
						public Integer next() {
							try {
								lastE = e;
								Edge edge = adjacent.get(source).get(e++);
								set.add(edge.getSource());
								getNext();
								return edge.getTarget();
							} catch (Exception e) {
								throw new java.util.NoSuchElementException();
							}
						}

						private void getNext() {
							while (hasNext() && set.contains(adjacent.get(source).get(e).getTarget())) ++e;
						}

						@Override
						public void remove() {
							if (lastE == -1) throw new IllegalStateException();
							java.util.ArrayList<Edge> list = adjacent.get(source);
							int last = list.get(lastE).getTarget();
							for (int i = 0; i < adjacent.size(); ++i) {
								if (list.get(i).getTarget() == last) {
									list.set(i, list.get(list.size() - 1));
									list.remove(list.size() - 1);
									--i;
								}
							}
							set.remove(last);
							getNext();
							lastE = -1;
						}
					}
					return new Iter();
				}

				@Override
				public int size() {
					if (adjacent.get(source).size() == 0) return 0;
					int[] test = new int[adjacent.get(source).size()];
					for (int i = 0; i < test.length; ++i) test[i] = adjacent.get(source).get(i).getTarget();
					java.util.Arrays.sort(test);
					int count = 1;
					for (int i = 1; i < test.length; ++i) if (test[i - 1] != test[i]) ++count;
					return count;
				}

			}
			return new Vertices();
		}

		@Override
		public Adjacency reverse() {
			return new AdjacencyList(size(),
					getEdges().stream().map(e -> new LongEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}
	}

}
