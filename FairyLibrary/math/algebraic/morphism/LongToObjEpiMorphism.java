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

	@Override
	public default <S> LongToObjEpiMorphism<S> andThen(EpiMorphism<T, S> after) {
		LongToObjEpiMorphism<T> now = this;
		return new LongToObjEpiMorphism<S>() {

			@Override
			public S apply(long operand) {
				return after.apply(now.apply(operand));
			}

			@Override
			public ObjToLongMonoMorphism<S> section() {
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
	 * @param <S> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 */
	public default <S> EpiMorphism<S, T> compose(ObjToLongEpiMorphism<S> before) {
		LongToObjEpiMorphism<T> now = this;
		return new EpiMorphism<S, T>() {

			@Override
			public T apply(S operand) {
				return now.apply(before.applyAsLong(operand));
			}

			@Override
			public MonoMorphism<T, S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}