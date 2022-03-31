package com._31536000.util.function;


/**
 * 単一のdouble値引数を受け取って結果を返さないオペレーションを表します。
 * これは、Consumerに対してプリミティブ型特殊化(double向け)を行ったものです。
 * DoubleConsumerは他のほとんどの関数型インタフェースと異なり、副作用を介して動作することを期待されます。
 * <p>
 * これは、{@link #accept(double)}を関数メソッドに持つ関数型インタフェースです。
 * @author 31536000
 *
 */
public interface DoubleConsumer extends Consumer<Double>, java.util.function.DoubleConsumer{
	/**
	 * 指定された引数でこのオペレーションを実行します。
	 * @param value 入力引数
	 */
	public void accept(double value);
	@Override
	public default void accept(Double t) {
		accept((double)t);
	}
	/**
	 * このオペレーションを実行した後、続けてafterオペレーションを実行する合成DoubleConsumerを返します。
	 * いずれかのオペレーションの実行時に例外がスローされた場合、その例外は合成オペレーションの呼出し元に中継されます。
	 * このオペレーションの実行時に例外がスローされた場合、afterオペレーションは実行されません。
	 * @param after このオペレーションの後で実行するオペレーション
	 * @return このオペレーションを実行した後、続けてafterオペレーションを実行する合成DoubleConsumer
	 * @exception NullPointerException afterがnullの場合
	 */
	public default DoubleConsumer andThen(DoubleConsumer after) {
		DoubleConsumer last = this;
		return new DoubleConsumer() {
			@Override
			public void accept(double value) {
				last.accept(value);
				after.accept(value);
			}
		};
	}
}
