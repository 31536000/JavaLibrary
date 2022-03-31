package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleIsoMorphism
		extends DoubleToObjIsoMorphism<Double>, ObjToDoubleIsoMorphism<Double>, DoubleEpiMorphism, DoubleMonoMorphism {

	@Override
	default Double apply(Double operand) {
		return applyAsDouble((double) operand);
	}

	@Override
	default Double apply(double operand) {
		return applyAsDouble(operand);
	}

	@Override
	default double applyAsDouble(Double operand) {
		return applyAsDouble((double) operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public DoubleIsoMorphism inverse();

	@Override
	public default DoubleIsoMorphism section() {
		return inverse();
	}

	@Override
	public default DoubleIsoMorphism retraction() {
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
	public default DoubleToIntIsoMorphism andThen(DoubleToIntIsoMorphism after) {
		DoubleIsoMorphism now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return after.applyAsInt(now.applyAsDouble(t));
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
	public default DoubleToLongIsoMorphism andThen(DoubleToLongIsoMorphism after) {
		DoubleIsoMorphism now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return after.applyAsLong(now.applyAsDouble(t));
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
	public default DoubleIsoMorphism andThen(DoubleIsoMorphism after) {
		DoubleIsoMorphism now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return after.applyAsDouble(now.applyAsDouble(t));
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
	 * @param <T> after関数および合成関数の出力の型
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	@Override
	public default <T> DoubleToObjIsoMorphism<T> andThen(DoubleToObjIsoMorphism<T> after) {
		DoubleIsoMorphism now = this;
		return new DoubleToObjIsoMorphism<T>() {
			@Override
			public T apply(double t) {
				return after.apply(now.applyAsDouble(t));
			}

			@Override
			public ObjToDoubleIsoMorphism<T> inverse() {
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
	public default IntToDoubleIsoMorphism compose(IntToDoubleIsoMorphism before) {
		DoubleIsoMorphism now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return now.applyAsDouble(before.applyAsDouble(t));
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
	public default LongToDoubleIsoMorphism compose(LongToDoubleIsoMorphism before) {
		DoubleIsoMorphism now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return now.applyAsDouble(before.applyAsDouble(t));
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
	public default DoubleIsoMorphism compose(DoubleIsoMorphism before) {
		DoubleIsoMorphism now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return now.applyAsDouble(before.applyAsDouble(t));
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
	 * @param <S> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	@Override
	public default <S> ObjToDoubleIsoMorphism<S> compose(ObjToDoubleIsoMorphism<S> before) {
		DoubleIsoMorphism now = this;
		return new ObjToDoubleIsoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return now.applyAsDouble(before.applyAsDouble(t));
			}

			@Override
			public DoubleToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
