package math.algebraic.group;

import java.util.function.BinaryOperator;

/**
 * 演算が冪等性を満たすことを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Idempotent<T> extends BinaryOperator<T>{

}
