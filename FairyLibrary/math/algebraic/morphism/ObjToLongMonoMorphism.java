package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対して、longを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToLongMonoMorphism<S> extends MonoMorphism<S, Long>, ObjToLongMorphism<S> {
	@Override
	public default Long apply(S operand) {
		return applyAsLong(operand);
	}

	@Override
	public LongToObjEpiMorphism<S> retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ObjToIntMonoMorphism<S> andThen(LongToIntMonoMorphism after) {
		ObjToLongMonoMorphism<S> now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return after.applyAsInt(now.applyAsLong(operand));
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
	public default ObjToLongMonoMorphism<S> andThen(LongMonoMorphism after) {
		ObjToLongMonoMorphism<S> now = this;
		return new ObjToLongMonoMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return after.applyAsLong(now.applyAsLong(operand));
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
	public default ObjToDoubleMonoMorphism<S> andThen(LongToDoubleMonoMorphism after) {
		ObjToLongMonoMorphism<S> now = this;
		return new ObjToDoubleMonoMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
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
	public default <T> MonoMorphism<S, T> andThen(LongToObjMonoMorphism<T> after) {
		ObjToLongMonoMorphism<S> now = this;
		return new MonoMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public EpiMorphism<T, S> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToLongMonoMorphism compose(IntToObjMonoMorphism<S> before) {
		ObjToLongMonoMorphism<S> now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongMonoMorphism compose(LongToObjMonoMorphism<S> before) {
		ObjToLongMonoMorphism<S> now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleToLongMonoMorphism compose(DoubleToObjMonoMorphism<S> before) {
		ObjToLongMonoMorphism<S> now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongToDoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <U> ObjToLongMonoMorphism<U> compose(MonoMorphism<U, S> before) {
		ObjToLongMonoMorphism<S> now = this;
		return new ObjToLongMonoMorphism<U>() {

			@Override
			public long applyAsLong(U operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongToObjEpiMorphism<U> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
