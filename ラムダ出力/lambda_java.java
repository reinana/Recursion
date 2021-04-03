import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.*;

class LambdaMachine{

    public ArrayList<BiFunction<Integer, Integer, Integer>> lambdaStorage;
    public HashMap<String, Integer> handlers;
    public int count;

    public LambdaMachine(){
        //ラムダ関数はこのlamdbaStorageに格納されます。
        this.lambdaStorage = new ArrayList<BiFunction<Integer, Integer, Integer>>();
        // handlersはキーと値のペアを含み、キーは関数名、値はlambdaStorageに格納された関数のインデックスになります。
        this.handlers = new HashMap<String, Integer>();
        this.count = 0;
    }

    // キーに基づいて、ラムダ関数をデータ構造に挿入します。
    public void insert(String key, BiFunction<Integer, Integer, Integer> lambda){

        // キーがすでに存在している場合は、ラムダと入れ替えます。
        if (this.handlers.get(key) != null){
            this.lambdaStorage.set(this.handlers.get(key), lambda);
        }
        else {
            this.lambdaStorage.add(lambda);
            this.handlers.put(key, this.lambdaStorage.size()-1);
        }
    }

    // 与えられたキーに応じて、ラムダを取得します。キーが存在しなければnull
    public BiFunction<Integer, Integer, ?> retrieve(String key){
        return this.lambdaStorage.size() > 0 && this.handlers.get(key) != null ? this.lambdaStorage.get(this.handlers.get(key)) : null;
    }

    // ラウンドロビンスタイルでラムダを選択します。ラムダと、関数が使用する引数を含むオブジェクトを返します。
    public BiFunction<Integer, Integer, Integer> roundRobinRetrieve(){
        int l = this.lambdaStorage.size();
        if(l == 0) return null;

        int index = this.count % l;
        System.out.println("Round-Robin retrieval at index: " + index);

        this.count++;
        return this.lambdaStorage.get(index);
    }
    
    public BiFunction<Integer, Integer, Integer> randomRetrieve(){
        int l = this.lambdaStorage.size();
        if (l == 0) return null;

        Random rand = new Random();
        int ran = (int)Math.floor(rand.nextInt(l));
        System.out.println("Round-Robin retrieval at index: " + ran);

        return this.lambdaStorage.get(ran);
    }
}

class Main{
    public static void main(String[] args){
        LambdaMachine lambdaMachine = new LambdaMachine();

        BiFunction<Integer,Integer,Integer> pythagora = (a,b) -> (int)(Math.sqrt(a*a +b*b));
        BiFunction<Integer,Integer,Integer> addition = (x,y) -> x+y;
        BiFunction<Integer,Integer,Integer> subtraction = (x,y) -> x-y;
        BiFunction<Integer,Integer,Integer> multiplication = (x,y) -> x*y;
        BiFunction<Integer,Integer,Integer> division = (x,y) -> x/y;
        
        // 2つの入力と共に、構造体ラムダに挿入します。
        lambdaMachine.insert("pythagora", pythagora);
        lambdaMachine.insert("addition", addition);
        lambdaMachine.insert("subtraction", subtraction);
        lambdaMachine.insert("multiplication", multiplication);
        lambdaMachine.insert("division", division);

        System.out.println(lambdaMachine.retrieve("pythagora"));
        System.out.println(lambdaMachine.retrieve("pythagora").apply(3,4));
        System.out.println(lambdaMachine.retrieve("multiplication"));
        System.out.println(lambdaMachine.retrieve("multiplication").apply(4,10));
        
        int x = 1;
        int y = 10;
        
        // ランダムな取得
        System.out.println(lambdaMachine.randomRetrieve().apply(x,y));
        System.out.println(lambdaMachine.randomRetrieve().apply(x,y));
        System.out.println(lambdaMachine.randomRetrieve().apply(x,y));

        // ラウンドロビンによる取得
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
        System.out.println(lambdaMachine.roundRobinRetrieve().apply(x,y));
    }
}
