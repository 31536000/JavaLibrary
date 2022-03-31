package com._31536000.util.collect;

class LargeIndexableDictionary  implements IndexableDictionary{
	final long[] bit;
	final int[] chunk; // band: 1024
	final short[] block; // band: 16
	final int[] selectBlock; // width: 32
	final int[] selectPoint;

	static final byte[] popcount;
	static{
		popcount = new byte[1 << 16];
		for (int i = 0;i < popcount.length;++ i) popcount[i] = (byte)Integer.bitCount(i);
	}

	public LargeIndexableDictionary(long[] bit) {
		this.bit = bit.clone();
		chunk = new int[bit.length + 31 >> 4];
		block = new short[bit.length << 2 | 1];
		int bi = 0, ci = 0;
		for (int i = 0;i < bit.length;++ i) {
			long l = bit[i];
			block[bi + 1] = (short) (block[bi++] + popcount[(int)(l & 0xFFFF)]);
			block[bi + 1] = (short) (block[bi++] + popcount[(int)(l >> 16 & 0xFFFF)]);
			block[bi + 1] = (short) (block[bi++] + popcount[(int)(l >> 32 & 0xFFFF)]);
			block[bi + 1] = (short) (block[bi++] + popcount[(int)(l >> 48 & 0xFFFF)]);
			if ((bi & 63) == 0) {
				chunk[ci + 1] = chunk[ci++] + block[bi];
				block[bi] = 0;
			}
		}
		if ((bi & 63) != 0) chunk[chunk.length - 1] = chunk[chunk.length - 2] + block[block.length - 1];
	}
	@Override
	public boolean get(int i) {
		return (bit[i >> 6] >> (i & 63) & 1) != 0;
	}

	@Override
	public int rank(int i) {
		int ans = chunk[i >> 10];
		ans += block[i >> 4];
		int b = (int)(bit[i >> 6] >> ((i >> 4 & 3) << 4) & 0xFFFF);
		b &= (1 << (i & 15)) - 1;
		ans += popcount[b];
		return ans;
	}

	@Override
	public int select(int i) {
		/*
		 * selectでやること
		 * 1. selectBlock[i>>5]を見ることで、i以下の最大の32の倍数の位置が分かる
		 * このとき、次の32個が1024個以内にあるならその座標を、そうでないなら32個の位置を負数で表現
		 * 負数xなら簡単で、selectPoint[~x<<5|i&31]が座標
		 * そうでないなら、x以上x2以下のどこか(この範囲は1024マス以内)
		 * chunkをどこかで跨ぐなら、まずそれを調べて同じchunkに収める
		 * 後はblockで二分探索して、popcountは良しなに
		 * 
		 */
		int l = selectBlock[i >> 5];
		if (l < 0) return selectPoint[~l << 5 | i & 31];
		int r = selectBlock[(i >> 5) + 1];
		if (l >> 10 != r >> 10) {
			int m = r >> 10;
			if (chunk[m] < i) {
				i -= chunk[m];
				l = m << 6;
				r >>= 4;
			} else {
				i -= chunk[l >> 10];
				l >>= 4;
				r = m << 6;
			}
		} else {
			i -= chunk[l >> 10];
			l >>= 4;
			r >>= 4;
		}
		// TODO [l, r)でblockを二分探索
		int m = l + r >> 1;
		while(l != m) {
			if (block[m] < i) l = m;
			else r = m;
			m = l + r >> 1;
		}
		i -= block[l];
		// あとはbit[l >> 2] >> (16 << (l & 3))のi番目のbitを得られればいい
	}

	@Override
	public int size() {
		return bit.length << 6;
	}

	@Override
	public int cardinality() {
		return chunk[chunk.length - 1];
	}
}
