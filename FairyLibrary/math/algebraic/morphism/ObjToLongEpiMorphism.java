package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対して、longを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToLongEpiMorphism<S> extends EpiMorphism<S, Long>, ObjToLongMorphism<S> {
	@Override
	public default Long apply(S operand) {
		return applyAsLong(operand);
	}

	@Override
	public LongToObjMonoMorphism<S> section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	public default ObjToIntEpiMorphism<S> andThen(LongToIntEpiMorphism after) {
		ObjToLongEpiMorphism<S> now = this;
		return new ObjToIntEpiMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return after.applyAsInt(now.applyAsLong(operand));
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
	 * @see {@link #compose(Function)}
	 */
	public default ObjToLongEpiMorphism<S> andThen(LongEpiMorphism after) {
		ObjToLongEpiMorphism<S> now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return after.applyAsLong(now.applyAsLong(operand));
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
	 * @see {@link #compose(Function)}
	 */
	public default ObjToDoubleEpiMorphism<S> andThen(LongToDoubleEpiMorphism after) {
		ObjToLongEpiMorphism<S> now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
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
	 * @see {@link #compose(Function)}
	 */
	public default <T> EpiMorphism<S, T> andThen(LongToObjEpiMorphism<T> after) {
		ObjToLongEpiMorphism<S> now = this;
		return new EpiMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public MonoMorphism<T, S> section() {
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
	@Override
	public default IntToLongEpiMorphism compose(IntToObjEpiMorphism<S> before) {
		ObjToLongEpiMorphism<S> now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
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
	@Override
	public default LongEpiMorphism compose(LongToObjEpiMorphism<S> before) {
		ObjToLongEpiMorphism<S> now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongMonoMorphism section() {
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
	@Override
	public default DoubleToLongEpiMorphism compose(DoubleToObjEpiMorphism<S> before) {
		ObjToLongEpiMorphism<S> now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
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
	@Override
	public default <U> ObjToLongEpiMorphism<U> compose(EpiMorphism<U, S> before) {
		ObjToLongEpiMorphism<S> now = this;
		return new ObjToLongEpiMorphism<U>() {

			@Override
			public long applyAsLong(U operand) {
				return now.applyAsLong(before.apply(operand));
			}

			@Override
			public LongToObjMonoMorphism<U> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
