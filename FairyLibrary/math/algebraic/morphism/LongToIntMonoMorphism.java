package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してlongからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToIntMonoMorphism
		extends LongToObjMonoMorphism<Integer>, ObjToIntMonoMorphism<Long>, LongToIntMorphism {
	@Override
	public default Integer apply(Long operand) {
		return applyAsInt((long) operand);
	}

	@Override
	public default Integer apply(long operand) {
		return applyAsInt(operand);
	}

	@Override
	public default int applyAsInt(Long operand) {
		return applyAsInt((long) operand);
	}

	@Override
	public IntToLongEpiMorphism retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongToIntMonoMorphism andThen(IntMonoMorphism after) {
		LongToIntMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsInt(operand));
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
	public default LongMonoMorphism andThen(IntToLongMonoMorphism after) {
		LongToIntMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsInt(operand));
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
	public default LongToDoubleMonoMorphism andThen(IntToDoubleMonoMorphism after) {
		LongToIntMonoMorphism now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
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
	public default <T> LongToObjMonoMorphism<T> andThen(IntToObjMonoMorphism<T> after) {
		LongToIntMonoMorphism now = this;
		return new LongToObjMonoMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsInt(operand));
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
	public default IntMonoMorphism compose(IntToLongMonoMorphism before) {
		LongToIntMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsLong(operand));
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
	public default LongToIntMonoMorphism compose(LongMonoMorphism before) {
		LongToIntMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsLong(operand));
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
	public default DoubleToIntMonoMorphism compose(DoubleToLongMonoMorphism before) {
		LongToIntMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsLong(operand));
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
	public default <S> ObjToIntMonoMorphism<S> compose(ObjToLongMonoMorphism<S> before) {
		LongToIntMonoMorphism now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsLong(operand));
			}

			@Override
			public IntToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
