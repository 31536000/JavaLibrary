package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してdoubleからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToLongEpiMorphism
		extends DoubleToObjEpiMorphism<Long>, ObjToLongEpiMorphism<Double>, DoubleToLongMorphism {
	@Override
	public default Long apply(Double operand) {
		return applyAsLong((double) operand);
	}

	@Override
	public default Long apply(double operand) {
		return applyAsLong(operand);
	}

	@Override
	public default long applyAsLong(Double operand) {
		return applyAsLong((double) operand);
	}

	@Override
	public LongToDoubleMonoMorphism section();

	@Override
	public default DoubleToIntEpiMorphism andThen(LongToIntEpiMorphism after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsLong(operand));
			}

			@Override
			public IntToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default DoubleToLongEpiMorphism andThen(LongEpiMorphism after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsLong(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default DoubleEpiMorphism andThen(LongToDoubleEpiMorphism after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <T> DoubleToObjEpiMorphism<T> andThen(LongToObjEpiMorphism<T> after) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public ObjToDoubleMonoMorphism<T> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToLongEpiMorphism compose(IntToDoubleEpiMorphism before) {
		DoubleToLongEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongEpiMorphism compose(LongToDoubleEpiMorphism before) {
		DoubleToLongEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleToLongEpiMorphism compose(DoubleEpiMorphism before) {
		DoubleToLongEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <S> ObjToLongEpiMorphism<S> compose(ObjToDoubleEpiMorphism<S> before) {
		DoubleToLongEpiMorphism now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
