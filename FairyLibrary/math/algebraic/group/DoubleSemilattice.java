package math.algebraic.group;


/**
 * 演算が半束であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Semilattice}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleSemilattice extends Semilattice<Double>, DoubleBand, DoubleCommutativeMonoid {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
}
