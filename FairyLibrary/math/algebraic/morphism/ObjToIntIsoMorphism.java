package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対して、intを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToIntIsoMorphism<S>
		extends IsoMorphism<S, Integer>, ObjToIntEpiMorphism<S>, ObjToIntMonoMorphism<S> {

	@Override
	public default Integer apply(S operand) {
		return applyAsInt(operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public IntToObjIsoMorphism<S> inverse();

	@Override
	public default IntToObjIsoMorphism<S> section() {
		return inverse();
	}

	@Override
	public default IntToObjIsoMorphism<S> retraction() {
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
	public default ObjToIntIsoMorphism<S> andThen(IntIsoMorphism after) {
		ObjToIntIsoMorphism<S> now = this;
		return new ObjToIntIsoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return after.applyAsInt(now.applyAsInt(t));
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
	public default ObjToLongIsoMorphism<S> andThen(IntToLongIsoMorphism after) {
		ObjToIntIsoMorphism<S> now = this;
		return new ObjToLongIsoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return after.applyAsLong(now.applyAsInt(t));
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
	public default ObjToDoubleIsoMorphism<S> andThen(IntToDoubleIsoMorphism after) {
		ObjToIntIsoMorphism<S> now = this;
		return new ObjToDoubleIsoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return after.applyAsDouble(now.applyAsInt(t));
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
	public default <T> IsoMorphism<S, T> andThen(IntToObjIsoMorphism<T> after) {
		ObjToIntIsoMorphism<S> now = this;
		return new IsoMorphism<S, T>() {
			@Override
			public T apply(S t) {
				return after.apply(now.applyAsInt(t));
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
	public default IntIsoMorphism compose(IntToObjIsoMorphism<S> before) {
		ObjToIntIsoMorphism<S> now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return now.applyAsInt(before.apply(t));
			}

			@Override
			public IntIsoMorphism inverse() {
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
	public default LongToIntIsoMorphism compose(LongToObjIsoMorphism<S> before) {
		ObjToIntIsoMorphism<S> now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return now.applyAsInt(before.apply(t));
			}

			@Override
			public IntToLongIsoMorphism inverse() {
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
	public default DoubleToIntIsoMorphism compose(DoubleToObjIsoMorphism<S> before) {
		ObjToIntIsoMorphism<S> now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return now.applyAsInt(before.apply(t));
			}

			@Override
			public IntToDoubleIsoMorphism inverse() {
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
	public default <U> ObjToIntIsoMorphism<U> compose(IsoMorphism<U, S> before) {
		ObjToIntIsoMorphism<S> now = this;
		return new ObjToIntIsoMorphism<U>() {
			@Override
			public int applyAsInt(U t) {
				return now.applyAsInt(before.apply(t));
			}

			@Override
			public IntToObjIsoMorphism<U> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
