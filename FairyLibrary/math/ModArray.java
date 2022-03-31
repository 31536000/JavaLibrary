package com._31536000.math;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator.OfInt;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com._31536000.math.algebraic.field.Field;
import com._31536000.math.algebraic.group.Abelian;
import com._31536000.util.collect.IntArray;
import com._31536000.util.collect.PrimitiveListIterator;

public abstract class ModArray<T extends ModArray<T>> extends IntArray implements Field<T, Abelian<T>, Abelian<T>> {
	private static class DefaultModArray extends ModArray<DefaultModArray> {
		private final ModUtility mod;
		final int[] array;

		DefaultModArray(ModUtility mod, int n) {
			this.mod = mod;
			array = new int[n];
		}

		DefaultModArray(ModUtility mod, int... array) {
			this.mod = mod;
			this.array = java.util.Arrays.copyOf(array, array.length);
		}

		private DefaultModArray(ModUtility mod, int[] array, int from, int to) {
			this.mod = mod;
			this.array = java.util.Arrays.copyOfRange(array, from, to);
		}

		@Override
		public boolean contains(int o) {
			for (int i : array) if (i == o) return true;
			return false;
		}

		@Override
		public boolean containsAll(int... c) {
			cont: for (int i : array) {
				for (int j : c) if (i == j) continue cont;
				return false;
			}
			return true;
		}

		@Override
		public OfInt iterator() {
			return listIterator();
		}

		@Override
		public int[] toArrayAsInt() {
			return java.util.Arrays.copyOf(array, array.length);
		}

		@Override
		public int getAsInt(int index) {
			return array[index];
		}

		@Override
		public int setAsInt(int index, int element) {
			int last = array[index];
			array[index] = mod.mod(element);
			return last;
		}

		@Override
		public int indexOf(int o) {
			for (int i = 0; i < array.length; ++i) if (array[i] == o) return i;
			return -1;
		}

		@Override
		public int lastIndexOf(int o) {
			for (int i = array.length - 1; i >= 0; --i) if (array[i] == o) return i;
			return -1;
		}

		private class ListIterator implements PrimitiveListIterator.OfInt {
			int index, last = -1;

			public ListIterator(int index) {
				this.index = index;
			}

			@Override
			public boolean hasNext() {
				return index < array.length;
			}

			@Override
			public boolean hasPrevious() {
				return index >= 0;
			}

			@Override
			public int nextIndex() {
				return index;
			}

			@Override
			public int previousIndex() {
				return index - 1;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int nextInt() {
				try {
					return array[last = index++];
				} catch (IndexOutOfBoundsException e) {
					throw new NoSuchElementException(String.valueOf(last));
				}
			}

			@Override
			public int previousInt() {
				try {
					return array[last = --index];
				} catch (IndexOutOfBoundsException e) {
					throw new NoSuchElementException(String.valueOf(last));
				}
			}

			@Override
			public void setAsInt(int e) {
				try {
					array[last] = e;
				} catch (IndexOutOfBoundsException e2) {
					throw new IllegalStateException();
				}
			}

			@Override
			public void addAsInt(int e) {
				throw new UnsupportedOperationException();
			}
		}

		@Override
		public com._31536000.util.collect.PrimitiveListIterator.OfInt listIterator() {
			return listIterator(0);
		}

		@Override
		public com._31536000.util.collect.PrimitiveListIterator.OfInt listIterator(int index) {
			return new ListIterator(index);
		}

		private class SubArray extends ModArray<DefaultModArray> {
			private final int from, to;

			SubArray(int fromIndex, int toIndex) {
				from = fromIndex;
				to = toIndex;
			}

			@Override
			public boolean contains(int o) {
				for (int i = from; i < to; ++i) if (array[i] == o) return true;
				return false;
			}

			@Override
			public boolean containsAll(int... c) {
				for (int i : c) if (!contains(i)) return false;
				return true;
			}

			@Override
			public int[] toArrayAsInt() {
				return java.util.Arrays.copyOfRange(array, from, to);
			}

