package math.algebraic.group;

import java.util.function.BinaryOperator;

/**
 * 演算が単位元を持つことを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Unital<T> extends BinaryOperator<T>{
	/**
	 * 単位元を返します。
	 * @return 単位元
	 */
	public T identity();
}
