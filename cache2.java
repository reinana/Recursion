import java.util.List;
import java.util.ArrayList;

class Main{
    // エラトステネスのふるいのアルゴリズム
    public static int[] allNPrimesSieve(int n){
        // サイズnのブーリアン値trueを持つリストを生成します。キャッシュ
        boolean[] cache = new boolean[n];
        for(int i = 0; i < n; i++) {
            cache[i]=true;
        }
        // ステップを√n回繰り返します。nが素数でないと仮定すると、n = a * bと表すことができるので、aとbの両方が√n 以上になることはありえません。
        // したがって、√n * √n = n は最大合成組み合わせになります。
        for (int currentPrime = 2; currentPrime < Math.ceil(Math.sqrt(n)); currentPrime++){
            // キャッシュ内の素数(p)の倍数をすべてfalseにしていきます。
            // iは2からスタートします。
            int i = 2;
            int ip = i * currentPrime;
            while (ip < n){
                cache[ip] = false;
                // i*pをアップデートします。
                i += 1;
                ip = i * currentPrime;
            }
        }

        // キャッシュ内のすべてのtrueのインデックスは素数です。
        ArrayList<Integer> primeNumbersDinamic = new ArrayList<Integer>();
        for (int i = 2; i < cache.length; i++){
            if (cache[i]){
                primeNumbersDinamic.add(i);
            }
        }
        //動的配列を固定配列に
        int[] primeNumbers = new int[primeNumbersDinamic.size()];
        for (int i = 0; i < primeNumbersDinamic.size(); i++){
            primeNumbers[i] = primeNumbersDinamic.get(i);
        }
        
        return primeNumbers;
    }

    //　配列を表示する関数
    public static void printIntArray(int intArr[]){
        // For文とインデックス演算子を使うことで、配列の各要素にアクセスすることができます。
        // Javaの配列はlengthというプロパティを持っています。lengthで配列のサイズを手に入れます。
        for(int i = 0; i < intArr.length; i++){
            System.out.print(intArr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){

        printIntArray(allNPrimesSieve(100));
        System.out.println("-----素数の数----");
        System.out.println(allNPrimesSieve(100).length);
    
    }
} 
