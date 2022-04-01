package com._31536000.math.algebraic.morphism;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対して、intからdoubleへのプリミティブ特殊化を行ったものです。
 * @author 31536000
 *
 */
public interface IntToDoubleMorphism
		extends IntToObjMorphism<Double>, ObjToDoubleMorphism<Integer>, java.util.function.IntToDoubleFunction {
	@Override
	public default Double apply(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	public default Double apply(int operand) {
		return applyAsDouble(operand);
	}

	@Override
	public default double applyAsDouble(Integer operand) {
		return applyAsDouble((int) operand);
	}

	@Override
	public default IntMorphism andThen(java.util.function.DoubleToIntFunction after) {
		return s -> after.applyAsInt(applyAsDouble(s));
	}

	@Override
	public default IntToLongMorphism andThen(java.util.function.DoubleToLongFunction after) {
		return s -> after.applyAsLong(applyAsDouble(s));
	}

	@Override
	public default IntToDoubleMorphism andThen(java.util.function.DoubleUnaryOperator after) {
		return s -> after.applyAsDouble(applyAsDouble(s));
	}

	@Override
	public default <T> IntToObjMorphism<T> andThen(java.util.function.DoubleFunction<? extends T> after) {
		return s -> after.apply(applyAsDouble(s));
	}

	@Override
	public default IntToDoubleMorphism compose(java.util.function.IntUnaryOperator before) {
		return s -> applyAsDouble(before.applyAsInt(s));
	}

	@Override
	public default LongToDoubleMorphism compose(java.util.function.LongToIntFunction before) {
		return s -> applyAsDouble(before.applyAsInt(s));
	}

	@Override
	public default DoubleMorphism compose(java.util.function.DoubleToIntFunction before) {
		return s -> applyAsDouble(before.applyAsInt(s));
	}

	@Override
	public default <S> ObjToDoubleMorphism<S> compose(java.util.function.ToIntFunction<? super S> before) {
		return s -> applyAsDouble(before.applyAsInt(s));
	}
}
