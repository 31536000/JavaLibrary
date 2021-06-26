package math.algebraic.group;


/**
 * 演算が帯であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Band<T> extends Associative<T>, Idempotent<T> {

}
