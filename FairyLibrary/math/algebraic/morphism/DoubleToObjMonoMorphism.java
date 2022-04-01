package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対して、doubleを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface DoubleToObjMonoMorphism<T> extends MonoMorphism<Double, T>, DoubleToObjMorphism<T> {
	@Override
	public default T apply(Double operand) {
		return apply((double) operand);
	}

	@Override
	public ObjToDoubleEpiMorphism<T> retraction();

	@Override
	public default DoubleToIntMonoMorphism andThen(ObjToIntMonoMorphism<T> after) {
		DoubleToObjMonoMorphism<T> now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.apply(operand));
			}

			@Override
			public IntToDoubleEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default DoubleToLongMonoMorphism andThen(ObjToLongMonoMorphism<T> after) {
		DoubleToObjMonoMorphism<T> now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.apply(operand));
			}

			@Override
			public LongToDoubleEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default DoubleMonoMorphism andThen(ObjToDoubleMonoMorphism<T> after) {
		DoubleToObjMonoMorphism<T> now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.apply(operand));
			}

			@Override
			public DoubleEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default <U> DoubleToObjMonoMorphism<U> andThen(MonoMorphism<T, U> after) {
		DoubleToObjMonoMorphism<T> now = this;
		return new DoubleToObjMonoMorphism<U>() {

			@Override
			public U apply(double operand) {
				return after.apply(now.apply(operand));
			}

			@Override
			public ObjToDoubleEpiMorphism<U> retraction() {
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
	 */
	public default IntToObjMonoMorphism<T> compose(IntToDoubleMonoMorphism before) {
		DoubleToObjMonoMorphism<T> now = this;
		return new IntToObjMonoMorphism<T>() {

			@Override
			public T apply(int operand) {
				return now.apply(before.applyAsDouble(operand));
			}

			@Override
			public ObjToIntEpiMorphism<T> retraction() {
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
	 */
	public default LongToObjMonoMorphism<T> compose(LongToDoubleMonoMorphism before) {
		DoubleToObjMonoMorphism<T> now = this;
		return new LongToObjMonoMorphism<T>() {

			@Override
			public T apply(long operand) {
				return now.apply(before.applyAsDouble(operand));
			}

			@Override
			public ObjToLongEpiMorphism<T> retraction() {
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
	 */
	public default DoubleToObjMonoMorphism<T> compose(DoubleMonoMorphism before) {
		DoubleToObjMonoMorphism<T> now = this;
		return new DoubleToObjMonoMorphism<T>() {

			@Override
			public T apply(double operand) {
				return now.apply(before.applyAsDouble(operand));
			}

			@Override
			public ObjToDoubleEpiMorphism<T> retraction() {
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
	 */
	public default <S> MonoMorphism<S, T> compose(ObjToDoubleMonoMorphism<S> before) {
		DoubleToObjMonoMorphism<T> now = this;
		return new MonoMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return now.apply(before.applyAsDouble(operand));
			}

			@Override
			public EpiMorphism<T, S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
