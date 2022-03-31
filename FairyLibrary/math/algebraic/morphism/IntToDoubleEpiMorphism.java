package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してintからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToDoubleEpiMorphism
		extends IntToObjEpiMorphism<Double>, ObjToDoubleEpiMorphism<Integer>, IntToDoubleMorphism {
	@Override
	public default Double apply(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	public default Double apply(int operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	public DoubleToIntMonoMorphism section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default IntEpiMorphism andThen(DoubleToIntEpiMorphism after) {
		IntToDoubleEpiMorphism now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
			}

			@Override
			public IntMonoMorphism section() {
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
	public default IntToLongEpiMorphism andThen(DoubleToLongEpiMorphism after) {
		IntToDoubleEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
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
	public default IntToDoubleEpiMorphism andThen(DoubleEpiMorphism after) {
		IntToDoubleEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
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
	public default <T> IntToObjEpiMorphism<T> andThen(DoubleToObjEpiMorphism<T> after) {
		IntToDoubleEpiMorphism now = this;
		return new IntToObjEpiMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsDouble(operand));
			}

			@Override
			public ObjToIntMonoMorphism<T> section() {
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
	public default IntToDoubleEpiMorphism compose(IntEpiMorphism before) {
		IntToDoubleEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
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
	public default LongToDoubleEpiMorphism compose(LongToIntEpiMorphism before) {
		IntToDoubleEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
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
	public default DoubleEpiMorphism compose(DoubleToIntEpiMorphism before) {
		IntToDoubleEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
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
	public default <S> ObjToDoubleEpiMorphism<S> compose(ObjToIntEpiMorphism<S> before) {
		IntToDoubleEpiMorphism now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
