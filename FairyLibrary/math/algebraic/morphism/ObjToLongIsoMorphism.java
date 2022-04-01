package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対して、longを生成するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <S> 始域
 */
public interface ObjToLongIsoMorphism<S>
		extends IsoMorphism<S, Long>, ObjToLongEpiMorphism<S>, ObjToLongMonoMorphism<S> {

	@Override
	public default Long apply(S operand) {
		return applyAsLong(operand);
	}

	@Override
	public LongToObjIsoMorphism<S> inverse();

	@Override
	public default LongToObjIsoMorphism<S> section() {
		return inverse();
	}

	@Override
	public default LongToObjIsoMorphism<S> retraction() {
		return inverse();
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default ObjToIntIsoMorphism<S> andThen(LongToIntIsoMorphism after) {
		ObjToLongIsoMorphism<S> now = this;
		return new ObjToIntIsoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return after.applyAsInt(now.applyAsLong(t));
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
	public default ObjToLongIsoMorphism<S> andThen(LongIsoMorphism after) {
		ObjToLongIsoMorphism<S> now = this;
		return new ObjToLongIsoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return after.applyAsLong(now.applyAsLong(t));
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
	public default ObjToDoubleIsoMorphism<S> andThen(LongToDoubleIsoMorphism after) {
		ObjToLongIsoMorphism<S> now = this;
		return new ObjToDoubleIsoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return after.applyAsDouble(now.applyAsLong(t));
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
	 */
	public default <T> IsoMorphism<S, T> andThen(LongToObjIsoMorphism<T> after) {
		ObjToLongIsoMorphism<S> now = this;
		return new IsoMorphism<S, T>() {
			@Override
			public T apply(S t) {
				return after.apply(now.applyAsLong(t));
			}

			@Override
			public IsoMorphism<T, S> inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToLongIsoMorphism compose(IntToObjIsoMorphism<S> before) {
		ObjToLongIsoMorphism<S> now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return now.applyAsLong(before.apply(t));
			}

			@Override
			public LongToIntIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default LongIsoMorphism compose(LongToObjIsoMorphism<S> before) {
		ObjToLongIsoMorphism<S> now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return now.applyAsLong(before.apply(t));
			}

			@Override
			public LongIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default DoubleToLongIsoMorphism compose(DoubleToObjIsoMorphism<S> before) {
		ObjToLongIsoMorphism<S> now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return now.applyAsLong(before.apply(t));
			}

			@Override
			public LongToDoubleIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default <U> ObjToLongIsoMorphism<U> compose(IsoMorphism<U, S> before) {
		ObjToLongIsoMorphism<S> now = this;
		return new ObjToLongIsoMorphism<U>() {
			@Override
			public long applyAsLong(U t) {
				return now.applyAsLong(before.apply(t));
			}

			@Override
			public LongToObjIsoMorphism<U> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
