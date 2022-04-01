package com._31536000.math.algebraic.morphism;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対してintからlongへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToLongMorphism
		extends IntToObjMorphism<Long>, ObjToLongMorphism<Integer>, java.util.function.IntToLongFunction {
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
	public default IntMorphism andThen(java.util.function.LongToIntFunction after) {
		return s -> after.applyAsInt(applyAsLong(s));
	}

	@Override
	public default IntToLongMorphism andThen(java.util.function.LongUnaryOperator after) {
		return s -> after.applyAsLong(applyAsLong(s));
	}

	@Override
	public default IntToDoubleMorphism andThen(java.util.function.LongToDoubleFunction after) {
		return s -> after.applyAsDouble(applyAsLong(s));
	}

	@Override
	public default <T> IntToObjMorphism<T> andThen(java.util.function.LongFunction<? extends T> after) {
		return s -> after.apply(applyAsLong(s));
	}

	@Override
	public default IntToLongMorphism compose(java.util.function.IntUnaryOperator before) {
		return s -> applyAsLong(before.applyAsInt(s));
	}

	@Override
	public default LongMorphism compose(java.util.function.LongToIntFunction before) {
		return s -> applyAsLong(before.applyAsInt(s));
	}

	@Override
	public default DoubleToLongMorphism compose(java.util.function.DoubleToIntFunction before) {
		return s -> applyAsLong(before.applyAsInt(s));
	}

	@Override
	public default <S> ObjToLongMorphism<S> compose(java.util.function.ToIntFunction<? super S> before) {
		return s -> applyAsLong(before.applyAsInt(s));
	}
}
