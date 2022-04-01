package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <S> 始域
 * @param <T> 終域
 */
public interface MonoMorphism<S, T> extends Morphism<S, T> {
	/**
	 * 左逆射を返します。
	 * @return 左逆射
	 */
	public EpiMorphism<T, S> retraction();

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ObjToIntMonoMorphism<S> andThen(ObjToIntMonoMorphism<T> after) {
		MonoMorphism<S, T> now = this;
		return new ObjToIntMonoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return after.applyAsInt(now.apply(t));
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
	public default ObjToLongMonoMorphism<S> andThen(ObjToLongMonoMorphism<T> after) {
		MonoMorphism<S, T> now = this;
		return new ObjToLongMonoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return after.applyAsLong(now.apply(t));
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
	public default ObjToDoubleMonoMorphism<S> andThen(ObjToDoubleMonoMorphism<T> after) {
		MonoMorphism<S, T> now = this;
		return new ObjToDoubleMonoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return after.applyAsDouble(now.apply(t));
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
	 * @param <U> after関数および合成関数の出力の型
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(MonoMorphism)
	 */
	public default <U> MonoMorphism<S, U> andThen(MonoMorphism<T, U> after) {
		MonoMorphism<S, T> now = this;
		return new MonoMorphism<S, U>() {
			@Override
			public U apply(S t) {
				return after.apply(now.apply(t));
			}

			@Override
			public EpiMorphism<U, S> retraction() {
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
	public default IntToObjMonoMorphism<T> compose(IntToObjMonoMorphism<S> before) {
		MonoMorphism<S, T> now = this;
		return new IntToObjMonoMorphism<T>() {
			@Override
			public T apply(int t) {
				return now.apply(before.apply(t));
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
	public default LongToObjMonoMorphism<T> compose(LongToObjMonoMorphism<S> before) {
		MonoMorphism<S, T> now = this;
		return new LongToObjMonoMorphism<T>() {
			@Override
			public T apply(long t) {
				return now.apply(before.apply(t));
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
	public default DoubleToObjMonoMorphism<T> compose(DoubleToObjMonoMorphism<S> before) {
		MonoMorphism<S, T> now = this;
		return new DoubleToObjMonoMorphism<T>() {
			@Override
			public T apply(double t) {
				return now.apply(before.apply(t));
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
	 * @param <U> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see #andThen(MonoMorphism)
	 */
	public default <U> MonoMorphism<U, T> compose(MonoMorphism<U, S> before) {
		MonoMorphism<S, T> now = this;
		return new MonoMorphism<U, T>() {
			@Override
			public T apply(U t) {
				return now.apply(before.apply(t));
			}

			@Override
			public EpiMorphism<T, U> retraction() {
				return now.retraction().andThen(before.retraction());
			}
		};
	}
}
