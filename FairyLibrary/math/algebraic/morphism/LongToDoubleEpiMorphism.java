package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してlongからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToDoubleEpiMorphism
		extends LongToObjEpiMorphism<Double>, ObjToDoubleEpiMorphism<Long>, LongToDoubleMorphism {
	@Override
	public default Double apply(Long operand) {
		return applyAsDouble((long) operand);
	}

	@Override
	public default Double apply(long operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Long operand) {
		return applyAsDouble((long) operand);
	}

	@Override
	public DoubleToLongMonoMorphism section();

	@Override
	public default LongToIntEpiMorphism andThen(DoubleToIntEpiMorphism after) {
		LongToDoubleEpiMorphism now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
			}

			@Override
			public IntToLongMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default LongEpiMorphism andThen(DoubleToLongEpiMorphism after) {
		LongToDoubleEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
			}

			@Override
			public LongMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default LongToDoubleEpiMorphism andThen(DoubleEpiMorphism after) {
		LongToDoubleEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <T> LongToObjEpiMorphism<T> andThen(DoubleToObjEpiMorphism<T> after) {
		LongToDoubleEpiMorphism now = this;
		return new LongToObjEpiMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsDouble(operand));
			}

			@Override
			public ObjToLongMonoMorphism<T> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToDoubleEpiMorphism compose(IntToLongEpiMorphism before) {
		LongToDoubleEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongToDoubleEpiMorphism compose(LongEpiMorphism before) {
		LongToDoubleEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleEpiMorphism compose(DoubleToLongEpiMorphism before) {
		LongToDoubleEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <S> ObjToDoubleEpiMorphism<S> compose(ObjToLongEpiMorphism<S> before) {
		LongToDoubleEpiMorphism now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsLong(operand));
			}

			@Override
			public DoubleToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
