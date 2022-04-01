package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対して、intを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToIntEpiMorphism<S> extends EpiMorphism<S, Integer>, ObjToIntMorphism<S> {
	@Override
	public default Integer apply(S operand) {
		return applyAsInt(operand);
	}

	@Override
	public IntToObjMonoMorphism<S> section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ObjToIntEpiMorphism<S> andThen(IntEpiMorphism after) {
		ObjToIntEpiMorphism<S> now = this;
		return new ObjToIntEpiMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return after.applyAsInt(now.applyAsInt(operand));
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
	 */
	public default ObjToLongEpiMorphism<S> andThen(IntToLongEpiMorphism after) {
		ObjToIntEpiMorphism<S> now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return after.applyAsLong(now.applyAsInt(operand));
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
	 */
	public default ObjToDoubleEpiMorphism<S> andThen(IntToDoubleEpiMorphism after) {
		ObjToIntEpiMorphism<S> now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
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
	 */
	public default <T> EpiMorphism<S, T> andThen(IntToObjEpiMorphism<T> after) {
		ObjToIntEpiMorphism<S> now = this;
		return new EpiMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public MonoMorphism<T, S> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntEpiMorphism compose(IntToObjEpiMorphism<S> before) {
		ObjToIntEpiMorphism<S> now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongToIntEpiMorphism compose(LongToObjEpiMorphism<S> before) {
		ObjToIntEpiMorphism<S> now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntToLongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleToIntEpiMorphism compose(DoubleToObjEpiMorphism<S> before) {
		ObjToIntEpiMorphism<S> now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntToDoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <U> ObjToIntEpiMorphism<U> compose(EpiMorphism<U, S> before) {
		ObjToIntEpiMorphism<S> now = this;
		return new ObjToIntEpiMorphism<U>() {

			@Override
			public int applyAsInt(U operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntToObjMonoMorphism<U> section() {
				return now.section().andThen(before.section());
			}

		};
	}

}
