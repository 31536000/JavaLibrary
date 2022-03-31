package com._31536000.util.function;


/**
 * 単一のshort値引数を受け取って結果を返さないオペレーションを表します。
 * これは、Consumerに対してプリミティブ型特殊化(short向け)を行ったものです。
 * ShortConsumerは他のほとんどの関数型インタフェースと異なり、副作用を介して動作することを期待されます。
 * <p>
 * これは、{@link #accept(short)}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 */
public interface ShortConsumer extends Consumer<Short>{
	/**
	 * 指定された引数でこのオペレーションを実行します。
	 * @param value 入力引数
	 */
	public void accept(short value);
	@Override
	public default void accept(Short t) {
		accept((short)t);
	}
	/**
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成ShortConsumerを返します。
	 * いずれかのオペレーションの実行時に例外がスローされた場合、その例外は合成オペレーションの呼出し元に中継されます。
	 * このオペレーションの実行時に例外がスローされた場合、afterオペレーションは実行されません。
	 * @param after このオペレーションの後で実行するオペレーション
	 * @return このオペレーションを実行した後、続けてafterオペレーションを実行する合成ShortConsumer
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ShortConsumer andThen(ShortConsumer after) {
		ShortConsumer last = this;
		return new ShortConsumer() {
			@Override
			public void accept(short value) {
				last.accept(value);
				after.accept(value);
			}
		};
	}
}
