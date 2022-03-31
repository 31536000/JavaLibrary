package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Monoid;

/**
 * 演算が自己射であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 定義域
 */
public interface EndoMorphism<T> extends Morphism<T, T>, java.util.function.UnaryOperator<T> {

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	public default EndoMorphism<T> andThen(EndoMorphism<T> after) {
		return s -> after.apply(apply(s));
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see {@link #andThen(Function)}
	 */
	public default EndoMorphism<T> compose(EndoMorphism<T> before) {
		return s -> apply(before.apply(s));
	}

	/**
	 * 恒等写像を返します。
	 * @return 恒等写像
	 */
	public static <T> EndoMorphism<T> identity() {
		return t -> t;
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * 二つの写像f, gを合成する演算apply(g, f)は、まず写像fを適用し、次にgを適用する写像となります(すなわちf.andThen(g)と一致します)。
	 * @return 関数の合成を行う二項演算
	 */
	public static <T> Monoid<EndoMorphism<T>> composition() {
		return new Monoid<EndoMorphism<T>>() {
			@Override
			public EndoMorphism<T> apply(EndoMorphism<T> t, EndoMorphism<T> u) {
				return u.andThen(t);
			}

			@Override
			public EndoMorphism<T> identity() {
				return EndoMorphism.identity();
			}
		};
	}
}
