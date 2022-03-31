package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 * @param <S> 始域
 * @param <T> 終域
 *
 */
public interface EpiMorphism<S, T> extends Morphism<S, T> {
	/**
	 * 右逆射を返します。
	 * @return 右逆射
	 */
	public MonoMorphism<T, S> section();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	public default ObjToIntEpiMorphism<S> andThen(ObjToIntEpiMorphism<T> after) {
		EpiMorphism<S, T> now = this;
		return new ObjToIntEpiMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return after.applyAsInt(now.apply(t));
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
	public default ObjToLongEpiMorphism<S> andThen(ObjToLongEpiMorphism<T> after) {
		EpiMorphism<S, T> now = this;
		return new ObjToLongEpiMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return after.applyAsLong(now.apply(t));
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
	public default ObjToDoubleEpiMorphism<S> andThen(ObjToDoubleEpiMorphism<T> after) {
		EpiMorphism<S, T> now = this;
		return new ObjToDoubleEpiMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return after.applyAsDouble(now.apply(t));
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
	 * @param <U> after関数および合成関数の出力の型
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	public default <U> EpiMorphism<S, U> andThen(EpiMorphism<T, U> after) {
		EpiMorphism<S, T> now = this;
		return new EpiMorphism<S, U>() {
			@Override
			public U apply(S t) {
				return after.apply(now.apply(t));
			}

			@Override
			public MonoMorphism<U, S> section() {
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
	public default IntToObjEpiMorphism<T> compose(IntToObjEpiMorphism<S> before) {
		EpiMorphism<S, T> now = this;
		return new IntToObjEpiMorphism<T>() {
			@Override
			public T apply(int t) {
				return now.apply(before.apply(t));
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
	public default LongToObjEpiMorphism<T> compose(LongToObjEpiMorphism<S> before) {
		EpiMorphism<S, T> now = this;
		return new LongToObjEpiMorphism<T>() {
			@Override
			public T apply(long t) {
				return now.apply(before.apply(t));
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
	public default DoubleToObjEpiMorphism<T> compose(DoubleToObjEpiMorphism<S> before) {
		EpiMorphism<S, T> now = this;
		return new DoubleToObjEpiMorphism<T>() {
			@Override
			public T apply(double t) {
				return now.apply(before.apply(t));
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
	public default <U> EpiMorphism<U, T> compose(EpiMorphism<U, S> before) {
		EpiMorphism<S, T> now = this;
		return new EpiMorphism<U, T>() {
			@Override
			public T apply(U t) {
				return now.apply(before.apply(t));
			}

			@Override
			public MonoMorphism<T, U> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
