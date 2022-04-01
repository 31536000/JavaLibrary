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
