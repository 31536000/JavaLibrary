package com._31536000.util.collect;

/**
 * 完備辞書は、bit列に対して以下の操作を提供します。
 * <ul>
 * <li>{@link #rank(i)} i番目までに立っているbitの個数を返します。</li>
 * <li>{@link #select(i)} i番目のbitが立っている位置を返します。</li>
 * </ul>
 * @author 31536000
 *
 */
public interface IndexableDictionary {
	/**
	 * 指定されたインデックスを持つビットの値を返します。
	 * @param bitIndex ビット・インデックス
	 * @return 指定されたインデックスを持つビットの値
	 * @exception IndexOutOfBoundsException 指定されたインデックスが負の場合
	 */
	public boolean get(int bitIndex);

	/**
	 * 指定されたインデックスまでに立っているbitの個数を返します。
	 * @param bitIndex ビット・インデックス
	 * @return 指定されたインデックスまでに立っているbitの個数
	 * @exception IndexOutOfBoundsException 指定されたインデックスが負の場合
	 */
	public int rank(int bitIndex);

	/**
	 * i番目のbitが立っている位置を返します。このようなものが無い場合、-1が返ります。
	 * @param i 何番目のbitを調べるか
	 * @return i番目のbitが立っている位置
	 * @exception IndexOutOfBoundsException iが負の場合
	 */
	public int select(int i);

	/**
	 * 完備辞書を生成します。
	 * この関数によって生成される完備辞書は、Nをbitの配列長として以下の性質を持ちます。
	 * <ul>
	 * <li>{@link #rank(i)}がO(1)で実行される。</li>
	 * <li>{@link #select(i)}がO(loglogN)で実行される。</li>
	 * <li>空間計算量がO(N)である。</li>
	 * </ul>
	 * @param bit 完備辞書を構築するbit列
	 * @return 完備辞書
	 */
	public static IndexableDictionary create(long[] bit) {
		return new LargeIndexableDictionary(bit);
	}

	/**
	 * この完備辞書でビット値を表すために実際に使用しているビットの数を返します。
	 * @return 完備辞書に現在あるビットの数
	 */
	public int size();

	/**
	 * この完備辞書で、trueに設定されたビットの数を返します。
	 * これはrank(size())と等しいです。
	 * @return この完備辞書で、trueに設定されたビットの数
	 */
	public default int cardinality() {
		return rank(size());
	}
}
