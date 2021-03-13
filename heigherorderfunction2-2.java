import java.util.function.*;

class Main{
    //Functionを使う
    public static Integer summation(Function<Integer, Integer> func, int a, int b){
        if(b < a) return 0;
        Integer applyNum = func.apply(b);
        return applyNum + summation(func, a, b-1);
    }

    public static Integer pPi(Function<Integer, Integer> func, int a, int b){
        if(b < a) return 1;
        Integer applyNum = func.apply(b);
        return applyNum * pPi(func, a, b-1);
    }
    //オーバーロード
    public static Long pPi(Function<Long, Long> func, long a, long b){
        if(b < a) return Long.valueOf(1);
        Long applyNum = Long.valueOf(func.apply(b));
        return applyNum * pPi(func, a, b-1);
    }
    public static void main(String[] args){

        //1から25までの2乗の総和
        Function<Integer, Integer> function = i -> i*i;
        System.out.println(summation(function, 1, 25));

        //1から10までの3i*(i+3)の総和
        Function<Integer, Integer> function2 = i -> 3 * i * (i + 3);
        System.out.println(summation(function2, 1, 10));

        //1からmまでの2j*(j-1)の総和　m=20
        Function<Integer, Integer> function3 = j -> 2 * j * (j - 1);
        int m = 20;
        System.out.println(summation(function3, 1, m));

        //1から6まで7-kの階乗
        Function<Integer, Integer> function4 = k -> 7 - k;
        System.out.println(pPi(function4, 1, 6));

        //3から9までのi^2+3の階乗
        Function<Long, Long> function5 = i -> i * i + 3;
        System.out.println(pPi(function5, 3, 9));


    }
}
