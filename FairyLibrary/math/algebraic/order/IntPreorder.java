package math.algebraic.order;


/**
 * 演算が前順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Preorder}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntPreorder extends Preorder<Integer>, IntReflexivity, IntTransitivity {

}
