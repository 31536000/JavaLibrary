package com._31536000.math.algebraic.ring;

import com._31536000.math.algebraic.group.IntAbelian;
import com._31536000.math.algebraic.group.IntCommutativeMonoid;

/**
 * 演算が可換環に属することを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeRing}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 *
 * @param <A> 和に関する演算
 * @param <M> 積に関する演算
 */
public interface IntCommutativeRing<A extends IntAbelian, M extends IntCommutativeMonoid> 
	extends CommutativeRing<Integer, A, M>, IntRing<A, M>, IntCommutativeSemiring<A, M>{

}
