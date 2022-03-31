package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してlongからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToIntEpiMorphism
		extends LongToObjEpiMorphism<Integer>, ObjToIntEpiMorphism<Long>, LongToIntMorphism {
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
	public IntToLongMonoMorphism section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongToIntEpiMorphism andThen(IntEpiMorphism after) {
		LongToIntEpiMorphism now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntToLongMonoMorphism section() {
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
	public default LongEpiMorphism andThen(IntToLongEpiMorphism after) {
		LongToIntEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongMonoMorphism section() {
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
	public default LongToDoubleEpiMorphism andThen(IntToDoubleEpiMorphism after) {
		LongToIntEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
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
	public default <T> LongToObjEpiMorphism<T> andThen(IntToObjEpiMorphism<T> after) {
		LongToIntEpiMorphism now = this;
		return new LongToObjEpiMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public ObjToLongMonoMorphism<T> section() {
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
	public default IntEpiMorphism compose(IntToLongEpiMorphism before) {
		LongToIntEpiMorphism now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsLong(operand));
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
	public default LongToIntEpiMorphism compose(LongEpiMorphism before) {
		LongToIntEpiMorphism now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsLong(operand));
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
	public default DoubleToIntEpiMorphism compose(DoubleToLongEpiMorphism before) {
		LongToIntEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsLong(operand));
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
	public default <S> ObjToIntEpiMorphism<S> compose(ObjToLongEpiMorphism<S> before) {
		LongToIntEpiMorphism now = this;
		return new ObjToIntEpiMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsLong(operand));
			}

			@Override
			public IntToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
