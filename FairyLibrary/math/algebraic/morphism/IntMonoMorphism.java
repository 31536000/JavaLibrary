package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 */
public interface IntMonoMorphism extends IntToObjMonoMorphism<Integer>, ObjToIntMonoMorphism<Integer>, IntMorphism {
	@Override
	public default Integer apply(Integer operand) {
		return applyAsInt((int) operand);
	}

	@Override
	public default Integer apply(int operand) {
		return applyAsInt(operand);
	}

	@Override
	public default int applyAsInt(Integer operand) {
		return applyAsInt((int) operand);
	}

	/**
	 * 右逆射を返します。
	 * @return 右逆射
	 */
	@Override
	public IntEpiMorphism retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default IntMonoMorphism andThen(IntMonoMorphism after) {
		IntMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
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
	public default IntToLongMonoMorphism andThen(IntToLongMonoMorphism after) {
		IntMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
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
	public default IntToDoubleMonoMorphism andThen(IntToDoubleMonoMorphism after) {
		IntMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
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
	public default <T> IntToObjMonoMorphism<T> andThen(IntToObjMonoMorphism<T> after) {
		IntMonoMorphism now = this;
		return new IntToObjMonoMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public ObjToIntEpiMorphism<T> retraction() {
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
	public default IntMonoMorphism compose(IntMonoMorphism before) {
		IntMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
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
	public default LongToIntMonoMorphism compose(LongToIntMonoMorphism before) {
		IntMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToLongEpiMorphism retraction() {
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
	public default DoubleToIntMonoMorphism compose(DoubleToIntMonoMorphism before) {
		IntMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToDoubleEpiMorphism retraction() {
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
	public default <S> ObjToIntMonoMorphism<S> compose(ObjToIntMonoMorphism<S> before) {
		IntMonoMorphism now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
