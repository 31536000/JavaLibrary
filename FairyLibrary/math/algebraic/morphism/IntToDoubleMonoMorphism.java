package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してintからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToDoubleMonoMorphism
		extends IntToObjMonoMorphism<Double>, ObjToDoubleMonoMorphism<Integer>, IntToDoubleMorphism {
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
	public DoubleToIntEpiMorphism retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default IntMonoMorphism andThen(DoubleToIntMonoMorphism after) {
		IntToDoubleMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
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
	public default IntToLongMonoMorphism andThen(DoubleToLongMonoMorphism after) {
		IntToDoubleMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
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
	public default IntToDoubleMonoMorphism andThen(DoubleMonoMorphism after) {
		IntToDoubleMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
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
	public default <T> IntToObjMonoMorphism<T> andThen(DoubleToObjMonoMorphism<T> after) {
		IntToDoubleMonoMorphism now = this;
		return new IntToObjMonoMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsDouble(operand));
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
	public default IntToDoubleMonoMorphism compose(IntMonoMorphism before) {
		IntToDoubleMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
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
	public default LongToDoubleMonoMorphism compose(LongToIntMonoMorphism before) {
		IntToDoubleMonoMorphism now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToLongEpiMorphism retraction() {
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
	public default DoubleMonoMorphism compose(DoubleToIntMonoMorphism before) {
		IntToDoubleMonoMorphism now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleEpiMorphism retraction() {
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
	public default <S> ObjToDoubleMonoMorphism<S> compose(ObjToIntMonoMorphism<S> before) {
		IntToDoubleMonoMorphism now = this;
		return new ObjToDoubleMonoMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
