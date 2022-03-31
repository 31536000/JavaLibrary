package com._31536000.math.algebraic.group;

/**
 * 演算が可換モノイドであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeMonoid}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongCommutativeMonoid extends CommutativeMonoid<Long>, LongMonoid, LongCommutative {

	@Override
	default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}

}
