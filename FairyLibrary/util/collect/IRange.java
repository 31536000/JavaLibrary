package com._31536000.util.collect;

import com._31536000.math.algebraic.Operator;
import com._31536000.math.algebraic.group.Semilattice;
import com._31536000.math.algebraic.lattice.Lattice;

/**
 * 区間を管理するインターフェースです。
 * <br>nullは本クラスにおいて特別な意味を持ちます。
 * もし区間の左側のみがnullの場合、このnullは無限小としての意味を持ちます。
 * また、もし区間の右側のみがnullの場合、このnullは無限大としての意味を持ちます。
 * 空集合は(null, null)、全体集合は[null, null]を用います。
 * 全体集合[null, null]を除き、nullは要素ではないので開区間として用いられます。
 *
 * @author 31536000
 *
 * @param <E> 区間の型、nullは許容されない
 * @param <R> 演算を行うときの自身の型
 * @param <A> 和
 * @param <M> 積
 * @param <L> 束
 */
public interface IRange<E, R extends IRange<E, R, A, M, L>, A extends Semilattice<R>, M extends Semilattice<R>, L extends Lattice<R, A, M>> extends Operator<L> {

	/**
	 * 区間の左端の要素を返します。
	 * @return 左端
	 */
	public E lowerEndpoint();

	/**
	 * 区間が左端を含むか返します。
	 * @return 左端を含むなら{@link BoundType.CLOSED}, 含まないなら{@link BoundType.OPEN}
	 */
	public BoundType lowerBoundType();

	/**
	 * 区間の右端の要素を返します。
	 * @return 右端
	 */
	public E upperEndpoint();

	/**
	 * 区間が右端を含むか返します。
	 * @return 右端を含むなら{@link BoundType.CLOSED}, 含まないなら{@link BoundType.OPEN}
	 */
	public BoundType upperBoundType();

	/**
	 * この区間が空集合か判定します。
	 * @return 空集合ならばtrue
	 */
	public default boolean isEmpty() {
		return lowerEndpoint() == null && upperEndpoint() == null && lowerBoundType() == BoundType.OPEN && upperBoundType() == BoundType.OPEN;
	}

	/**
	 * 与えられた引数が区間の左側に位置するか判定します。
	 * これは区間内の任意の点xに対して value &lt; x を満たすことと同値です。
	 * @param value 調べる引数
	 * @return 区間の左側に位置するならtrue
	 */
	public boolean isLess(E value);

	/**
	 * 与えられた引数が区間の右側に位置するか判定します。
	 * これは区間内の任意の点xに対して value &gt; x を満たすことと同値です。
	 * @param value 調べる引数
	 * @return 区間の右側に位置するならtrue
	 */
	public boolean isGreater(E value);

	/**
	 * 与えられた引数が区間内に位置するか判定します。
	 * これは区間内にある点x, yが存在して x &lt;= value &lt;= y を満たすことと同値です。
	 * @param value 調べる引数
	 * @return 区間内に位置するならtrue
	 */
	public boolean contains(E value);

	/**
	 * 与えられた引数すべてが区間内に位置するか判定します。
	 * @param value 調べる要素
	 * @return 全ての要素が区間内に位置するならtrue
	 */
	public boolean containsAll(Iterable<? extends E> values);

	/**
	 * 与えられた区間がこの区間に内包されるか判定します。<br>
	 * 与えられた二つの区間が同一の場合でも内包されると判定します。
	 *
	 * @param other 包含しているか調べる区間
	 * @return 与えられた区間がこの区間に内包されるならtrue
	 */
	public boolean encloses(IRange<? extends E, ?, ?, ?, ?> other);

	/**
	 * 与えられた区間がこの区間と公差するか判定します。
	 * すなわち、2つの区間が共通部分を持つときにtrueを返します。
	 * @param value 調べる引数
	 * @return 区間が交差するならtrue
	 */
	public boolean isIntersect(IRange<? extends E, ?, ?, ?, ?> other);
	/**
	 * この区間との共通区間を返します。
	 * @param connectedRange 共通区間を求める区間
	 * @return 共通区間
	 */
	public IRange<? extends E, ? extends R, ? extends A, ? extends M, ? extends L> intersection(IRange<? extends E, ?, ?, ?, ?> connectedRange);

	/**
	 * 2つの区間を包含する最小の区間を返します。
	 * @param other 包含したい区間
	 * @return 2つの区間を包含する最小の区間
	 */
	public IRange<? extends E, ? extends R, ? extends A, ? extends M, ? extends L> span(IRange<? extends E, ?, ?, ?, ?> other);

	public IRange<? extends E, ? extends R, ? extends A, ? extends M, ? extends L> create(E lower, BoundType lowerBoundType, E upper, BoundType upperBoundType);
}
