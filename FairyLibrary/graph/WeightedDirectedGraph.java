package com._31536000.graph;

import com._31536000.util.collect.Array;
import com._31536000.util.collect.HashMultiSet;

public class WeightedDirectedGraph<W> extends java.util.AbstractCollection<IWeightedDirectedGraph.Edge<W>>
		implements IWeightedDirectedGraph<W, IWeightedDirectedGraph.Edge<W>> {
	private abstract class Adjacency {
		public abstract boolean addEdge(Edge<W> edge);

		public abstract java.util.Collection<Edge<W>> getEdges();

		public abstract java.util.Collection<Edge<W>> getEdges(int source);

		public abstract Edge<W> getEdge(int source, int target);

		public abstract boolean removeEdge(int source, int target);

		public abstract boolean removeEdge(Edge<W> edge);

		public abstract int removeEdges(int source);

		public abstract boolean hasEdge(int source, int target);

		public abstract boolean hasEdge(Edge<W> edge);

		public abstract void clear();

		public abstract int size();

		public abstract int indegree(int vertex);

		public abstract int outdegree(int vertex);

		public abstract java.util.Collection<Integer> adjacentVertices(int source);

		public abstract Adjacency reverse();
	}

	private class ObjectEdge implements Edge<W> {
		final int source, target;
		final W weight;

		ObjectEdge(int source, int target, W weight) {
			this.source = source;
			this.target = target;
			this.weight = weight;
		}

		@Override
		public int getSource() { return source; }

		@Override
		public int getTarget() { return target; }

		@Override
		public W getWeight() { return weight; }

		@Override
		public boolean equals(Object o) {
			if (o instanceof Edge) {
				Edge<?> e = (Edge<?>) o;
				return source == e.getSource() && target == e.getTarget()
						&& java.util.Objects.equals(weight, e.getWeight());
			}
			return false;
		}

		@Override
		public int hashCode() {
			return source ^ target ^ java.util.Objects.hashCode(weight);
		}

		@Override
		public String toString() {
			return "{" + source + "->" + target + ": " + weight + "}";
		}
	}

	private class AdjacencyArray extends Adjacency {
		final Object[][] adjacent;
		int size;

		AdjacencyArray(int order, java.util.Collection<? extends Edge<W>> edge) {
			adjacent = new Object[order][];
			int[] count = new int[order];
			for (Edge<?> e : edge) ++count[e.getSource()];
			for (int i = 0; i < order; ++i) {
				adjacent[i] = new Object[count[i]];
				count[i] = 0;
			}
			for (Edge<?> e : edge) adjacent[e.getSource()][count[e.getSource()]++] = e;
			size = edge.size();
		}

		@Override
		public boolean addEdge(Edge<W> edge) {
			int source = edge.getSource(), target = edge.getTarget();
			Object[] array = adjacent[source];
			for (int i = 0; i < array.length; ++i) {
				@SuppressWarnings("unchecked")
				Edge<W> tmp = (Edge<W>) array[i];
				if (tmp.getTarget() == target) return false;
			}
			adjacent[source] = java.util.Arrays.copyOf(array, array.length + 1);
			array[array.length - 1] = edge;
			++size;
			return true;
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges() {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					return size;
				}

				class Iter implements java.util.Iterator<Edge<W>> {
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
					public Edge<W> next() {
						try {
							@SuppressWarnings("unchecked")
							Edge<W> ret = (Edge<W>) adjacent[v][e];
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
						Object[] array = adjacent[lastV];
						array[lastE] = array[array.length - 1];
						adjacent[lastV] = java.util.Arrays.copyOf(array, array.length - 1);
						if (v == lastV) --e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges(int source) {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					return adjacent[source].length;
				}

				class Iter implements java.util.Iterator<Edge<W>> {
					int e = 0;
					int lastE = -1;

					@Override
					public boolean hasNext() {
						return e < adjacent[source].length;
					}

					@SuppressWarnings("unchecked")
					@Override
					public Edge<W> next() {
						try {
							lastE = e;
							return (Edge<W>) adjacent[source][e++];
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						Object[] array = adjacent[source];
						array[lastE] = array[array.length - 1];
						adjacent[source] = java.util.Arrays.copyOf(array, array.length - 1);
						--e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public Edge<W> getEdge(int source, int target) {
			for (Object o : adjacent[source]) {
				@SuppressWarnings("unchecked")
				Edge<W> tmp = (Edge<W>) o;
				if (tmp.getTarget() == target) return tmp;
			}
			return null;
		}

		@Override
		public boolean removeEdge(int source, int target) {
			Object[] array = adjacent[source];
			for (int i = 0; i < array.length; ++i) {
				@SuppressWarnings("unchecked")
				Edge<W> tmp = (Edge<W>) array[i];
				if (tmp.getTarget() == target) {
					array[i] = array[array.length - 1];
					adjacent[source] = java.util.Arrays.copyOf(array, array.length - 1);
					--size;
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean removeEdge(Edge<W> edge) {
			Object[] array = adjacent[edge.getSource()];
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
			adjacent[source] = new Object[0];
			size -= count;
			return count;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean hasEdge(int source, int target) {
			for (Object i : adjacent[source]) if (((Edge<W>) i).getTarget() == target) return true;
			return false;
		}

		@Override
		public boolean hasEdge(Edge<W> edge) {
			for (Object i : adjacent[edge.getSource()]) if (java.util.Objects.equals(i, edge)) return true;
			return false;
		}

		@Override
		public void clear() {
			for (int i = 0; i < adjacent.length; ++i) adjacent[i] = new Object[0];
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

						@SuppressWarnings("unchecked")
						@Override
						public Integer next() {
							try {
								lastE = e;
								return ((Edge<W>) adjacent[source][e++]).getTarget();
							} catch (Exception e) {
								throw new java.util.NoSuchElementException();
							}
						}

						@Override
						public void remove() {
							if (lastE == -1) throw new IllegalStateException();
							Object[] array = adjacent[source];
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
		Array<java.util.ArrayList<Edge<W>>> adjacent;
		int size;

		AdjacencyList(int order, java.util.Collection<? extends Edge<W>> edge) {
			adjacent = Array.create(order);
			for (int i = 0; i < order; ++i) adjacent.set(i, new java.util.ArrayList<Edge<W>>());
			for (Edge<W> e : edge) adjacent.get(e.getSource()).add(e);
			size = edge.size();
		}

		@Override
		public boolean addEdge(Edge<W> edge) {
			adjacent.get(edge.getSource()).add(edge);
			++size;
			return true;
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges() {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					return size;
				}

				class Iter implements java.util.Iterator<Edge<W>> {
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
					public Edge<W> next() {
						try {
							Edge<W> ret = adjacent.get(v).get(e);
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
						java.util.ArrayList<Edge<W>> array = adjacent.get(lastV);
						array.set(lastE, array.get(array.size() - 1));
						array.remove(array.size() - 1);
						if (v == lastV) --e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges(int source) {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					return adjacent.get(source).size();
				}

				class Iter implements java.util.Iterator<Edge<W>> {
					int e = 0;
					int lastE = -1;

					@Override
					public boolean hasNext() {
						return e < adjacent.get(source).size();
					}

					@Override
					public Edge<W> next() {
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
						java.util.ArrayList<Edge<W>> array = adjacent.get(source);
						array.set(lastE, array.get(array.size() - 1));
						array.remove(array.size() - 1);
						--e;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public Edge<W> getEdge(int source, int target) {
			for (Edge<W> e : adjacent.get(source)) if (e.getTarget() == target) return e;
			return null;
		}

		@Override
		public boolean removeEdge(int source, int target) {
			for (java.util.Iterator<Edge<W>> i = adjacent.get(source).iterator(); i.hasNext();) {
				if (i.next().getTarget() == target) {
					i.remove();
					--size;
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean removeEdge(Edge<W> edge) {
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
			for (Edge<W> e : adjacent.get(source)) if (e.getTarget() == target) return true;
			return false;
		}

		@Override
		public boolean hasEdge(Edge<W> edge) {
			return adjacent.get(edge.getSource()).contains(edge);
		}

		@Override
		public void clear() {
			for (java.util.ArrayList<Edge<W>> i : adjacent) i.clear();
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
						int lastE = -1, e = 0;

						@Override
						public boolean hasNext() {
							return e < adjacent.get(source).size();
						}

						@Override
						public Integer next() {
							try {
								lastE = e;
								return adjacent.get(source).get(e++).getTarget();
							} catch (Exception e) {
								throw new java.util.NoSuchElementException();
							}
						}

						@Override
						public void remove() {
							if (lastE == -1) throw new IllegalStateException();
							java.util.ArrayList<Edge<W>> list = adjacent.get(source);
							list.set(lastE, list.get(list.size() - 1));
							list.remove(list.size() - 1);
							--size;
							--e;
							lastE = -1;
						}
					}
					return new Iter();
				}

				@Override
				public int size() {
					return adjacent.get(source).size();
				}

			}
			return new Vertices();
		}

		@Override
		public Adjacency reverse() {
			return new AdjacencyList(size(),
					getEdges().stream().map(e -> new ObjectEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}
	}

	private class AdjacencySet extends Adjacency {

		final Array<java.util.HashMap<Integer, Edge<W>>> adjacent;
		int size;

		boolean add(Edge<W> edge, java.util.HashMap<Integer, Edge<W>> map) {
			if (map.putIfAbsent(edge.getTarget(), edge) == null) {
				++ size;
				return true;
			}
			return false;
		}

		AdjacencySet(int order, java.util.Collection<? extends Edge<W>> edge) {
			adjacent = Array.create(order);
			for (int i = 0; i < order; ++i) adjacent.set(i, new java.util.HashMap<>());
			for (Edge<W> e : edge) add(e, adjacent.get(e.getSource()));
		}

		@Override
		public boolean addEdge(Edge<W> edge) {
			return add(edge, adjacent.get(edge.getSource()));
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges() {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					return size;
				}

				class Iter implements java.util.Iterator<Edge<W>> {
					int v = -1;
					java.util.Iterator<Edge<W>> edges, lastEdges;

					Iter() {
						while (++v < adjacent.size()) {
							if (adjacent.get(v).isEmpty()) continue;
							edges = adjacent.get(v).values().iterator();
							break;
						}
					}

					@Override
					public boolean hasNext() {
						return v < adjacent.size();
					}

					@Override
					public Edge<W> next() {
						try {
							lastEdges = edges;
							Edge<W> ret = edges.next();
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						if (edges.hasNext()) return;
						while (++v < adjacent.size()) {
							if (adjacent.get(v).isEmpty()) continue;
							edges = adjacent.get(v).values().iterator();
							return;
						}
					}

					@Override
					public void remove() {
						if (lastEdges == null) throw new IllegalStateException();
						lastEdges.remove();
						lastEdges = null;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges(int source) {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					return adjacent.get(source).size();
				}

				class Iter implements java.util.Iterator<Edge<W>> {
					java.util.Iterator<Edge<W>> iter;
					Iter() {
						iter = adjacent.get(source).values().iterator();
					}
					@Override
					public boolean hasNext() {
						return iter.hasNext();
					}
					@Override
					public Edge<W> next() {
						return iter.next();
					}
					@Override
					public void remove() {
						iter.remove();
						-- size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public Edge<W> getEdge(int source, int target) {
			return adjacent.get(source).get(target);
		}

		@Override
		public boolean removeEdge(int source, int target) {
			if (adjacent.get(source).remove(target) != null) {
				-- size;
				return true;
			}
			return false;
		}

		@Override
		public boolean removeEdge(Edge<W> edge) {
			if (adjacent.get(edge.getSource()).remove(edge.getTarget(), edge)) {
				-- size;
				return true;
			}
			return false;
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
			return adjacent.get(source).containsKey(target);
		}

		@Override
		public boolean hasEdge(Edge<W> edge) {
			Edge<W> tmp = adjacent.get(edge.getSource()).get(edge.getTarget());
			return tmp != null && tmp.equals(edge);
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
			return adjacent.get(vertex).size();
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
					getEdges().stream().map(e -> new ObjectEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}

	}

	private class AdjacencyMatrix extends Adjacency {
		final int order;
		final Object[] adjacent;
		int size;

		AdjacencyMatrix(int order, java.util.Collection<? extends Edge<W>> edge) {
			this.order = order;
			adjacent = new Object[order * order];
			for (Edge<W> e : edge) {
				if (adjacent[getIndex(e)] == null) {
					adjacent[getIndex(e)] = e;
					++ size;
				}
			}
		}

		private int getIndex(int source, int target) {
			return source * order + target;
		}

		private int getIndex(Edge<W> e) {
			return getIndex(e.getSource(), e.getTarget());
		}

		@Override
		public boolean addEdge(Edge<W> edge) {
			if (adjacent[getIndex(edge)] == null) {
				adjacent[getIndex(edge)] = edge;
				++ size;
				return true;
			}
			return false;
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges() {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					return size;
				}

				class Iter implements java.util.Iterator<Edge<W>> {
					int v = 0, lastV, e = -1, lastE = -1;

					Iter() {
						getNext();
					}

					@Override
					public boolean hasNext() {
						return v < order;
					}

					@Override
					public Edge<W> next() {
						try {
							lastV = v;
							lastE = e;
							@SuppressWarnings("unchecked")
							Edge<W> ret = (Edge<W>) adjacent[getIndex(v, e)];
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						do {
							while (++e < order) if (adjacent[getIndex(v, e)] != null) return;
						} while (++v < order);
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						adjacent[getIndex(lastV, lastE)] = null;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@Override
		public java.util.Collection<Edge<W>> getEdges(int source) {
			class Edges extends java.util.AbstractCollection<Edge<W>> {
				@Override
				public int size() {
					int count = 0;
					for (int i = getIndex(source, 0), l = i + order; i < l; ++i) if (adjacent[i] != null) ++ count;
					return count;
				}

				class Iter implements java.util.Iterator<Edge<W>> {
					int e = -1, lastE = -1;

					Iter() {
						getNext();
					}

					@Override
					public boolean hasNext() {
						return e < order;
					}

					@Override
					public Edge<W> next() {
						try {
							lastE = e;
							@SuppressWarnings("unchecked")
							Edge<W> ret = (Edge<W>) adjacent[getIndex(source, e)];
							getNext();
							return ret;
						} catch (Exception e) {
							throw new java.util.NoSuchElementException();
						}
					}

					private void getNext() {
						while (++e < order) if (adjacent[getIndex(source, e)] != null) return;
					}

					@Override
					public void remove() {
						if (lastE == -1) throw new IllegalStateException();
						adjacent[getIndex(source, lastE)] = null;
						lastE = -1;
						--size;
					}
				}

				@Override
				public java.util.Iterator<Edge<W>> iterator() {
					return new Iter();
				}
			}
			return new Edges();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Edge<W> getEdge(int source, int target) {
			return (Edge<W>) adjacent[getIndex(source, target)];
		}

		@Override
		public boolean removeEdge(int source, int target) {
			if (adjacent[getIndex(source, target)] != null) {
				adjacent[getIndex(source, target)] = null;
				return true;
			}
			return false;
		}

		@Override
		public boolean removeEdge(Edge<W> edge) {
			if (java.util.Objects.equals(adjacent[getIndex(edge)], edge)) {
				adjacent[getIndex(edge)] = null;
				return true;
			}
			return false;
		}

		@Override
		public int removeEdges(int source) {
			int count = 0;
			for (int i = getIndex(source, 0), l = i + order; i < l; ++i) {
				Object o = adjacent[i];
				if (o == null) continue;
				++ count;
				adjacent[i] = null;
			}
			size -= count;
			return count;
		}

		@Override
		public boolean hasEdge(int source, int target) {
			return adjacent[getIndex(source, target)] != null;
		}

		@Override
		public boolean hasEdge(Edge<W> edge) {
			return java.util.Objects.equals(adjacent[getIndex(edge)], edge);
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
			for (int i = getIndex(vertex, 0), l = i + order; i < l; ++i) {
				Object o = adjacent[i];
				if (o == null) continue;
				++ count;
			}
			return count;
		}

		@Override
		public int outdegree(int vertex) {
			int count = 0;
			for (int i = vertex; i < adjacent.length; i += order) {
				Object o = adjacent[i];
				if (o == null) continue;
				++ count;
			}
			return count;
		}

		@Override
		public java.util.Collection<Integer> adjacentVertices(int source) {
			class Vertices extends java.util.AbstractCollection<Integer> {

				@Override
				public java.util.Iterator<Integer> iterator() {
					class Iter implements java.util.Iterator<Integer> {
						int e = getIndex(source, 0), lastE = -1, l = e + order;

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

						@Override
						public void remove() {
							if (lastE == -1) throw new IllegalStateException();
							-- size;
							adjacent[lastE] = null;
							lastE = -1;
						}

					}
					return new Iter();
				}

				@Override
				public int size() {
					int count = 0;
					for (int i = getIndex(source, 0), l = i + order; i < l; ++i) if (adjacent[i] != null) ++count;
					return count;
				}

			}
			return new Vertices();
		}

		@Override
		public Adjacency reverse() {
			return new AdjacencyMatrix(size(),
					getEdges().stream().map(e -> new ObjectEdge(e.getTarget(), e.getSource(), e.getWeight()))
							.collect(java.util.stream.Collectors.toList()));
		}

	}

	Adjacency adjacency;
	final int order;

	private WeightedDirectedGraph(int order, Adjacency adjacency) {
		this.order = order;
		this.adjacency = adjacency;
	}

	private WeightedDirectedGraph(int order, java.util.Collection<? extends Edge<W>> edge, Adjacent adjacent) {
		this.order = order;
		switch(adjacent) {
			case ARRAY:
				this.adjacency = new AdjacencyArray(order, edge);
				break;
			case LIST:
				this.adjacency = new AdjacencyList(order, edge);
				break;
			case MATRIX:
				this.adjacency = new AdjacencyMatrix(order, edge);
				break;
			case SET:
				this.adjacency = new AdjacencySet(order, edge);
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public java.util.Iterator<Edge<W>> iterator() {
		return adjacency.getEdges().iterator();
	}

	@Override
	public boolean add(Edge<W> e) {
		return adjacency.addEdge(e);
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof Edge) {
			try {
				@SuppressWarnings("unchecked")
				Edge<W> e = (Edge<W>) o;
				return adjacency.removeEdge(e);
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public boolean addEdge(int source, int target, W weight) {
		return adjacency.addEdge(new ObjectEdge(source, target, weight));
	}

	@Override
	public boolean addEdge(Edge<W> edge) {
		return adjacency.addEdge(edge);
	}

	@Override
	public java.util.Collection<Edge<W>> getEdges() { return adjacency.getEdges(); }

	@Override
	public java.util.Collection<Edge<W>> getEdges(int source) {
		return adjacency.getEdges(source);
	}

	@Override
	public Edge<W> getEdge(int source, int target) {
		return adjacency.getEdge(source, target);
	}

	@Override
	public boolean removeEdge(int source, int target) {
		return adjacency.removeEdge(source, target);
	}

	@Override
	public boolean removeEdge(Edge<W> edge) {
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
	public boolean hasEdge(Edge<W> edge) {
		return adjacency.hasEdge(edge);
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
	public WeightedDirectedGraph<W> reverse() {
		return new WeightedDirectedGraph<>(order, adjacency.reverse());
	}

	@Override
	public void build(Adjacent adjacent) {
		switch (adjacent) {
			case ARRAY:
				this.adjacency = new AdjacencyArray(order, this.adjacency.getEdges());
				break;
			case LIST:
				this.adjacency = new AdjacencyList(order, this.adjacency.getEdges());
				break;
			case MATRIX:
				this.adjacency = new AdjacencyMatrix(order, this.adjacency.getEdges());
				break;
			case SET:
				this.adjacency = new AdjacencySet(order, this.adjacency.getEdges());
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

	public static <W> WeightedDirectedGraph<W> create(int order) {
		return create(order, java.util.Collections.emptyList(), Adjacent.LIST);
	}

	public static <W> WeightedDirectedGraph<W> create(int order, Adjacent adjacent) {
		return create(order, java.util.Collections.emptyList(), adjacent);
	}

	public static <W> WeightedDirectedGraph<W> create(int order, java.util.Collection<? extends Edge<W>> edge) {
		return create(order, edge, Adjacent.LIST);
	}

	public static <W> WeightedDirectedGraph<W> create(int order, java.util.Collection<? extends Edge<W>> edge, Adjacent adjacent) {
		if (order < 0) throw new IllegalArgumentException("negative size: " + order);
		if (edge == null) edge = java.util.Collections.emptyList();
		if (adjacent == null) adjacent = Adjacent.LIST;
		return new WeightedDirectedGraph<>(order, edge, adjacent);
	}
}
