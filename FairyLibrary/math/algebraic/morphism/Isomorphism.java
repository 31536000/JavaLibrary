package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <S> 始域
 * @param <T> 終域
 */
public interface IsoMorphism<S, T> extends EpiMorphism<S, T>, MonoMorphism<S, T> {
	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	public IsoMorphism<T, S> inverse();

	@Override
	public default IsoMorphism<T, S> section() {
		return inverse();
	}

	@Override
	public default IsoMorphism<T, S> retraction() {
		return inverse();
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ObjToIntIsoMorphism<S> andThen(ObjToIntIsoMorphism<T> after) {
		IsoMorphism<S, T> now = this;
		return new ObjToIntIsoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return after.applyAsInt(now.apply(t));
			}

			@Override
			public IntToObjIsoMorphism<S> inverse() {
				return after.inverse().andThen(now.inverse());
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
	public default ObjToLongIsoMorphism<S> andThen(ObjToLongIsoMorphism<T> after) {
		IsoMorphism<S, T> now = this;
		return new ObjToLongIsoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return after.applyAsLong(now.apply(t));
			}

			@Override
			public LongToObjIsoMorphism<S> inverse() {
				return after.inverse().andThen(now.inverse());
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
	public default ObjToDoubleIsoMorphism<S> andThen(ObjToDoubleIsoMorphism<T> after) {
		IsoMorphism<S, T> now = this;
		return new ObjToDoubleIsoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return after.applyAsDouble(now.apply(t));
			}

			@Override
			public DoubleToObjIsoMorphism<S> inverse() {
				return after.inverse().andThen(now.inverse());
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
	 * @see #compose(IsoMorphism)
	 */
	public default <U> IsoMorphism<S, U> andThen(IsoMorphism<T, U> after) {
		IsoMorphism<S, T> now = this;
		return new IsoMorphism<S, U>() {
			@Override
			public U apply(S t) {
				return after.apply(now.apply(t));
			}

			@Override
			public IsoMorphism<U, S> inverse() {
				return after.inverse().andThen(now.inverse());
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
	public default IntToObjIsoMorphism<T> compose(IntToObjIsoMorphism<S> before) {
		IsoMorphism<S, T> now = this;
		return new IntToObjIsoMorphism<T>() {
			@Override
			public T apply(int t) {
				return now.apply(before.apply(t));
			}

			@Override
			public ObjToIntIsoMorphism<T> inverse() {
				return now.inverse().andThen(before.inverse());
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
	public default LongToObjIsoMorphism<T> compose(LongToObjIsoMorphism<S> before) {
		IsoMorphism<S, T> now = this;
		return new LongToObjIsoMorphism<T>() {
			@Override
			public T apply(long t) {
				return now.apply(before.apply(t));
			}

			@Override
			public ObjToLongIsoMorphism<T> inverse() {
				return now.inverse().andThen(before.inverse());
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
	public default DoubleToObjIsoMorphism<T> compose(DoubleToObjIsoMorphism<S> before) {
		IsoMorphism<S, T> now = this;
		return new DoubleToObjIsoMorphism<T>() {
			@Override
			public T apply(double t) {
				return now.apply(before.apply(t));
			}

			@Override
			public ObjToDoubleIsoMorphism<T> inverse() {
				return now.inverse().andThen(before.inverse());
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
	 * @see #andThen(IsoMorphism)
	 */
	public default <U> IsoMorphism<U, T> compose(IsoMorphism<U, S> before) {
		IsoMorphism<S, T> now = this;
		return new IsoMorphism<U, T>() {
			@Override
			public T apply(U t) {
				return now.apply(before.apply(t));
			}

			@Override
			public IsoMorphism<T, U> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
