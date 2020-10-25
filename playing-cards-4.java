// Javaで開発しましょう。

class Main{
    public static void printArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void main(String[] args){

        // Math.random --> 0以上1未満のランダムな数
        System.out.println(Math.random());

        // 0以上10未満のランダムな数
        System.out.println(Math.random() * 10);

        // 0以上10未満のランダムな整数
        System.out.println(Math.floor(Math.random() * 10));

        // では配列の中の数字をランダムに並び替えてみます。
        int[] intArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        printArr(intArr);

        for (int i = intArr.length - 1; i >= 0 ; i--) {
            // ランダムに得た数値をインデックスとし、two pointerで入れ替えます。
            int j = (int)Math.floor(Math.random() * (i + 1));

            // temporary(仮)の意味
            // i番目をtempに保存し、i番目をj番目で更新し、j番目をtempで更新します。
            int temp = intArr[i];
            intArr[i] = intArr[j];
            intArr[j] = temp;
        }
        System.out.println();//改行
        // ランダムに入れ替えた後の配列
        printArr(intArr);
    }
}
