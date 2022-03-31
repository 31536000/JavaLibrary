package com._31536000.math.algebraic.group;

/**
 * 演算が準群であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link QuasiGroup}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongQuasiGroup extends QuasiGroup<Long>, LongInvertible{

}
