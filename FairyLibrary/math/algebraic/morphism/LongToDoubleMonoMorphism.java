package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してlongからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToDoubleMonoMorphism
		extends LongToObjMonoMorphism<Double>, ObjToDoubleMonoMorphism<Long>, LongToDoubleMorphism {
	@Override
	public default Double apply(Long operand) {
		return applyAsDouble((long) operand);
	}

	@Override
	public default Double apply(long operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Long operand) {
		return applyAsDouble((long) operand);
	}

	@Override
	public DoubleToLongEpiMorphism retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongToIntMonoMorphism andThen(DoubleToIntMonoMorphism after) {
		LongToDoubleMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
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
	public default LongMonoMorphism andThen(DoubleToLongMonoMorphism after) {
		LongToDoubleMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
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
	public default LongToDoubleMonoMorphism andThen(DoubleMonoMorphism after) {
		LongToDoubleMonoMorphism now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
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
	public default <T> LongToObjMonoMorphism<T> andThen(DoubleToObjMonoMorphism<T> after) {
		LongToDoubleMonoMorphism now = this;
		return new LongToObjMonoMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsDouble(operand));
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
	public default IntToDoubleMonoMorphism compose(IntToLongMonoMorphism before) {
		LongToDoubleMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
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
	public default LongToDoubleMonoMorphism compose(LongMonoMorphism before) {
		LongToDoubleMonoMorphism now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
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
	public default DoubleMonoMorphism compose(DoubleToLongMonoMorphism before) {
		LongToDoubleMonoMorphism now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
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
	public default <S> ObjToDoubleMonoMorphism<S> compose(ObjToLongMonoMorphism<S> before) {
		LongToDoubleMonoMorphism now = this;
		return new ObjToDoubleMonoMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
			}

			@Override
			public DoubleToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
