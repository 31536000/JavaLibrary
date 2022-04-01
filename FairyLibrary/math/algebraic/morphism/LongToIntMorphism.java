package com._31536000.math.algebraic.morphism;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対して、longからintへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface LongToIntMorphism
		extends LongToObjMorphism<Integer>, ObjToIntMorphism<Long>, java.util.function.LongToIntFunction {
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
	public default LongToIntMorphism andThen(java.util.function.IntUnaryOperator after) {
		return s -> after.applyAsInt(applyAsInt(s));
	}

	@Override
	public default LongMorphism andThen(java.util.function.IntToLongFunction after) {
		return s -> after.applyAsLong(applyAsInt(s));
	}

	@Override
	public default LongToDoubleMorphism andThen(java.util.function.IntToDoubleFunction after) {
		return s -> after.applyAsDouble(applyAsInt(s));
	}

	@Override
	public default <T> LongToObjMorphism<T> andThen(java.util.function.IntFunction<? extends T> after) {
		return s -> after.apply(applyAsInt(s));
	}

	@Override
	public default IntMorphism compose(java.util.function.IntToLongFunction before) {
		return s -> applyAsInt(before.applyAsLong(s));
	}

	@Override
	public default LongToIntMorphism compose(java.util.function.LongUnaryOperator before) {
		return s -> applyAsInt(before.applyAsLong(s));
	}

	@Override
	public default DoubleToIntMorphism compose(java.util.function.DoubleToLongFunction before) {
		return s -> applyAsInt(before.applyAsLong(s));
	}

	@Override
	public default <S> ObjToIntMorphism<S> compose(java.util.function.ToLongFunction<? super S> before) {
		return s -> applyAsInt(before.applyAsLong(s));
	}
}
