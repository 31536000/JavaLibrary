package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対して、doubleを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface DoubleToObjIsoMorphism<T>
		extends IsoMorphism<Double, T>, DoubleToObjEpiMorphism<T>, DoubleToObjMonoMorphism<T> {

	@Override
	default T apply(Double operand) {
		return apply((double) operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public ObjToDoubleIsoMorphism<T> inverse();

	@Override
	public default ObjToDoubleIsoMorphism<T> section() {
		return inverse();
	}

	@Override
	public default ObjToDoubleIsoMorphism<T> retraction() {
		return inverse();
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
	public default DoubleToIntIsoMorphism andThen(ObjToIntIsoMorphism<T> after) {
		DoubleToObjIsoMorphism<T> now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return after.applyAsInt(now.apply(t));
			}

			@Override
			public IntToDoubleIsoMorphism inverse() {
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
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default DoubleToLongIsoMorphism andThen(ObjToLongIsoMorphism<T> after) {
		DoubleToObjIsoMorphism<T> now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return after.applyAsLong(now.apply(t));
			}

			@Override
			public LongToDoubleIsoMorphism inverse() {
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
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default DoubleIsoMorphism andThen(ObjToDoubleIsoMorphism<T> after) {
		DoubleToObjIsoMorphism<T> now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return after.applyAsDouble(now.apply(t));
			}

			@Override
			public DoubleIsoMorphism inverse() {
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
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default <U> DoubleToObjIsoMorphism<U> andThen(IsoMorphism<T, U> after) {
		DoubleToObjIsoMorphism<T> now = this;
		return new DoubleToObjIsoMorphism<U>() {
			@Override
			public U apply(double t) {
				return after.apply(now.apply(t));
			}

			@Override
			public ObjToDoubleIsoMorphism<U> inverse() {
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
	 * @see {@link #andThen(Function)}
	 */
	public default IntToObjIsoMorphism<T> compose(IntToDoubleIsoMorphism before) {
		DoubleToObjIsoMorphism<T> now = this;
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
	 * @see {@link #andThen(Function)}
	 */
	public default LongToObjIsoMorphism<T> compose(LongToDoubleIsoMorphism before) {
		DoubleToObjIsoMorphism<T> now = this;
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
	 * @see {@link #andThen(Function)}
	 */
	public default DoubleToObjIsoMorphism<T> compose(DoubleIsoMorphism before) {
		DoubleToObjIsoMorphism<T> now = this;
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
	 * @see {@link #andThen(Function)}
	 */
	public default <U> IsoMorphism<U, T> compose(ObjToDoubleIsoMorphism<U> before) {
		DoubleToObjIsoMorphism<T> now = this;
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
