
package com._31536000.util.collect;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMultiMap<K, V> implements SetMultiMap<K, V>, Serializable {

	private static final long serialVersionUID = -3522722031250641186L;
	private final transient HashMap<K, Collection<V>> map;
	private final transient int expectedValuesPerKey;
	private transient int size = 0;

	private HashMultiMap() {
		map = new HashMap<>();
		expectedValuesPerKey = 2;
	}

	private HashMultiMap(int expectedKeys, int expectedValuesPerKey) {
		map = new HashMap<>(expectedKeys);
		this.expectedValuesPerKey = expectedValuesPerKey;
	}

	private HashMultiMap(MultiMap<? extends K, ? extends V> multimap) {
		this();
		putAll(multimap);
	}

	public static <K, V> HashMultiMap<K, V> create() {
		return new HashMultiMap<>();
	}

	public static <K, V> HashMultiMap<K, V> create(int expectedKeys, int expectedValuesPerKey) {
		return new HashMultiMap<>(expectedKeys, expectedValuesPerKey);
	}

	public static <K, V> HashMultiMap<K, V> create(MultiMap<? extends K, ? extends V> multimap) {
		return new HashMultiMap<>(multimap);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		for (Collection<V> i : map.values()) if (i.contains(value)) return true;
		return false;
	}

	@Override
	public boolean containsEntry(Object key, Object value) {
		Set<V> set = (Set<V>)map.get(key);
		if (set == null) return false;
		return set.contains(value);
	}

	@Override
	public boolean put(K key, V value) {
		Set<V> set = (Set<V>)map.get(key);
		if (set == null) map.put(key, set = new HashSet<>(expectedValuesPerKey));
		if (set.add(value)) {
			++ size;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object key, Object value) {
		Set<V> set = (Set<V>)map.get(key);
		if (set == null) return false;
		if (set.remove(value)) {
			-- size;
			return true;
		}
		return false;
	}

	@Override
	public boolean putAll(K key, Iterable<? extends V> values) {
		boolean ret = false;
		for (V i : values) ret &= put(key, i);
		return ret;
	}

	@Override
	public boolean putAll(MultiMap<? extends K, ? extends V> multimap) {
		boolean ret = false;
		for (Entry<? extends K, ? extends V> i : multimap.entries()) ret &= put(i.getKey(), i.getValue());
		return ret;
	}

	@Override
	public void clear() {
		map.clear();
		size = 0;
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	private class HashMultiSet extends AbstractCollection<K> implements MultiSet<K>, Serializable {

		private static final long serialVersionUID = 4122596903774870369L;

		@Override
		public int size() {
			return HashMultiMap.this.size();
		}

		@Override
		public boolean isEmpty() {
			return HashMultiMap.this.isEmpty();
		}

		@Override
		public boolean contains(Object o) {
			return HashMultiMap.this.containsKey(o);
		}

		@Override
		public void clear() {
			HashMultiMap.this.clear();
		}

		@Override
		public int add(K element, int occurrences) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int count(Object element) {
			return map.get(element).size();
		}

		@Override
		public Set<K> elementSet() {
			return map.keySet();
		}

		@Override
		public boolean remove(Object element, int occurrences) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int setCount(K element, int count) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean setCount(K element, int oldCount, int newCount) {
			throw new UnsupportedOperationException();
		}

		private class Iter implements Iterator<K> {

			private final Iterator<Entry<K, Collection<V>>> iter = map.entrySet().iterator();
			private K value;
			private int size = 0;

			@Override
			public boolean hasNext() {
				while(size == 0) {
					if (iter.hasNext()) {
						Entry<K, Collection<V>> entry = iter.next();
						value = entry.getKey();
						size = entry.getValue().size();
					}
					else return false;
				}
				return true;
			}

			@Override
			public K next() {
				return value;
			}

		}

		@Override
		public Iterator<K> iterator() {
			return new Iter();
		}
	}

	@Override
	public MultiSet<K> keys() {
		return new HashMultiSet();
	}

	private class Values extends AbstractCollection<V> {

		private class Iter implements Iterator<V> {

			private final Iterator<Entry<K, Collection<V>>> iter = map.entrySet().iterator();
			private Iterator<V> value;

			@Override
			public boolean hasNext() {
				while(value == null || !value.hasNext()) {
					if (iter.hasNext()) {
						Entry<K, Collection<V>> entry = iter.next();
						value = entry.getValue().iterator();
					}
					else return false;
				}
				return true;
			}

			@Override
			public V next() {
				return value.next();
			}

		}

		@Override
		public Iterator<V> iterator() {
			return new Iter();
		}

		@Override
		public int size() {
			return HashMultiMap.this.size();
		}

	}

	@Override
	public Collection<V> values() {
		return new Values();
	}

	@Override
	public Set<V> get(K key) {
		return (Set<V>)map.get(key);
	}

	@Override
	public Set<V> removeAll(Object key) {
		return (Set<V>)map.remove(key);
	}

	@Override
	public Set<V> replaceValues(K key, Iterable<? extends V> values) {
		Set<V> ret = (Set<V>)map.remove(key);
		putAll(key, values);
		return ret;
	}

	private class EntrySet extends AbstractCollection<Entry<K, V>> implements Set<Entry<K, V>>, Serializable {

		private static final long serialVersionUID = -4247227529619874920L;

		@Override
		public int size() {
			return HashMultiMap.this.size();
		}

		@Override
		public boolean isEmpty() {
			return HashMultiMap.this.isEmpty();
		}

		@Override
		public boolean contains(Object o) {
			return HashMultiMap.this.containsKey(o);
		}

		@Override
		public void clear() {
			HashMultiMap.this.clear();
		}

		private class Iter implements Iterator<Entry<K, V>> {

			private final Iterator<Entry<K, Collection<V>>> iter = map.entrySet().iterator();
			private K key;
			private Iterator<V> value;

			private class EntryPair implements Entry<K, V> {

				private K key;
				private V value;

				EntryPair(K k, V v) {
					key = k;
					value = v;
				}
				@Override
				public K getKey() {
					return key;
				}

				@Override
				public V getValue() {
					return value;
				}

				@Override
				public V setValue(V value) {
					V ret = this.value;
					this.value = value;
					return ret;
				}

			}

			@Override
			public boolean hasNext() {
				while(!value.hasNext()) {
					if (iter.hasNext()) {
						Entry<K, Collection<V>> entry = iter.next();
						key = entry.getKey();
						value = entry.getValue().iterator();
					}
					else return false;
				}
				return true;
			}

			@Override
			public Entry<K, V> next() {
				return new EntryPair(key, value.next());
			}

		}

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new Iter();
		}
	}

	@Override
	public Set<Entry<K, V>> entries() {
		return new EntrySet();
	}

	@Override
	public Map<K, Collection<V>> asMap() {
		return map;
	}

}
