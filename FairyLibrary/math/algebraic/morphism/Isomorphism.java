package math.algebraic.morphism;

public interface Isomorphism<S, T> extends Bimorphism<S, T>, SplitEpiMorphism<S, T>, SplitMonoMorphism<S, T> {
	/**
	 * 逆射を返します。
	 * @return 逆射
	 */
	public Isomorphism<T, S> inverse();

	@Override
	public default Isomorphism<T, S> section() {
		return inverse();
	}

	@Override
	public default Isomorphism<T, S> retraction() {
		return inverse();
	}
}
