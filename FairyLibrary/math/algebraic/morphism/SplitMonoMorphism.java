package math.algebraic.morphism;

public interface SplitMonoMorphism<S, T> extends MonoMorphism<S, T> {
	/**
	 * 左逆射を返します。
	 * @return 左逆射
	 */
	public Morphism<T, S> retraction();
}
