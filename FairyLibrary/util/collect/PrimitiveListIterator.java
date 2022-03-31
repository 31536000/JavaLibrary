package com._31536000.util.collect;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;

/**
 * ListIteratorのプリミティブ特化に使用するベース・タイプ。
 * 特殊化されたサブタイプが、{@link PrimitiveListIterator.OfInt int}、{@link PrimitiveListIterator.OfLong long}および{@link PrimitiveListIterator.OfDouble double}値のために提供されています。<br>
 * {@link ListIterator#next()}および{@link ListIterator#forEachRemaining(java.util.function.Consumer)}などの特殊化されたサブタイプ・デフォルト実装は、
 * プリミティブ値を対応するラッパー・クラスのインスタンスにボクシングします。
 * このようなボクシングは、プリミティブ特殊化の使用時に得られるすべての利点を相殺する場合があります。
 * ボクシングを避けるには、対応するプリミティブ・ベース・メソッドを使用してください。
 * たとえば、{@link PrimitiveListIterator.OfInt#nextInt()}と{@link PrimitiveIterator.OfInt#forEachRemaining(java.util.function.IntConsumer)}を、
 * {@link PrimitiveIterator.OfInt#next()}と{@link PrimitiveListIterator.OfInt#forEachRemaining(java.util.function.Consumer)}よりも優先して使用することをお薦めします。<br>
 * ボクシング・ベース・メソッド{@link #next()}および{@link #forEachRemaining()}などを使用したプリミティブ値の反復は、値がボクシング値に変換されて検出される順序に影響しません。
 * @author 31536000
 *
 * @param <T> このPrimitiveListIteratorで返される要素の型。この型はプリミティブ型に対するラッパー型である必要があります(プリミティブint型に対するIntegerなど)。
 * @param <T_CONS> プリミティブ・コンシューマの型。この型はTに対する{@link Consumer}のプリミティブ特殊化である必要があります(Integerに対する{@link IntConsumer}など)。
 */
public interface PrimitiveListIterator<T, T_CONS> extends java.util.PrimitiveIterator<T, T_CONS>, java.util.ListIterator<T>{
	/**
	 * double値に特化されたリストイテレータ。
	 *
	 */
	static interface OfDouble extends java.util.PrimitiveIterator.OfDouble, PrimitiveListIterator<Double, java.util.function.DoubleConsumer> {

		@Override
		default void add(Double e) {
			addAsDouble(e);
		}
		/**
		 * 指定された要素をリストに挿入します(オプションの操作)。
		 * 要素は、{@link #next()}によって返される要素(ある場合)の直前、および{@link #previous()}によって返される要素(ある場合)の直後に挿入されます。
		 * リストに要素がない場合は、新しい要素がリストの唯一の要素になります。
		 * 新しい要素は、暗黙のカーソルの前に挿入されます。
		 * 後続のnextの呼出しは影響を受けず、後続のpreviousの呼出しは新しい要素を返します。
		 * この呼出しは、nextIndexまたはpreviousIndexの呼出しによって返される値を1増やします。
		 * @param e 挿入する要素
		 * @exception UnsupportedOperationException addメソッドがこのリスト・イテレータでサポートされない場合
		 * @exception IllegalArgumentException この要素のある特性が原因で、このリストにその要素を追加できない場合
		 */
		default void addAsDouble(double e) {
			throw new UnsupportedOperationException();
		}
		@Override
		default Double next() {
			return java.util.PrimitiveIterator.OfDouble.super.next();
		}
		@Override
		default Double previous() {
			return previousDouble();
		}
		/**
		 * 反復で前のdouble要素を返します。
		 * @return 反復での前のdouble要素
		 * @exception NoSuchElementException 反復処理で要素がない場合
		 */
		double previousDouble();
		/**
		 * {@link #next()}または{@link #previous()}から最後に返された要素を指定された要素で置き換えます(オプションの操作)。
		 * この呼出しは、前回のnextまたはpreviousの呼出し以降に{@link #remove()}と{@link #add(double)}のどちらも呼び出されていない場合にだけ行うことができます。
		 * @param e 挿入する要素
		 * @exception UnsupportedOperationException addメソッドがこのリスト・イテレータでサポートされない場合
		 * @exception IllegalArgumentException この要素のある特性が原因で、このリストにその要素を追加できない場合
		 */
		default void setAsDouble(double e) {
			throw new UnsupportedOperationException();
		}
		@Override
		default void set(Double e) {
			setAsDouble(e);
		}
		@Override
		public default void remove() {
			throw new UnsupportedOperationException();
		}
	}
	/**
	 * int値に特化されたリストイテレータ。
	 *
	 */
	static interface OfInt extends java.util.PrimitiveIterator.OfInt, PrimitiveListIterator<Integer, java.util.function.IntConsumer> {

