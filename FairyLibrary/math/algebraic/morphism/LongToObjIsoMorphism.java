package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対して、longを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface LongToObjIsoMorphism<T>
		extends IsoMorphism<Long, T>, LongToObjEpiMorphism<T>, LongToObjMonoMorphism<T> {

	@Override
	default T apply(Long operand) {
		return apply((long) operand);
	}

	@Override
	public ObjToLongIsoMorphism<T> inverse();

	@Override
	public default ObjToLongIsoMorphism<T> section() {
		return inverse();
	}

	@Override
	public default ObjToLongIsoMorphism<T> retraction() {
		return inverse();
	}

	@Override
	public default LongToIntIsoMorphism andThen(ObjToIntIsoMorphism<T> after) {
		LongToObjIsoMorphism<T> now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return after.applyAsInt(now.apply(t));
			}

			@Override
			public IntToLongIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default LongIsoMorphism andThen(ObjToLongIsoMorphism<T> after) {
		LongToObjIsoMorphism<T> now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return after.applyAsLong(now.apply(t));
			}

			@Override
			public LongIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default LongToDoubleIsoMorphism andThen(ObjToDoubleIsoMorphism<T> after) {
		LongToObjIsoMorphism<T> now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return after.applyAsDouble(now.apply(t));
			}

			@Override
			public DoubleToLongIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default <S> LongToObjIsoMorphism<S> andThen(IsoMorphism<T, S> after) {
		LongToObjIsoMorphism<T> now = this;
		return new LongToObjIsoMorphism<S>() {
			@Override
			public S apply(long t) {
				return after.apply(now.apply(t));
			}

			@Override
			public ObjToLongIsoMorphism<S> inverse() {
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
	public default IntToObjIsoMorphism<T> compose(IntToLongIsoMorphism before) {
		LongToObjIsoMorphism<T> now = this;
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
	public default LongToObjIsoMorphism<T> compose(LongIsoMorphism before) {
		LongToObjIsoMorphism<T> now = this;
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
	public default DoubleToObjIsoMorphism<T> compose(DoubleToLongIsoMorphism before) {
		LongToObjIsoMorphism<T> now = this;
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
	 * @param <S> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 */
	public default <S> IsoMorphism<S, T> compose(ObjToLongIsoMorphism<S> before) {
		LongToObjIsoMorphism<T> now = this;
		return new IsoMorphism<S, T>() {
			@Override
			public T apply(S t) {
				return now.apply(before.apply(t));
			}

			@Override
			public IsoMorphism<T, S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
