package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してlongからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToIntMonoMorphism
		extends LongToObjMonoMorphism<Integer>, ObjToIntMonoMorphism<Long>, LongToIntMorphism {
	@Override
	public default Integer apply(Long operand) {
		return applyAsInt((long) operand);
	}

	@Override
	public default Integer apply(long operand) {
		return applyAsInt(operand);
	}

	@Override
	public default int applyAsInt(Long operand) {
		return applyAsInt((long) operand);
	}

	@Override
	public IntToLongEpiMorphism retraction();

	@Override
	public default LongToIntMonoMorphism andThen(IntMonoMorphism after) {
		LongToIntMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsInt(operand));
			}

			@Override
			public IntToLongEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default LongMonoMorphism andThen(IntToLongMonoMorphism after) {
		LongToIntMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsInt(operand));
			}

			@Override
			public LongEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default LongToDoubleMonoMorphism andThen(IntToDoubleMonoMorphism after) {
		LongToIntMonoMorphism now = this;
		return new LongToDoubleMonoMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsInt(operand));
			}

			@Override
			public DoubleToLongEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default <T> LongToObjMonoMorphism<T> andThen(IntToObjMonoMorphism<T> after) {
		LongToIntMonoMorphism now = this;
		return new LongToObjMonoMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsInt(operand));
			}

			@Override
			public ObjToLongEpiMorphism<T> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntMonoMorphism compose(IntToLongMonoMorphism before) {
		LongToIntMonoMorphism now = this;
		return new IntMonoMorphism() {

			@Override
			public int applyAsInt(int operand) {
				return now.applyAsInt(before.applyAsLong(operand));
			}

			@Override
			public IntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongToIntMonoMorphism compose(LongMonoMorphism before) {
		LongToIntMonoMorphism now = this;
		return new LongToIntMonoMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return now.applyAsInt(before.applyAsLong(operand));
			}

			@Override
			public IntToLongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleToIntMonoMorphism compose(DoubleToLongMonoMorphism before) {
		LongToIntMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return now.applyAsInt(before.applyAsLong(operand));
			}

			@Override
			public IntToDoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <S> ObjToIntMonoMorphism<S> compose(ObjToLongMonoMorphism<S> before) {
		LongToIntMonoMorphism now = this;
		return new ObjToIntMonoMorphism<S>() {

			@Override
			public int applyAsInt(S operand) {
				return now.applyAsInt(before.applyAsLong(operand));
			}

			@Override
			public IntToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
