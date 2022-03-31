package com._31536000.util.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface SetMultiMap<K, V> extends MultiMap<K, V> {
	@Override
	public Set<V> get(K key);
	@Override
	public Set<V> removeAll(Object key);
	@Override
	public Set<V> replaceValues(K key, Iterable<? extends V> values);
	@Override
	public Set<Map.Entry<K, V>> entries();
	@Override
	public Map<K, Collection<V>> asMap();
}
