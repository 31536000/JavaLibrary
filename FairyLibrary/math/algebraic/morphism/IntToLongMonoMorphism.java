package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してintからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToLongMonoMorphism
		extends IntToObjMonoMorphism<Long>, ObjToLongMonoMorphism<Integer>, IntToLongMorphism {
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
	public LongToIntEpiMorphism retraction();

	@Override
	public default IntMonoMorphism andThen(LongToIntMonoMorphism after) {
		IntToLongMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsLong(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToLongMonoMorphism andThen(LongMonoMorphism after) {
		IntToLongMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsLong(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToDoubleMonoMorphism andThen(LongToDoubleMonoMorphism after) {
		IntToLongMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default <T> IntToObjMonoMorphism<T> andThen(LongToObjMonoMorphism<T> after) {
		IntToLongMonoMorphism now = this;
		return new IntToObjMonoMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public ObjToIntEpiMorphism<T> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToLongMonoMorphism compose(IntMonoMorphism before) {
		IntToLongMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongMonoMorphism compose(LongToIntMonoMorphism before) {
		IntToLongMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleToLongMonoMorphism compose(DoubleToIntMonoMorphism before) {
		IntToLongMonoMorphism now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongToDoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <S> ObjToLongMonoMorphism<S> compose(ObjToIntMonoMorphism<S> before) {
		IntToLongMonoMorphism now = this;
		return new ObjToLongMonoMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsInt(operand));
			}

			@Override
			public LongToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
