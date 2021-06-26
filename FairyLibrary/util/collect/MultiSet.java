package util.collect;


/**
 * 多重集合を管理するクラスです。
 * @author 31536000
 *
 * @param <E> この多重集合で保持される要素の型
 */
public interface MultiSet<E> extends java.util.Collection<E>{
	public static interface Entry<E> {
		/**
		 * この要素が入っている個数を返します。
		 * @return 個数
		 */
		public int getCount();
		/**
		 * 要素を返します。
		 * @return 要素
		 */
		public E getElement();
	}
	/**
	 * 要素を一つ、この多重集合に追加します。
	 * この関数はadd(element, 1)と同じです。
	 * @param element 追加する要素
	 * @return true
	 */
	@Override
	public default boolean add(E element) {
		add(element, 1);
		return true;
	}
	/**
	 * 要素を指定した個数だけこの多重集合に追加します。
	 * @param element 追加する要素
	 * @param occurrences 追加する個数
	 * @return この操作以前に要素が入っていた個数
	 * @throws IllegalArgumentException occurrences < 0 || count(element) + occurrences >= Integer.MAX_VALUE
	 * @throws NullPointerException 指定された要素がnullであり、この多重集合がnull要素を許可しない場合
	 */
	public int add(E element, int occurrences);
	/**
	 * 指定した要素がこの多重集合に何個含まれているかを返します。
	 * @param element 調べる要素
	 * @return 要素の個数
	 */
	public int count(Object element);
	/**
	 * この多重集合に含まれる要素のSetビューを返します。
	 * セットは多重集合と連動しているので、多重集合に対する変更はセットに反映され、また、セットに対する変更は多重集合に反映されます。
	 * セットの反復処理中に多重集合が変更された場合、反復処理の結果は定義されていません(イテレータ自身のremoveオペレーションを除く)。
	 * セットは要素の削除をサポートします。
	 * Iterator.remove、Set.remove、removeAll、retainAll、およびclearオペレーションで対応する要素を多重集合から全て削除します。
	 * addまたはaddAllオペレーションはサポートしていません。
	 * @return 多重集合に含まれている要素のセット・ビュー
	 */
	public java.util.Set<? extends E> keySet();
	/**
	 * この多重集合に含まれる要素と個数の組のSetビューを返します。
	 * セットは多重集合と連動しているので、多重集合に対する変更はセットに反映され、また、セットに対する変更は多重集合に反映されます。
	 * セットの反復処理中に多重集合が変更された場合、反復処理の結果は定義されていません(イテレータ自身のremoveオペレーション、またはイテレータにより返されるマルチセット・エントリに対するsetValueオペレーションを除く)。
	 * セットは要素の削除をサポートします。
	 * Iterator.remove、Set.remove、removeAll、retainAll、およびclearオペレーションで対応する要素を多重集合から全て削除します。
	 * addまたはaddAllオペレーションはサポートしていません。
	 * @return 多重集合内に保持されている要素と個数の組のセット・ビュー
	 */
	public java.util.Set<? extends Entry<E>> entrySet();
	/**
	 * 要素を一つ、この多重集合から削除します。
	 * この関数はremove(element, 1)と同じです。
	 * @param element 削除する要素
	 * @return この操作によって多重集合が変更されたならばtrue
	 */
	@Override
	public default boolean remove(Object element) {
		return remove(element, 1) != 0;
	}
	/**
	 * 要素を指定した個数だけこの多重集合から削除します。
	 * @param element 削除する要素
	 * @param occurrences 削除する個数
	 * @return この操作以前に要素が入っていた個数
	 * @throws IllegalArgumentException occurrences < 0
	 */
	public int remove(Object element, int occurrences);
	/**
	 * 指定した要素をこの多重集合から全て削除します。
	 * @param element 削除する要素
	 * @return この操作によって多重集合が変更されたならばtrue
	 */
	public boolean removeAll(Object element);
	/**
	 * この集合に指定された要素を指定された個数だけ入っている状態にします。
	 * @param element 集合に代入する要素
	 * @param count 設定する個数
	 * @return この操作以前に要素が入っていた個数
	 * @throws IllegalArgumentException count < 0
	 * @throws NullPointerException 指定された要素がnullであり、この多重集合がnull要素を許可しない場合
	 */
	public int setCount(E element, int count);
	/**
	 * この集合に指定された要素が丁度oldCount個入っているとき、要素をnewCount個だけ入っている状態にします。
	 * @param element 集合に代入する要素
	 * @param oldCount この操作以前に要素が入っていた個数と予想する値
	 * @param newCount 置き換えたい値
	 * @return この操作によって集合が変更された場合
	 * @throws IllegalArgumentException oldCount < 0 || newCount < 0
	 * @throws NullPointerException 指定された要素がnullであり、この多重集合がnull要素を許可しない場合
	 */
	public boolean setCount(E element, int oldCount, int newCount);

}
