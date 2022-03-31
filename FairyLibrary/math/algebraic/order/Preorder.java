package com._31536000.math.algebraic.order;


/**
 * 演算が前順序であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Preorder<T> extends Reflexivity<T>, Transitivity<T> {

}
