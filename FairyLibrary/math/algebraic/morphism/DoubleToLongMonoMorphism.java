package com._31536000.math.algebraic.morphism;

/**
 * 演算が単射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link MonoMorphism}に対してdoubleからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToLongMonoMorphism
		extends DoubleToObjMonoMorphism<Long>, ObjToLongMonoMorphism<Double>, DoubleToLongMorphism {
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
	public LongToDoubleEpiMorphism retraction();

	@Override
	public default DoubleToIntMonoMorphism andThen(LongToIntMonoMorphism after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToIntMonoMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsLong(operand));
			}

			@Override
			public IntToDoubleEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default DoubleToLongMonoMorphism andThen(LongMonoMorphism after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsLong(operand));
			}

			@Override
			public LongToDoubleEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default DoubleMonoMorphism andThen(LongToDoubleMonoMorphism after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleMonoMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
			}

			@Override
			public DoubleEpiMorphism retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default <T> DoubleToObjMonoMorphism<T> andThen(LongToObjMonoMorphism<T> after) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToObjMonoMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public ObjToDoubleEpiMorphism<T> retraction() {
				return after.retraction().andThen(now.retraction());
			}

		};
	}

	@Override
	public default IntToLongMonoMorphism compose(IntToDoubleMonoMorphism before) {
		DoubleToLongMonoMorphism now = this;
		return new IntToLongMonoMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToIntEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default LongMonoMorphism compose(LongToDoubleMonoMorphism before) {
		DoubleToLongMonoMorphism now = this;
		return new LongMonoMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default DoubleToLongMonoMorphism compose(DoubleMonoMorphism before) {
		DoubleToLongMonoMorphism now = this;
		return new DoubleToLongMonoMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToDoubleEpiMorphism retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}

	@Override
	public default <S> ObjToLongMonoMorphism<S> compose(ObjToDoubleMonoMorphism<S> before) {
		DoubleToLongMonoMorphism now = this;
		return new ObjToLongMonoMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsDouble(operand));
			}

			@Override
			public LongToObjEpiMorphism<S> retraction() {
				return now.retraction().andThen(before.retraction());
			}

		};
	}
}
