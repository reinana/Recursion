import java.util.function.*;

class Main{

    public static Integer summation(Function<Integer,Integer> func, int a, int b){
        if(b < a) return 0;
        Integer applyNum = (Integer)func.apply(b);

        return applyNum + summation(func, a, b-1);
    }
    //オーバーロード
    public static Integer summation(Supplier<Integer> func, int a, int b){
        if(b < a) return 0;
        Integer getNum = (Integer)func.get();

        return getNum + summation(func, a, b-1);
    }

    public static Integer pPi(Function<Integer, Integer> func, int a, int b){
        if(b < a) return 1;
        Integer applyNum = (Integer)func.apply(b);
        return applyNum * pPi(func, a, b-1);
    }

    //オーバーロード
    public static Integer pPi(Supplier<Integer> func, int a, int b){
        if(b < a) return 1;
        Integer getNum = (Integer)func.get();
        return getNum * pPi(func, a, b-1);
    }

    public static void main(String[] args){

        // 10までの総和
        // 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = 55
        Function<Integer, Integer> identity = i -> i;
        System.out.println(summation(identity, 1, 10));

        // 10 * 100 の計算
        Supplier<Integer> function = () -> 10;
        System.out.println(summation(function, 1, 100));

        // 10の階乗(10!)
        System.out.println(pPi(identity, 1, 10));//3,628,800
        
        // 5^10 の計算
        Supplier<Integer> function5 = () -> 5;
        System.out.println(pPi(function5, 1, 10));// 9765625
    }
}
