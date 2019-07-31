package math.group;

/**
 * 演算がアーベル群(可換群)であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Abelian<T> extends Group<T>, CommutativeMonoid<T> {

}
