package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対して、intを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface IntToObjMonoMorphism<T> extends MonoMorphism<Integer, T>, IntToObjMorphism<T> {
	@Override
	public default T apply(Integer operand) {
		return apply((int) operand);
	}

	@Override
	public ObjToIntEpiMorphism<T> retraction();

	@Override
	public default IntMonoMorphism andThen(ObjToIntMonoMorphism<T> after) {
		IntToObjMonoMorphism<T> now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.apply(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToLongMonoMorphism andThen(ObjToLongMonoMorphism<T> after) {
		IntToObjMonoMorphism<T> now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.apply(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToDoubleMonoMorphism andThen(ObjToDoubleMonoMorphism<T> after) {
		IntToObjMonoMorphism<T> now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.apply(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default <S> IntToObjMonoMorphism<S> andThen(MonoMorphism<T, S> after) {
		IntToObjMonoMorphism<T> now = this;
		return new IntToObjMonoMorphism<S>() {

			@Override
			public S apply(int operand) {
				return after.apply(now.apply(operand));
			}

			@Override
			public ObjToIntEpiMorphism<S> retraction() {
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
	public default IntToObjMonoMorphism<T> compose(IntMonoMorphism before) {
		IntToObjMonoMorphism<T> now = this;
		return new IntToObjMonoMorphism<T>() {

			@Override
			public T apply(int operand) {
				return now.apply(before.applyAsInt(operand));
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
	public default LongToObjMonoMorphism<T> compose(LongToIntMonoMorphism before) {
		IntToObjMonoMorphism<T> now = this;
		return new LongToObjMonoMorphism<T>() {

			@Override
			public T apply(long operand) {
				return now.apply(before.applyAsInt(operand));
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
	public default DoubleToObjMonoMorphism<T> compose(DoubleToIntMonoMorphism before) {
		IntToObjMonoMorphism<T> now = this;
		return new DoubleToObjMonoMorphism<T>() {

			@Override
			public T apply(double operand) {
				return now.apply(before.applyAsInt(operand));
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
	public default <S> MonoMorphism<S, T> compose(ObjToIntMonoMorphism<S> before) {
		IntToObjMonoMorphism<T> now = this;
		return new MonoMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return now.apply(before.applyAsInt(operand));
			}

			@Override
			public EpiMorphism<T, S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
