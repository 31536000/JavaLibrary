/**
 * 環論を扱うためのインターフェース群を提供します。
 * <p>
 * このパッケージでは、環論を扱うためのインターフェース群及びユーティリティクラスが提供されます。
 * 一般に、このパッケージで提供されるインターフェースは二つの二項演算子に対する性質を付与するものとなります。
 * 二つの二項演算はそれぞれ加法、乗法と呼ばれ、getAddition()及びgetMultiplication()を用いてそれぞれの演算を取得できます。
 * 多くの場合、乗法は加法の上に分配的です。
 * </p>
 * <p>
 * getAddition()及びgetMultiplication()で提供される演算は、通常は新たなインスタンスを生成しません。
 * また、加法及び乗法によって定義される演算に対して直接的にアクセスする関数が定義されています(例えば、getAddition().apply(left, right)の代わりにplus(left, right)を用いることができます)。
 * </p>
 * <p>
 * 環が保持する二つの二項演算は群論によって規定されます。それぞれの演算を実装する際には{@link com._31536000.math.algebraic.group 群}も参照してください。
 * </p>
 * @author 31536000
 *
 */
package com._31536000.math.algebraic.ring;