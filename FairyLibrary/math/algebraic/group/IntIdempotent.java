package math.algebraic.group;

import java.util.function.IntBinaryOperator;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Idempotent}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntIdempotent extends Idempotent<Integer>, IntBinaryOperator{
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
}
