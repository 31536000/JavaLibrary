# はじめに

このライブラリは、私が個人で開発しているライブラリとなります。

現時点では未完成な部分が多く、まだ実用はおススメできません。鑑賞用となります。

# このライブラリは何？

このライブラリは、抽象代数論を用いて数学的な操作を簡単に行えるようにするライブラリです。

例えば、配列に対してその最大値を取りたいときを考えます。
従来であれば、例えば整数に対する最大値は <code>Arrays.stream(array).max().getAsInt()</code> で得られます。

ですが、もしこれが文字列であれば？
この場合は <code>Arrays.stream(array).max(Comparator.naturalOrder()).get()</code> などと書くことになるでしょう。
では、 <code>Comparator.naturalOrder()</code> とは何なのでしょうか？

このような疑問に対し、抽象代数学は適切な答えを与えます。

今回の場合、 <code>Comparator.naturalOrder()</code> は全順序関係である、と言えます。
そして、この条件が最大値を取るための十分条件であることも分かります。

このように、抽象代数学を用いればアルゴリズムに必要な最低限の性質とは何か、何を実装していれば良いのかを説明することができます。

このライブラリではその考えを更に推し進め、抽象代数を用いて様々な操作を行えるようになります。

# このライブラリを用いるメリットは？

このライブラリを用いると、様々なバグを未然に回避することができます。

例えば、ゲームのステータス補正バフをその上昇値で管理していたとします。
最初は乗算のバフのみがあったとします。

この時、バフは実数値として表され、演算 $\times$ を用いて計算されます。
そして、バフを集合を用いて管理しているとします。
すなわち、 <code>Set&lt;Buff&gt; buff;</code> のような形で管理しているとします。

```Java
static class Buff {
    static Buff IDENTITY = new Buff(1);
    final double value;
    Buff(double value) {
        this.value = value;
    }
    int getStatus(int status) {
        return (int)Math.round(status * value);
    }
    static CommutativeMonoid<Buff> operator() {
        return new CommutativeMonoid<Buff>() {

            @Override
            public Buff apply(Buff t, Buff u) {
                return new Buff(t.value * u.value);
            }

            @Override
            public Buff identity() {
                return IDENTITY;
            }

        };
    }
}
Set<Buff> buff = new HashSet<>();
```

この時、バフの値は以下の様に得ることができます。

```Java
int nextStatus = HashSet.fold(buff).getStatus(status);
```

ここで、掛け算は交換法則を満たします。このことから、バフを集合として管理することの正当性が従います。

ある時、固定値を加算する類のバフも追加したいと考えたとします。
つまり、バフを <!-- $f(x)=ax+b$ --> <img style="transform: translateY(0.1em); background: white;" src="https://render.githubusercontent.com/render/math?math=f(x)%3Dax%2Bb"> という形の一次関数で表せば良いと考えました。

```Java
static class Buff {
    static final DoubleLinearFunctionGenerator generator = DoubleLinearFunctionGenerator.create(DoubleMath.getAlgebraic());
    static final Buff IDENTITY = new Buff(generator.identity());
    final DoubleLinearFunction buff;
    Buff(double mul, double add) {
        this(generator.linearFunction(mul, add));
    }
    Buff(DoubleLinearFunction buff) {
        this.buff = buff;
    }
    int getStatus(int status) {
        return (int)Math.round(buff.apply(status));
    }
    static Monoid<Buff> operator() {
        return new Monoid<Buff>() {

            @Override
            public Buff apply(Buff t, Buff u) {
                return new Buff(t.andThen(u));
            }

            @Override
            public Buff identity() {
                return IDENTITY;
            }

        };
    }
}
Set<Buff> buff = new HashSet<>();
```

この時、以下のコードはエラーになります。

```Java
int nextStatus = HashSet.fold(buff).getStatus(status);
```

これは、 <code>HashSet</code> は順序を保証していないため、可換でない演算を <cold>fold</code> することはできないようになっているからです。

現時点では上のコードは残念ながらまだ定義されていません。

ですが、本ライブラリは最終的に上のようなコードを書けるようにすることを目標としています。

# 現在の進捗

### 代数インターフェース

代数インターフェースは、様々な代数を載せるためのマーカーインタフェースとなります。

現時点で必要な部分の大半の整備が終わっており、次は実際に載せる数学的構造を定義したいと考えています。

### 数学的構造

数学的構造は、多項式や行列などの数学で用いられる台集合となります。

まだ殆ど作られていません。次に作りたいもの: 分数

### 標準データ構造

標準データ構造は、一般によく使われる配列やリストなどを定義し、更に数学的な演算ができるように拡張したものです。

まだ殆ど作られていません。次に作りたいもの: Collection の定義を検討中
