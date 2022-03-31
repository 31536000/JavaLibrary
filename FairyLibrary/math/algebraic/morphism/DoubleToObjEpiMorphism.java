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

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
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

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
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

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
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

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param <U> after関数および合成関数の出力の型
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default <U> DoubleToObjEpiMorphism<U> andThen(EpiMorphism<T, U> after) {
		DoubleToObjEpiMorphism<T> now = this;
		return new DoubleToObjEpiMorphism<U>() {

			@Override
			public U apply(double operand) {
				return after.apply(now.apply(operand));
			}

			@Override
			public ObjToDoubleMonoMorphism<U> section() {
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
	 * @see {@link #andThen(Function)}
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
	 * @see {@link #andThen(Function)}
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
	 * @param before この関数を適用する前に適用する関数
	 * @param <U> 始域
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	public default <U> EpiMorphism<U, T> compose(ObjToDoubleEpiMorphism<U> before) {
		DoubleToObjEpiMorphism<T> now = this;
		return new EpiMorphism<U, T>() {

			@Override
			public T apply(U operand) {
				return now.apply(before.applyAsDouble(operand));
			}

			@Override
			public MonoMorphism<T, U> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
