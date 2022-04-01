package com._31536000.math.algebraic.morphism;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対して、doubleからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToIntMorphism
		extends DoubleToObjMorphism<Integer>, ObjToIntMorphism<Double>, java.util.function.DoubleToIntFunction {
	@Override
	public default Integer apply(Double operand) {
		return applyAsInt((double) operand);
	}

	@Override
	public default Integer apply(double operand) {
		return applyAsInt(operand);
	}

	@Override
	public default int applyAsInt(Double operand) {
		return applyAsInt((double) operand);
	}

	@Override
	public default DoubleToIntMorphism andThen(java.util.function.IntUnaryOperator after) {
		return s -> after.applyAsInt(applyAsInt(s));
	}

	@Override
	public default DoubleToLongMorphism andThen(java.util.function.IntToLongFunction after) {
		return s -> after.applyAsLong(applyAsInt(s));
	}

	@Override
	public default DoubleMorphism andThen(java.util.function.IntToDoubleFunction after) {
		return s -> after.applyAsDouble(applyAsInt(s));
	}

	@Override
	public default <T> DoubleToObjMorphism<T> andThen(java.util.function.IntFunction<? extends T> after) {
		return s -> after.apply(applyAsInt(s));
	}

	@Override
	public default IntMorphism compose(java.util.function.IntToDoubleFunction before) {
		return s -> applyAsInt(before.applyAsDouble(s));
	}

	@Override
	public default LongToIntMorphism compose(java.util.function.LongToDoubleFunction before) {
		return s -> applyAsInt(before.applyAsDouble(s));
	}

	@Override
	public default DoubleToIntMorphism compose(java.util.function.DoubleUnaryOperator before) {
		return s -> applyAsInt(before.applyAsDouble(s));
	}

	@Override
	public default <S> ObjToIntMorphism<S> compose(java.util.function.ToDoubleFunction<? super S> before) {
		return s -> applyAsInt(before.applyAsDouble(s));
	}
}
