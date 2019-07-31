package math.group;

import java.util.function.BinaryOperator;

/**
 * 演算が交換法則を満たすことを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Commutative<T> extends BinaryOperator<T>{

}
