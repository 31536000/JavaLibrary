package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対して、doubleを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface DoubleToObjEpiMorphism<T> extends EpiMorphism<Double, T>, DoubleToObjMorphism<T> {
	@Override
	public default T apply(Double operand) {
		return apply((double) operand);
	}

	@Override
	public ObjToDoubleMonoMorphism<T> section();

	@Override
	public default DoubleToIntEpiMorphism andThen(ObjToIntEpiMorphism<T> after) {
		DoubleToObjEpiMorphism<T> now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.apply(operand));
			}

			@Override
			public IntToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default DoubleToLongEpiMorphism andThen(ObjToLongEpiMorphism<T> after) {
		DoubleToObjEpiMorphism<T> now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.apply(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default DoubleEpiMorphism andThen(ObjToDoubleEpiMorphism<T> after) {
		DoubleToObjEpiMorphism<T> now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.apply(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <S> DoubleToObjEpiMorphism<S> andThen(EpiMorphism<T, S> after) {
		DoubleToObjEpiMorphism<T> now = this;
		return new DoubleToObjEpiMorphism<S>() {

			@Override
			public S apply(double operand) {
				return after.apply(now.apply(operand));
			}

			@Override
			public ObjToDoubleMonoMorphism<S> section() {
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
	public default IntToObjEpiMorphism<T> compose(IntToDoubleEpiMorphism before) {
		DoubleToObjEpiMorphism<T> now = this;
		return new IntToObjEpiMorphism<T>() {

			@Override
			public T apply(int operand) {
				return now.apply(before.applyAsDouble(operand));
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
	public default LongToObjEpiMorphism<T> compose(LongToDoubleEpiMorphism before) {
		DoubleToObjEpiMorphism<T> now = this;
		return new LongToObjEpiMorphism<T>() {

			@Override
			public T apply(long operand) {
				return now.apply(before.applyAsDouble(operand));
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
	public default DoubleToObjEpiMorphism<T> compose(DoubleEpiMorphism before) {
		DoubleToObjEpiMorphism<T> now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return now.apply(before.applyAsDouble(operand));
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
	public default <S> EpiMorphism<S, T> compose(ObjToDoubleEpiMorphism<S> before) {
		DoubleToObjEpiMorphism<T> now = this;
		return new EpiMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return now.apply(before.applyAsDouble(operand));
			}

			@Override
			public MonoMorphism<T, S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
