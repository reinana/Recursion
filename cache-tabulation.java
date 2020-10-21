class Main{

    // タビュレーションは、ツリー構造が下から上へと続くアルゴリズムでのキャッシングです。フィボナッチ5のツリーを見ると、0から始まり、上に向かって計算が行われます。fib1...fib2...fib3...fibn
    // タビュレーションは、ほとんどの場合、不必要な計算がないため、メモ化よりもはるかに効果的です。
    public static long tabulationFib(int n){
        // これはキャッシュであり、計算済みのフィボナッチ数をすべて保存します。全てを0に設定します.
        long[] cache = new long[n+1];

        // キャッシュの最初の2つは0と1です。fib0は0、fib1は1であり、他のすべての数字はfib(n) = fib(n-1) + fib(n-2)となります．
        cache[0] = 0;
        cache[1] = 1;

        // 再帰の代わりに反復を使用します. 0,1,2,3,...nから始まります。
        // 2からnまでのすべてのfib数を計算します。
        for (int i = 2; i < n+1; i++){
            cache[i] = cache[i-1] + cache[i-2];
        }
        // n番目のフィボナッチを返します。
        return cache[n];
    }

    public static void main(String[] args){
        System.out.println(tabulationFib(50));
    }

}
