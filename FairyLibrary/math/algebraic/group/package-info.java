/**
 * 群論を扱うためのインターフェース群を提供します。
 * <p>
 * このパッケージでは、群論を扱うためのインターフェース群及びユーティリティクラスが提供されます。
 * 一般に、このパッケージで提供されるインターフェースは二項演算子に対する性質を付与するものとなります。
 * 例えば、二項演算子が結合法則に従うことを表したいときは{@link Associative}を継承することで結合法則を表現できます。
 * </p>
 * <p>
 * このパッケージのインターフェースを用いる際には、以下の二点に気を付けてください。
 * <ol>
 * <li>新たな演算を作る際には、最も多くの性質を継承するインターフェースを継承する</li>
 * 例えば、二つのint型の値を受け取って、小さい方の値を返す関数minを考えます。
 * この関数は結合法則を持ち、単位元Integer.MIN_VALUEが存在して、可換であり、冪等性があります。
 * 従って、例えば次のように定義することができます。
 * <pre><code>
 * class IntMin implements IntAssociative, IntUnital, IntCommutative, IntIdempotent {
 * 	&#064;Override
 * 	public int applyAsInt(int left, int right) {
 * 		return Math.min(left, right);
 * 	}
 * 	&#064;Override
 * 	public int identityAsInt() {
 * 		return Integer.MIN_VALUE;
 * 	}
 * 	&#064;Override
 * 	public Integer apply(Integer left, Integer right) {
 * 		return applyAsInt(left, right);
 * 	}
 * }
 * </code></pre>
 * このように定義することもできますが、性質が複数ある場合は継承するインターフェースの数も増え、煩雑になってしまいます。
 * 今回の場合、この関数は半束であると言えば全ての性質を説明できるため、代わりに次のように定義することが推奨されます。
 * <pre><code>
 * class IntMin implements IntSemilattice {
 * 	&#064;Override
 * 	public int applyAsInt(int left, int right) {
 * 		return Math.min(left, right);
 * 	}
 * 	&#064;Override
 * 	public int identityAsInt() {
 * 		return Integer.MIN_VALUE;
 * 	}
 * }
 * </code></pre>
 * <li>演算を受け取って処理を行うクラスを作る際には、最も小さい性質を持つインターフェースの結合を受け取るようにする</li>
 * 例えば、リストを引数に取り、先頭から順に、奇数番目の項なら足し、偶数番目の項なら引いた値を返す関数を考えます。
 * この関数が必要とする演算は、結合法則、単位元、逆元を持っている必要があります。
 * この性質はすなわち群であるため、例えば次のように書くことができます。
 * <pre><code>
 * &lt;T&gt; T addSubSum(List&lt;T&gt; list, Group&lt;T&gt; operator) {
 * 	T ret = operator.identity();
 * 	boolean add = true;
 * 	for (T i : list) {
 * 		ret = operator.apply(ret, add ? i : operator.inverse(i));
 * 		add = !add;
 * 	}
 * 	return ret;
 * }
 * </code></pre>
 * ですが、この関数は結合法則、単位元、逆元を全て継承している演算であっても群を継承していないと引数に与えることができません。
 * 代わりに次のように定義することで、このような問題を防ぐことができます。
 * <pre><code>
 * &lt;T, O extends Associative&lt;T&gt; & Unital&lt;T&gt; & Invertible&lt;T&gt;&gt; T addSubSum(List&lt;T&gt; list, O operator) {
 * 	T ret = operator.identity();
 * 	boolean add = true;
 * 	for (T i : list) {
 * 		ret = operator.apply(ret, add ? i : operator.inverse(i));
 * 		add = !add;
 * 	}
 * 	return ret;
 * }
 * </code></pre>
 * </ol>
 * </p>
 * <p>
 * このパッケージにおいて、最小の性質をもつインターフェースは以下の5つになります。
 * <ul>
 * <li>{@link Associative} 結合法則 任意の元x, y, zに対して (x * y) * z = x * (y * z)</li>
 * <li>{@link Commutative} 交換法則 任意の元x, yに対して x * y = y * x</li>
 * <li>{@link Idempotent} 冪等性 任意の元xに対して x * x = x</li>
 * <li>{@link Invertible} 逆元 任意の元xに対してある元yが存在して x * y = e となる(ここでeは単位元)</li>
 * <li>{@link Unital} 単位元 任意の元xに対して x * e = e * x = e となる元eが存在する</li>
 * </ul>
 * </p>
 * @author 31536000
 */
package com._31536000.math.algebraic.group;
