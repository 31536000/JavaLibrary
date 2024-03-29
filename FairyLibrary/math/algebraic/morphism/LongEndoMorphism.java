package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Monoid;

/**
 * 演算が自己射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EndoMorphism}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongEndoMorphism extends LongMorphism, EndoMorphism<Long>, java.util.function.LongUnaryOperator {
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

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(LongEndoMorphism)
	 */
	public default LongEndoMorphism andThen(LongEndoMorphism after) {
		return s -> after.applyAsLong(applyAsLong(s));
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see #andThen(LongEndoMorphism)
	 */
	public default LongEndoMorphism compose(LongEndoMorphism before) {
		return s -> applyAsLong(before.applyAsLong(s));
	}

	/**
	 * 恒等写像を返します。
	 * @return 恒等写像
	 */
	public static LongEndoMorphism identity() {
		return t -> t;
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * 二つの写像f, gを合成する演算apply(g, f)は、まず写像fを適用し、次にgを適用する写像となります(すなわちf.andThen(g)と一致します)。
	 * @return 関数の合成を行う二項演算
	 */
	public static Monoid<LongEndoMorphism> composition() {
		return new Monoid<LongEndoMorphism>() {
			@Override
			public LongEndoMorphism apply(LongEndoMorphism t, LongEndoMorphism u) {
				return u.andThen(t);
			}

			@Override
			public LongEndoMorphism identity() {
				return LongEndoMorphism.identity();
			}
		};
	}
}
