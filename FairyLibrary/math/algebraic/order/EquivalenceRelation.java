package com._31536000.math.algebraic.order;

/**
 * 演算が同値関係であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface EquivalenceRelation<T> extends Preorder<T>, Symmetry<T>{

}
