package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Associative;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 */
public interface IntMorphism
		extends IntToObjMorphism<Integer>, ObjToIntMorphism<Integer>, java.util.function.IntUnaryOperator {
	@Override
	public default Integer apply(Integer operand) {
		return applyAsInt((int) operand);
	}

	@Override
	public default Integer apply(int operand) {
		return applyAsInt(operand);
	}

	@Override
	public default int applyAsInt(Integer operand) {
		return applyAsInt((int) operand);
	}

	@Override
	public default IntMorphism andThen(java.util.function.IntUnaryOperator after) {
		return s -> after.applyAsInt(applyAsInt(s));
	}

	@Override
	public default IntToLongMorphism andThen(java.util.function.IntToLongFunction after) {
		return s -> after.applyAsLong(applyAsInt(s));
	}

	@Override
	public default IntToDoubleMorphism andThen(java.util.function.IntToDoubleFunction after) {
		return s -> after.applyAsDouble(applyAsInt(s));
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(IntMorphism)
	 */
	public default IntMorphism andThen(IntMorphism after) {
		return s -> after.applyAsInt(applyAsInt(s));
	}

	@Override
	public default <T> IntToObjMorphism<T> andThen(java.util.function.IntFunction<? extends T> after) {
		return s -> after.apply(applyAsInt(s));
	}

	@Override
	public default IntMorphism compose(java.util.function.IntUnaryOperator before) {
		return s -> applyAsInt(before.applyAsInt(s));
	}

	@Override
	public default LongToIntMorphism compose(java.util.function.LongToIntFunction before) {
		return s -> applyAsInt(before.applyAsInt(s));
	}

	@Override
	public default DoubleToIntMorphism compose(java.util.function.DoubleToIntFunction before) {
		return s -> applyAsInt(before.applyAsInt(s));
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see #andThen(IntMorphism)
	 */
	public default IntMorphism compose(IntMorphism before) {
		return s -> applyAsInt(before.applyAsInt(s));
	}

	@Override
	public default <S> ObjToIntMorphism<S> compose(java.util.function.ToIntFunction<? super S> before) {
		return s -> applyAsInt(before.applyAsInt(s));
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * @return 関数の合成を行う二項演算
	 */
	public static Associative<IntMorphism> composition() {
		return (t, u) -> t.andThen(u);
	}
}
