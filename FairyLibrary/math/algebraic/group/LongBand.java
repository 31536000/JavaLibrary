package math.algebraic.group;


/**
 * 演算が帯であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Band}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongBand extends Band<Long>, LongAssociative, LongIdempotent {
	@Override
	default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}
}
