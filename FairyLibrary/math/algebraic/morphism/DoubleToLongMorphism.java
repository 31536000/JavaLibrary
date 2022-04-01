package com._31536000.math.algebraic.morphism;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対して、doubleからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleToLongMorphism
		extends DoubleToObjMorphism<Long>, ObjToLongMorphism<Double>, java.util.function.DoubleToLongFunction {
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
	public default DoubleToIntMorphism andThen(java.util.function.LongToIntFunction after) {
		return s -> after.applyAsInt(applyAsLong(s));
	}

	@Override
	public default DoubleToLongMorphism andThen(java.util.function.LongUnaryOperator after) {
		return s -> after.applyAsLong(applyAsLong(s));
	}

	@Override
	public default DoubleMorphism andThen(java.util.function.LongToDoubleFunction after) {
		return s -> after.applyAsDouble(applyAsLong(s));
	}

	@Override
	public default <T> DoubleToObjMorphism<T> andThen(java.util.function.LongFunction<? extends T> after) {
		return s -> after.apply(applyAsLong(s));
	}

	@Override
	public default IntToLongMorphism compose(java.util.function.IntToDoubleFunction before) {
		return s -> applyAsLong(before.applyAsDouble(s));
	}

	@Override
	public default LongMorphism compose(java.util.function.LongToDoubleFunction before) {
		return s -> applyAsLong(before.applyAsDouble(s));
	}

	@Override
	public default DoubleToLongMorphism compose(java.util.function.DoubleUnaryOperator before) {
		return s -> applyAsLong(before.applyAsDouble(s));
	}

	@Override
	public default <S> ObjToLongMorphism<S> compose(java.util.function.ToDoubleFunction<? super S> before) {
		return s -> applyAsLong(before.applyAsDouble(s));
	}
}
