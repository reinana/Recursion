// funcAdd = function(a, b){return a + b};
// useFunction(funcAdd, 10, 5);
// Javaでは上記のような関数を入力として受け取り、関数を出力する関数は構造上できない仕様になっていますが、インターフェースを使って匿名関数を作成し、呼び出し可能オブジェクトの参照を受け取ることができます。

//ラムダ式に実装できる主な関数型インターフェース
//  　　　　　　引数あり　　引数なし
//  戻り値あり　Function   Supplier　
//　戻り値なし  Consumer      ×

//  引数あり戻り値boolean　Predicate
//  引数2つ戻り値あり　　　BiFunction

// 他にもたくさんあるのでドキュメンテーションを確認しましょう。

import java.util.function.*;

class Main{

    //anotherFunction()メソッドはSuppulierを受け取り、Stringを返す関数です。
    public static String anotherFunction(Supplier<String> func){
        //Supplierを実行するget関数を使います。
        return func.get() + ".... called from another function!";
    }

    public static Integer fSquaredX(Function<Integer, Integer> func, int x){
        int p = x * x;
        // Functionを実行するapply関数を使います。
        return func.apply(p);
    }
    
    public static String fSquaredX2(Function<Integer, String> func, int x){
        int p = x * x;
        return func.apply(p);
    }
    
    public static void main(String[] args){

        // 零項関数（引数を取らない関数）Supplierを使います。この「Supplier」はデータを供給します。
        Supplier<String> myCallable = () -> {return "hello world";};
        System.out.println(myCallable.get());// hello world
        System.out.println(anotherFunction(myCallable));

        // 単項関数（入力を1つ取り込んで出力を返す関数）である関数型を使って、出力を返します。
        Function<Integer, Integer> callable1 = (p) -> {return p + 30;};
        System.out.println(fSquaredX(callable1, 5));// 55

        Function<Integer, String> callable2 = (p) -> {return "p is " + p;};
        System.out.println(fSquaredX2(callable2, 10));// p is 100
        System.out.println(fSquaredX2(callable2, 8));// p is 64
    }
    
}