			@Override
			public int getAsInt(int index) {
				return array[from + index];
			}

			@Override
			public int setAsInt(int index, int element) {
				index += from;
				int last = array[index];
				array[index] = element;
				return last;
			}

			@Override
			public int indexOf(int o) {
				for (int i = from; i < to; ++i) if (array[i] == o) return i;
				return -1;
			}

			@Override
			public int lastIndexOf(int o) {
				for (int i = to - 1; i >= from; --i) if (array[i] == o) return i;
				return -1;
			}

			private class ListIterator implements PrimitiveListIterator.OfInt {
				int index, last = -1;

				public ListIterator(int index) {
					this.index = index;
				}

				@Override
				public boolean hasNext() {
					return index < to;
				}

				@Override
				public boolean hasPrevious() {
					return index > from;
				}

				@Override
				public int nextIndex() {
					return index;
				}

				@Override
				public int previousIndex() {
					return index - 1;
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}

				@Override
				public int nextInt() {
					try {
						return array[last = index++];
					} catch (IndexOutOfBoundsException e) {
						throw new NoSuchElementException(String.valueOf(last));
					}
				}

				@Override
				public int previousInt() {
					try {
						return array[last = --index];
					} catch (IndexOutOfBoundsException e) {
						throw new NoSuchElementException(String.valueOf(last));
					}
				}

				@Override
				public void setAsInt(int e) {
					try {
						array[last] = e;
					} catch (IndexOutOfBoundsException e2) {
						throw new IllegalStateException();
					}
				}

				@Override
				public void addAsInt(int e) {
					throw new UnsupportedOperationException();
				}
			}

			@Override
			public com._31536000.util.collect.PrimitiveListIterator.OfInt listIterator() {
				return listIterator(0);
			}

			@Override
			public com._31536000.util.collect.PrimitiveListIterator.OfInt listIterator(int index) {
				return new ListIterator(from + index);
			}

			@Override
			public ModArray<DefaultModArray> subArray(int fromIndex, int toIndex) {
				if (fromIndex < 0 || toIndex > size())
					throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
				if (fromIndex > toIndex)
					throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
				return DefaultModArray.this.subArray(from, to);
			}

			@Override
			public void forEach(IntConsumer action) {
				for (int i = from; i < to; ++i) action.accept(array[i]);
			}

			@Override
			public IntStream intstream() {
				return java.util.Arrays.stream(array, from, to);
			}

			@Override
			public java.util.Spliterator.OfInt spliterator() {
				return java.util.Arrays.spliterator(array, from, to);
			}

			@Override
			public DefaultModArray clone() {
				return new DefaultModArray(mod, array, from, to);
			}

			@Override
			public int size() {
				return to - from;
			}

			@Override
			public boolean equals(Object o) {
				if (o instanceof ModArray) {
					ModArray<?> o2 = (ModArray<?>) o;
					for (int i = from; i < to; ++i) if (array[i] != o2.getAsInt(i - from)) return false;
					return size() == o2.size();
				}
				return false;
			}

			@Override
			public int hashCode() {
				int hashCode = 1;
				for (int i = from; i < to; ++i) hashCode = 31 * hashCode + array[i];
				return hashCode;
			}

			@Override
			public String toString() {
				return intstream().mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(", ", "[", "]"));
			}

			@Override
			public int getMod() { return mod.getMod(); }
		}

		@Override
		public ModArray<DefaultModArray> subArray(int fromIndex, int toIndex) {
			if (fromIndex < 0 || toIndex > size())
				throw new IndexOutOfBoundsException(String.format("[%d, %d)", fromIndex, toIndex));
			if (fromIndex > toIndex) throw new IllegalArgumentException(String.format("[%d, %d)", fromIndex, toIndex));
			return new SubArray(fromIndex, toIndex);
		}

		@Override
		public void forEach(IntConsumer action) {
			for (int i : array) action.accept(i);
		}

		@Override
		public IntStream intstream() {
			return java.util.Arrays.stream(array);
		}

