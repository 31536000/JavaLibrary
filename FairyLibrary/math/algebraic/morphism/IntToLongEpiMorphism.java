package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してintからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToLongEpiMorphism
		extends IntToObjEpiMorphism<Long>, ObjToLongEpiMorphism<Integer>, IntToLongMorphism {
	@Override
	public default Long apply(Integer operand) {
		return applyAsLong((int) operand);
	}

	@Override
	public default Long apply(int operand) {
		return applyAsLong(operand);
	}

	@Override
	public default long applyAsLong(Integer operand) {
		return applyAsLong((int) operand);
	}

	@Override
	public LongToIntMonoMorphism section();

	@Override
	public default IntEpiMorphism andThen(LongToIntEpiMorphism after) {
		IntToLongEpiMorphism now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsLong(operand));
			}

			@Override
			public IntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToLongEpiMorphism andThen(LongEpiMorphism after) {
		IntToLongEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsLong(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToDoubleEpiMorphism andThen(LongToDoubleEpiMorphism after) {
		IntToLongEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <T> IntToObjEpiMorphism<T> andThen(LongToObjEpiMorphism<T> after) {
		IntToLongEpiMorphism now = this;
		return new IntToObjEpiMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public ObjToIntMonoMorphism<T> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToLongEpiMorphism compose(IntEpiMorphism before) {
		IntToLongEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongEpiMorphism compose(LongToIntEpiMorphism before) {
		IntToLongEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleToLongEpiMorphism compose(DoubleToIntEpiMorphism before) {
		IntToLongEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <S> ObjToLongEpiMorphism<S> compose(ObjToIntEpiMorphism<S> before) {
		IntToLongEpiMorphism now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
