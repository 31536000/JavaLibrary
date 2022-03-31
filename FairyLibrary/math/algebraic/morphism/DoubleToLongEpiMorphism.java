package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してdoubleからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToLongEpiMorphism
		extends DoubleToObjEpiMorphism<Long>, ObjToLongEpiMorphism<Double>, DoubleToLongMorphism {
	@Override
	public default Long apply(Double operand) {
		return applyAsLong((double) operand);
	}

	@Override
	public default Long apply(double operand) {
		return applyAsLong(operand);
	}

	@Override
	public default long applyAsLong(Double operand) {
		return applyAsLong((double) operand);
	}

	@Override
	public LongToDoubleMonoMorphism section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default DoubleToIntEpiMorphism andThen(LongToIntEpiMorphism after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsLong(operand));
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
	public default DoubleToLongEpiMorphism andThen(LongEpiMorphism after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsLong(operand));
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
	public default DoubleEpiMorphism andThen(LongToDoubleEpiMorphism after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
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
	public default <T> DoubleToObjEpiMorphism<T> andThen(LongToObjEpiMorphism<T> after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsLong(operand));
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
	public default IntToLongEpiMorphism compose(IntToDoubleEpiMorphism before) {
		DoubleToLongEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
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
	public default LongEpiMorphism compose(LongToDoubleEpiMorphism before) {
		DoubleToLongEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongMonoMorphism section() {
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
	public default DoubleToLongEpiMorphism compose(DoubleEpiMorphism before) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
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
	public default <S> ObjToLongEpiMorphism<S> compose(ObjToDoubleEpiMorphism<S> before) {
		DoubleToLongEpiMorphism now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
