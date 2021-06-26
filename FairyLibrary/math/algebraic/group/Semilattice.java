package math.algebraic.group;


/**
 * 演算が半束であることを示すために使用するマーカー・インターフェースです。
 * @author 31536000
 *
 * @param <T> 二項演算の型
 */
public interface Semilattice<T> extends Band<T>, CommutativeMonoid<T> {

}
