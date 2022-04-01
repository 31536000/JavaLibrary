package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対して、intを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface IntToObjEpiMorphism<T> extends EpiMorphism<Integer, T>, IntToObjMorphism<T> {
	@Override
	public default T apply(Integer operand) {
		return apply((int) operand);
	}

	@Override
	public ObjToIntMonoMorphism<T> section();

	@Override
	public default IntEpiMorphism andThen(ObjToIntEpiMorphism<T> after) {
		IntToObjEpiMorphism<T> now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.apply(operand));
			}

			@Override
			public IntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToLongEpiMorphism andThen(ObjToLongEpiMorphism<T> after) {
		IntToObjEpiMorphism<T> now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.apply(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToDoubleEpiMorphism andThen(ObjToDoubleEpiMorphism<T> after) {
		IntToObjEpiMorphism<T> now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.apply(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <S> IntToObjEpiMorphism<S> andThen(EpiMorphism<T, S> after) {
		IntToObjEpiMorphism<T> now = this;
		return new IntToObjEpiMorphism<S>() {

			@Override
			public S apply(int operand) {
				return after.apply(now.apply(operand));
			}

			@Override
			public ObjToIntMonoMorphism<S> section() {
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
	 */
	public default IntToObjEpiMorphism<T> compose(IntEpiMorphism before) {
		IntToObjEpiMorphism<T> now = this;
		return new IntToObjEpiMorphism<T>() {

			@Override
			public T apply(int operand) {
				return now.apply(before.applyAsInt(operand));
			}

			@Override
			public ObjToIntMonoMorphism<T> section() {
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
	 */
	public default LongToObjEpiMorphism<T> compose(LongToIntEpiMorphism before) {
		IntToObjEpiMorphism<T> now = this;
		return new LongToObjEpiMorphism<T>() {

			@Override
			public T apply(long operand) {
				return now.apply(before.applyAsInt(operand));
			}

			@Override
			public ObjToLongMonoMorphism<T> section() {
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
	 */
	public default DoubleToObjEpiMorphism<T> compose(DoubleToIntEpiMorphism before) {
		IntToObjEpiMorphism<T> now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return now.apply(before.applyAsInt(operand));
			}

			@Override
			public ObjToDoubleMonoMorphism<T> section() {
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
	 */
	public default <S> EpiMorphism<S, T> compose(ObjToIntEpiMorphism<S> before) {
		IntToObjEpiMorphism<T> now = this;
		return new EpiMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return now.apply(before.applyAsInt(operand));
			}

			@Override
			public MonoMorphism<T, S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
