package com._31536000.util.function;

/**
 * 結果のサプライヤを表します。
 * <p>
 * サプライヤを呼び出すたびに新しい(異なる)結果が返される必要はありません。
 * <p>
 * これは、{@link #get()}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 * @param <T> このサプライヤから提供される結果の型
 */
public interface Supplier<T> extends java.util.function.Supplier<T> {

}
