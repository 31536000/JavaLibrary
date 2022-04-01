package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対して、intを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToIntMonoMorphism<S> extends MonoMorphism<S, Integer>, ObjToIntMorphism<S> {
	@Override
	public default Integer apply(S operand) {
		return applyAsInt(operand);
	}

	@Override
	public IntToObjEpiMorphism<S> retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ObjToIntMonoMorphism<S> andThen(IntMonoMorphism after) {
		ObjToIntMonoMorphism<S> now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntToObjEpiMorphism<S> retraction() {
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
	 */
	public default ObjToLongMonoMorphism<S> andThen(IntToLongMonoMorphism after) {
		ObjToIntMonoMorphism<S> now = this;
		return new ObjToLongMonoMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongToObjEpiMorphism<S> retraction() {
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
	 */
	public default ObjToDoubleMonoMorphism<S> andThen(IntToDoubleMonoMorphism after) {
		ObjToIntMonoMorphism<S> now = this;
		return new ObjToDoubleMonoMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleToObjEpiMorphism<S> retraction() {
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
	 */
	public default <T> MonoMorphism<S, T> andThen(IntToObjMonoMorphism<T> after) {
		ObjToIntMonoMorphism<S> now = this;
		return new MonoMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public EpiMorphism<T, S> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntMonoMorphism compose(IntToObjMonoMorphism<S> before) {
		ObjToIntMonoMorphism<S> now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongToIntMonoMorphism compose(LongToObjMonoMorphism<S> before) {
		ObjToIntMonoMorphism<S> now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntToLongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleToIntMonoMorphism compose(DoubleToObjMonoMorphism<S> before) {
		ObjToIntMonoMorphism<S> now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntToDoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <U> ObjToIntMonoMorphism<U> compose(MonoMorphism<U, S> before) {
		ObjToIntMonoMorphism<S> now = this;
		return new ObjToIntMonoMorphism<U>() {

			@Override
			public int applyAsInt(U operand) {
				return now.applyAsInt(before.apply(operand));
			}

			@Override
			public IntToObjEpiMorphism<U> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
