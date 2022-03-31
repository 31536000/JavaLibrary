package com._31536000.graph;

import com._31536000.util.collect.Array;
import com._31536000.util.collect.HashMultiSet;

public class LongWeightedDirectedMultiGraph extends java.util.AbstractCollection<ILongWeightedDirectedMultiGraph.Edge>
implements ILongWeightedDirectedMultiGraph<ILongWeightedDirectedMultiGraph.Edge> {
	private abstract class Adjacency {
		public abstract boolean addEdge(Edge edge);

		public abstract java.util.Collection<Edge> getEdges();

		public abstract java.util.Collection<Edge> getEdges(int source);

		public abstract java.util.Collection<Edge> getEdges(int source, int target);

		public abstract int removeEdges(int source, int target);

		public abstract boolean removeEdge(Edge edge);

		public abstract int removeEdges(int source);

		public abstract boolean hasEdge(int source, int target);

		public abstract boolean hasEdge(Edge edge);

		public abstract int size(int source, int target);

		public abstract void clear();

		public abstract int size();

		public abstract int indegree(int vertex);

		public abstract int outdegree(int vertex);

		public abstract java.util.Collection<Integer> adjacentVertices(int source);

		public abstract Adjacency reverse();
	}

	private class LongEdge implements Edge {
		final int source, target;
		final long weight;

		LongEdge(int source, int target, long weight) {
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
			if (o instanceof Edge) {
				Edge e = (Edge) o;
				return source == e.getSource() && target == e.getTarget()
						&& weight == e.getWeightAsLong();
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
		final Edge[][] adjacent;
		int size;

		AdjacencyArray(int order, java.util.Collection<? extends Edge> edge) {
			adjacent = new Edge[order][];
			int[] count = new int[order];
			for (Edge e : edge) ++count[e.getSource()];
			for (int i = 0; i < order; ++i) {
				adjacent[i] = new Edge[count[i]];
				count[i] = 0;
			}
			for (Edge e : edge) adjacent[e.getSource()][count[e.getSource()]++] = e;
			size = edge.size();
		}

		@Override
		public boolean addEdge(Edge edge) {
			int source = edge.getSource();
			Edge[] array = adjacent[source];
			adjacent[source] = java.util.Arrays.copyOf(array, array.length + 1);
			array[array.length - 1] = edge;
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
						return v < adjacent.length;
					}

					@Override
					public Edge next() {
						try {
							Edge ret = adjacent[v][e];
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
						Edge[] array = adjacent[lastV];
						array[lastE] = array[array.length - 1];
						adjacent[lastV] = java.util.Arrays.copyOf(array, array.length - 1);
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
					return adjacent[source].length;
				}

				class Iter implements java.util.Iterator<Edge> {
					int e = 0;
					int lastE = -1;

					@Override
					public boolean hasNext() {
						return e < adjacent[source].length;
					}

					@Override
					public Edge next() {
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
						Edge[] array = adjacent[source];
						array[lastE] = array[array.length - 1];
						adjacent[source] = java.util.Arrays.copyOf(array, array.length - 1);
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
					return AdjacencyArray.this.size(source, target);
				}

				class Iter implements java.util.Iterator<Edge> {
					int e = 0;
					int lastE = -1;

					@Override
					public boolean hasNext() {
						return e < adjacent[source].length;
					}

					@Override
					public Edge next() {
						try {
							lastE = e;
							Edge ret = adjacent[source][e++];
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						while (hasNext() && adjacent[source][e].getTarget() != target) ++e;
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						Edge[] array = adjacent[source];
						array[lastE] = array[array.length - 1];
						adjacent[source] = java.util.Arrays.copyOf(array, array.length - 1);
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
			Edge[] array = adjacent[source];
			int len = array.length;
			for (int i = 0; i < len; ++i) {
				if (array[i].getTarget() == target) {
					array[i] = array[--len];
				}
			}
			count = array.length - len;
			adjacent[source] = java.util.Arrays.copyOf(array, len);
			size -= count;
			return count;
		}

		@Override
		public boolean removeEdge(Edge edge) {
			Edge[] array = adjacent[edge.getSource()];
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
			adjacent[source] = new Edge[0];
			size -= count;
			return count;
		}

		@Override
		public boolean hasEdge(int source, int target) {
			for (Edge i : adjacent[source]) if (i.getTarget() == target) return true;
			return false;
		}

		@Override
		public boolean hasEdge(Edge edge) {
			for (Edge i : adjacent[edge.getSource()]) if (java.util.Objects.equals(i, edge)) return true;
			return false;
		}

		@Override
		public int size(int source, int target) {
			int count = 0;
			for (Edge i : adjacent[source]) if (i.getTarget() == target) ++count;
			return count;
		}

		@Override
		public void clear() {
			for (int i = 0; i < adjacent.length; ++i) adjacent[i] = new Edge[0];
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
						java.util.HashSet<Integer> set = new java.util.HashSet<>();
						int lastE = -1, e = 0;

						@Override
						public boolean hasNext() {
							return e < adjacent[source].length;
						}

						@Override
						public Integer next() {
							try {
								lastE = e;
								Edge edge = adjacent[source][e++];
								set.add(edge.getSource());
								getNext();
								return edge.getTarget();
							} catch (Exception e) {
								throw new java.util.NoSuchElementException();
							}
						}

						private void getNext() {
							while (hasNext() && set.contains(adjacent[source][e].getTarget())) ++e;
						}

						@Override
						public void remove() {
							if (lastE == -1) throw new IllegalStateException();
							Edge[] array = adjacent[source];
							int len = array.length;
							int last = array[lastE].getTarget();
							for (int i = 0; i < len; ++i) {
								if (array[i].getTarget() == last) {
									array[i] = array[--len];
									--i;
									-- size;
								}
							}
							adjacent[source] = java.util.Arrays.copyOf(array, len);
							set.remove(last);
							getNext();
							lastE = -1;
						}
					}
					return new Iter();
				}

				@Override
				public int size() {
					Edge[] array = adjacent[source];
					if (array.length == 0) return 0;
					int[] test = new int[array.length];
					for (int i = 0; i < test.length; ++i) test[i] = array[i].getTarget();
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
			return new AdjacencyArray(size(),
					getEdges().stream().map(e -> new LongEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}
	}

	private class AdjacencyList extends Adjacency {
		Array<java.util.ArrayList<Edge>> adjacent;
		int size;

		AdjacencyList(int order, java.util.Collection<? extends Edge> edge) {
			adjacent = Array.create(order);
			for (int i = 0; i < order; ++i) adjacent.set(i, new java.util.ArrayList<Edge>());
			for (Edge e : edge) {
				adjacent.get(e.getSource()).add(e);
			}
			size = edge.size();
		}

		@Override
		public boolean addEdge(Edge edge) {
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

	private class AdjacencySet extends Adjacency {

		final Array<java.util.HashMap<Integer, java.util.ArrayList<Edge>>> adjacent;
		int size;

		void add(Edge edge, java.util.HashMap<Integer, java.util.ArrayList<Edge>> map) {
			map.compute(edge.getTarget(), (k, v) -> {
				if (v == null) v = new java.util.ArrayList<>();
				v.add(edge);
				return v;
			});
			++ size;
		}

		AdjacencySet(int order, java.util.Collection<? extends Edge> edge) {
			adjacent = Array.create(order);
			for (int i = 0; i < order; ++i) adjacent.set(i, new java.util.HashMap<>());
			for (Edge e : edge) add(e, adjacent.get(e.getSource()));
		}

		@Override
		public boolean addEdge(Edge edge) {
			add(edge, adjacent.get(edge.getSource()));
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
					int v = -1;
					java.util.Iterator<java.util.ArrayList<Edge>> iter, lastIter;
					java.util.ArrayList<Edge> edges, lastEdges;
					int e, lastE = -1;

					Iter() {
						while (++v < adjacent.size()) {
							if (adjacent.get(v).isEmpty()) continue;
							iter = adjacent.get(v).values().iterator();
							edges = iter.next();
							e = 0;
							break;
						}
					}

					@Override
					public boolean hasNext() {
						return v < adjacent.size();
					}

					@Override
					public Edge next() {
						try {
							lastIter = iter;
							lastEdges = edges;
							lastE = e;
							Edge ret = edges.get(e);
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						if (++e < edges.size()) return;
						if (iter.hasNext()) {
							edges = iter.next();
							e = 0;
							return;
						}
						while (++v < adjacent.size()) {
							if (adjacent.get(v).isEmpty()) continue;
							iter = adjacent.get(v).values().iterator();
							edges = iter.next();
							e = 0;
							return;
						}
						edges = null;
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						lastEdges.set(lastE, lastEdges.get(lastEdges.size() - 1));
						lastEdges.remove(lastEdges.size() - 1);
						if (lastEdges.isEmpty()) lastIter.remove();
						if (edges == lastEdges) --e;
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
					int count = 0;
					for (java.util.ArrayList<Edge> list : adjacent.get(source).values()) count += list.size();
					return count;
				}

				class Iter implements java.util.Iterator<Edge> {
					java.util.Iterator<java.util.ArrayList<Edge>> iter;
					java.util.ArrayList<Edge> edges, lastEdges;
					int e, lastE = -1;

					Iter() {
						iter = adjacent.get(source).values().iterator();
						if (iter.hasNext()) edges = iter.next();
					}

					@Override
					public boolean hasNext() {
						return edges != null;
					}

					@Override
					public Edge next() {
						try {
							lastEdges = edges;
							lastE = e;
							Edge ret = edges.get(e++);
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						if (e >= edges.size()) {
							edges = iter.hasNext() ? iter.next() : null;
							e = 0;
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						lastEdges.set(lastE, lastEdges.get(lastEdges.size() - 1));
						lastEdges.remove(lastEdges.size() - 1);
						if (edges == lastEdges) --e;
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
					return AdjacencySet.this.size(source, target);
				}

				class Iter implements java.util.Iterator<Edge> {
					java.util.ArrayList<Edge> edges;
					int e = 0, lastE = -1;

					Iter() {
						edges = adjacent.get(source).get(target);
						if (edges == null) edges = new java.util.ArrayList<>();
					}

					@Override
					public boolean hasNext() {
						return e < edges.size();
					}

					@Override
					public Edge next() {
						try {
							lastE = e;
							return edges.get(e++);
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						edges.set(lastE, edges.get(edges.size() - 1));
						edges.remove(edges.size() - 1);
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
		public int removeEdges(int source, int target) {
			java.util.ArrayList<?> list = adjacent.get(source).remove(target);
			if (list == null) return 0;
			size -= list.size();
			return list.size();
		}

		@Override
		public boolean removeEdge(Edge edge) {
			java.util.ArrayList<?> list = adjacent.get(edge.getSource()).get(edge.getTarget());
			if (list == null) return false;
			boolean ret = list.remove(edge);
			if (ret) {
				--size;
				if (list.isEmpty()) adjacent.get(edge.getSource()).remove(edge.getTarget());
				return true;
			}
			return false;
		}

		@Override
		public int removeEdges(int source) {
			int count = 0;
			for (java.util.ArrayList<Edge> list : adjacent.get(source).values()) count += list.size();
			adjacent.get(source).clear();
			size -= count;
			return count;
		}

		@Override
		public boolean hasEdge(int source, int target) {
			return adjacent.get(source).containsKey(target);
		}

		@Override
		public boolean hasEdge(Edge edge) {
			java.util.ArrayList<?> list = adjacent.get(edge.getSource()).get(edge.getTarget());
			if (list == null) return false;
			return list.contains(edge);
		}

		@Override
		public int size(int source, int target) {
			java.util.ArrayList<?> list = adjacent.get(source).get(target);
			if (list == null) return 0;
			return list.size();
		}

		@Override
		public void clear() {
			for (int i = 0; i < adjacent.size(); ++i) adjacent.get(i).clear();
			size = 0;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public int indegree(int vertex) {
			int count = 0;
			for (java.util.ArrayList<Edge> list : adjacent.get(vertex).values()) count += list.size();
			return count;
		}

		@Override
		public int outdegree(int vertex) {
			return reverse().indegree(vertex);
		}

		@Override
		public java.util.Collection<Integer> adjacentVertices(int source) {
			return adjacent.get(source).keySet();
		}

		@Override
		public Adjacency reverse() {
			return new AdjacencySet(size(),
					getEdges().stream().map(e -> new LongEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}

	}

	private class AdjacencyMatrix extends Adjacency {
		final int order;
		final Object[] adjacent;
		int size;

		AdjacencyMatrix(int order, java.util.Collection<? extends Edge> edge) {
			this.order = order;
			adjacent = new Object[order * order];
			for (Edge e : edge) add(e);
			size = edge.size();
		}

		@SuppressWarnings("unchecked")
		private void add(Edge e) {
			Object o = adjacent[e.getSource() * order + e.getTarget()];
			if (o == null) {
				o = new java.util.ArrayList<Edge>();
				adjacent[e.getSource() * order + e.getTarget()] = o;
			}
			((java.util.ArrayList<Edge>) o).add(e);
		}

		@SuppressWarnings("unchecked")
		private java.util.ArrayList<Edge> get(int source, int target) {
			Object o = adjacent[source * order + target];
			if (o == null) return null;
			return (java.util.ArrayList<Edge>) o;
		}

		@Override
		public boolean addEdge(Edge edge) {
			add(edge);
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
					int s = 0, lastS, t, lastT, e, lastE = -1;

					Iter() {
						do {
							while (++t < order) {
								if (get(s, t) != null) return;
							}
						} while (++s < order);
					}

					@Override
					public boolean hasNext() {
						return s < order;
					}

					@Override
					public Edge next() {
						try {
							lastS = s;
							lastT = t;
							lastE = e;
							Edge ret = get(s, t).get(e);
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						if (++e < get(s, t).size()) return;
						e = 0;
						do {
							while (++t < order) {
								if (get(s, t) != null) return;
							}
						} while (++s < order);
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						java.util.ArrayList<Edge> list = get(lastS, lastT);
						list.set(lastE, list.get(list.size() - 1));
						list.remove(list.size() - 1);
						if (e != 0) --e;
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
					int count = 0;
					for (int i = source * order, l = i + order; i < l; ++i) {
						Object o = adjacent[i];
						if (o != null) count += ((java.util.ArrayList<?>) o).size();
					}
					return count;
				}

				class Iter implements java.util.Iterator<Edge> {
					int t = -1, lastT, e, lastE = -1;

					Iter() {
						while (++t < order) {
							if (get(source, t) != null) return;
						}
					}

					@Override
					public boolean hasNext() {
						return t < order;
					}

					@Override
					public Edge next() {
						try {
							lastT = t;
							lastE = e;
							Edge ret = get(source, t).get(e);
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						if (++e < get(source, t).size()) return;
						e = 0;
						while (++t < order) {
							if (get(source, t) != null) return;
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						java.util.ArrayList<Edge> list = get(source, lastT);
						list.set(lastE, list.get(list.size() - 1));
						list.remove(list.size() - 1);
						if (e != 0) --e;
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
					java.util.ArrayList<Edge> list = get(source, target);
					if (list == null) return 0;
					return list.size();
				}

				class Iter implements java.util.Iterator<Edge> {
					int e = 0, lastE = -1;
					int len = size();

					@Override
					public boolean hasNext() {
						return e < len;
					}

					@Override
					public Edge next() {
						try {
							lastE = e;
							return get(source, target).get(e++);
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						java.util.ArrayList<Edge> list = get(source, target);
						list.set(lastE, list.get(list.size() - 1));
						list.remove(list.size() - 1);
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
		public int removeEdges(int source, int target) {
			java.util.ArrayList<?> list = get(source, target);
			if (list == null) return 0;
			adjacent[source * order + target] = null;
			size -= list.size();
			return list.size();
		}

		@Override
		public boolean removeEdge(Edge edge) {
			java.util.ArrayList<?> list = get(edge.getSource(), edge.getTarget());
			if (list == null) return false;
			if (list.remove(edge)) {
				--size;
				if (list.isEmpty()) adjacent[edge.getSource() * order + edge.getTarget()] = null;
				return true;
			}
			return false;
		}

		@Override
		public int removeEdges(int source) {
			int count = 0;
			for (int i = source * order, l = i + order; i < l; ++i) {
				Object o = adjacent[i];
				if (o == null) continue;
				count += ((java.util.ArrayList<?>) o).size();
				adjacent[i] = null;
			}
			size -= count;
			return count;
		}

		@Override
		public boolean hasEdge(int source, int target) {
			return adjacent[source * order + target] != null;
		}

		@Override
		public boolean hasEdge(Edge edge) {
			java.util.ArrayList<?> list = get(edge.getSource(), edge.getTarget());
			if (list == null) return false;
			return list.contains(edge);
		}

		@Override
		public int size(int source, int target) {
			java.util.ArrayList<?> list = get(source, target);
			if (list == null) return 0;
			return list.size();
		}

		@Override
		public void clear() {
			java.util.Arrays.fill(adjacent, null);
			size = 0;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public int indegree(int vertex) {
			int count = 0;
			for (int i = vertex * order, l = i + order; i < l; ++i) {
				Object o = adjacent[i];
				if (o == null) continue;
				count += ((java.util.ArrayList<?>) o).size();
			}
			return count;
		}

		@Override
		public int outdegree(int vertex) {
			int count = 0;
			for (int i = vertex; i < adjacent.length; i += order) {
				Object o = adjacent[i];
				if (o == null) continue;
				count += ((java.util.ArrayList<?>) o).size();
			}
			return count;
		}

		@Override
		public java.util.Collection<Integer> adjacentVertices(int source) {
			class Vertices extends java.util.AbstractCollection<Integer> {

				@Override
				public java.util.Iterator<Integer> iterator() {
					class Iter implements java.util.Iterator<Integer> {
						int e = source * order, lastE = -1, l = e + order;

						Iter() {
							getNext();
						}

						@Override
						public boolean hasNext() {
							return e < l;
						}

						@Override
						public Integer next() {
							if (!hasNext()) throw new java.util.NoSuchElementException();
							lastE = e;
							return e - l + order;
						}

						private void getNext() {
							while (e < l && adjacent[e] == null) ++e;
						}

						@SuppressWarnings("unchecked")
						@Override
						public void remove() {
							if (lastE == -1) throw new IllegalStateException();
							size -= ((java.util.ArrayList<Edge>) adjacent[lastE]).size();
							adjacent[lastE] = null;
							lastE = -1;
						}

					}
					return new Iter();
				}

				@Override
				public int size() {
					int count = 0;
					for (int i = source * order, l = i + order; i < l; ++i) if (adjacent[i] != null) ++count;
					return count;
				}

			}
			return new Vertices();
		}

		@Override
		public Adjacency reverse() {
			return new AdjacencyMatrix(size(),
					getEdges().stream().map(e -> new LongEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}

	}

	Adjacency adjacency;
	final int order;

	private LongWeightedDirectedMultiGraph(int order, Adjacency adjacency) {
		this.order = order;
		this.adjacency = adjacency;
	}

	private LongWeightedDirectedMultiGraph(int order, java.util.Collection<? extends Edge> edge, Adjacent adjacent) {
		this.order = order;
		switch(adjacent) {
			case ARRAY:
				adjacency = new AdjacencyArray(order, edge);
				break;
			case LIST:
				adjacency = new AdjacencyList(order, edge);
				break;
			case MATRIX:
				adjacency = new AdjacencyMatrix(order, edge);
				break;
			case SET:
				adjacency = new AdjacencySet(order, edge);
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public java.util.Iterator<Edge> iterator() {
		return adjacency.getEdges().iterator();
	}

	@Override
	public boolean add(Edge e) {
		return adjacency.addEdge(e);
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof Edge) {
			try {
				Edge e = (Edge) o;
				return adjacency.removeEdge(e);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public boolean addEdgeAsLong(int source, int target, long weight) {
		return adjacency.addEdge(new LongEdge(source, target, weight));
	}

	@Override
	public boolean addEdge(Edge edge) {
		return adjacency.addEdge(edge);
	}

	@Override
	public java.util.Collection<Edge> getEdges() { return adjacency.getEdges(); }

	@Override
	public java.util.Collection<Edge> getEdges(int source) {
		return adjacency.getEdges(source);
	}

	@Override
	public java.util.Collection<Edge> getEdges(int source, int target) {
		return adjacency.getEdges(source, target);
	}

	@Override
	public int removeEdges(int source, int target) {
		return adjacency.removeEdges(source, target);
	}

	@Override
	public boolean removeEdge(Edge edge) {
		return adjacency.removeEdge(edge);
	}

	@Override
	public int removeEdges(int source) {
		return adjacency.removeEdges(source);
	}

	@Override
	public boolean hasEdge(int source, int target) {
		return adjacency.hasEdge(source, target);
	}

	@Override
	public boolean hasEdge(Edge edge) {
		return adjacency.hasEdge(edge);
	}

	@Override
	public int size(int source, int target) {
		return adjacency.size(source, target);
	}

	@Override
	public void clear() {
		adjacency.clear();
	}

	@Override
	public int size() {
		return adjacency.size();
	}

	@Override
	public int order() {
		return order;
	}

	@Override
	public int indegree(int vertex) {
		return adjacency.indegree(vertex);
	}

	@Override
	public int outdegree(int vertex) {
		return adjacency.outdegree(vertex);
	}

	@Override
	public java.util.Collection<Integer> adjacentVertices(int source) {
		return adjacency.adjacentVertices(source);
	}

	@Override
	public LongWeightedDirectedMultiGraph reverse() {
		return new LongWeightedDirectedMultiGraph(order, adjacency.reverse());
	}

	@Override
	public void build(Adjacent adjacent) {
		switch (adjacent) {
			case ARRAY:
				adjacency = new AdjacencyArray(order, adjacency.getEdges());
				break;
			case LIST:
				adjacency = new AdjacencyList(order, adjacency.getEdges());
				break;
			case MATRIX:
				adjacency = new AdjacencyMatrix(order, adjacency.getEdges());
				break;
			case SET:
				adjacency = new AdjacencySet(order, adjacency.getEdges());
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof IWeightedDirectedMultiGraph) {
			IWeightedDirectedMultiGraph<?, ?> g = (IWeightedDirectedMultiGraph<?, ?>) o;
			if (order != g.order()) return false;
			return HashMultiSet.create(getEdges()).equals(HashMultiSet.create(g.getEdges()));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return HashMultiSet.create(getEdges()).hashCode();
	}

	public static LongWeightedDirectedMultiGraph create(int order) {
		return create(order, java.util.Collections.emptyList(), Adjacent.LIST);
	}

	public static LongWeightedDirectedMultiGraph create(int order, Adjacent adjacent) {
		return create(order, java.util.Collections.emptyList(), adjacent);
	}

	public static LongWeightedDirectedMultiGraph create(int order, java.util.Collection<? extends Edge> edge) {
		return create(order, edge, Adjacent.LIST);
	}

	public static LongWeightedDirectedMultiGraph create(int order, java.util.Collection<? extends Edge> edge, Adjacent adjacent) {
		if (order < 0) throw new IllegalArgumentException("negative size: " + order);
		if (edge == null) edge = java.util.Collections.emptyList();
		if (adjacent == null) adjacent = Adjacent.LIST;
		return new LongWeightedDirectedMultiGraph(order, edge, adjacent);
	}
}
