package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Group;

/**
 * 演算が自己同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link AutoMorphism}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 *
 */
public interface DoubleAutoMorphism extends AutoMorphism<Double>, DoubleEndoMorphism, DoubleIsoMorphism {

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
	public DoubleAutoMorphism inverse();

	@Override
	public default DoubleAutoMorphism section() {
		return inverse();
	}

	@Override
	public default DoubleAutoMorphism retraction() {
		return inverse();
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(DoubleAutoMorphism)
	 */
	public default DoubleAutoMorphism andThen(DoubleAutoMorphism after) {
		DoubleAutoMorphism now = this;
		return new DoubleAutoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return after.applyAsDouble(now.applyAsDouble(t));
			}

			@Override
			public DoubleAutoMorphism inverse() {
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
	 * @see #andThen(DoubleAutoMorphism)
	 */
	public default DoubleAutoMorphism compose(DoubleAutoMorphism before) {
		DoubleAutoMorphism now = this;
		return new DoubleAutoMorphism() {
			@Override
			public double applyAsDouble(double t) {
				return now.applyAsDouble(before.applyAsDouble(t));
			}

			@Override
			public DoubleAutoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	/**
	 * 恒等写像を返します。
	 * @return 恒等写像
	 */
	public static DoubleAutoMorphism identity() {
		return new DoubleAutoMorphism() {

			@Override
			public double applyAsDouble(double t) {
				return t;
			}

			@Override
			public DoubleAutoMorphism inverse() {
				return this;
			}

		};
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * @return 関数の合成を行う二項演算
	 */
	public static Group<DoubleAutoMorphism> composition() {
		return new Group<DoubleAutoMorphism>() {
			@Override
			public DoubleAutoMorphism apply(DoubleAutoMorphism t, DoubleAutoMorphism u) {
				return t.andThen(u);
			}

			@Override
			public DoubleAutoMorphism identity() {
				return DoubleAutoMorphism.identity();
			}

			@Override
			public DoubleAutoMorphism inverse(DoubleAutoMorphism element) {
				return element.inverse();
			}
		};
	}
}
