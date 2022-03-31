package com._31536000.util.collect;

import java.util.Spliterator;
import java.util.Spliterators;

public interface LongCollection extends Collection<Long>, LongIterable{
	@Override
	public default Spliterator.OfLong spliterator() {
		return Spliterators.spliteratorUnknownSize(iterator(), 0);
	}
	@Override
	public default boolean add(Long e) {
		return addAsLong(e);
	}

	public boolean addAsLong(long e);
	
	@Override
	public default boolean addAll(Collection<? extends Long> c) {
		
	}
	public boolean addAllAsLong(LongCollection c);
}
