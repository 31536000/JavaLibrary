package com._31536000.util.collect;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public abstract class RangeSet<E, R extends Range<E>> implements java.util.Set<R>{
	public abstract java.util.Comparator<? super E> comparator();
	public abstract R first();
	public abstract R last();
	public abstract RangeSet<E, R> headSet(E toElement);
	public abstract RangeSet<E, R> headSet(E toElement, BoundType boundType);
	public abstract RangeSet<E, R> subSet(E fromElement, E toElement);
	public abstract RangeSet<E, R> subSet(E fromElement, BoundType fromBoundType, E toElement, BoundType toBoundType);
	public abstract RangeSet<E, R> tailSet(E fromElement);
	public abstract RangeSet<E, R> tailSet(E fromElement, BoundType boundType);
	@Override
	public java.util.Spliterator<R> spliterator() {
		return java.util.Spliterators.spliterator(this, java.util.Spliterator.DISTINCT | java.util.Spliterator.SORTED | java.util.Spliterator.ORDERED | java.util.Spliterator.NONNULL);
	}
	private static class ObjectRangeSet<E, R extends Range<E>> extends RangeSet<E, R> {

		private java.util.TreeSet<R> set;
		@Override
		public int size() {
			return set.size();
		}

		@Override
		public boolean isEmpty() { return set.isEmpty(); }

		@Override
		public boolean contains(Object o) {
			if (o instanceof Range<?>) {
				try {
					@SuppressWarnings("unchecked")
					R test = (R) o;
					R other = set.floor(test);
					if (other != null && test.isIntersect(other)) return true;
					other = set.ceiling(test);
					return other != null && test.isIntersect(other);
				} catch (ClassCastException e) {
				}
			}
			try {
				@SuppressWarnings("unchecked")
				E test = (E)o;
				Range<E> range = Range.create(test
			} catch (ClassCastException e) {
				return false;
			}
		}

		@Override
		public Iterator<R> iterator() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public boolean add(R e) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean remove(Object o) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends R> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO 自動生成されたメソッド・スタブ
			return false;
		}

		@Override
		public void clear() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public Comparator<? super E> comparator() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public R first() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public R last() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public RangeSet<E, R> headSet(E toElement) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public RangeSet<E, R> headSet(E toElement, BoundType boundType) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public RangeSet<E, R> subSet(E fromElement, E toElement) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public RangeSet<E, R> subSet(E fromElement, BoundType fromBoundType, E toElement, BoundType toBoundType) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public RangeSet<E, R> tailSet(E fromElement) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

		@Override
		public RangeSet<E, R> tailSet(E fromElement, BoundType boundType) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}

	}
}
