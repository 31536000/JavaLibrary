package math.algebraic.order;


/**
 * 演算が半順序であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link PartialOrder}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoublePartialOrder extends PartialOrder<Double>, DoublePreorder, DoubleAntisymmetry {

}
