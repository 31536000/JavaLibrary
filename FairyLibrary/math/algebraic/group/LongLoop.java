package com._31536000.math.algebraic.group;

/**
 * 演算が擬群であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Loop}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongLoop extends Loop<Long>, LongQuasiGroup, LongUnital{

}
