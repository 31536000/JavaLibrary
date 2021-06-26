package math.algebraic.group;

import java.util.function.IntBinaryOperator;

/**
 * 演算が単位元を持つことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Unital}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntUnital extends Unital<Integer>, IntBinaryOperator{
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
	@Override
	public default Integer identity() {
		return identityAsInt();
	}
	/**
	 * 単位元を返します。
	 * @return 単位元
	 */
	public int identityAsInt();
}
