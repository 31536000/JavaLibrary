package com._31536000.util;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<E> extends AbstractBinarySearchTree<E> {
	Node first, last;
	NilNode nil;
	Node.Edge tree;

	public abstract class Node {

		protected class Edge {
			public Node prev, next;

			public Edge (Node prev, Node next) {
				this.prev = prev;
				this.next = next;
			}

		}

		protected Edge parent, left, right, edge;
		protected E element;
		protected int size = 0, height = 0;

		public abstract boolean hasPrevious();

		public abstract Node previous();

		public abstract boolean hasNext();

		public abstract Node next();

		public abstract void add(E e);

		public abstract E get(int i);

		public abstract int indexOf(E e);

		public abstract int lastIndexOf(E e);

		public abstract boolean remove(E e);

		public abstract E remove(int i);

		public abstract E higher(E e);

		public abstract E ceiling(E e);

		public abstract E floor(E e);

		public abstract E lower(E e);

		public abstract E getMinimum();

		public abstract E getMaximum();

		public abstract E removeMinimum();

		public abstract E removeMaximum();

		public abstract boolean isNil();

		public abstract void calcHeight();
	}

	public class DatNode extends Node {

		public DatNode(E e, Edge parentEdge, Edge edge) {
			this.parent = parentEdge;
			parentEdge.next = this;
			element = e;
			this.edge = edge;
			edge.prev.edge.next = this;
			edge.next.edge.prev = this;
			left = new Edge(this, nil);
			right = new Edge(this, nil);
			size = 1;
			height = 1;
		}

		@Override
		public boolean hasPrevious() {
			return true;
		}

		@Override
		public Node previous() {
			return edge.prev;
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Node next() {
			return edge.next;
		}

		@Override
		public void add(E e) {
			Node node = this, previous = this;
			Edge parent = this.parent, edge = new Edge(first, last);
			while(!node.isNil()) {
				++ node.size;
				if (compare(node.element, e) < 0) node = (parent = (previous = edge.prev = node).left).next;
				else node = (parent = (previous = edge.next = node).right).next;
			}
			new DatNode(e, parent, edge);
			node = previous;
			while(node != this) {
				node.calcHeight();
				node = node.parent.prev;
			}
		}

		@Override
		public E get(int i) {
			Node node = this;
			int comp;
			while (i != (comp = node.left.next.size)) {
				if (i < comp) node = node.left.next;
				else {
					node = node.right.next;
					i -= comp + 1;
				}
				if (node.isNil()) throw new IndexOutOfBoundsException();
			}
			return node.element;
		}

		@Override
		public int indexOf(E e) {
			Node node = this;
			int comp, ret = -1, size = 0;
			while (!node.isNil()) {
				if ((comp = compare(node.element, e)) < 0) node = node.left.next;
				else if (comp > 0) {
					size += node.left.next.size + 1;
					node = node.right.next;
				} else {
					ret = size + node.left.next.size;
					node = node.left.next;
				}
			}
			return ret;
		}

		@Override
		public int lastIndexOf(E e) {
			Node node = this;
			int comp, ret = -1, size = 0;
			while (!node.isNil()) {
				if ((comp = compare(node.element, e)) < 0) node = node.left.next;
				else if (comp > 0) {
					size += node.left.next.size + 1;
					node = node.right.next;
				} else {
					ret = (size += node.left.next.size);
					++ size;
					node = node.right.next;
				}
			}
			return ret;
		}

		@Override
		public boolean remove(E e) {
			Node node = this;
			int comp;
			try {
				while ((comp = compare(node.element, e)) != 0) {
					if (comp < 0) node = node.left.next;
					else node = node.right.next;
				}
				if (!node.left.next.isNil()) node.element = node.left.next.removeMaximum();
				else if (!node.right.next.isNil()) node.element = node.right.next.removeMinimum();
				else node.parent.next = nil;
				while (node != this) {
					-- node.size;
					node.calcHeight();
					node = node.parent.prev;
				}
				-- node.size;
				node.calcHeight();
				return true;
			} catch (Exception e2) {
				return false;
			}
		}

		@Override
		public E remove(int i) {
			Node node = this;
			int size;
			while (i != (size = node.left.next.size)) {
				if (i < size) node = node.left.next;
				else {
					node = node.right.next;
					i -= size + 1;
				}
			}
			E ret = node.element;
			if (!node.left.next.isNil()) node.element = node.left.next.removeMaximum();
			else if (!node.right.next.isNil()) node.element = node.right.next.removeMinimum();
			else node.parent.next = nil;
			do {
				-- node.size;
				node.calcHeight();
			} while((node = node.parent.prev) != this);
			-- node.size;
			node.calcHeight();
			return ret;
		}

		@Override
		public E higher(E e) {
			Node node = this;
			E ret = null;
			while(!node.isNil()) {
				if (compare(node.element, e) >= 0) node = node.right.next;
				else {
					if (ret == null || compare(node.element, ret) < 0) ret = node.element;
					node = node.left.next;
				}
			}
			return ret;
		}

		@Override
		public E ceiling(E e) {
			Node node = this;
			E ret = null;
			while(!node.isNil()) {
				if (compare(node.element, e) > 0) node = node.right.next;
				else {
					if (ret == null || compare(node.element, ret) < 0) ret = node.element;
					node = node.left.next;
				}
			}
			return ret;
		}

		@Override
		public E floor(E e) {
			Node node = this;
			E ret = null;
			while(!node.isNil()) {
				if (compare(node.element, e) < 0) node = node.left.next;
				else {
					if (ret == null || compare(node.element, ret) > 0) ret = node.element;
					node = node.right.next;
				}
			}
			return ret;
		}

		@Override
		public E lower(E e) {
			Node node = this;
			E ret = null;
			while(!node.isNil()) {
				if (compare(node.element, e) <= 0) node = node.left.next;
				else {
					if (ret == null || compare(node.element, ret) > 0) ret = node.element;
					node = node.right.next;
				}
			}
			return ret;
		}

		@Override
		public E getMinimum() {
			Node node = this, last = this;
			while(node.isNil()) node = (last = node).left.next;
			return last.element;
		}

		@Override
		public E getMaximum() {
			Node node = this, last = this;
			while(node.isNil()) node = (last = node).right.next;
			return last.element;
		}

		@Override
		public E removeMinimum() {
			Node node = this, last = this;
			while (node.isNil()) node = (last = node).left.next;
			(node = last).parent.next = node.right.next;
			node.right.next.parent = node.parent;
			node.edge.next.edge.prev = node.edge.prev;
			node.edge.prev.edge.next = node.edge.next;
			while (node != this) {
				node = node.parent.prev;
				-- node.size;
				node.calcHeight();
			}
			return last.element;
		}

		@Override
		public E removeMaximum() {
			Node node = this, last = this;
			while (node.isNil()) node = (last = node).right.next;
			(node = last).parent.next = node.left.next;
			node.left.next.parent = node.parent;
			node.edge.next.edge.prev = node.edge.prev;
			node.edge.prev.edge.next = node.edge.next;
			while (node != this) {
				node = node.parent.prev;
				-- node.size;
				node.calcHeight();
			}
			return last.element;
		}

		@Override
		public void calcHeight() {
			height = Math.max(left.next.height, right.next.height) + 1;
		}

		@Override
		public boolean isNil() {
			return false;
		}
	}

	public class NilNode extends Node {

		public Edge getFirstEdge() {
			return new Edge(nil, nil);
		}

		@Override
		public boolean hasPrevious() {
			return false;
		}

		@Override
		public Node previous() {
			throw new NoSuchElementException();
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Node next() {
			throw new NoSuchElementException();
		}

		@Override
		public void add(E e) {
			new DatNode(e, tree, new Edge(first, last));
		}

		@Override
		public E get(int i) {
			throw new NoSuchElementException();
		}

		@Override
		public int indexOf(E e) {
			return -1;
		}

		@Override
		public int lastIndexOf(E e) {
			return -1;
		}

		@Override
		public boolean remove(E e) {
			return false;
		}

		@Override
		public E remove(int i) {
			throw new IndexOutOfBoundsException();
		}

		@Override
		public E higher(E e) {
			return null;
		}

		@Override
		public E ceiling(E e) {
			return null;
		}

		@Override
		public E floor(E e) {
			return null;
		}

		@Override
		public E lower(E e) {
			return null;
		}

		@Override
		public E getMinimum() {
			return null;
		}

		@Override
		public E getMaximum() {
			return null;
		}

		@Override
		public E removeMinimum() {
			throw new NoSuchElementException();
		}

		@Override
		public E removeMaximum() {
			throw new NoSuchElementException();
		}

		@Override
		public void calcHeight() {
			throw new IllegalStateException();
		}

		@Override
		public boolean isNil() {
			return true;
		}
	}

	public class FirstNode extends NilNode {

		public FirstNode() {
			edge = new Edge(nil, last);
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Node next() {
			return edge.next;
		}
	}

	public class LastNode extends NilNode {

		public LastNode() {
			edge = new Edge(this, first);
		}

		@Override
		public boolean hasPrevious() {
			return true;
		}

		@Override
		public Node previous() {
			return edge.prev;
		}
	}

	public class Iter implements Iterator<E> {

		private Node node;

		public Iter() {
			node = first;
		}

		public Iter(Node node) {
			this.node = node;
		}

		@Override
		public boolean hasNext() {
			return node.next().hasNext();
		}

		@Override
		public E next() {
			return (node = node.next()).element;
		}

		@Override
	    public void remove() {
	        throw new UnsupportedOperationException("remove");
	    }
	}

	public class RevIter implements Iterator<E> {

		private Node node;

		public RevIter() {
			node = last;
		}

		public RevIter(Node node) {
			this.node = node;
		}

		@Override
		public boolean hasNext() {
			return node.previous().hasPrevious();
		}

		@Override
		public E next() {
			return (node = node.previous()).element;
		}

		@Override
	    public void remove() {
	        throw new UnsupportedOperationException("remove");
	    }
	}

	public BinarySearchTree() {
		first = new FirstNode();
		last = new LastNode();
		nil = new NilNode();
		clear();
	}

	@Override
	public boolean add(E e) {
		tree.next.add(e);
		return true;
	}

	@Override
	public E get(int i) {
		return tree.next.get(i);
	}

	@Override
	public int indexOf(E e) {
		return tree.next.indexOf(e);
	}

	@Override
	public int lastIndexOf(E e) {
		return tree.next.lastIndexOf(e);
	}

	@Override
	public E remove(int i) {
		return tree.next.remove(i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e) {
		if (e == null) throw new NullPointerException();
		return tree.next.remove((E) e);
	}

	@Override
	public void clear() {
		first.edge.next = last;
		last.edge.prev = first;
		tree = nil.getFirstEdge();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new RevIter();
	}

	@Override
	public E getFirst() {
		return tree.next.getMinimum();
	}

	@Override
	public E getLast() {
		return tree.next.getMinimum();
	}

	@Override
	public E removeFirst() {
		return tree.next.removeMinimum();
	}

	@Override
	public E removeLast() {
		return tree.next.removeMaximum();
	}

	@Override
	public E higher(E e) {
		return tree.next.higher(e);
	}

	@Override
	public E ceiling(E e) {
		return tree.next.ceiling(e);
	}

	@Override
	public E floor(E e) {
		return tree.next.floor(e);
	}

	@Override
	public E lower(E e) {
		return tree.next.lower(e);
	}

	@Override
	public int size() {
		return tree.next.size;
	}
}