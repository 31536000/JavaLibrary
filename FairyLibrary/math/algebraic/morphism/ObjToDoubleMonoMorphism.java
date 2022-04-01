package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対して、doubleを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToDoubleMonoMorphism<S> extends MonoMorphism<S, Double>, ObjToDoubleMorphism<S> {
	@Override
	public default Double apply(S operand) {
		return applyAsDouble(operand);
	}

	@Override
	public DoubleToObjEpiMorphism<S> retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ObjToIntMonoMorphism<S> andThen(DoubleToIntMonoMorphism after) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
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
	public default ObjToLongMonoMorphism<S> andThen(DoubleToLongMonoMorphism after) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new ObjToLongMonoMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
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
	public default ObjToDoubleMonoMorphism<S> andThen(DoubleMonoMorphism after) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new ObjToDoubleMonoMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
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
	public default <T> MonoMorphism<S, T> andThen(DoubleToObjMonoMorphism<T> after) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new MonoMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return after.apply(now.applyAsDouble(operand));
			}

			@Override
			public EpiMorphism<T, S> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToDoubleMonoMorphism compose(IntToObjMonoMorphism<S> before) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.apply(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongToDoubleMonoMorphism compose(LongToObjMonoMorphism<S> before) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.apply(operand));
			}

			@Override
			public DoubleToLongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleMonoMorphism compose(DoubleToObjMonoMorphism<S> before) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.apply(operand));
			}

			@Override
			public DoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <U> ObjToDoubleMonoMorphism<U> compose(MonoMorphism<U, S> before) {
		ObjToDoubleMonoMorphism<S> now = this;
		return new ObjToDoubleMonoMorphism<U>() {

			@Override
			public double applyAsDouble(U operand) {
				return now.applyAsDouble(before.apply(operand));
			}

			@Override
			public DoubleToObjEpiMorphism<U> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
