package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してdoubleからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToIntIsoMorphism extends DoubleToObjIsoMorphism<Integer>, ObjToIntIsoMorphism<Double>,
		DoubleToIntEpiMorphism, DoubleToIntMonoMorphism {

	@Override
	default Integer apply(Double operand) {
		return applyAsInt((double) operand);
	}

	@Override
	default Integer apply(double operand) {
		return applyAsInt(operand);
	}

	@Override
	default int applyAsInt(Double operand) {
		return applyAsInt((double) operand);
	}

	@Override
	public IntToDoubleIsoMorphism inverse();

	@Override
	public default IntToDoubleIsoMorphism section() {
		return inverse();
	}

	@Override
	public default IntToDoubleIsoMorphism retraction() {
		return inverse();
	}

	@Override
	public default DoubleToIntIsoMorphism andThen(IntIsoMorphism after) {
		DoubleToIntIsoMorphism now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return after.applyAsInt(now.applyAsInt(t));
			}

			@Override
			public IntToDoubleIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default DoubleToLongIsoMorphism andThen(IntToLongIsoMorphism after) {
		DoubleToIntIsoMorphism now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return after.applyAsLong(now.applyAsInt(t));
			}

			@Override
			public LongToDoubleIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default DoubleIsoMorphism andThen(IntToDoubleIsoMorphism after) {
		DoubleToIntIsoMorphism now = this;
		return new DoubleIsoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return after.applyAsDouble(now.applyAsInt(t));
			}

			@Override
			public DoubleIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default <T> DoubleToObjIsoMorphism<T> andThen(IntToObjIsoMorphism<T> after) {
		DoubleToIntIsoMorphism now = this;
		return new DoubleToObjIsoMorphism<T>() {
			@Override
			public T apply(double t) {
				return after.apply(now.applyAsInt(t));
			}

			@Override
			public ObjToDoubleIsoMorphism<T> inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntIsoMorphism compose(IntToDoubleIsoMorphism before) {
		DoubleToIntIsoMorphism now = this;
		return new IntIsoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return now.applyAsInt(before.applyAsDouble(t));
			}

			@Override
			public IntIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default LongToIntIsoMorphism compose(LongToDoubleIsoMorphism before) {
		DoubleToIntIsoMorphism now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return now.applyAsInt(before.applyAsDouble(t));
			}

			@Override
			public IntToLongIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default DoubleToIntIsoMorphism compose(DoubleIsoMorphism before) {
		DoubleToIntIsoMorphism now = this;
		return new DoubleToIntIsoMorphism() {
			@Override
			public int applyAsInt(double t) {
				return now.applyAsInt(before.applyAsDouble(t));
			}

			@Override
			public IntToDoubleIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default <S> ObjToIntIsoMorphism<S> compose(ObjToDoubleIsoMorphism<S> before) {
		DoubleToIntIsoMorphism now = this;
		return new ObjToIntIsoMorphism<S>() {
			@Override
			public int applyAsInt(S t) {
				return now.applyAsInt(before.applyAsDouble(t));
			}

			@Override
			public IntToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
