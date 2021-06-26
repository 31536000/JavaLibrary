package math.algebraic.group;


/**
 * 演算が半束であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semilattice}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntSemilattice extends Semilattice<Integer>, IntBand, IntCommutativeMonoid {
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
}
