package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してlongからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToIntIsoMorphism
		extends LongToObjIsoMorphism<Integer>, ObjToIntIsoMorphism<Long>, LongToIntEpiMorphism, LongToIntMonoMorphism {

	@Override
	default Integer apply(Long operand) {
		return applyAsInt((long) operand);
	}

	@Override
	default Integer apply(long operand) {
		return applyAsInt(operand);
	}

	@Override
	default int applyAsInt(Long operand) {
		return applyAsInt((long) operand);
	}

	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	@Override
	public IntToLongIsoMorphism inverse();

	@Override
	public default IntToLongIsoMorphism section() {
		return inverse();
	}

	@Override
	public default IntToLongIsoMorphism retraction() {
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
	public default LongToIntIsoMorphism andThen(IntIsoMorphism after) {
		LongToIntIsoMorphism now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return after.applyAsInt(now.applyAsInt(t));
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
	public default LongIsoMorphism andThen(IntToLongIsoMorphism after) {
		LongToIntIsoMorphism now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return after.applyAsLong(now.applyAsInt(t));
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
	public default LongToDoubleIsoMorphism andThen(IntToDoubleIsoMorphism after) {
		LongToIntIsoMorphism now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return after.applyAsDouble(now.applyAsInt(t));
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
	public default <T> LongToObjIsoMorphism<T> andThen(IntToObjIsoMorphism<T> after) {
		LongToIntIsoMorphism now = this;
		return new LongToObjIsoMorphism<T>() {
			@Override
			public T apply(long t) {
				return after.apply(now.applyAsInt(t));
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
	public default IntIsoMorphism compose(IntToLongIsoMorphism before) {
		LongToIntIsoMorphism now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return now.applyAsInt(before.applyAsLong(t));
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
	public default LongToIntIsoMorphism compose(LongIsoMorphism before) {
		LongToIntIsoMorphism now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return now.applyAsInt(before.applyAsLong(t));
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
	public default DoubleToIntIsoMorphism compose(DoubleToLongIsoMorphism before) {
		LongToIntIsoMorphism now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return now.applyAsInt(before.applyAsLong(t));
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
	 * @param <S> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	@Override
	public default <S> ObjToIntIsoMorphism<S> compose(ObjToLongIsoMorphism<S> before) {
		LongToIntIsoMorphism now = this;
		return new ObjToIntIsoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return now.applyAsInt(before.applyAsLong(t));
			}

			@Override
			public IntToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
