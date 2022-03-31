package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対して、longを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface LongToObjEpiMorphism<T> extends EpiMorphism<Long, T>, LongToObjMorphism<T> {
	@Override
	public default T apply(Long operand) {
		return apply((long) operand);
	}

	@Override
	public ObjToLongMonoMorphism<T> section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default LongToIntEpiMorphism andThen(ObjToIntEpiMorphism<T> after) {
		LongToObjEpiMorphism<T> now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.apply(operand));
			}

			@Override
			public IntToLongMonoMorphism section() {
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
	@Override
	public default LongEpiMorphism andThen(ObjToLongEpiMorphism<T> after) {
		LongToObjEpiMorphism<T> now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.apply(operand));
			}

			@Override
			public LongMonoMorphism section() {
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
	@Override
	public default LongToDoubleEpiMorphism andThen(ObjToDoubleEpiMorphism<T> after) {
		LongToObjEpiMorphism<T> now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.apply(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
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
	public default <U> LongToObjEpiMorphism<U> andThen(EpiMorphism<T, U> after) {
		LongToObjEpiMorphism<T> now = this;
		return new LongToObjEpiMorphism<U>() {

			@Override
			public U apply(long operand) {
				return after.apply(now.apply(operand));
			}

			@Override
			public ObjToLongMonoMorphism<U> section() {
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
	public default IntToObjEpiMorphism<T> compose(IntToLongEpiMorphism before) {
		LongToObjEpiMorphism<T> now = this;
		return new IntToObjEpiMorphism<T>() {

			@Override
			public T apply(int operand) {
				return now.apply(before.applyAsLong(operand));
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
	public default LongToObjEpiMorphism<T> compose(LongEpiMorphism before) {
		LongToObjEpiMorphism<T> now = this;
		return new LongToObjEpiMorphism<T>() {

			@Override
			public T apply(long operand) {
				return now.apply(before.applyAsLong(operand));
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
	public default DoubleToObjEpiMorphism<T> compose(DoubleToLongEpiMorphism before) {
		LongToObjEpiMorphism<T> now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return now.apply(before.applyAsLong(operand));
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
	 * @param <U> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	public default <U> EpiMorphism<U, T> compose(ObjToLongEpiMorphism<U> before) {
		LongToObjEpiMorphism<T> now = this;
		return new EpiMorphism<U, T>() {

			@Override
			public T apply(U operand) {
				return now.apply(before.applyAsLong(operand));
			}

			@Override
			public MonoMorphism<T, U> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}