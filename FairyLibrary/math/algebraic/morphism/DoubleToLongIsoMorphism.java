package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してdoubleからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToLongIsoMorphism extends DoubleToObjIsoMorphism<Long>, ObjToLongIsoMorphism<Double>,
		DoubleToLongEpiMorphism, DoubleToLongMonoMorphism {

	@Override
	default Long apply(Double operand) {
		return applyAsLong((double) operand);
	}

	@Override
	default Long apply(double operand) {
		return applyAsLong(operand);
	}

	@Override
	default long applyAsLong(Double operand) {
		return applyAsLong((double) operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public LongToDoubleIsoMorphism inverse();

	@Override
	public default LongToDoubleIsoMorphism section() {
		return inverse();
	}

	@Override
	public default LongToDoubleIsoMorphism retraction() {
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
	public default DoubleToIntIsoMorphism andThen(LongToIntIsoMorphism after) {
		DoubleToLongIsoMorphism now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return after.applyAsInt(now.applyAsLong(t));
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
	public default DoubleToLongIsoMorphism andThen(LongIsoMorphism after) {
		DoubleToLongIsoMorphism now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return after.applyAsLong(now.applyAsLong(t));
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
	public default DoubleIsoMorphism andThen(LongToDoubleIsoMorphism after) {
		DoubleToLongIsoMorphism now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return after.applyAsDouble(now.applyAsLong(t));
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
	public default <T> DoubleToObjIsoMorphism<T> andThen(LongToObjIsoMorphism<T> after) {
		DoubleToLongIsoMorphism now = this;
		return new DoubleToObjIsoMorphism<T>() {
			@Override
			public T apply(double t) {
				return after.apply(now.applyAsLong(t));
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
	public default IntToLongIsoMorphism compose(IntToDoubleIsoMorphism before) {
		DoubleToLongIsoMorphism now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return now.applyAsLong(before.applyAsDouble(t));
			}

			@Override
			public LongToIntIsoMorphism inverse() {
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
	public default LongIsoMorphism compose(LongToDoubleIsoMorphism before) {
		DoubleToLongIsoMorphism now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return now.applyAsLong(before.applyAsDouble(t));
			}

			@Override
			public LongIsoMorphism inverse() {
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
	public default DoubleToLongIsoMorphism compose(DoubleIsoMorphism before) {
		DoubleToLongIsoMorphism now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return now.applyAsLong(before.applyAsDouble(t));
			}

			@Override
			public LongToDoubleIsoMorphism inverse() {
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
	public default <S> ObjToLongIsoMorphism<S> compose(ObjToDoubleIsoMorphism<S> before) {
		DoubleToLongIsoMorphism now = this;
		return new ObjToLongIsoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return now.applyAsLong(before.applyAsDouble(t));
			}

			@Override
			public LongToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
