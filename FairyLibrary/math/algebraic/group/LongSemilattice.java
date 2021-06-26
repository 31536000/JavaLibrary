package math.algebraic.group;


/**
 * 演算が半束であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semilattice}に対してプリミティブ型特殊化(long向け)を行ったものです。
 * @author 31536000
 */
public interface LongSemilattice extends Semilattice<Long>, LongBand, LongCommutativeMonoid {
	@Override
	default Long apply(Long t, Long u) {
		return applyAsLong(t, u);
	}
}
