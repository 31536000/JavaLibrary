package com._31536000.util.collect;

public class HashMultiSet<E> implements MultiSet<E>, Cloneable, java.io.Serializable {

	private static final long serialVersionUID = -4571774103323510263L;
	private long size;
	private transient int lastCount;

	private static interface IEntry<E> extends MultiSet.Entry<E> {
		int merge(int n);

		boolean result(int n);

		default Object getObject() { return getElement(); }
	}

	private static class Entry<E> implements IEntry<E> {
		private final E element;
		private int count;

		private Entry(E e, int count) {
			this.element = e;
			this.count = count;
		}

		@Override
		public int getCount() { return count; }

		@Override
		public E getElement() { return element; }

		@Override
		public int merge(int n) {
			return count + n;
		}

		@Override
		public boolean result(int n) {
			return true;
		}

		@Override
		public int hashCode() {
			return element.hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof IEntry) {
				@SuppressWarnings("unchecked")
				IEntry<E> entry = (IEntry<E>) o;
				if (!element.equals(entry.getObject())) return false;
				return entry.result(count = entry.merge(count));
			}
			return java.util.Objects.equals(element, o);
		}

		@Override
		public String toString() {
			return element + ": " + count;
		}
	}

	private final java.util.HashSet<IEntry<E>> set;

	public HashMultiSet() {
		this(16, 0.75f);
	}

	public HashMultiSet(java.util.Collection<? extends E> c) {
		this();
		addAll(c);
	}

	public HashMultiSet(int initialCapacity) {
		this(initialCapacity, 0.75f);
	}

	public HashMultiSet(int initialCapacity, float loadFactor) {
		set = new java.util.HashSet<>(initialCapacity, loadFactor);
	}

	private HashMultiSet(HashMultiSet<E> copy) {
		set = copy.set;
		size = copy.size;
	}

	@Override
	public int size() {
		return (int) Math.min(Integer.MAX_VALUE, size);
	}

	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public boolean contains(Object o) {
		return count(o) != 0;
	}

	private static class Iter<E> implements java.util.Iterator<E> {
		private final java.util.Iterator<IEntry<E>> iter;
		private E element;
		private int count;

		Iter(java.util.Iterator<IEntry<E>> iter) {
			this.iter = iter;
			step();
		}

		private void step() {
			if (iter.hasNext()) {
				IEntry<E> entry = iter.next();
				element = entry.getElement();
				count = entry.getCount();
			} else {
				element = null;
			}
		}

		@Override
		public boolean hasNext() {
			return element != null;
		}

		@Override
		public E next() {
			E ret = element;
			if (--count == 0) step();
			return ret;
		}

	}

	@Override
	public java.util.Iterator<E> iterator() {
		return new Iter<>(set.iterator());
	}

	@Override
	public Object[] toArray() {
		Object[] ret = new Object[size()];
		int i = 0;
		for (E e : this) {
			ret[i] = e;
			if (++i >= ret.length) break;
		}
		return ret;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		int len = size();
		if (a.length < len) {
			@SuppressWarnings("unchecked")
			T[] copy = (T[])java.lang.reflect.Array.newInstance(a.getClass(), len);
			a = copy;
		}
		int i = 0;
		for (E e : this) {
			@SuppressWarnings("unchecked")
			T put = (T)e;
			a[i] = put;
			if (++i >= a.length) break;
		}
		return a;
	}

	@Override
	public boolean containsAll(java.util.Collection<?> c) {
		for (Object o : c) if (!contains(o)) return false;
		return true;
	}

	@Override
	public boolean addAll(java.util.Collection<? extends E> c) {
		boolean ret = false;
		for (E e : c) ret |= add(e);
		return ret;
	}

	@Override
	public boolean removeAll(java.util.Collection<?> c) {
		boolean ret = false;
		for (Object o : c) ret |= removeAll(o);
		return ret;
	}

	@Override
	public boolean retainAll(java.util.Collection<?> c) {
		java.util.ArrayList<IEntry<E>> copy = new java.util.ArrayList<>();
		long nextSize = 0;
		for (IEntry<E> entry : set) {
			for (Object o : c) {
				if (entry.getElement().equals(o)) {
					copy.add(entry);
					nextSize += entry.getCount();
					break;
				}
			}
		}
		boolean ret = size == nextSize;
		set.clear();
		set.addAll(copy);
		size = nextSize;
		return ret;
	}

	@Override
	public void clear() {
		size = 0;
		set.clear();
	}

	private class DummyAddEntry implements IEntry<E> {
		private E element;
		private int count;

		@Override
		public int getCount() { return count; }

		@Override
		public E getElement() { return element; }

		@Override
		public int merge(int n) {
			if (n + count < 0) throw new IllegalArgumentException(element + " is overflow");
			lastCount = n;
			size += count;
			return n + count;
		}

		@Override
		public boolean result(int n) {
			return true;
		}

		@Override
		public int hashCode() {
			return element.hashCode();
		}
	}

	private transient final DummyAddEntry dummyAdd = new DummyAddEntry();

	@Override
	public int add(E element, int occurrences) {
		if (occurrences <= 0) {
			if (occurrences == 0) return count(element);
			throw new IllegalArgumentException(occurrences + " is less than 0");
		}
		dummyAdd.element = element;
		dummyAdd.count = occurrences;
		if (set.contains(dummyAdd)) return lastCount;
		Entry<E> e = new Entry<>(element, occurrences);
		set.add(e);
		size += occurrences;
		return 0;
	}

	private class DummyCountEntry implements IEntry<E> {
		private Object element;

		@Override
		public int getCount() { return 0; }

		@Override
		public E getElement() { return null; }

		@Override
		public Object getObject() { return element; }

		@Override
		public int merge(int n) {
			lastCount = n;
			return n;
		}

		@Override
		public boolean result(int n) {
			return true;
		}

		@Override
		public int hashCode() {
			return element.hashCode();
		}
	}

	private transient final DummyCountEntry dummyCount = new DummyCountEntry();

	@Override
	public int count(Object element) {
		dummyCount.element = element;
		lastCount = 0;
		set.contains(dummyCount);
		return lastCount;
	}

	private static class ElementIter<E> implements java.util.Iterator<E> {
		private final java.util.Iterator<IEntry<E>> iter;

		ElementIter(java.util.Iterator<IEntry<E>> iter) {
			this.iter = iter;
		}

		@Override
		public boolean hasNext() {
			return iter.hasNext();
		}

		@Override
		public E next() {
			return iter.next().getElement();
		}
	}

	private class KeySet implements java.util.Set<E>, Cloneable, java.io.Serializable {

		private static final long serialVersionUID = 4290195633641615399L;

		@Override
		public int size() {
			return set.size();
		}

		@Override
		public boolean isEmpty() { return set.isEmpty(); }

		@Override
		public boolean contains(Object o) {
			return HashMultiSet.this.contains(o);
		}

		@Override
		public java.util.Iterator<E> iterator() {
			return new ElementIter<>(set.iterator());
		}

		@Override
		public Object[] toArray() {
			Object[] ret = new Object[size()];
			int i = 0;
			for (E e : this) {
				ret[i] = e;
				if (++i >= ret.length) break;
			}
			return ret;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			int len = size();
			if (a.length < len) {
				@SuppressWarnings("unchecked")
				T[] copy = (T[])java.lang.reflect.Array.newInstance(a.getClass(), len);
				a = copy;
			}
			int i = 0;
			for (E e : this) {
				@SuppressWarnings("unchecked")
				T put = (T)e;
				a[i] = put;
				if (++i >= a.length) break;
			}
			return a;
		}

		@Override
		public boolean add(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean remove(Object o) {
			return HashMultiSet.this.removeAll(o);
		}

		@Override
		public boolean containsAll(java.util.Collection<?> c) {
			return HashMultiSet.this.containsAll(c);
		}

		@Override
		public boolean addAll(java.util.Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(java.util.Collection<?> c) {
			return HashMultiSet.this.retainAll(c);
		}

		@Override
		public boolean removeAll(java.util.Collection<?> c) {
			return HashMultiSet.this.removeAll(c);
		}

		@Override
		public void clear() {
			HashMultiSet.this.clear();
		}

		@Override
		public boolean equals(Object o) {
			return set.equals(o);
		}

		@Override
		public int hashCode() {
			return set.hashCode();
		}

		@Override
		public String toString() {
			return stream().map(i -> i.toString()).collect(java.util.stream.Collectors.joining(", ", "[", "]"));
		}

		@Override
		public java.util.Set<E> clone() {
			java.util.HashSet<E> ret = new java.util.HashSet<>(this);
			return ret;
		}
	}

	@Override
	public java.util.Set<E> keySet() {
		return new KeySet();
	}

	@Override
	public java.util.Set<? extends MultiSet.Entry<E>> entrySet() {
		return set;
	}

	private class DummyRemoveEntry implements IEntry<E> {
		private Object element;
		private int count;

		@Override
		public int getCount() { return count; }

		@Override
		public E getElement() { return null; }

		@Override
		public Object getObject() { return element; }

		@Override
		public int merge(int n) {
			lastCount = n;
			int diff = Math.min(n, count);
			size -= diff;
			return n - diff;
		}

		@Override
		public boolean result(int n) {
			return n == 0;
		}

		@Override
		public int hashCode() {
			return element.hashCode();
		}

	}
	private transient final DummyRemoveEntry dummyRemove = new DummyRemoveEntry();

	@Override
	public int remove(Object element, int occurrences) {
		if (occurrences <= 0) {
			if (occurrences == 0) return count(element);
			throw new IllegalArgumentException(occurrences + " is less than 0");
		}
		dummyRemove.element = element;
		dummyRemove.count = occurrences;
		lastCount = 0;
		set.remove(dummyRemove);
		return lastCount;
	}

	@Override
	public boolean removeAll(Object element) {
		return remove(element, Integer.MAX_VALUE) != 0;
	}

	private class DummySetEntry implements IEntry<E> {
		private E element;
		private int count;
		@Override
		public int getCount() {return count; }

		@Override
		public E getElement() {return element;}

		@Override
		public int merge(int n) {
			lastCount = n;
			size += count - n;
			return count;
		}

		@Override
		public boolean result(int n) {
			return true;
		}

		@Override
		public int hashCode() {
			return element.hashCode();
		}
	}

	private transient final DummySetEntry dummySet1 = new DummySetEntry();

	@Override
	public int setCount(E element, int count) {
		if (count <= 0) {
			if (count == 0) return remove(element, Integer.MAX_VALUE);
			throw new IllegalArgumentException(count + " is less than 0");
		}
		lastCount = 0;
		dummySet1.count = count;
		dummySet1.element = element;
		if (set.contains(dummySet1)) return lastCount;
		set.add(new Entry<>(element, count));
		return 0;
	}

	private class DummySetEntry2 implements IEntry<E> {
		private E element;
		private int oldCount, newCount;
		private boolean replace;
		@Override
		public int getCount() {return newCount; }

		@Override
		public E getElement() {return element;}

		@Override
		public int merge(int n) {
			replace = false;
			if (n != oldCount) return n;
			replace = true;
			lastCount = n;
			size += newCount - n;
			return newCount;
		}

		@Override
		public boolean result(int n) {
			return replace;
		}

	}

	private transient final DummySetEntry2 dummySet2 = new DummySetEntry2();
	@Override
	public boolean setCount(E element, int oldCount, int newCount) {
		if (oldCount <= 0) {
			if (oldCount == 0) {
				if (newCount <= 0) {
					if (newCount == 0) return false;
					throw new IllegalArgumentException(newCount + " is less than 0");
				}
				if (set.contains(element)) return false;
				set.add(new Entry<>(element, newCount));
				return true;
			}
			throw new IllegalArgumentException(oldCount + " is less than 0");
		}
		if (newCount <= 0) {
			if (newCount == 0) {
				dummySet2.element = element;
				dummySet2.oldCount = oldCount;
				dummySet2.newCount = newCount;
				return set.remove(dummySet2);
			}
			throw new IllegalArgumentException(newCount + " is less than 0");
		}
		dummySet2.element = element;
		dummySet2.oldCount = oldCount;
		dummySet2.newCount = newCount;
		return set.contains(dummySet2);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MultiSet) {
			long match = 0;
			for (MultiSet.Entry<?> entry : ((MultiSet<?>)o).entrySet()) {
				if (count(entry.getElement()) == entry.getCount()) match += entry.getCount();
				else return false;
			}
			return match == size;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return entrySet().hashCode();
	}

	@Override
	public String toString() {
		return set.stream().map(i -> i.toString()).collect(java.util.stream.Collectors.joining(", ", "{", "}"));
	}

	@Override
	public HashMultiSet<E> clone() {
		return new HashMultiSet<>(this);
	}

	public static <E> HashMultiSet<E> create() {
		return new HashMultiSet<>();
	}

	public static <E> HashMultiSet<E> create(int initialCapacity) {
		return new HashMultiSet<>(initialCapacity);
	}

	public static <E> HashMultiSet<E> create(int initialCapacity, float loadFactor) {
		return new HashMultiSet<>(initialCapacity, loadFactor);
	}

	public static <E> HashMultiSet<E> create(java.util.Collection<? extends E> c) {
		return new HashMultiSet<>(c);
	}

	public static <E> HashMultiSet<E> create(HashMultiSet<E> clone) {
		return new HashMultiSet<>(clone);
	}
}
