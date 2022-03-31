package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対して、doubleを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToDoubleEpiMorphism<S> extends EpiMorphism<S, Double>, ObjToDoubleMorphism<S> {
	@Override
	public default Double apply(S operand) {
		return applyAsDouble(operand);
	}

	@Override
	public DoubleToObjMonoMorphism<S> section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	public default ObjToIntEpiMorphism<S> andThen(DoubleToIntEpiMorphism after) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new ObjToIntEpiMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
			}

			@Override
			public IntToObjMonoMorphism<S> section() {
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
	public default ObjToLongEpiMorphism<S> andThen(DoubleToLongEpiMorphism after) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
			}

			@Override
			public LongToObjMonoMorphism<S> section() {
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
	public default ObjToDoubleEpiMorphism<S> andThen(DoubleEpiMorphism after) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
			}

			@Override
			public DoubleToObjMonoMorphism<S> section() {
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
	public default <T> EpiMorphism<S, T> andThen(DoubleToObjEpiMorphism<T> after) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new EpiMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return after.apply(now.applyAsDouble(operand));
			}

			@Override
			public MonoMorphism<T, S> section() {
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
	public default IntToDoubleEpiMorphism compose(IntToObjEpiMorphism<S> before) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.apply(operand));
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
	public default LongToDoubleEpiMorphism compose(LongToObjEpiMorphism<S> before) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.apply(operand));
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
	public default DoubleEpiMorphism compose(DoubleToObjEpiMorphism<S> before) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.apply(operand));
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
	 * @param <U> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	@Override
	public default <U> ObjToDoubleEpiMorphism<U> compose(EpiMorphism<U, S> before) {
		ObjToDoubleEpiMorphism<S> now = this;
		return new ObjToDoubleEpiMorphism<U>() {

			@Override
			public double applyAsDouble(U operand) {
				return now.applyAsDouble(before.apply(operand));
			}

			@Override
			public DoubleToObjMonoMorphism<U> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
