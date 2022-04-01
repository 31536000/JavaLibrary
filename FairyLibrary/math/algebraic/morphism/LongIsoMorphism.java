package com._31536000.math.algebraic.morphism;

/**
 * 演算が同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link IsoMorphism}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongIsoMorphism
		extends LongToObjIsoMorphism<Long>, ObjToLongIsoMorphism<Long>, LongEpiMorphism, LongMonoMorphism {

	@Override
	default Long apply(Long operand) {
		return applyAsLong((long) operand);
	}

	@Override
	default Long apply(long operand) {
		return applyAsLong(operand);
	}

	@Override
	default long applyAsLong(Long operand) {
		return applyAsLong((long) operand);
	}

	@Override
	public LongIsoMorphism inverse();

	@Override
	public default LongIsoMorphism section() {
		return inverse();
	}

	@Override
	public default LongIsoMorphism retraction() {
		return inverse();
	}

	@Override
	public default LongToIntIsoMorphism andThen(LongToIntIsoMorphism after) {
		LongIsoMorphism now = this;
		return new LongToIntIsoMorphism() {
			@Override
			public int applyAsInt(long t) {
				return after.applyAsInt(now.applyAsLong(t));
			}

			@Override
			public IntToLongIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default LongIsoMorphism andThen(LongIsoMorphism after) {
		LongIsoMorphism now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return after.applyAsLong(now.applyAsLong(t));
			}

			@Override
			public LongIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default LongToDoubleIsoMorphism andThen(LongToDoubleIsoMorphism after) {
		LongIsoMorphism now = this;
		return new LongToDoubleIsoMorphism() {
			@Override
			public double applyAsDouble(long t) {
				return after.applyAsDouble(now.applyAsLong(t));
			}

			@Override
			public DoubleToLongIsoMorphism inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default <T> LongToObjIsoMorphism<T> andThen(LongToObjIsoMorphism<T> after) {
		LongIsoMorphism now = this;
		return new LongToObjIsoMorphism<T>() {
			@Override
			public T apply(long t) {
				return after.apply(now.applyAsLong(t));
			}

			@Override
			public ObjToLongIsoMorphism<T> inverse() {
				return after.inverse().andThen(now.inverse());
			}
		};
	}

	@Override
	public default IntToLongIsoMorphism compose(IntToLongIsoMorphism before) {
		LongIsoMorphism now = this;
		return new IntToLongIsoMorphism() {
			@Override
			public long applyAsLong(int t) {
				return now.applyAsLong(before.applyAsLong(t));
			}

			@Override
			public LongToIntIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default LongIsoMorphism compose(LongIsoMorphism before) {
		LongIsoMorphism now = this;
		return new LongIsoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return now.applyAsLong(before.applyAsLong(t));
			}

			@Override
			public LongIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default DoubleToLongIsoMorphism compose(DoubleToLongIsoMorphism before) {
		LongIsoMorphism now = this;
		return new DoubleToLongIsoMorphism() {
			@Override
			public long applyAsLong(double t) {
				return now.applyAsLong(before.applyAsLong(t));
			}

			@Override
			public LongToDoubleIsoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	@Override
	public default <S> ObjToLongIsoMorphism<S> compose(ObjToLongIsoMorphism<S> before) {
		LongIsoMorphism now = this;
		return new ObjToLongIsoMorphism<S>() {
			@Override
			public long applyAsLong(S t) {
				return now.applyAsLong(before.applyAsLong(t));
			}

			@Override
			public LongToObjIsoMorphism<S> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}
}
