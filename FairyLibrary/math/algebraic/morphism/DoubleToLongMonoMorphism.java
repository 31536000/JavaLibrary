package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してdoubleからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToLongMonoMorphism
		extends DoubleToObjMonoMorphism<Long>, ObjToLongMonoMorphism<Double>, DoubleToLongMorphism {
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
	public LongToDoubleEpiMorphism retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default DoubleToIntMonoMorphism andThen(LongToIntMonoMorphism after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsLong(operand));
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
	public default DoubleToLongMonoMorphism andThen(LongMonoMorphism after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsLong(operand));
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
	public default DoubleMonoMorphism andThen(LongToDoubleMonoMorphism after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
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
	public default <T> DoubleToObjMonoMorphism<T> andThen(LongToObjMonoMorphism<T> after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToObjMonoMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsLong(operand));
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
	public default IntToLongMonoMorphism compose(IntToDoubleMonoMorphism before) {
		DoubleToLongMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
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
	public default LongMonoMorphism compose(LongToDoubleMonoMorphism before) {
		DoubleToLongMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
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
	public default DoubleToLongMonoMorphism compose(DoubleMonoMorphism before) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
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
	public default <S> ObjToLongMonoMorphism<S> compose(ObjToDoubleMonoMorphism<S> before) {
		DoubleToLongMonoMorphism now = this;
		return new ObjToLongMonoMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
