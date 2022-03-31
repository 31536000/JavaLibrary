package com._31536000.math.algebraic.order;


/**
 * 演算が半順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PartialOrder}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntPartialOrder extends PartialOrder<Integer>, IntPreorder, IntAntisymmetry {

}
