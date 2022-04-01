package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してintからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToDoubleEpiMorphism
		extends IntToObjEpiMorphism<Double>, ObjToDoubleEpiMorphism<Integer>, IntToDoubleMorphism {
	@Override
	public default Double apply(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	public default Double apply(int operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	public DoubleToIntMonoMorphism section();

	@Override
	public default IntEpiMorphism andThen(DoubleToIntEpiMorphism after) {
		IntToDoubleEpiMorphism now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
			}

			@Override
			public IntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToLongEpiMorphism andThen(DoubleToLongEpiMorphism after) {
		IntToDoubleEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToDoubleEpiMorphism andThen(DoubleEpiMorphism after) {
		IntToDoubleEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <T> IntToObjEpiMorphism<T> andThen(DoubleToObjEpiMorphism<T> after) {
		IntToDoubleEpiMorphism now = this;
		return new IntToObjEpiMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsDouble(operand));
			}

			@Override
			public ObjToIntMonoMorphism<T> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToDoubleEpiMorphism compose(IntEpiMorphism before) {
		IntToDoubleEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongToDoubleEpiMorphism compose(LongToIntEpiMorphism before) {
		IntToDoubleEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleEpiMorphism compose(DoubleToIntEpiMorphism before) {
		IntToDoubleEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <S> ObjToDoubleEpiMorphism<S> compose(ObjToIntEpiMorphism<S> before) {
		IntToDoubleEpiMorphism now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
