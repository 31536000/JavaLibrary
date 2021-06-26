package math.algebraic.order;

/**
 * 演算が反射律を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Reflexivity}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntReflexivity extends Reflexivity<Integer>, IntOrder{

}
