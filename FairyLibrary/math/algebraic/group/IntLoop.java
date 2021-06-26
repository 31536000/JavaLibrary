package math.algebraic.group;

/**
 * 演算が擬群であることを示すために使用するマーカー・インターフェースです。
 * これは、{@link Loop}に対してプリミティブ型特殊化(int向け)を行ったものです。
 * @author 31536000
 */
public interface IntLoop extends Loop<Integer>, IntQuasiGroup, IntUnital{
	@Override
	public default Integer apply(Integer t, Integer u) {
		return applyAsInt(t, u);
	}
}
