package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してintからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToDoubleIsoMorphism extends IntToObjIsoMorphism<Double>, ObjToDoubleIsoMorphism<Integer>,
		IntToDoubleEpiMorphism, IntToDoubleMonoMorphism {

	@Override
	default Double apply(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	default Double apply(int operand) {
		return applyAsDouble(operand);
	}

	@Override
	default double applyAsDouble(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	public DoubleToIntIsoMorphism inverse();

	@Override
	public default DoubleToIntIsoMorphism section() {
		return inverse();
	}

	@Override
	public default DoubleToIntIsoMorphism retraction() {
		return inverse();
	}

	@Override
	public default IntIsoMorphism andThen(DoubleToIntIsoMorphism after) {
		IntToDoubleIsoMorphism now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return after.applyAsInt(now.applyAsDouble(t));
			}

			@Override
			public IntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToLongIsoMorphism andThen(DoubleToLongIsoMorphism after) {
		IntToDoubleIsoMorphism now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return after.applyAsLong(now.applyAsDouble(t));
			}

			@Override
			public LongToIntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToDoubleIsoMorphism andThen(DoubleIsoMorphism after) {
		IntToDoubleIsoMorphism now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return after.applyAsDouble(now.applyAsDouble(t));
			}

			@Override
			public DoubleToIntIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default <T> IntToObjIsoMorphism<T> andThen(DoubleToObjIsoMorphism<T> after) {
		IntToDoubleIsoMorphism now = this;
		return new IntToObjIsoMorphism<T>() {
			@Override
			public T apply(int t) {
				return after.apply(now.applyAsDouble(t));
			}

			@Override
			public ObjToIntIsoMorphism<T> inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToDoubleIsoMorphism compose(IntIsoMorphism before) {
		IntToDoubleIsoMorphism now = this;
		return new IntToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(int t) {
				return now.applyAsDouble(before.applyAsInt(t));
			}

			@Override
			public DoubleToIntIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default LongToDoubleIsoMorphism compose(LongToIntIsoMorphism before) {
		IntToDoubleIsoMorphism now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return now.applyAsDouble(before.applyAsInt(t));
			}

			@Override
			public DoubleToLongIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default DoubleIsoMorphism compose(DoubleToIntIsoMorphism before) {
		IntToDoubleIsoMorphism now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return now.applyAsDouble(before.applyAsInt(t));
			}

			@Override
			public DoubleIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default <S> ObjToDoubleIsoMorphism<S> compose(ObjToIntIsoMorphism<S> before) {
		IntToDoubleIsoMorphism now = this;
		return new ObjToDoubleIsoMorphism<S>() {
			@Override
			public double applyAsDouble(S t) {
				return now.applyAsDouble(before.applyAsInt(t));
			}

			@Override
			public DoubleToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
