package com._31536000.math.algebraic.order;

/**
 * 演算が同値関係であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link EquivalenceRelation}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongEquivalenceRelation extends EquivalenceRelation<Long>, LongPreorder, LongSymmetry{

}
