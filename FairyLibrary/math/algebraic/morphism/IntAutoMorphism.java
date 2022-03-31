package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Group;

/**
 * 演算が自己同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link AutoMorphism}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 */
public interface IntAutoMorphism extends AutoMorphism<Integer>, IntEndoMorphism, IntIsoMorphism {

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
	public IntAutoMorphism inverse();

	@Override
	public default IntAutoMorphism section() {
		return inverse();
	}

	@Override
	public default IntAutoMorphism retraction() {
		return inverse();
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see {@link #compose(Function)}
	 */
	public default IntAutoMorphism andThen(IntAutoMorphism after) {
		IntAutoMorphism now = this;
		return new IntAutoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return after.applyAsInt(now.applyAsInt(t));
			}

			@Override
			public IntAutoMorphism inverse() {
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
	 * @see {@link #andThen(Function)}
	 */
	public default IntAutoMorphism compose(IntAutoMorphism before) {
		IntAutoMorphism now = this;
		return new IntAutoMorphism() {
			@Override
			public int applyAsInt(int t) {
				return now.applyAsInt(before.applyAsInt(t));
			}

			@Override
			public IntAutoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	/**
	 * 恒等写像を返します。
	 * @return 恒等写像
	 */
	public static IntAutoMorphism identity() {
		return new IntAutoMorphism() {

			@Override
			public int applyAsInt(int t) {
				return t;
			}

			@Override
			public IntAutoMorphism inverse() {
				return this;
			}

		};
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * @return 関数の合成を行う二項演算
	 */
	public static Group<IntAutoMorphism> composition() {
		return new Group<IntAutoMorphism>() {
			@Override
			public IntAutoMorphism apply(IntAutoMorphism t, IntAutoMorphism u) {
				return t.andThen(u);
			}

			@Override
			public IntAutoMorphism identity() {
				return IntAutoMorphism.identity();
			}

			@Override
			public IntAutoMorphism inverse(IntAutoMorphism element) {
				return element.inverse();
			}
		};
	}
}
