package com._31536000.util.function;


/**
 * 単一のint値引数を受け取って結果を返さないオペレーションを表します。
 * これは、Consumerに対してプリミティブ型特殊化(int向け)を行ったものです。
 * IntConsumerは他のほとんどの関数型インタフェースと異なり、副作用を介して動作することを期待されます。
 * <p>
 * これは、{@link #accept(int)}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 */
public interface IntConsumer extends Consumer<Integer>, java.util.function.IntConsumer{
	/**
	 * 指定された引数でこのオペレーションを実行します。
	 * @param value 入力引数
	 */
	@Override
	public void accept(int value);
	@Override
	public default void accept(Integer t) {
		accept((int)t);
	}
	/**
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成IntConsumerを返します。
	 * いずれかのオペレーションの実行時に例外がスローされた場合、その例外は合成オペレーションの呼出し元に中継されます。
	 * このオペレーションの実行時に例外がスローされた場合、afterオペレーションは実行されません。
	 * @param after このオペレーションの後で実行するオペレーション
	 * @return このオペレーションを実行した後、続けてafterオペレーションを実行する合成IntConsumer
	 * @exception NullPointerException afterがnullの場合
	 */
	public default IntConsumer andThen(IntConsumer after) {
		IntConsumer last = this;
		return new IntConsumer() {
			@Override
			public void accept(int value) {
				last.accept(value);
				after.accept(value);
			}
		};
	}
}
