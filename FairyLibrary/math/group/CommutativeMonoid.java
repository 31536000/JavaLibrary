package math.group;

/**
 * 演算が可換モノイドであることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface CommutativeMonoid<T> extends Monoid<T>, Commutative<T> {

}
