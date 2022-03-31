package com._31536000.util.function;

/**
 * int値の結果のサプライヤを表します。
 * これは、Supplierに対して、intを生成するプリミティブ特殊化を行ったものです。
 * <p>
 * サプライヤを呼び出すたびに異なる結果が返される必要はありません。
 * <p>
 * これは、{@link #getAsInt()}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 * @param <T> このサプライヤから提供される結果の型
 */
public interface IntSupplier extends Supplier<Integer>, java.util.function.IntSupplier {
	@Override
	public default Integer get() {
		return getAsInt();
	}
}
