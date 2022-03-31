package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongMonoMorphism extends LongToObjMonoMorphism<Long>, ObjToLongMonoMorphism<Long>, LongMorphism {
	@Override
	public default Long apply(Long operand) {
		return applyAsLong((long) operand);
	}

	@Override
	public default Long apply(long operand) {
		return applyAsLong(operand);
	}

	@Override
	public default long applyAsLong(Long operand) {
		return applyAsLong((long) operand);
	}

	/**
	 * 右逆射を返します。
	 * @return 右逆射
	 */
	@Override
	public LongEpiMorphism retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongToIntMonoMorphism andThen(LongToIntMonoMorphism after) {
		LongMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsLong(operand));
			}

			@Override
			public IntToLongEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongMonoMorphism andThen(LongMonoMorphism after) {
		LongMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsLong(operand));
			}

			@Override
			public LongEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongToDoubleMonoMorphism andThen(LongToDoubleMonoMorphism after) {
		LongMonoMorphism now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
			}

			@Override
			public DoubleToLongEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param <T> after関数および合成関数の出力の型
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default <T> LongToObjMonoMorphism<T> andThen(LongToObjMonoMorphism<T> after) {
		LongMonoMorphism now = this;
		return new LongToObjMonoMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public ObjToLongEpiMorphism<T> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	@Override
	public default IntToLongMonoMorphism compose(IntToLongMonoMorphism before) {
		LongMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	@Override
	public default LongMonoMorphism compose(LongMonoMorphism before) {
		LongMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	@Override
	public default DoubleToLongMonoMorphism compose(DoubleToLongMonoMorphism before) {
		LongMonoMorphism now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongToDoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param <S> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	@Override
	public default <S> ObjToLongMonoMorphism<S> compose(ObjToLongMonoMorphism<S> before) {
		LongMonoMorphism now = this;
		return new ObjToLongMonoMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
