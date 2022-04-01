package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 */
public interface IntMonoMorphism extends IntToObjMonoMorphism<Integer>, ObjToIntMonoMorphism<Integer>, IntMorphism {
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
	public IntEpiMorphism retraction();

	@Override
	public default IntMonoMorphism andThen(IntMonoMorphism after) {
		IntMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToLongMonoMorphism andThen(IntToLongMonoMorphism after) {
		IntMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToDoubleMonoMorphism andThen(IntToDoubleMonoMorphism after) {
		IntMonoMorphism now = this;
		return new IntToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleToIntEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default <T> IntToObjMonoMorphism<T> andThen(IntToObjMonoMorphism<T> after) {
		IntMonoMorphism now = this;
		return new IntToObjMonoMorphism<T>() {

			@Override
			public T apply(int operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public ObjToIntEpiMorphism<T> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntMonoMorphism compose(IntMonoMorphism before) {
		IntMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongToIntMonoMorphism compose(LongToIntMonoMorphism before) {
		IntMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToLongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleToIntMonoMorphism compose(DoubleToIntMonoMorphism before) {
		IntMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToDoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <S> ObjToIntMonoMorphism<S> compose(ObjToIntMonoMorphism<S> before) {
		IntMonoMorphism now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsInt(operand));
			}

			@Override
			public IntToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
