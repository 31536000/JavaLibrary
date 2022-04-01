package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Group;

/**
 * 演算が自己同型射であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link AutoMorphism}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 *
 */
public interface LongAutoMorphism extends AutoMorphism<Long>, LongEndoMorphism, LongIsoMorphism {

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
	public LongAutoMorphism inverse();

	@Override
	public default LongAutoMorphism section() {
		return inverse();
	}

	@Override
	public default LongAutoMorphism retraction() {
		return inverse();
	}

	/**
	 * まず入力にこの関数を適用し、次に結果に関数afterを適用する合成関数を返します。
	 * いずれかの関数の評価時に例外がスローされた場合、その例外は合成関数の呼出し元に中継されます。
	 * @param after この関数を適用した後で適用する関数
	 * @return まずこの関数を適用し、次にafter関数を適用する合成関数
	 * @exception NullPointerException afterがnullの場合
	 * @see #compose(LongAutoMorphism)
	 */
	public default LongAutoMorphism andThen(LongAutoMorphism after) {
		LongAutoMorphism now = this;
		return new LongAutoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return after.applyAsLong(now.applyAsLong(t));
			}

			@Override
			public LongAutoMorphism inverse() {
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
	 * @see #andThen(LongAutoMorphism)
	 */
	public default LongAutoMorphism compose(LongAutoMorphism before) {
		LongAutoMorphism now = this;
		return new LongAutoMorphism() {
			@Override
			public long applyAsLong(long t) {
				return now.applyAsLong(before.applyAsLong(t));
			}

			@Override
			public LongAutoMorphism inverse() {
				return now.inverse().andThen(before.inverse());
			}

		};
	}

	/**
	 * 恒等写像を返します。
	 * @return 恒等写像
	 */
	public static LongAutoMorphism identity() {
		return new LongAutoMorphism() {

			@Override
			public long applyAsLong(long t) {
				return t;
			}

			@Override
			public LongAutoMorphism inverse() {
				return this;
			}

		};
	}

	/**
	 * 関数の合成からなる二項演算を返します。
	 * @return 関数の合成を行う二項演算
	 */
	public static Group<LongAutoMorphism> composition() {
		return new Group<LongAutoMorphism>() {
			@Override
			public LongAutoMorphism apply(LongAutoMorphism t, LongAutoMorphism u) {
				return t.andThen(u);
			}

			@Override
			public LongAutoMorphism identity() {
				return LongAutoMorphism.identity();
			}

			@Override
			public LongAutoMorphism inverse(LongAutoMorphism element) {
				return element.inverse();
			}
		};
	}
}
