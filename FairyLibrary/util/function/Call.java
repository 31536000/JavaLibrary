package com._31536000.util.function;

/**
 * 引数を受け取らず、結果を返さないオペレーションを表します。
 * Callは他のほとんどの関数型インタフェースと異なり、副作用を介して動作することを期待されます。
 * <p>
 * これは、{@link #call()}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 */
public interface Call {
	/**
	 * 関数を呼び出します。
	 */
	public void call();

	/**
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成Callを返します。
	 * @param after このオペレーションの後で実行するオペレーション
	 * @return このオペレーションを実行した後、続けてafterオペレーションを実行する合成Call
	 * @exception NullPointerException afterがnullの場合
	 */
	public default Call andThen(Call after) {
		Call last = this;
		return new Call() {
			@Override
			public void call() {
				last.call();
				after.call();
			}
		};
	}
}
