class Main{
    // メモ化は、ツリー構造が上から下へと続くアルゴリズムでのキャッシングです。
    // フィボナッチのツリーを見てみると、nから始まり、n-1、n-2、n-3と下に向かって計算していきます。

    //グローバル変数にメモ用の配列を宣言
    public static long[] cache;

    public static long memoizationFib(int totalFibNumbers){
      //これはキャッシュであり，すでに計算したフィボナッチ数をすべて保存します。全てを0に設定します。
      cache = new long[totalFibNumbers+1];

      return memoizationFibHelper(totalFibNumbers);
    }  

    // キャッシュを更新するには、このヘルパー関数を使用します。
    public static long memoizationFibHelper(int n){
        // キャッシュされていないフィボナッチ数を処理するだけです。
        // フィボナッチのnを再帰的に計算し，キャッシュに追加します。
      if (cache[n] == 0){
        if(n == 0) cache[n] = 0;
        else if(n == 1) cache[n] = 1;
        else cache[n] = memoizationFibHelper(n - 1) + memoizationFibHelper(n - 2);
      }
      // フィボナッチはすでに計算されているのでただ返すだけで問題ありません。
      return cache[n];
    }

    public static void main(String[] args){
        System.out.println(memoizationFib(50));
    }
}
