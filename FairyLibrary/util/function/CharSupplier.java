package com._31536000.util.function;

/**
 * char値の結果のサプライヤを表します。
 * これは、Supplierに対して、charを生成するプリミティブ特殊化を行ったものです。
 * <p>
 * サプライヤを呼び出すたびに異なる結果が返される必要はありません。
 * <p>
 * これは、{@link #getAsChar()}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 * @param <T> このサプライヤから提供される結果の型
 */
public interface CharSupplier extends Supplier<Character> {
	/**
	 * 結果を取得します。
	 * @return 結果
	 */
	public char getAsChar();

	@Override
	public default Character get() {
		return getAsChar();
	}
}
