import java.util.Arrays;
import java.util.ArrayList;

class Main{    
    public static void main(String[] args){

        // Math.random --> 0以上1未満のランダムな数
        System.out.println(Math.random());

        // 0以上10未満のランダムな数
        System.out.println(Math.random() * 10);

        // 0以上10未満のランダムな整数
        System.out.println((int)Math.floor(Math.random() * 10));

        // では配列の中の数字をランダムに並び替えてみます。
        // ArraysクラスにあるasList()メソッドで配列を初期化します。
        ArrayList<Integer> intArr = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(intArr);

        for (int i = intArr.size() - 1; i >= 0 ; i--) {
            // ランダムに得た数値をインデックスとし、two pointerで入れ替えます。
            int j = (int)Math.floor(Math.random() * (i + 1));

            // temporary(仮)の意味
            // i番目をtempに保存します。
            // ArrayListクラスに用意されているsetメソッドを使い、i番目をj番目で更新し、j番目をtempで更新します。
            int temp = intArr.get(i);
            intArr.set(i, intArr.get(j));
            intArr.set(j, temp);
        }
        System.out.println();//改行
        // ランダムに入れ替えた後の配列
        System.out.println(intArr);
    }
}
