package com._31536000.util.function;

/**
 * 単一のbyte値引数を受け取って結果を返さないオペレーションを表します。
 * これは、Consumerに対してプリミティブ型特殊化(byte向け)を行ったものです。
 * ByteConsumerは他のほとんどの関数型インタフェースと異なり、副作用を介して動作することを期待されます。
 * <p>
 * これは、{@link #accept(byte)}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 */
public interface ByteConsumer extends Consumer<Byte>{
	/**
	 * 指定された引数でこのオペレーションを実行します。
	 * @param value 入力引数
	 */
	public void accept(byte value);
	@Override
	public default void accept(Byte t) {
		accept((byte)t);
	}
	/**
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成ByteConsumerを返します。
	 * いずれかのオペレーションの実行時に例外がスローされた場合、その例外は合成オペレーションの呼出し元に中継されます。
	 * このオペレーションの実行時に例外がスローされた場合、afterオペレーションは実行されません。
	 * @param after このオペレーションの後で実行するオペレーション
	 * @return このオペレーションを実行した後、続けてafterオペレーションを実行する合成ByteConsumer
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ByteConsumer andThen(ByteConsumer after) {
		ByteConsumer last = this;
		return new ByteConsumer() {
			@Override
			public void accept(byte value) {
				last.accept(value);
				after.accept(value);
			}
		};
	}
}
