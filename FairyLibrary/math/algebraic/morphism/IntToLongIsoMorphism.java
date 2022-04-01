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
