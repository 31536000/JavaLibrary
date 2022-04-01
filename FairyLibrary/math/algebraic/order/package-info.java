/**
 * 順序を扱うためのインターフェース群を提供します。
 * <p>
 * このパッケージでは、順序を扱うためのインターフェース群及びユーティリティクラスが提供されます。
 * このパッケージに属するインターフェースは全て{@link Order}を継承しています。
 * </p>
 * <p>
 * {@link Order}は比較関数{@link Order#compare compare}を持つ関数型インターフェースです。
 * この比較は、全順序においては{@link java.util.Comparator#compare Comparator.compare}と同じ働きをします。
 * </p>
 * <p>
 * このパッケージに属する全てのインターフェースは、{@link Order}に対して追加の性質を持つ形で定義されます。
 * 追加の性質として用意されているものは以下の五つです。
 * <ul>
 * <li>{@link Antisymmetry} 反対称律 任意の元x, yに対して x * y かつ y * x ならば x = y</li>
 * <li>{@link Connexity} 完全律 任意の元x, yに対して x * y が定義される</li>
 * <li>{@link Reflexivity} 反射律 任意の元xに対して x * x が定義される</li>
 * <li>{@link Symmetry} 対称律 任意の元x, yに対して x * y ならば y * x</li>
 * <li>{@link Transitivity} 推移律 任意の元x, y, zに対して x * y かつ y * z ならば x * z</li>
 * </ul>
 * @author 31536000
 *
 */
package com._31536000.math.algebraic.order;