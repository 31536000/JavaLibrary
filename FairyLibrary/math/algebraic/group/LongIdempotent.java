package math.algebraic.group;

import java.util.function.LongBinaryOperator;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * これは、{@link Idempotent}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongIdempotent extends Idempotent<Long>, LongBinaryOperator{
	@Override
	public default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}
}
