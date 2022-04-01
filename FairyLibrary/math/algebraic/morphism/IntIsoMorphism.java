package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 */
public interface IntIsoMorphism
		extends IntToObjIsoMorphism<Integer>, ObjToIntIsoMorphism<Integer>, IntEpiMorphism, IntMonoMorphism {

	@Override
	default Integer apply(Integer operand) {
		return applyAsInt((int) operand);
	}

	@Override
	default Integer apply(int operand) {
		return applyAsInt(operand);
	}

	@Override
	default int applyAsInt(Integer operand) {
		return applyAsInt((int) operand);
	}

	@Override
	public IntIsoMorphism inverse();

	@Override
	public default IntIsoMorphism section() {
		return inverse();
	}

	@Override
	public default IntIsoMorphism retraction() {
		return inverse();
	}

	@Override
	public default IntIsoMorphism andThen(IntIsoMorphism after) {
		IntIsoMorphism now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return after.applyAsInt(now.applyAsInt(t));
			}

			@Override
			public IntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToLongIsoMorphism andThen(IntToLongIsoMorphism after) {
		IntIsoMorphism now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return after.applyAsLong(now.applyAsInt(t));
			}

			@Override
			public LongToIntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToDoubleIsoMorphism andThen(IntToDoubleIsoMorphism after) {
		IntIsoMorphism now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return after.applyAsDouble(now.applyAsInt(t));
			}

			@Override
			public DoubleToIntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default <T> IntToObjIsoMorphism<T> andThen(IntToObjIsoMorphism<T> after) {
		IntIsoMorphism now = this;
		return new IntToObjIsoMorphism<T>() {
			@Override
			public T apply(int t) {
				return after.apply(now.applyAsInt(t));
			}

			@Override
			public ObjToIntIsoMorphism<T> inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntIsoMorphism compose(IntIsoMorphism before) {
		IntIsoMorphism now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return now.applyAsInt(before.applyAsInt(t));
			}

			@Override
			public IntIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default LongToIntIsoMorphism compose(LongToIntIsoMorphism before) {
		IntIsoMorphism now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return now.applyAsInt(before.applyAsInt(t));
			}

			@Override
			public IntToLongIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default DoubleToIntIsoMorphism compose(DoubleToIntIsoMorphism before) {
		IntIsoMorphism now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return now.applyAsInt(before.applyAsInt(t));
			}

			@Override
			public IntToDoubleIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default <S> ObjToIntIsoMorphism<S> compose(ObjToIntIsoMorphism<S> before) {
		IntIsoMorphism now = this;
		return new ObjToIntIsoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return now.applyAsInt(before.applyAsInt(t));
			}

			@Override
			public IntToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
