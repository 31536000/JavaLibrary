/**
 * 域を扱うためのインターフェース群を提供します。
 * <p>
 * このパッケージでは、域を扱うためのインターフェース群及びユーティリティクラスが提供されます。
 * 一般に、このパッケージで提供されるインターフェースは環に加えて整除の概念などを加えたものになります。
 * </p>
 * <p>
 * 域では、整除関係を定義します。
 * aがbを整除するとは、ax = bとなるような元xが存在することを指します。
 * このような元は存在するとは限りませんが、存在するならば一意です。
 * </p>
 * <p>
 * 全ての域は{@link com._31536000.math.algebraic.ring 環}でもあります。本パッケージで定義されるインターフェースを継承する際には、そちらも参照してください。
 * </p>
 * @author 31536000
 */
package com._31536000.math.algebraic.domain;