		@Override
		public java.util.Spliterator.OfInt spliterator() {
			return java.util.Arrays.spliterator(array);
		}

		@Override
		public DefaultModArray clone() {
			return new DefaultModArray(mod, array);
		}

		@Override
		public int size() {
			return array.length;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof ModArray) {
				ModArray<?> o2 = (ModArray<?>) o;
				for (int i = 0; i < array.length; ++i) if (array[i] != o2.getAsInt(i)) return false;
				return size() == o2.size();
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(array);
		}

		@Override
		public String toString() {
			return Arrays.toString(array);
		}

		@Override
		public int getMod() { return mod.getMod(); }

		public static class Addition implements Abelian<DefaultModArray> {
			final ModUtility mod;

			Addition(ModUtility mod) {
				this.mod = mod;
			}

			@Override
			public DefaultModArray apply(DefaultModArray t, DefaultModArray u) {
				if (t.size() >= u.size()) {
					DefaultModArray ret = t.clone();
					for (int i = 0; i < u.array.length; ++i) ret.array[i] = mod.add(ret.array[i], u.array[i]);
					return ret;
				}
				DefaultModArray ret = u.clone();
				for (int i = 0; i < t.array.length; ++i) ret.array[i] = mod.add(ret.array[i], t.array[i]);
				return ret;
			}

			@Override
			public DefaultModArray identity() {
				return new DefaultModArray(mod, 0);
			}

			@Override
			public DefaultModArray inverse(DefaultModArray element) {
				DefaultModArray ret = element.clone();
				for (int i = 0; i < ret.array.length; ++i) ret.array[i] = mod.subtract(0, ret.array[i]);
				return ret;
			}
		}

		public static class Multiplication implements Abelian<DefaultModArray> {
			final ModUtility mod;

			Multiplication(ModUtility mod) {
				this.mod = mod;
			}
			@Override
			public DefaultModArray apply(DefaultModArray t, DefaultModArray u) {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

			@Override
			public DefaultModArray identity() {
				DefaultModArray ret = new DefaultModArray(mod, 1);
				ret.set(0, 1);
				return ret;
			}

			@Override
			public DefaultModArray inverse(DefaultModArray element) {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}

		}

		@Override
		public Abelian<DefaultModArray> getAddition() { return new Addition(mod); }

		@Override
		public Abelian<DefaultModArray> getMultiplication() { // TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public int plus(int index, int value) {
			return mod.add(array[index], value);
		}

		@Override
		public int plusAssign(int index, int value) {
			return array[index] = mod.add(array[index], value);
		}

		@Override
		public int minus(int index, int value) {
			return mod.subtract(array[index], value);
		}

		@Override
		public int minusAssign(int index, int value) {
			return array[index] = mod.subtract(array[index], value);
		}

		@Override
		public int times(int index, int value) {
			return mod.multiply(array[index], value);
		}

		@Override
		public int timesAssign(int index, int value) {
			return array[index] = mod.multiply(array[index], value);
		}

		@Override
		public int div(int index, int value) {
			return mod.divide(array[index], value);
		}

		@Override
		public int divAssign(int index, int value) {
			return array[index] = mod.divide(array[index], value);
		}
	}

	@Override
	public abstract ModArray<T> subArray(int fromIndex, int toIndex);

	@Override
	public abstract ModArray<T> clone();

	public static ModArray<DefaultModArray> create(ModUtility mod, int n) {
		return new DefaultModArray(mod, n);
	}

	public static ModArray<DefaultModArray> create(ModUtility mod, int... array) {
		return new DefaultModArray(mod, array);
	}

	public abstract int getMod();

	public abstract int plus(int index, int value);

	public abstract int plusAssign(int index, int value);

	public abstract int minus(int index, int value);

	public abstract int minusAssign(int index, int value);

	public abstract int times(int index, int value);

	public abstract int timesAssign(int index, int value);

	public abstract int div(int index, int value);

	public abstract int divAssign(int index, int value);
}