		@Override
		default void add(Integer e) {
			addAsInt(e);
		}
		/**
		 * 指定された要素をリストに挿入します(オプションの操作)。
		 * 要素は、{@link #next()}によって返される要素(ある場合)の直前、および{@link #previous()}によって返される要素(ある場合)の直後に挿入されます。
		 * リストに要素がない場合は、新しい要素がリストの唯一の要素になります。
		 * 新しい要素は、暗黙のカーソルの前に挿入されます。
		 * 後続のnextの呼出しは影響を受けず、後続のpreviousの呼出しは新しい要素を返します。
		 * この呼出しは、nextIndexまたはpreviousIndexの呼出しによって返される値を1増やします。
		 * @param e 挿入する要素
		 * @exception UnsupportedOperationException addメソッドがこのリスト・イテレータでサポートされない場合
		 * @exception IllegalArgumentException この要素のある特性が原因で、このリストにその要素を追加できない場合
		 */
		default void addAsInt(int e) {
			throw new UnsupportedOperationException();
		}
		@Override
		default Integer next() {
			return java.util.PrimitiveIterator.OfInt.super.next();
		}
		@Override
		default Integer previous() {
			return previousInt();
		}
		/**
		 * 反復で前のint要素を返します。
		 * @return 反復での前のint要素
		 * @exception NoSuchElementException 反復処理で要素がない場合
		 */
		int previousInt();
		/**
		 * {@link #next()}または{@link #previous()}から最後に返された要素を指定された要素で置き換えます(オプションの操作)。
		 * この呼出しは、前回のnextまたはpreviousの呼出し以降に{@link #remove()}と{@link #add(int)}のどちらも呼び出されていない場合にだけ行うことができます。
		 * @param e 挿入する要素
		 * @exception UnsupportedOperationException addメソッドがこのリスト・イテレータでサポートされない場合
		 * @exception IllegalArgumentException この要素のある特性が原因で、このリストにその要素を追加できない場合
		 */
		default void setAsInt(int e) {
			throw new UnsupportedOperationException();
		}
		@Override
		default void set(Integer e) {
			setAsInt(e);
		}
		@Override
		public default void remove() {
			throw new UnsupportedOperationException();
		}
	}
	/**
	 * long値に特化されたリストイテレータ。
	 *
	 */
	static interface OfLong extends java.util.PrimitiveIterator.OfLong, PrimitiveListIterator<Long, java.util.function.LongConsumer> {

		@Override
		default void add(Long e) {
			addAsLong(e);
		}
		/**
		 * 指定された要素をリストに挿入します(オプションの操作)。
		 * 要素は、{@link #next()}によって返される要素(ある場合)の直前、および{@link #previous()}によって返される要素(ある場合)の直後に挿入されます。
		 * リストに要素がない場合は、新しい要素がリストの唯一の要素になります。
		 * 新しい要素は、暗黙のカーソルの前に挿入されます。
		 * 後続のnextの呼出しは影響を受けず、後続のpreviousの呼出しは新しい要素を返します。
		 * この呼出しは、nextIndexまたはpreviousIndexの呼出しによって返される値を1増やします。
		 * @param e 挿入する要素
		 * @exception UnsupportedOperationException addメソッドがこのリスト・イテレータでサポートされない場合
		 * @exception IllegalArgumentException この要素のある特性が原因で、このリストにその要素を追加できない場合
		 */
		default void addAsLong(long e) {
			throw new UnsupportedOperationException();
		}
		@Override
		default Long next() {
			return java.util.PrimitiveIterator.OfLong.super.next();
		}
		@Override
		default Long previous() {
			return previousLong();
		}
		/**
		 * 反復で前のlong要素を返します。
		 * @return 反復での前のlong要素
		 * @exception NoSuchElementException 反復処理で要素がない場合
		 */
		long previousLong();
		/**
		 * {@link #next()}または{@link #previous()}から最後に返された要素を指定された要素で置き換えます(オプションの操作)。
		 * この呼出しは、前回のnextまたはpreviousの呼出し以降に{@link #remove()}と{@link #add(long)}のどちらも呼び出されていない場合にだけ行うことができます。
		 * @param e 挿入する要素
		 * @exception UnsupportedOperationException addメソッドがこのリスト・イテレータでサポートされない場合
		 * @exception IllegalArgumentException この要素のある特性が原因で、このリストにその要素を追加できない場合
		 */
		default void setAsLong(long e) {
			throw new UnsupportedOperationException();
		}
		@Override
		default void set(Long e) {
			setAsLong(e);
		}
		@Override
		public default void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
