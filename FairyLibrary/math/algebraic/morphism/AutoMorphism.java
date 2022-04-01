package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Group;

/**
 * 演算が自己同型射であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 定義域
 */
public interface AutoMorphism<T> extends EndoMorphism<T>, IsoMorphism<T, T> {
	@Override
	public AutoMorphism<T> inverse();

	@Override
	public default AutoMorphism<T> section() {
		return inverse();
	}

	@Override
	public default AutoMorphism<T> retraction() {
		return inverse();
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(AutoMorphism)
	 */
	public default AutoMorphism<T> andThen(AutoMorphism<T> after) {
		AutoMorphism<T> now = this;
		return new AutoMorphism<T>() {
			@Override
			public T apply(T t) {
				return after.apply(now.apply(t));
			}

			@Override
			public AutoMorphism<T> inverse() {
				return after.inverse().andThen(now.inverse());
			}

		};
	}

	/**
	 * まず入力に関数beforeを適用し、次に結果にこの関数を適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param before この関数を適用する前に適用する関数
	 * @return まずbefore関数を適用し、次にこの関数を適用する合成関数
	 * @exception NullPointerException beforeがnullの場合
	 * @see #andThen(AutoMorphism)
	 */
	public default AutoMorphism<T> compose(AutoMorphism<T> before) {
		AutoMorphism<T> now = this;
		return new AutoMorphism<T>() {
			@Override
			public T apply(T t) {
				return now.apply(before.apply(t));
			}

			@Override
			public AutoMorphism<T> inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	/**
	 * 恒等写像を返します。
	 * @param <T> 定義域
	 * @return 恒等写像
	 */
	public static <T> AutoMorphism<T> identity() {
		return new AutoMorphism<T>() {

			@Override
			public T apply(T t) {
				return t;
			}

			@Override
			public AutoMorphism<T> inverse() {
				return this;
			}

		};
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * @param <T> 定義域
	 * @return 関数の合成を行う二項演算
	 */
	public static <T> Group<AutoMorphism<T>> composition() {
		return new Group<AutoMorphism<T>>() {
			@Override
			public AutoMorphism<T> apply(AutoMorphism<T> t, AutoMorphism<T> u) {
				return t.andThen(u);
			}

			@Override
			public AutoMorphism<T> identity() {
				return AutoMorphism.identity();
			}

			@Override
			public AutoMorphism<T> inverse(AutoMorphism<T> element) {
				return element.inverse();
			}
		};
	}
}
