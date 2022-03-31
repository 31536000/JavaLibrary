package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してlongからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToDoubleIsoMorphism extends LongToObjIsoMorphism<Double>, ObjToDoubleIsoMorphism<Long>,
		LongToDoubleEpiMorphism, LongToDoubleMonoMorphism {

	@Override
	default Double apply(Long operand) {
		return applyAsDouble((long) operand);
	}

	@Override
	default Double apply(long operand) {
		return applyAsDouble(operand);
	}

	@Override
	default double applyAsDouble(Long operand) {
		return applyAsDouble((long) operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public DoubleToLongIsoMorphism inverse();

	@Override
	public default DoubleToLongIsoMorphism section() {
		return inverse();
	}

	@Override
	public default DoubleToLongIsoMorphism retraction() {
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
	public default LongToIntIsoMorphism andThen(DoubleToIntIsoMorphism after) {
		LongToDoubleIsoMorphism now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return after.applyAsInt(now.applyAsDouble(t));
			}

			@Override
			public IntToLongIsoMorphism inverse() {
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
	public default LongIsoMorphism andThen(DoubleToLongIsoMorphism after) {
		LongToDoubleIsoMorphism now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return after.applyAsLong(now.applyAsDouble(t));
			}

			@Override
			public LongIsoMorphism inverse() {
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
	public default LongToDoubleIsoMorphism andThen(DoubleIsoMorphism after) {
		LongToDoubleIsoMorphism now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return after.applyAsDouble(now.applyAsDouble(t));
			}

			@Override
			public DoubleToLongIsoMorphism inverse() {
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
	public default <T> LongToObjIsoMorphism<T> andThen(DoubleToObjIsoMorphism<T> after) {
		LongToDoubleIsoMorphism now = this;
		return new LongToObjIsoMorphism<T>() {
			@Override
			public T apply(long t) {
				return after.apply(now.applyAsDouble(t));
			}

			@Override
			public ObjToLongIsoMorphism<T> inverse() {
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
	public default IntToDoubleIsoMorphism compose(IntToLongIsoMorphism before) {
		LongToDoubleIsoMorphism now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return now.applyAsDouble(before.applyAsLong(t));
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
	public default LongToDoubleIsoMorphism compose(LongIsoMorphism before) {
		LongToDoubleIsoMorphism now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return now.applyAsDouble(before.applyAsLong(t));
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
	public default DoubleIsoMorphism compose(DoubleToLongIsoMorphism before) {
		LongToDoubleIsoMorphism now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return now.applyAsDouble(before.applyAsLong(t));
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
	public default <S> ObjToDoubleIsoMorphism<S> compose(ObjToLongIsoMorphism<S> before) {
		LongToDoubleIsoMorphism now = this;
		return new ObjToDoubleIsoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return now.applyAsDouble(before.applyAsLong(t));
			}

			@Override
			public DoubleToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
