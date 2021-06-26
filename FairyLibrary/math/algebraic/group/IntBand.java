package math.algebraic.group;


/**
 * 演算が帯であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Band}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntBand extends Band<Integer>, IntAssociative, IntIdempotent {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
}
