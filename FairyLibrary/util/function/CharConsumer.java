package com._31536000.util.function;


/**
 * 単一のchar値引数を受け取って結果を返さないオペレーションを表します。
 * これは、Consumerに対してプリミティブ型特殊化(char向け)を行ったものです。
 * CharConsumerは他のほとんどの関数型インタフェースと異なり、副作用を介して動作することを期待されます。
 * <p>
 * これは、{@link #accept(char)}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 */
public interface CharConsumer extends Consumer<Character>{
	/**
	 * 指定された引数でこのオペレーションを実行します。
	 * @param value 入力引数
	 */
	public void accept(char value);
	@Override
	public default void accept(Character t) {
		accept((char)t);
	}
	/**
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成CharConsumerを返します。
	 * いずれかのオペレーションの実行時に例外がスローされた場合、その例外は合成オペレーションの呼出し元に中継されます。
	 * このオペレーションの実行時に例外がスローされた場合、afterオペレーションは実行されません。
	 * @param after このオペレーションの後で実行するオペレーション
	 * @return このオペレーションを実行した後、続けてafterオペレーションを実行する合成CharConsumer
	 * @exception NullPointerException afterがnullの場合
	 */
	public default CharConsumer andThen(CharConsumer after) {
		CharConsumer last = this;
		return new CharConsumer() {
			@Override
			public void accept(char value) {
				last.accept(value);
				after.accept(value);
			}
		};
	}
}
