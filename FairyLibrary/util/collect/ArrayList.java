package com._31536000.util.collect;

public abstract class ArrayList<E> implements List<E>, java.util.RandomAccess, Cloneable{
	private static class ObjectArrayList<E> extends ArrayList<E> implements java.io.Serializable {
		private static final long serialVersionUID = -6363841049593623806L;
		private Object[] list;
		private int size = 0;
		private static final int DEFAULT_SIZE = 11;
		private transient int modCount = 0;
		private ObjectArrayList() {
			this(DEFAULT_SIZE);
		}
		private ObjectArrayList(int size) {
			list = new Object[size];
		}
		private ObjectArrayList(E[] array) {
			list = new Object[array.length];
			System.arraycopy(array, 0, list, 0, list.length);
			size = list.length;
		}
		private ObjectArrayList(java.util.Collection<? extends E> collection) {
			list = collection.toArray();
			size = list.length;
		}
		private ObjectArrayList(Object[] array, int fromIndex, int toIndex) {
			list = java.util.Arrays.copyOfRange(array, fromIndex, toIndex);
			size = list.length;
		}
		private void resize() {
			list = java.util.Arrays.copyOf(list, Math.max(DEFAULT_SIZE, (size << 1) + size >>> 1));
		}
		private void resize(int add) {
			if (size + add < list.length) return;
			list = java.util.Arrays.copyOf(list, Math.max(DEFAULT_SIZE, (size + add << 1) + size >>> 1));
		}
		@Override
		public void ensureCapacity(int minCapacity) {
			list = java.util.Arrays.copyOf(list, Math.max(size, minCapacity));
		}
		@Override
		public void trimToSize() {
			list = java.util.Arrays.copyOf(list, size);
		}
		@Override
		public void add(int index, E element) {
			if (index < 0 || index > size) throw new IndexOutOfBoundsException();
			if (size == list.length) resize();
			System.arraycopy(list, index, list, index + 1, size - index);
			list[index] = element;
			++ size;
			++ modCount;
		}
		@Override
		public boolean add(E e) {
			if (size == list.length) resize();
			list[size++] = e;
			++ modCount;
			return true;
		}
		@Override
		public boolean addAll(java.util.Collection<? extends E> c) {
			if (c == this) {
				if (size == 0) return false;
				resize(size);
				System.arraycopy(list, 0, list, size, size);
				size <<= 1;
				++ modCount;
				return true;
			}
			final int s = c.size();
			if (s == 0) return false;
			resize(s);
			for (E i : c) list[size++] = i;
			++ modCount;
			return false;
		}
		@Override
		public boolean addAll(int index, java.util.Collection<? extends E> c) {
			if (index < 0 || index > size) throw new IndexOutOfBoundsException(String.valueOf(index));
			if (c == this) {
				if (size == 0) return false;
				resize(size);
				System.arraycopy(list, index, list, size + index, size - index);
				System.arraycopy(list, index, list, size, size - index);
				System.arraycopy(list, 0, list, index, index);
				size <<= 1;
				++ modCount;
				return true;
			}
			final int s = c.size();
			if (s == 0) return false;
			resize(s);
			System.arraycopy(list, index, list, index + s, size - index);
			for (E i : c) list[index++] = i;
			size += s;
			++ modCount;
			return true;
		}
		@Override
		public E get(int index) {
			try {
				@SuppressWarnings("unchecked")
				E ret = (E)list[index];
				return ret;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(String.valueOf(index));
			}
		}
		@Override
		public E getFirst() {
			try {
				@SuppressWarnings("unchecked")
				E ret = (E)list[0];
				return ret;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new java.util.NoSuchElementException();
			}
		}
		@Override
		public E getLast() {
			try {
				@SuppressWarnings("unchecked")
				E ret = (E)list[size - 1];
				return ret;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new java.util.NoSuchElementException();
			}
		}
		@Override
		public E set(int index, E element) {
			try {
				@SuppressWarnings("unchecked")
				E ret = (E)list[index];
				list[index] = element;
				return ret;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(String.valueOf(index));
			}
		}
		@Override
		public E remove(int index) {
			try {
				@SuppressWarnings("unchecked")
				E ret = (E)list[index];
				System.arraycopy(list, index + 1, list, index, --size - index);
				++ modCount;
				return ret;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(String.valueOf(index));
			}
		}
		@Override
		public boolean remove(Object o) {
			for (int i = 0;i < size;++ i) {
				if (java.util.Objects.equals(list[i], o)) {
					System.arraycopy(list, i + 1, list, i, --size - i);
					++ modCount;
					return true;
				}
			}
			return false;
		}
		@Override
		public boolean removeAll(java.util.Collection<?> c) {
			int s = 0;
			for (int i = 0;i < size;++ i) {
				if (!c.contains(list[i])) list[s++] = list[i];
			}
			if (s != size) {
				java.util.Arrays.fill(list, s, size, null);
				size = s;
				++ modCount;
				return true;
			}
			return false;
		}
		@Override
		public boolean removeIf(java.util.function.Predicate<? super E> filter) {
			int s = 0;
			for (int i = 0;i < size;++ i) {
				@SuppressWarnings("unchecked")
				E test = (E)list[i];
				if (!filter.test(test)) list[s++] = list[i];
			}
			if (s != size) {
				java.util.Arrays.fill(list, s, size, null);
				size = s;
				++ modCount;
				return true;
			}
			return false;
		}
		@Override
		public boolean retainAll(java.util.Collection<?> c) {
			int s = 0;
			for (int i = 0;i < size;++ i) {
				if (c.contains(list[i])) list[s++] = list[i];
			}
			if (s != size) {
				java.util.Arrays.fill(list, s, size, null);
				size = s;
				++ modCount;
				return true;
			}
			return false;
		}
		@Override
		public void clear() {
			if (size != 0) {
				size = 0;
				++ modCount;
				java.util.Arrays.fill(list, null);
			}
		}
		@Override
		public boolean contains(Object o) {
			for (int i = 0;i < size;++ i) {
				if (java.util.Objects.equals(list[i], o)) return true;
			}
			return false;
		}
		@Override
		public boolean containsAll(java.util.Collection<?> c) {
			for (Object o : c) if (!contains(o)) return false;
			return true;
		}
		@Override
		public int indexOf(Object o) {
			for (int i = 0;i < size;++ i) {
				if (java.util.Objects.equals(list[i], o)) return i;
			}
			return -1;
		}
		@Override
		public int lastIndexOf(Object o) {
			for (int i = size - 1;i >= 0;-- i) if (java.util.Objects.equals(list[i], o)) return i;
			return -1;
		}
		@Override
		public int size() {
			return size;
		}
		@Override
		public boolean isEmpty() {
			return size == 0;
		}
		private static class SubArrayList<E> extends ArrayList<E>{
			private transient int modCount;
			private int from, to;
			private ObjectArrayList<E> list;
			private SubArrayList(ObjectArrayList<E> list, int from, int to) {
				modCount = list.modCount;
				this.from = from;
				this.to = to;
				this.list = list;
			}
			private void checkModification() {
				if (modCount != list.modCount) throw new java.util.ConcurrentModificationException();
			}
			@Override
			public int size() {
				return to - from;
			}
			@Override
			public boolean isEmpty() {
				return from == to;
			}
			@Override
			public boolean contains(Object o) {
				checkModification();
				Object[] array = list.list;
				for (int i = from;i < to;++ i) if (java.util.Objects.equals(array[i], o)) return true;
				return false;
			}
			@Override
			public java.util.Iterator<E> iterator() {
				return listIterator();
			}
			@Override
			public Object[] toArray() {
				checkModification();
				return java.util.Arrays.copyOfRange(list.list, from, to);
			}
			@Override
			public <T> T[] toArray(T[] a) {
				checkModification();
				int size = to - from;
				if (a.length < size) {
					@SuppressWarnings("unchecked")
					T[] copy = (T[])java.lang.reflect.Array.newInstance(a.getClass(), size);
					a = copy;
				}
				System.arraycopy(list, from, a, 0, size);
				return a;
			}
			@Override
			public boolean add(E e) {
				checkModification();
				list.add(to ++, e);
				++ modCount;
				return true;
			}
			@Override
			public boolean remove(Object o) {
				checkModification();
				Object[] array = list.list;
				for (int i = from;i < to;++ i) {
					if (java.util.Objects.equals(array[i], o)) {
						remove(i);
						-- to;
						++ modCount;
						return true;
					}
				}
				return false;
			}
			@Override
			public boolean containsAll(java.util.Collection<?> c) {
				checkModification();
				for (Object o : c) if (!contains(o)) return false;
				return true;
			}
			@Override
			public boolean addAll(java.util.Collection<? extends E> c) {
				checkModification();
				boolean ret = list.addAll(to, c);
				if (ret) {
					to += c.size();
					++ modCount;
					return true;
				}
				return false;
			}
			@Override
			public boolean removeAll(java.util.Collection<?> c) {
				checkModification();
				int i = from, j = from;
				while(j < to) {
					if (!c.contains(list.list[j])) list.list[i++] = list.list[j];
					++ j;
				}
				if (i != to) {
					System.arraycopy(list.list, to, list.list, i, list.size - to);
					list.size -= to - i;
					to = i;
					++ list.modCount;
					++ modCount;
					return true;
				}
				return false;
			}
			@Override
			public boolean removeIf(java.util.function.Predicate<? super E> filter) {
				checkModification();
				int i = from, j = from;
				while(j < to) {
					@SuppressWarnings("unchecked")
					E test = (E)list.list[j];
					if (!filter.test(test)) list.list[i++] = list.list[j];
					++ j;
				}
				if (i != to) {
					System.arraycopy(list.list, to, list.list, i, list.size - to);
					list.size -= to - i;
					to = i;
					++ list.modCount;
					++ modCount;
					return true;
				}
				return false;
			}
			@Override
			public boolean retainAll(java.util.Collection<?> c) {
				checkModification();
				int i = from, j = from;
				while(j < to) {
					if (c.contains(list.list[j])) list.list[i++] = list.list[j];
					++ j;
				}
				if (i != to) {
					System.arraycopy(list.list, to, list.list, i, list.size - to);
					list.size -= to - i;
					to = i;
					++ list.modCount;
					++ modCount;
					return true;
				}
				return false;
			}
			@Override
			public void clear() {
				checkModification();
				System.arraycopy(list.list, to, list.list, from, list.size - to);
				++ list.modCount;
				++ modCount;
				to = from;
			}
			@Override
			public boolean addAll(int index, java.util.Collection<? extends E> c) {
				if (index < 0 || index > to) throw new IndexOutOfBoundsException(String.valueOf(index));
				checkModification();
				if (list.addAll(from + index, c)) {
					to += c.size();
					++ modCount;
					return true;
				}
				return false;
			}
			@Override
			public E get(int index) {
				if (index < 0 || index > to) throw new IndexOutOfBoundsException(String.valueOf(index));
				@SuppressWarnings("unchecked")
				E ret = (E)list.list[from + index];
				return ret;
			}
			@Override
			public E set(int index, E element) {
				if (index < 0 || index > to) throw new IndexOutOfBoundsException(String.valueOf(index));
				@SuppressWarnings("unchecked")
				E last = (E)list.list[from + index];
				list.list[from + index] = element;
				return last;
			}
			@Override
			public void add(int index, E element) {
				if (index < 0 || index > to) throw new IndexOutOfBoundsException(String.valueOf(index));
				checkModification();
				list.add(from + index, element);
				++ to;
				++ modCount;
			}
			@Override
			public E remove(int index) {
				if (index < 0 || index > to) throw new IndexOutOfBoundsException(String.valueOf(index));
				checkModification();
				E ret = list.remove(from + index);
				-- to;
				++ modCount;
				return ret;
			}
			@Override
			public int indexOf(Object o) {
				checkModification();
				Object[] array = list.list;
				for (int i = from;i < to;++ i) {
					if (java.util.Objects.equals(o, array[i])) return i;
				}
				return -1;
			}

