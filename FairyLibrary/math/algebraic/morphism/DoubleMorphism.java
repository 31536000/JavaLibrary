package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Associative;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Morphism}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleMorphism
		extends DoubleToObjMorphism<Double>, ObjToDoubleMorphism<Double>, java.util.function.DoubleUnaryOperator {
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
	public default DoubleToIntMorphism andThen(java.util.function.DoubleToIntFunction after) {
		return s -> after.applyAsInt(applyAsDouble(s));
	}

	@Override
	public default DoubleToLongMorphism andThen(java.util.function.DoubleToLongFunction after) {
		return s -> after.applyAsLong(applyAsDouble(s));
	}

	@Override
	public default DoubleMorphism andThen(java.util.function.DoubleUnaryOperator after) {
		return s -> after.applyAsDouble(applyAsDouble(s));
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(DoubleMorphism)
	 */
	public default DoubleMorphism andThen(DoubleMorphism after) {
		return s -> after.applyAsDouble(applyAsDouble(s));
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param <T> after関数および合成関数の出力の型
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 */
	public default <T> DoubleToObjMorphism<T> andThen(DoubleToObjMorphism<? extends T> after) {
		return s -> after.apply(applyAsDouble(s));
	}

	@Override
	public default IntToDoubleMorphism compose(java.util.function.IntToDoubleFunction before) {
		return s -> applyAsDouble(before.applyAsDouble(s));
	}

	@Override
	public default LongToDoubleMorphism compose(java.util.function.LongToDoubleFunction before) {
		return s -> applyAsDouble(before.applyAsDouble(s));
	}

	@Override
	public default DoubleMorphism compose(java.util.function.DoubleUnaryOperator before) {
		return s -> applyAsDouble(before.applyAsDouble(s));
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see #andThen(DoubleMorphism)
	 */
	public default DoubleMorphism compose(DoubleMorphism before) {
		return s -> applyAsDouble(before.applyAsDouble(s));
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param <S> before関数および合成関数の入力の型
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 */
	public default <S> ObjToDoubleMorphism<S> compose(ObjToDoubleMorphism<? super S> before) {
		return s -> applyAsDouble(before.applyAsDouble(s));
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * @return 関数の合成を行う二項演算
	 */
	public static Associative<DoubleMorphism> composition() {
		return (t, u) -> t.andThen(u);
	}
}
