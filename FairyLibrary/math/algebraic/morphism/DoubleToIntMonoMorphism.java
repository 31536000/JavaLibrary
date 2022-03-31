package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してdoubleからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToIntMonoMorphism
		extends DoubleToObjMonoMorphism<Integer>, ObjToIntMonoMorphism<Double>, DoubleToIntMorphism {
	@Override
	public default Integer apply(Double operand) {
		return applyAsInt((double) operand);
	}

	@Override
	public default Integer apply(double operand) {
		return applyAsInt(operand);
	}

	@Override
	public default int applyAsInt(Double operand) {
		return applyAsInt((double) operand);
	}

	@Override
	public IntToDoubleEpiMorphism retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default DoubleToIntMonoMorphism andThen(IntMonoMorphism after) {
		DoubleToIntMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntToDoubleEpiMorphism retraction() {
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
	public default DoubleToLongMonoMorphism andThen(IntToLongMonoMorphism after) {
		DoubleToIntMonoMorphism now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongToDoubleEpiMorphism retraction() {
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
	public default DoubleMonoMorphism andThen(IntToDoubleMonoMorphism after) {
		DoubleToIntMonoMorphism now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleEpiMorphism retraction() {
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
	public default <T> DoubleToObjMonoMorphism<T> andThen(IntToObjMonoMorphism<T> after) {
		DoubleToIntMonoMorphism now = this;
		return new DoubleToObjMonoMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public ObjToDoubleEpiMorphism<T> retraction() {
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
	public default IntMonoMorphism compose(IntToDoubleMonoMorphism before) {
		DoubleToIntMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
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
	public default LongToIntMonoMorphism compose(LongToDoubleMonoMorphism before) {
		DoubleToIntMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
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
	public default DoubleToIntMonoMorphism compose(DoubleMonoMorphism before) {
		DoubleToIntMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
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
	public default <S> ObjToIntMonoMorphism<S> compose(ObjToDoubleMonoMorphism<S> before) {
		DoubleToIntMonoMorphism now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
			}

			@Override
			public IntToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
