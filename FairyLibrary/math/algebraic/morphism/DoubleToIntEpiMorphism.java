package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してdoubleからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToIntEpiMorphism
		extends DoubleToObjEpiMorphism<Integer>, ObjToIntEpiMorphism<Double>, DoubleToIntMorphism {
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
	public IntToDoubleMonoMorphism section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default DoubleToIntEpiMorphism andThen(IntEpiMorphism after) {
		DoubleToIntEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
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
	public default DoubleToLongEpiMorphism andThen(IntToLongEpiMorphism after) {
		DoubleToIntEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
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
	public default DoubleEpiMorphism andThen(IntToDoubleEpiMorphism after) {
		DoubleToIntEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
				return after.section().andThen(now.section());
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
	public default <T> DoubleToObjEpiMorphism<T> andThen(IntToObjEpiMorphism<T> after) {
		DoubleToIntEpiMorphism now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public ObjToDoubleMonoMorphism<T> section() {
				return after.section().andThen(now.section());
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
	public default IntEpiMorphism compose(IntToDoubleEpiMorphism before) {
		DoubleToIntEpiMorphism now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
			}

			@Override
			public IntMonoMorphism section() {
				return now.section().andThen(before.section());
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
	public default LongToIntEpiMorphism compose(LongToDoubleEpiMorphism before) {
		DoubleToIntEpiMorphism now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
			}

			@Override
			public IntToLongMonoMorphism section() {
				return now.section().andThen(before.section());
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
	public default DoubleToIntEpiMorphism compose(DoubleEpiMorphism before) {
		DoubleToIntEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
			}

			@Override
			public IntToDoubleMonoMorphism section() {
				return now.section().andThen(before.section());
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
	public default <S> ObjToIntEpiMorphism<S> compose(ObjToDoubleEpiMorphism<S> before) {
		DoubleToIntEpiMorphism now = this;
		return new ObjToIntEpiMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsDouble(operand));
			}

			@Override
			public IntToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
