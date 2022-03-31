package com._31536000.math.algebraic.order;

/**
 * 演算が弱順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link WeakOrder}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongWeakOrder extends WeakOrder<Long>, LongPreorder, LongConnexity{

}
