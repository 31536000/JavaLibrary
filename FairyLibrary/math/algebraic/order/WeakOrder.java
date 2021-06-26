package math.algebraic.order;

/**
 * 演算が弱順序であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface WeakOrder<T> extends Preorder<T>, Connexity<T>{
	
}
