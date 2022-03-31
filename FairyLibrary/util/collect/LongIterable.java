package com._31536000.util.collect;

import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.LongConsumer;

public interface LongIterable extends Iterable<Long>{
	@Override
	public PrimitiveIterator.OfLong iterator();
	@Override
	public default Spliterator.OfLong spliterator() {
		return Spliterators.spliteratorUnknownSize(iterator(), 0);
	}
	public default void forEach(LongConsumer action) {
		iterator().forEachRemaining(action);
	}
}
