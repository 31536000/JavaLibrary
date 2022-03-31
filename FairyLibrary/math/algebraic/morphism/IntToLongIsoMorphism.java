package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してintからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToLongIsoMorphism
		extends IntToObjIsoMorphism<Long>, ObjToLongIsoMorphism<Integer>, IntToLongEpiMorphism, IntToLongMonoMorphism {

	@Override
	default Long apply(Integer operand) {
		return applyAsLong((int) operand);
	}

	@Override
	default Long apply(int operand) {
		return applyAsLong(operand);
	}

	@Override
	default long applyAsLong(Integer operand) {
		return applyAsLong((int) operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public LongToIntIsoMorphism inverse();

	@Override
	public default LongToIntIsoMorphism section() {
		return inverse();
	}

	@Override
	public default LongToIntIsoMorphism retraction() {
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
	public default IntIsoMorphism andThen(LongToIntIsoMorphism after) {
		IntToLongIsoMorphism now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return after.applyAsInt(now.applyAsLong(t));
			}

			@Override
			public IntIsoMorphism inverse() {
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
	public default IntToLongIsoMorphism andThen(LongIsoMorphism after) {
		IntToLongIsoMorphism now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return after.applyAsLong(now.applyAsLong(t));
			}

			@Override
			public LongToIntIsoMorphism inverse() {
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
	public default IntToDoubleIsoMorphism andThen(LongToDoubleIsoMorphism after) {
		IntToLongIsoMorphism now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return after.applyAsDouble(now.applyAsLong(t));
			}

			@Override
			public DoubleToIntIsoMorphism inverse() {
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
	public default <T> IntToObjIsoMorphism<T> andThen(LongToObjIsoMorphism<T> after) {
		IntToLongIsoMorphism now = this;
		return new IntToObjIsoMorphism<T>() {
			@Override
			public T apply(int t) {
				return after.apply(now.applyAsLong(t));
			}

			@Override
			public ObjToIntIsoMorphism<T> inverse() {
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
	public default IntToLongIsoMorphism compose(IntIsoMorphism before) {
		IntToLongIsoMorphism now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return now.applyAsLong(before.applyAsInt(t));
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
	public default LongIsoMorphism compose(LongToIntIsoMorphism before) {
		IntToLongIsoMorphism now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return now.applyAsLong(before.applyAsInt(t));
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
	public default DoubleToLongIsoMorphism compose(DoubleToIntIsoMorphism before) {
		IntToLongIsoMorphism now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return now.applyAsLong(before.applyAsInt(t));
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
	public default <S> ObjToLongIsoMorphism<S> compose(ObjToIntIsoMorphism<S> before) {
		IntToLongIsoMorphism now = this;
		return new ObjToLongIsoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return now.applyAsLong(before.applyAsInt(t));
			}

			@Override
			public LongToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
