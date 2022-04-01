package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してintからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToDoubleMonoMorphism
		extends IntToObjMonoMorphism<Double>, ObjToDoubleMonoMorphism<Integer>, IntToDoubleMorphism {
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
	public DoubleToIntEpiMorphism retraction();

	@Override
	public default IntMonoMorphism andThen(DoubleToIntMonoMorphism after) {
		IntToDoubleMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToLongMonoMorphism andThen(DoubleToLongMonoMorphism after) {
		IntToDoubleMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToDoubleMonoMorphism andThen(DoubleMonoMorphism after) {
		IntToDoubleMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default <T> IntToObjMonoMorphism<T> andThen(DoubleToObjMonoMorphism<T> after) {
		IntToDoubleMonoMorphism now = this;
		return new IntToObjMonoMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsDouble(operand));
			}

			@Override
			public ObjToIntEpiMorphism<T> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToDoubleMonoMorphism compose(IntMonoMorphism before) {
		IntToDoubleMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongToDoubleMonoMorphism compose(LongToIntMonoMorphism before) {
		IntToDoubleMonoMorphism now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToLongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleMonoMorphism compose(DoubleToIntMonoMorphism before) {
		IntToDoubleMonoMorphism now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <S> ObjToDoubleMonoMorphism<S> compose(ObjToIntMonoMorphism<S> before) {
		IntToDoubleMonoMorphism now = this;
		return new ObjToDoubleMonoMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsInt(operand));
			}

			@Override
			public DoubleToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
