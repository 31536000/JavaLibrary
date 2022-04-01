package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Associative;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongMorphism
		extends LongToObjMorphism<Long>, ObjToLongMorphism<Long>, java.util.function.LongUnaryOperator {
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
	public default LongToIntMorphism andThen(java.util.function.LongToIntFunction after) {
		return s -> after.applyAsInt(applyAsLong(s));
	}

	@Override
	public default LongMorphism andThen(java.util.function.LongUnaryOperator after) {
		return s -> after.applyAsLong(applyAsLong(s));
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(LongMorphism)
	 */
	public default LongMorphism andThen(LongMorphism after) {
		return s -> after.applyAsLong(applyAsLong(s));
	}

	@Override
	public default LongToDoubleMorphism andThen(java.util.function.LongToDoubleFunction after) {
		return s -> after.applyAsDouble(applyAsLong(s));
	}

	@Override
	public default <T> LongToObjMorphism<T> andThen(java.util.function.LongFunction<? extends T> after) {
		return s -> after.apply(applyAsLong(s));
	}

	@Override
	public default IntToLongMorphism compose(java.util.function.IntToLongFunction before) {
		return s -> applyAsLong(before.applyAsLong(s));
	}

	@Override
	public default LongMorphism compose(java.util.function.LongUnaryOperator before) {
		return s -> applyAsLong(before.applyAsLong(s));
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see #andThen(LongMorphism)
	 */
	public default LongMorphism compose(LongMorphism before) {
		return s -> applyAsLong(before.applyAsLong(s));
	}

	@Override
	public default DoubleToLongMorphism compose(java.util.function.DoubleToLongFunction before) {
		return s -> applyAsLong(before.applyAsLong(s));
	}

	@Override
	public default <S> ObjToLongMorphism<S> compose(java.util.function.ToLongFunction<? super S> before) {
		return s -> applyAsLong(before.applyAsLong(s));
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * @return 関数の合成を行う二項演算
	 */
	public static Associative<LongMorphism> composition() {
		return (t, u) -> t.andThen(u);
	}
}
