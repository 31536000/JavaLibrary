package com._31536000.util.function;

/**
 * float値の結果のサプライヤを表します。
 * これは、Supplierに対して、floatを生成するプリミティブ特殊化を行ったものです。
 * <p>
 * サプライヤを呼び出すたびに異なる結果が返される必要はありません。
 * <p>
 * これは、{@link #getAsFloat()}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 * @param <T> このサプライヤから提供される結果の型
 */
public interface FloatSupplier extends Supplier<Float> {
	/**
	 * 結果を取得します。
	 * @return 結果
	 */
	public float getAsFloat();

	@Override
	public default Float get() {
		return getAsFloat();
	}
}
