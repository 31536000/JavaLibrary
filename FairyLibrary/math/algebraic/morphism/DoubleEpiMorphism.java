package com._31536000.math.algebraic.morphism;

/**
 * 演算が全射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EpiMorphism}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleEpiMorphism
		extends DoubleToObjEpiMorphism<Double>, ObjToDoubleEpiMorphism<Double>, DoubleMorphism {
	@Override
	public default Double apply(Double operand) {
		return applyAsDouble((double) operand);
	}

	@Override
	public default Double apply(double operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Double operand) {
		return applyAsDouble((double) operand);
	}

	@Override
	public DoubleMonoMorphism section();

	@Override
	public default DoubleToIntEpiMorphism andThen(DoubleToIntEpiMorphism after) {
		DoubleEpiMorphism now = this;
		return new DoubleToIntEpiMorphism() {

			@Override
			public int applyAsInt(double operand) {
				return after.applyAsInt(now.applyAsDouble(operand));
			}

			@Override
			public IntToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default DoubleToLongEpiMorphism andThen(DoubleToLongEpiMorphism after) {
		DoubleEpiMorphism now = this;
		return new DoubleToLongEpiMorphism() {

			@Override
			public long applyAsLong(double operand) {
				return after.applyAsLong(now.applyAsDouble(operand));
			}

			@Override
			public LongToDoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default DoubleEpiMorphism andThen(DoubleEpiMorphism after) {
		DoubleEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return after.applyAsDouble(now.applyAsDouble(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default <T> DoubleToObjEpiMorphism<T> andThen(DoubleToObjEpiMorphism<T> after) {
		DoubleEpiMorphism now = this;
		return new DoubleToObjEpiMorphism<T>() {

			@Override
			public T apply(double operand) {
				return after.apply(now.applyAsDouble(operand));
			}

			@Override
			public ObjToDoubleMonoMorphism<T> section() {
				return after.section().andThen(now.section());
			}

		};
	}

	@Override
	public default IntToDoubleEpiMorphism compose(IntToDoubleEpiMorphism before) {
		DoubleEpiMorphism now = this;
		return new IntToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(int operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
			}

			@Override
			public DoubleToIntMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default LongToDoubleEpiMorphism compose(LongToDoubleEpiMorphism before) {
		DoubleEpiMorphism now = this;
		return new LongToDoubleEpiMorphism() {

			@Override
			public double applyAsDouble(long operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
			}

			@Override
			public DoubleToLongMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default DoubleEpiMorphism compose(DoubleEpiMorphism before) {
		DoubleEpiMorphism now = this;
		return new DoubleEpiMorphism() {

			@Override
			public double applyAsDouble(double operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
			}

			@Override
			public DoubleMonoMorphism section() {
				return now.section().andThen(before.section());
			}

		};
	}

	@Override
	public default <S> ObjToDoubleEpiMorphism<S> compose(ObjToDoubleEpiMorphism<S> before) {
		DoubleEpiMorphism now = this;
		return new ObjToDoubleEpiMorphism<S>() {

			@Override
			public double applyAsDouble(S operand) {
				return now.applyAsDouble(before.applyAsDouble(operand));
			}

			@Override
			public DoubleToObjMonoMorphism<S> section() {
				return now.section().andThen(before.section());
			}

		};
	}
}
