package math.algebraic.group;

/**
 * 演算が可換モノイドであることを示すために使用するマーカー・インターフェースです。
 * これは、{@link CommutativeMonoid}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntCommutativeMonoid extends CommutativeMonoid<Integer>, IntMonoid, IntCommutative {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
}
