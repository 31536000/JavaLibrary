package com._31536000.util;


public class BitSet implements Cloneable, java.io.Serializable{

	private static final long serialVersionUID = -3634549518640192789L;
	private long[] bitset;

	public BitSet() {
		bitset = new long[0];
	}

	public BitSet(int size) {
		bitset = new long[size + 63 >> 6];
	}

	public BitSet(BitSet set) {
		bitset = java.util.Arrays.copyOf(set.bitset, set.bitset.length);
	}

	public final void and(BitSet set) {
		if (bitset.length > set.bitset.length) {
			for (int i = 0;i < set.bitset.length;++ i) bitset[i] &= set.bitset[i];
			java.util.Arrays.fill(bitset, set.bitset.length, bitset.length, 0);
		} else {
			for (int i = 0;i < bitset.length;++ i) bitset[i] &= set.bitset[i];
		}
	}

	public final void andNot(BitSet set) {
		if (bitset.length > set.bitset.length) {
			for (int i = 0;i < set.bitset.length;++ i) bitset[i] &= ~set.bitset[i];
			java.util.Arrays.fill(bitset, set.bitset.length, bitset.length, 0);
		} else {
			for (int i = 0;i < bitset.length;++ i) bitset[i] &= ~set.bitset[i];
		}
	}

	public final int cardinality() {
		int cardinality = 0;
		for (long i : bitset) cardinality += Long.bitCount(i);
		return cardinality;
	}

	public void clear() {
		java.util.Arrays.fill(bitset, 0);
	}

	public final void clear(int bitIndex) {
		try {
			if (bitIndex >= size()) return;
			bitset[bitIndex >> 6] &= ~(1L << bitIndex);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
	}

	public final void clear(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex > toIndex) throw new IndexOutOfBoundsException();
		if (toIndex >= size()) {
			if (fromIndex >= size()) return;
			toIndex = size();
		}
		int left = fromIndex >> 6, right = toIndex >> 6;
		long l = (1L << fromIndex) - 1, r = (1L << toIndex) - 1;
		switch(right - left) {
			case 1:
				bitset[left] &= l;
				bitset[right] &= ~r;
				return;
			case 0:
				bitset[left] &= l | ~r;
				return;
			default:
				java.util.Arrays.fill(bitset, left + 1, right, 0);
		}
	}

	@Override
	public final BitSet clone() {
		return new BitSet(this);
	}

