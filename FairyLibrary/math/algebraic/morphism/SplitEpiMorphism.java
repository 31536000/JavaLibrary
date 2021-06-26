package math.algebraic.morphism;

public interface SplitEpiMorphism<S, T> extends EpiMorphism<S, T>{
	/**
	 * 右逆射を返します。
	 * @return 右逆射
	 */
	public Morphism<T, S> section();
}
