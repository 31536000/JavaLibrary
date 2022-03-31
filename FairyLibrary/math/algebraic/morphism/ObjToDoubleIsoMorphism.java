package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対して、doubleを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToDoubleIsoMorphism<S>
		extends IsoMorphism<S, Double>, ObjToDoubleEpiMorphism<S>, ObjToDoubleMonoMorphism<S> {

	@Override
	public default Double apply(S operand) {
		return applyAsDouble(operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public DoubleToObjIsoMorphism<S> inverse();

	@Override
	public default DoubleToObjIsoMorphism<S> section() {
		return inverse();
	}

	@Override
	public default DoubleToObjIsoMorphism<S> retraction() {
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
	public default ObjToIntIsoMorphism<S> andThen(DoubleToIntIsoMorphism after) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new ObjToIntIsoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return after.applyAsInt(now.applyAsDouble(t));
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
	 * @see {@link #compose(Function)}
	 */
	public default ObjToLongIsoMorphism<S> andThen(DoubleToLongIsoMorphism after) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new ObjToLongIsoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return after.applyAsLong(now.applyAsDouble(t));
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
	 * @see {@link #compose(Function)}
	 */
	public default ObjToDoubleIsoMorphism<S> andThen(DoubleIsoMorphism after) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new ObjToDoubleIsoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return after.applyAsDouble(now.applyAsDouble(t));
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
	 * @param <T> after関数および合成関数の出力の型
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	public default <T> IsoMorphism<S, T> andThen(DoubleToObjIsoMorphism<T> after) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new IsoMorphism<S, T>() {
			@Override
			public T apply(S t) {
				return after.apply(now.applyAsDouble(t));
			}

			@Override
			public IsoMorphism<T, S> inverse() {
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
	@Override
	public default IntToDoubleIsoMorphism compose(IntToObjIsoMorphism<S> before) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return now.applyAsDouble(before.apply(t));
			}

			@Override
			public DoubleToIntIsoMorphism inverse() {
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
	@Override
	public default LongToDoubleIsoMorphism compose(LongToObjIsoMorphism<S> before) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return now.applyAsDouble(before.apply(t));
			}

			@Override
			public DoubleToLongIsoMorphism inverse() {
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
	@Override
	public default DoubleIsoMorphism compose(DoubleToObjIsoMorphism<S> before) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return now.applyAsDouble(before.apply(t));
			}

			@Override
			public DoubleIsoMorphism inverse() {
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
	@Override
	public default <U> ObjToDoubleIsoMorphism<U> compose(IsoMorphism<U, S> before) {
		ObjToDoubleIsoMorphism<S> now = this;
		return new ObjToDoubleIsoMorphism<U>() {
			@Override
			public double applyAsDouble(U t) {
				return now.applyAsDouble(before.apply(t));
			}

			@Override
			public DoubleToObjIsoMorphism<U> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
