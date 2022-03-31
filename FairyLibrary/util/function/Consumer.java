package com._31536000.util.function;

/**
 * 単一の入力引数を受け取って結果を返さないオペレーションを表します。
 * Consumerは他のほとんどの関数型インタフェースと異なり、副作用を介して動作することを期待されます。
 * <p>
 * これは、{@link #accept(Object)}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 * @param <T> オペレーションの入力の型
 */
public interface Consumer<T> extends java.util.function.Consumer<T>{

}