			@Override
			public int lastIndexOf(Object o) {
				checkModification();
				Object[] array = list.list;
				for (int i = to - 1;i >= from;-- i) {
					if (java.util.Objects.equals(o, array[i])) return i;
				}
				return -1;
			}
			private class ListIterator implements java.util.ListIterator<E> {
				private int modCount;
				private int index;
				private int lastIndex = -1;
				public ListIterator(int index) {
					this.index = index;
					this.modCount = SubArrayList.this.modCount;
				}
				@Override
				public boolean hasNext() {
					return index < to;
				}
				@Override
				public E next() {
					if (modCount != SubArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
					lastIndex = index;
					return SubArrayList.this.get(index++);
				}
				@Override
				public boolean hasPrevious() {
					return index > 0;
				}
				@Override
				public E previous() {
					if (modCount != SubArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
					lastIndex = --index;
					return SubArrayList.this.get(index);
				}
				@Override
				public int nextIndex() {
					return index + 1;
				}
				@Override
				public int previousIndex() {
					return index - 1;
				}
				@Override
				public void remove() {
					if (modCount != SubArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
					try {
						SubArrayList.this.remove(lastIndex);
						lastIndex = -1;
						++ modCount;
					} catch (IndexOutOfBoundsException e) {
						throw new IllegalStateException();
					}
				}
				@Override
				public void set(E e) {
					if (modCount != SubArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
					try {
						SubArrayList.this.set(lastIndex, e);
						lastIndex = -1;
					} catch (IndexOutOfBoundsException e2) {
						throw new IllegalStateException();
					}
				}
				@Override
				public void add(E e) {
					if (modCount != SubArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
					SubArrayList.this.add(index, e);
					++ modCount;
				}
			}
			@Override
			public java.util.ListIterator<E> listIterator() {
				checkModification();
				return new ListIterator(0);
			}

			@Override
			public java.util.ListIterator<E> listIterator(int index) {
				checkModification();
				return new ListIterator(index);
			}
			@Override
			public void trimToSize() {
				list.trimToSize();
			}
			@Override
			public void ensureCapacity(int minCapacity) {
				list.ensureCapacity(minCapacity);
			}
			@Override
			public ArrayList<E> split(int fromIndex, int toIndex) {
				checkModification();
				if (toIndex > to - from) throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
				ArrayList<E> ret = list.split(fromIndex + from, toIndex + from);
				to -= toIndex - fromIndex;
				++ modCount;
				return ret;
			}
			@Override
			public ArrayList<E> subList(int fromIndex, int toIndex) {
				if (fromIndex < 0 || toIndex > to - from) throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
				if (fromIndex > toIndex) throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
				return new SubArrayList<>(list, fromIndex + from, toIndex + from);
			}
			@Override
			public ArrayList<E> clone() {
				checkModification();
				return new ObjectArrayList<>(list.list, from, to);
			}
			@Override
			public java.util.Spliterator<E> spliterator() {
				return java.util.Spliterators.spliterator(this, java.util.Spliterator.ORDERED);
			}
		}
		@Override
		public ArrayList<E> subList(int fromIndex, int toIndex) {
			if (fromIndex < 0 || toIndex > size) throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
			if (fromIndex > toIndex) throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
			return new SubArrayList<>(this, fromIndex, toIndex);
		}
		@Override
		public ArrayList<E> split(int fromIndex, int toIndex) {
			if (fromIndex < 0 || toIndex > size) throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
			if (fromIndex > toIndex) throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
			ObjectArrayList<E> ret = new ObjectArrayList<E>(list, fromIndex, toIndex);
			System.arraycopy(list, toIndex, list, fromIndex, size - toIndex);
			size -= toIndex - fromIndex;
			++ modCount;
			return ret;
		}
		@Override
		public void merge(java.util.Collection<? extends E> c) {
			if (c instanceof ObjectArrayList) {
				@SuppressWarnings("unchecked")
				ObjectArrayList<E> array = (ObjectArrayList<E>)c;
				if (size + array.size > list.length) list = java.util.Arrays.copyOf(list, Math.max(DEFAULT_SIZE, (size + array.size << 1) + size >>> 1));
				System.arraycopy(array.list, 0, list, size, array.size);
				size += array.size;
				++ modCount;
				array.clear();
			} else {
				addAll(c);
				c.clear();
			}
		}
		@Override
		public java.util.Iterator<E> iterator() {
			return listIterator();
		}
		@Override
		public Object[] toArray() {
			return java.util.Arrays.copyOf(list, size);
		}
		@Override
		public <T> T[] toArray(T[] a) {
			if (a.length < size) {
				@SuppressWarnings("unchecked")
				T[] ret = (T[])java.util.Arrays.copyOf(list, size, a.getClass());
				return ret;
			}
			System.arraycopy(list, 0, a, 0, size);
			return a;
		}
		private class ListIterator implements java.util.ListIterator<E> {
			private int modCount;
			private int index;
			private int lastIndex = -1;
			public ListIterator(int index) {
				this.index = index;
				this.modCount = ObjectArrayList.this.modCount;
			}
			@Override
			public boolean hasNext() {
				return index < size;
			}
			@Override
			public E next() {
				if (modCount != ObjectArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
				lastIndex = index;
				return ObjectArrayList.this.get(index++);
			}
			@Override
			public boolean hasPrevious() {
				return index > 0;
			}
			@Override
			public E previous() {
				if (modCount != ObjectArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
				lastIndex = --index;
				return ObjectArrayList.this.get(index);
			}
			@Override
			public int nextIndex() {
				return index + 1;
			}
			@Override
			public int previousIndex() {
				return index - 1;
			}
			@Override
			public void remove() {
				if (modCount != ObjectArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
				try {
					ObjectArrayList.this.remove(lastIndex);
					lastIndex = -1;
					++ modCount;
				} catch (IndexOutOfBoundsException e) {
					throw new IllegalStateException();
				}
			}
			@Override
			public void set(E e) {
				if (modCount != ObjectArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
				try {
					ObjectArrayList.this.set(lastIndex, e);
					lastIndex = -1;
				} catch (IndexOutOfBoundsException e2) {
					throw new IllegalStateException();
				}
			}
			@Override
			public void add(E e) {
				if (modCount != ObjectArrayList.this.modCount) throw new java.util.ConcurrentModificationException();
				ObjectArrayList.this.add(index, e);
				++ modCount;
			}
		}
		@Override
		public java.util.ListIterator<E> listIterator() {
			return new ListIterator(0);
		}
		@Override
		public java.util.ListIterator<E> listIterator(int index) {
			return new ListIterator(index);
		}
		@Override
		public ArrayList<E> clone() {
			return new ObjectArrayList<E>(list, 0, size);
		}
		@Override
		public java.util.Spliterator<E> spliterator() {
			return java.util.Spliterators.spliterator(this, java.util.Spliterator.ORDERED);
		}
	}
	public abstract void  trimToSize();
	public abstract void ensureCapacity(int minCapacity);
	public static <E> ArrayList<E> create() {
		return new ObjectArrayList<>();
	}
	public static <E> ArrayList<E> create(int size) {
		return new ObjectArrayList<>(size);
	}
	public static <E> ArrayList<E> create(java.util.Collection<? extends E> collection) {
		return new ObjectArrayList<>(collection);
	}
	public static <E> ArrayList<E> create(E[] array) {
		return new ObjectArrayList<>(array);
	}
	@Override
	public abstract ArrayList<E> split(int fromIndex, int toIndex);
	@Override
	public abstract ArrayList<E> subList(int fromIndex, int toIndex);
	@Override
	public abstract ArrayList<E> clone();
	@Override
	public String toString() {
		return stream().map(i -> String.valueOf(i)).collect(java.util.stream.Collectors.joining(", ", "[", "]"));
	}
	@Override
	public boolean equals(Object o) {
		return o instanceof java.util.List ? equals((java.util.List<?>)o) : false;
	}
	/**
	 * 指定されたリストがこのリストと等しいかどうかを比較します。
	 * 指定されたリストのサイズが同じで、2つのリストの対応する要素がすべて等しい場合にだけtrueを返します。
	 * 2つの要素e1とe2は、(e1==null ? e2==null : e1.equals(e2))である場合に等しくなります。
	 * つまり2つのリストは、同じ要素が同じ順序で含まれている場合に等しいものとして定義されます。
	 * この定義により、Listインタフェースの実装が異なっても、equalsメソッドが正しく動作することが保証されます。
	 * @param o このリストと等しいかどうかを比較するオブジェクト
	 * @return 指定されたオブジェクトがこのリストと等しい場合はtrue
	 */
	public boolean equals(java.util.List<?> o) {
		int size = size();
		if (size != o.size()) return false;
		java.util.Iterator<?> iter1 = iterator(), iter2 = o.iterator();
		for (int i = 0;i < size;++ i) {
			if (!java.util.Objects.equals(iter1.next(), iter2.next())) return false;
		}
		return true;
	}
	@Override
	public int hashCode() {
		int hashCode = 1;
		for (E e : this) hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}
}
