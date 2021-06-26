package math.algebraic.group;


/**
 * 演算が帯であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Band}に対してプリミティブ型特殊化(double向け)を行ったものです。
 * @author 31536000
 */
public interface DoubleBand extends Band<Double>, DoubleAssociative, DoubleIdempotent {
	@Override
	public default Double apply(Double t, Double u) {
		return applyAsDouble(t, u);
	}
}
