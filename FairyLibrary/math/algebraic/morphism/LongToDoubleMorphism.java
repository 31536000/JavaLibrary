package com._31536000.math.algebraic.morphism;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対して、longからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToDoubleMorphism
		extends LongToObjMorphism<Double>, ObjToDoubleMorphism<Long>, java.util.function.LongToDoubleFunction {
	@Override
	public default Double apply(Long operand) {
		return applyAsDouble((long) operand);
	}

	@Override
	public default Double apply(long operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Long operand) {
		return applyAsDouble((long) operand);
	}

	@Override
	public default LongToIntMorphism andThen(java.util.function.DoubleToIntFunction after) {
		return s -> after.applyAsInt(applyAsDouble(s));
	}

	@Override
	public default LongMorphism andThen(java.util.function.DoubleToLongFunction after) {
		return s -> after.applyAsLong(applyAsDouble(s));
	}

	@Override
	public default LongToDoubleMorphism andThen(java.util.function.DoubleUnaryOperator after) {
		return s -> after.applyAsDouble(applyAsDouble(s));
	}

	@Override
	public default <T> LongToObjMorphism<T> andThen(java.util.function.DoubleFunction<? extends T> after) {
		return s -> after.apply(applyAsDouble(s));
	}

	@Override
	public default IntToDoubleMorphism compose(java.util.function.IntToLongFunction before) {
		return s -> applyAsDouble(before.applyAsLong(s));
	}

	@Override
	public default LongToDoubleMorphism compose(java.util.function.LongUnaryOperator before) {
		return s -> applyAsDouble(before.applyAsLong(s));
	}

	@Override
	public default DoubleMorphism compose(java.util.function.DoubleToLongFunction before) {
		return s -> applyAsDouble(before.applyAsLong(s));
	}

	@Override
	public default <S> ObjToDoubleMorphism<S> compose(java.util.function.ToLongFunction<? super S> before) {
		return s -> applyAsDouble(before.applyAsLong(s));
	}
}
