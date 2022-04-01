package com._31536000.math.algebraic.morphism;

import com._31536000.math.algebraic.group.Associative;

/**
 * 演算が射であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <S> 始域
 * @param <T> 終域
 */
public interface Morphism<S, T> extends java.util.function.Function<S, T>{
	@Override
	public default <U> Morphism<S, U> andThen(java.util.function.Function<? super T, ? extends U> after) {
		return s -> after.apply(apply(s));
	}
	@Override
	public default <U> Morphism<U,T> compose(java.util.function.Function<? super U,? extends S> before) {
		return s -> apply(before.apply(s));
	}
	/**
	 * 関数の合成からなる二項演算を返します。
	 * @param <T> 定義域
	 * @return 関数の合成を行う二項演算
	 */
	public static <T> Associative<Morphism<? super T, T>> composition() {
		return (t, u) -> t.andThen(u);
	}
}
