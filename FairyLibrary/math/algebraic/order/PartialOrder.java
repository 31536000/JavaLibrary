package com._31536000.math.algebraic.order;


/**
 * 演算が半順序であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface PartialOrder<T> extends Preorder<T>, Antisymmetry<T> {

}
