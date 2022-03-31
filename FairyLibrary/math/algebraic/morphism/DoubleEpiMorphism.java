package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleEpiMorphism
		extends DoubleToObjEpiMorphism<Double>, ObjToDoubleEpiMorphism<Double>, DoubleMorphism {
	@Override
	public default Double apply(Double operand) {
		return applyAsDouble((double) operand);
	}

	@Override
	public default Double apply(double operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Double operand) {
		return applyAsDouble((double) operand);
	}

	@Override
	public DoubleMonoMorphism section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default DoubleToIntEpiMorphism andThen(DoubleToIntEpiMorphism after) {
		DoubleEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
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
	public default DoubleToLongEpiMorphism andThen(DoubleToLongEpiMorphism after) {
		DoubleEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
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
	public default DoubleEpiMorphism andThen(DoubleEpiMorphism after) {
		DoubleEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
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
	public default <T> DoubleToObjEpiMorphism<T> andThen(DoubleToObjEpiMorphism<T> after) {
		DoubleEpiMorphism now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsDouble(operand));
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
	public default IntToDoubleEpiMorphism compose(IntToDoubleEpiMorphism before) {
		DoubleEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
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
	public default LongToDoubleEpiMorphism compose(LongToDoubleEpiMorphism before) {
		DoubleEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
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
	public default DoubleEpiMorphism compose(DoubleEpiMorphism before) {
		DoubleEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
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
	public default <S> ObjToDoubleEpiMorphism<S> compose(ObjToDoubleEpiMorphism<S> before) {
		DoubleEpiMorphism now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
			}

			@Override
			public DoubleToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
