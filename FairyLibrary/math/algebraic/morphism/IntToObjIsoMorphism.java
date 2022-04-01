package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対して、intを消費するプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 * @param <T> 終域
 */
public interface IntToObjIsoMorphism<T>
		extends IsoMorphism<Integer, T>, IntToObjEpiMorphism<T>, IntToObjMonoMorphism<T> {

	@Override
	default T apply(Integer operand) {
		return apply((int) operand);
	}

	@Override
	public ObjToIntIsoMorphism<T> inverse();

	@Override
	public default ObjToIntIsoMorphism<T> section() {
		return inverse();
	}

	@Override
	public default ObjToIntIsoMorphism<T> retraction() {
		return inverse();
	}

	@Override
	public default IntIsoMorphism andThen(ObjToIntIsoMorphism<T> after) {
		IntToObjIsoMorphism<T> now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return after.applyAsInt(now.apply(t));
			}

			@Override
			public IntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToLongIsoMorphism andThen(ObjToLongIsoMorphism<T> after) {
		IntToObjIsoMorphism<T> now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return after.applyAsLong(now.apply(t));
			}

			@Override
			public LongToIntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToDoubleIsoMorphism andThen(ObjToDoubleIsoMorphism<T> after) {
		IntToObjIsoMorphism<T> now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return after.applyAsDouble(now.apply(t));
			}

			@Override
			public DoubleToIntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default <S> IntToObjIsoMorphism<S> andThen(IsoMorphism<T, S> after) {
		IntToObjIsoMorphism<T> now = this;
		return new IntToObjIsoMorphism<S>() {
			@Override
			public S apply(int t) {
				return after.apply(now.apply(t));
			}

			@Override
			public ObjToIntIsoMorphism<S> inverse() {
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
	public default IntToObjIsoMorphism<T> compose(IntIsoMorphism before) {
		IntToObjIsoMorphism<T> now = this;
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
	public default LongToObjIsoMorphism<T> compose(LongToIntIsoMorphism before) {
		IntToObjIsoMorphism<T> now = this;
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
	public default DoubleToObjIsoMorphism<T> compose(DoubleToIntIsoMorphism before) {
		IntToObjIsoMorphism<T> now = this;
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
	public default <S> IsoMorphism<S, T> compose(ObjToIntIsoMorphism<S> before) {
		IntToObjIsoMorphism<T> now = this;
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
