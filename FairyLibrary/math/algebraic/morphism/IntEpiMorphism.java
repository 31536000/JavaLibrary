package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 */
public interface IntEpiMorphism extends IntToObjEpiMorphism<Integer>, ObjToIntEpiMorphism<Integer>, IntMorphism {
	@Override
	public default Integer apply(Integer operand) {
		return applyAsInt((int) operand);
	}

	@Override
	public default Integer apply(int operand) {
		return applyAsInt(operand);
	}

	@Override
	public default int applyAsInt(Integer operand) {
		return applyAsInt((int) operand);
	}

	@Override
	public IntMonoMorphism section();

	@Override
	public default IntEpiMorphism andThen(IntEpiMorphism after) {
		IntEpiMorphism now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToLongEpiMorphism andThen(IntToLongEpiMorphism after) {
		IntEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToDoubleEpiMorphism andThen(IntToDoubleEpiMorphism after) {
		IntEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <T> IntToObjEpiMorphism<T> andThen(IntToObjEpiMorphism<T> after) {
		IntEpiMorphism now = this;
		return new IntToObjEpiMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public ObjToIntMonoMorphism<T> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntEpiMorphism compose(IntEpiMorphism before) {
		IntEpiMorphism now = this;
		return new IntEpiMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongToIntEpiMorphism compose(LongToIntEpiMorphism before) {
		IntEpiMorphism now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToLongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleToIntEpiMorphism compose(DoubleToIntEpiMorphism before) {
		IntEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToDoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <S> ObjToIntEpiMorphism<S> compose(ObjToIntEpiMorphism<S> before) {
		IntEpiMorphism now = this;
		return new ObjToIntEpiMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
