package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongEpiMorphism extends LongToObjEpiMorphism<Long>, ObjToLongEpiMorphism<Long>, LongMorphism {
	@Override
	public default Long apply(Long operand) {
		return applyAsLong((long) operand);
	}

	@Override
	public default Long apply(long operand) {
		return applyAsLong(operand);
	}

	@Override
	public default long applyAsLong(Long operand) {
		return applyAsLong((long) operand);
	}

	@Override
	public LongMonoMorphism section();

	@Override
	public default LongToIntEpiMorphism andThen(LongToIntEpiMorphism after) {
		LongEpiMorphism now = this;
		return new LongToIntEpiMorphism() {

			@Override
			public int applyAsInt(long operand) {
				return after.applyAsInt(now.applyAsLong(operand));
			}

			@Override
			public IntToLongMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default LongEpiMorphism andThen(LongEpiMorphism after) {
		LongEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return after.applyAsLong(now.applyAsLong(operand));
			}

			@Override
			public LongMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default LongToDoubleEpiMorphism andThen(LongToDoubleEpiMorphism after) {
		LongEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return after.applyAsDouble(now.applyAsLong(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <T> LongToObjEpiMorphism<T> andThen(LongToObjEpiMorphism<T> after) {
		LongEpiMorphism now = this;
		return new LongToObjEpiMorphism<T>() {

			@Override
			public T apply(long operand) {
				return after.apply(now.applyAsLong(operand));
			}

			@Override
			public ObjToLongMonoMorphism<T> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToLongEpiMorphism compose(IntToLongEpiMorphism before) {
		LongEpiMorphism now = this;
		return new IntToLongEpiMorphism() {

			@Override
			public long applyAsLong(int operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongToIntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongEpiMorphism compose(LongEpiMorphism before) {
		LongEpiMorphism now = this;
		return new LongEpiMorphism() {

			@Override
			public long applyAsLong(long operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleToLongEpiMorphism compose(DoubleToLongEpiMorphism before) {
		LongEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <S> ObjToLongEpiMorphism<S> compose(ObjToLongEpiMorphism<S> before) {
		LongEpiMorphism now = this;
		return new ObjToLongEpiMorphism<S>() {

			@Override
			public long applyAsLong(S operand) {
				return now.applyAsLong(before.applyAsLong(operand));
			}

			@Override
			public LongToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
