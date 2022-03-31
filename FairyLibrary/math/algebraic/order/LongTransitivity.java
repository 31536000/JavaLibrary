package com._31536000.math.algebraic.order;

/**
 * 演算が推移律を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Transitivity}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongTransitivity extends Transitivity<Long>, LongOrder{

}