	public void ensureCapacity(int minCapacity) {
		if (size() >= minCapacity) return;
		bitset = java.util.Arrays.copyOf(bitset, minCapacity + 63 >> 6);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof BitSet) {
			BitSet set = (BitSet)o;
			boolean ret = true;
			if (bitset.length <= set.bitset.length) {
				for (int i = 0;i < bitset.length;++ i) ret &= (bitset[i] ^ set.bitset[i]) == 0;
				for (int i = bitset.length;i < set.bitset.length;++ i) ret &= set.bitset[i] == 0;
				return ret;
			}
			for (int i = 0;i < set.bitset.length;++ i) ret &= (bitset[i] ^ set.bitset[i]) == 0;
			for (int i = set.bitset.length;i < bitset.length;++ i) ret &= bitset[i] == 0;
			return ret;
		}
		return false;
	}

	public void flip(int bitIndex) {
		try {
			ensureCapacity(bitIndex);
			bitset[bitIndex >> 6] ^= ~(1L << bitIndex);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
	}

	public void flip(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex > toIndex) throw new IndexOutOfBoundsException();
		ensureCapacity(toIndex);
		int left = fromIndex >> 6, right = toIndex >> 6;
		long l = (1L << fromIndex) - 1, r = (1L << toIndex) - 1;
		bitset[left] ^= l;
		for (int i = left + 1;i < right;++ i) bitset[i] = ~bitset[i];
		bitset[right] ^= ~r;
	}

	public boolean get(int bitIndex) {
		try {
			if (bitIndex >= size()) return false;
			return (bitset[bitIndex >> 6] >>> (bitIndex & 63) & 1) != 0;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
	}

	public BitSet get(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex > toIndex) throw new IndexOutOfBoundsException();
		BitSet ret = new BitSet();
		ret.bitset = new long[toIndex - fromIndex + 63 >> 6];
		if ((fromIndex & 63) == 0) {
			final int pre = fromIndex >> 6;
			for (int i = 0;i < ret.bitset.length;++ i) ret.bitset[i] = bitset[i + pre];
			ret.bitset[ret.bitset.length - 1] &= (1L << toIndex) - 1;
			return ret;
		}
		final int pre = fromIndex >> 6, from = fromIndex & 63, to = 64 - from;
		if (toIndex >> 6 < bitset.length) for (int i = 0;i < ret.bitset.length;) ret.bitset[i] = bitset[i + pre] >>> from | bitset[++i + pre] << to;
		else for (int i = 0, l = ret.bitset.length - 1;i < l;) ret.bitset[i] = bitset[i + pre] >>> from | bitset[++i + pre] << to;
		ret.bitset[ret.bitset.length - 1] &= (1L << toIndex - fromIndex) - 1;
		return ret;
	}

	@Override
	public int hashCode() {
		long h = 1234;
		for (int i = bitset.length; --i >= 0;h ^= bitset[i] * (i + 1));
		return (int)(h >> 32 ^ h);
	}

	public boolean intersects(BitSet set) {
		int ans = 0;
		if (bitset.length < set.bitset.length) for (int i = 0;i < bitset.length;++ i) ans |= bitset[i] & set.bitset[i];
		else  for (int i = 0;i < set.bitset.length;++ i) ans |= bitset[i] & set.bitset[i];
		return ans != 0;
	}

	public boolean isEmpty() {
		long ans = 0;
		for (long i : bitset) ans |= i;
		return ans == 0;
	}

	public int length() {
		for (int i = bitset.length - 1;i >= 0;-- i) if (bitset[i] != 0) return (i << 6) + 64 - Long.numberOfLeadingZeros(bitset[i]);
		return 0;
	}

	public int nextClearBit(int fromIndex) {
		try {
			int pre = fromIndex >> 6;
			if (pre >= bitset.length) return fromIndex;
			long test = bitset[pre] | (1 << fromIndex) - 1;
			if (test != 0xFFFFFFFFFFFFFFFFL) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.lowestOneBit(~test));
			while(++pre < bitset.length) if (bitset[pre] != 0xFFFFFFFFFFFFFFFFL) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.lowestOneBit(~bitset[pre]));
			return bitset.length;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.valueOf(fromIndex));
		}
	}

	public int nextSetBit(int fromIndex) {
		try {
			int pre = fromIndex >> 6;
			if (pre >= bitset.length) return -1;
			long test = bitset[pre] & ~((1 << fromIndex) - 1);
			if (test != 0) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.lowestOneBit(test));
			while(++pre < bitset.length) if (bitset[pre] != 0) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.lowestOneBit(bitset[pre]));
			return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.valueOf(fromIndex));
		}
	}

	public void or(BitSet set) {
		if (bitset.length < set.bitset.length) bitset = java.util.Arrays.copyOf(bitset, set.bitset.length);
		for (int i = 0;i < set.bitset.length;++ i) bitset[i] |= set.bitset[i];
	}

	public int previousClearBit(int fromIndex) { // TODO
		try {
			int pre = fromIndex >> 6;
			if (pre >= bitset.length) return fromIndex;
			long test = ~bitset[pre] & (1 << fromIndex) - 1;
			if (test != 0) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.highestOneBit(test));
			while(--pre >= 0) if (bitset[pre] != 0xFFFFFFFFFFFFFFFFL) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.highestOneBit(~bitset[pre]));
			return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.valueOf(fromIndex));
		}
	}

	public int previousSetBit(int fromIndex) {
		try {
			int pre = fromIndex >> 6;
			if (pre >= bitset.length) return length() - 1;
			long test = bitset[pre] & (1 << fromIndex) - 1;
			if (test != 0) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.highestOneBit(test));
			while(--pre >= 0) if (bitset[pre] != 0) return (pre << 6) + 64 - Long.numberOfLeadingZeros(Long.highestOneBit(bitset[pre]));
			return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.valueOf(fromIndex));
		}
	}

	public void set(int bitIndex) {
		try {
			ensureCapacity(bitIndex);
			bitset[bitIndex >> 6] |= 1L << bitIndex;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.valueOf(bitIndex));
		}
	}

	public void set(int bitIndex, boolean value) {
		try {
			ensureCapacity(bitIndex);
			if (value) bitset[bitIndex >> 6] |= 1L << bitIndex;
			else bitset[bitIndex >> 6] &= ~(1L << bitIndex);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.valueOf(bitIndex));
		}
	}

	public void set(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex > toIndex) throw new IndexOutOfBoundsException();
		ensureCapacity(toIndex);
		int left = fromIndex >> 6, right = toIndex >> 6;
		long l = (1L << fromIndex) - 1, r = (1L << toIndex) - 1;
		bitset[left] |= l;
		for (int i = left + 1;i < right;++ i) bitset[i] = 0xFFFFFFFFFFFFFFFFL;
		bitset[right] |= ~r;
	}

	public void set(int fromIndex, int toIndex, boolean value) {
		if (fromIndex < 0 || fromIndex > toIndex) throw new IndexOutOfBoundsException();
		ensureCapacity(toIndex);
		int left = fromIndex >> 6, right = toIndex >> 6;
		long l = (1L << fromIndex) - 1, r = (1L << toIndex) - 1;
		if (value) {
			bitset[left] |= l;
			for (int i = left + 1;i < right;++ i) bitset[i] = 0xFFFFFFFFFFFFFFFFL;
			bitset[right] |= ~r;
		} else {
			bitset[left] &= ~l;
			for (int i = left + 1;i < right;++ i) bitset[i] = 0;
			bitset[right] &= r;
		}
	}

	public void shift(int shift) {
		if (shift < 0) {
			shift = -shift;
			final int pre = shift >> 6, set = shift & 63;
			for (int i = 0, l = bitset.length - 1 - pre;i < l;++ i)
				bitset[i] = bitset[i + pre] >>> set | bitset[i + pre + 1] << 64 - set;
			bitset[bitset.length - pre - 1] = bitset[bitset.length - 1] >>> set;
			java.util.Arrays.fill(bitset, bitset.length - pre, bitset.length, 0);
		} else {
			ensureCapacity(size() + shift);
			final int pre = shift >> 6, set = shift & 63;
			for (int i = bitset.length - 1;i > pre;-- i) {
				bitset[i] = bitset[i - pre] << set | bitset[i - pre - 1] >>> 64 - set;
			}
			bitset[pre] = bitset[0] << set;
			java.util.Arrays.fill(bitset, 0, pre, 0);
		}
	}

	public int size() {
		return bitset.length << 6;
	}

	public java.util.stream.IntStream stream() {
		java.util.stream.IntStream.Builder builder = java.util.stream.IntStream.builder();
		for (int i = 0;i < bitset.length;++ i) {
			for (int j = 0;j < 64;++ j) if ((bitset[i] >>> j & 1) != 0) builder.add(i << 6 | j);
		}
		return builder.build();
	}

	public byte[] toByteArray() {
		byte[] ret = new byte[bitset.length << 3];
		for (int i = 0;i < ret.length;++ i) ret[i] = (byte)(bitset[i >> 3] >>> ((i & 0b111) << 3));
		return ret;
	}

	public long[] toLongArray() {
		return java.util.Arrays.copyOf(bitset, bitset.length);
	}

	@Override
	public String toString() {
		java.util.StringJoiner join = new java.util.StringJoiner(", ", "{", "}");
		for (int i = 0;i < bitset.length;++ i) {
			if (bitset[i] == 0) continue;
			for (int j = 0;j < 64;++ j) if ((bitset[i] >>> j & 1) != 0) join.add(String.valueOf(i << 6 | j));
		}
		return join.toString();
	}

	public void trimToSize() {
		bitset = java.util.Arrays.copyOf(bitset, length() + 63 >> 6);
	}

	public BitSet valueOf(byte[] bytes) {
		BitSet ret = new BitSet(bytes.length >> 3);
		for (int i = 0;i < bytes.length;++ i) ret.bitset[i >> 3] |= bytes[i] << ((i & 0b111) << 3);
		return ret;
	}

	public BitSet valueOf(java.nio.ByteBuffer bb) {
		BitSet ret = new BitSet(bb.remaining() >> 3);
		for (int i = 0;i < ret.bitset.length;++ i) ret.bitset[i] = bb.getLong(i << 3);
		return ret;
	}

	public BitSet valueOf(long[] longs) {
		BitSet ret = new BitSet(longs.length);
		System.arraycopy(longs, 0, ret.bitset, 0, longs.length);
		return ret;
	}

	public BitSet valueOf(java.nio.LongBuffer lb) {
		BitSet ret = new BitSet(lb.remaining());
		for (int i = 0;i < ret.bitset.length;++ i) ret.bitset[i] = lb.get(i);
		return ret;
	}

	public void xor(BitSet set) {
		if (bitset.length < set.bitset.length) bitset = java.util.Arrays.copyOf(bitset, set.bitset.length);
		for (int i = 0;i < set.bitset.length;++ i) bitset[i] ^= set.bitset[i];
	}
}
