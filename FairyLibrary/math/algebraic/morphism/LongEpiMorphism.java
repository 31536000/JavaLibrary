package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongEpiMorphism extends LongToObjEpiMorphism<Long>, ObjToLongEpiMorphism<Long>, LongMorphism {
	@Override
	public default Long apply(Long operand) {
		return applyAsLong((long) operand);
	}

	@Override
	public default Long apply(long operand) {
		return applyAsLong(operand);
	}

	@Override
	public default long applyAsLong(Long operand) {
		return applyAsLong((long) operand);
	}

	@Override
	public LongMonoMorphism section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongToIntEpiMorphism andThen(LongToIntEpiMorphism after) {
		LongEpiMorphism now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsLong(operand));
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
	public default LongEpiMorphism andThen(LongEpiMorphism after) {
		LongEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsLong(operand));
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
	public default LongToDoubleEpiMorphism andThen(LongToDoubleEpiMorphism after) {
		LongEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
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
	public default <T> LongToObjEpiMorphism<T> andThen(LongToObjEpiMorphism<T> after) {
		LongEpiMorphism now = this;
		return new LongToObjEpiMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsLong(operand));
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
	public default IntToLongEpiMorphism compose(IntToLongEpiMorphism before) {
		LongEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsLong(operand));
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
	public default LongEpiMorphism compose(LongEpiMorphism before) {
		LongEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsLong(operand));
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
	public default DoubleToLongEpiMorphism compose(DoubleToLongEpiMorphism before) {
		LongEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsLong(operand));
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
	public default <S> ObjToLongEpiMorphism<S> compose(ObjToLongEpiMorphism<S> before) {
		LongEpiMorphism now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
