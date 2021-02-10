import java.util.Arrays;
import java.util.HashMap;
class Wallet{
    private int bill1;
    private int bill5;
    private int bill10;
    private int bill20;
    private int bill50;
    private int bill100;

    public Wallet(){}
    public Wallet(int bill1, int bill5, int bill10, int bill20, int bill50, int bill100){
        this.bill1 = bill1;
        this.bill5 = bill5;
        this.bill10 = bill10;
        this.bill20 = bill20;
        this.bill50 = bill50;
        this.bill100 = bill100;
    }

    public int getTotalMoney(){
        return (1*bill1) + (5*bill5) + (10*bill10) + (20*bill20) + (50*bill50) + (100*bill100);
    }
    public int getBill100(){
        return this.bill100;
    }
    public int getBill50(){
        return this.bill50;
    }
    public int getBill20(){
        return this.bill20;
    }
    public int getBill10(){
        return this.bill10;
    }
    public int getBill5(){
        return this.bill5;
    }
    public int getBill1(){
        return this.bill1;
    }

    public int insertBill(int bill, int amount){
        switch(bill){
            case(1):
                bill1 += amount;
                break;
            case(5):
                bill5 += amount;
                break;
            case(10):
                bill10 += amount;
                break;
            case(20):
                bill20 += amount;
                break;
            case(50):
                bill50 += amount;
                break;
            case(100):
                bill100 += amount;
                break;
            default:
                return 0;
        }

        return bill*amount;
    }
    public int removeBill(int bill, int amount){
        switch(bill){
            case(1):
                bill1 -= amount;
                break;
            case(5):
                bill5 -= amount;
                break;
            case(10):
                bill10 -= amount;
                break;
            case(20):
                bill20 -= amount;
                break;
            case(50):
                bill50 -= amount;
                break;
            case(100):
                bill100 -= amount;
                break;
            default:
                return 0;
        }

        return bill*amount;
    }
}

class Person{
    private String firstName;
    private String lastName;
    private int age;
    private double heightM;
    private double weightKg;
    public Wallet wallet;
    private String denomination = "highestFirst";

    public Person(String firstName, String lastName, int age, double heightM, double weightKg){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.wallet = new Wallet();
    }
    public Person(String firstName, String lastName, int age, double heightM, double weightKg, int initialMoney){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.wallet = new Wallet();
    }

    public int getCash(){
        if(this.wallet == null) return 0;
        return this.wallet.getTotalMoney();
    }
    public String getFullName(){
        return this.firstName+ " " + this.lastName;
    }
    public int[] getPayed(int money){

        if(this.wallet==null) return null;
        int[] moneys = new int[6];
        if(money >= 100){
            moneys[5] = money/100;
            money %= 100;

        }
        if(money>=50){
            moneys[4] = money/50;
            money %= 50;

        }
        if(money>=20){
            moneys[3] = money/20;
            money %= 20;
        }
        if(money >= 10){
            moneys[2] = money/10;
            money %= 10;
        }
        if(money >= 5){
            moneys[1] = money/5;
            money %= 5;
        }
        if(money >= 1){
            moneys[0] = money;
        }

        for(int i=0; i<moneys.length;i++){

        }
        this.wallet.insertBill(100, moneys[5]);
        this.wallet.insertBill(50, moneys[4]);
        this.wallet.insertBill(20, moneys[3]);
        this.wallet.insertBill(10, moneys[2]);
        this.wallet.insertBill(5, moneys[1]);
        this.wallet.insertBill(1, moneys[0]);

        return moneys;
    }
    
    public String spendMoney(int money){
        int[] bills = new int[]{1,5,10,20,50,100};
        int[] moneys = new int[6];
        moneys[5] = this.wallet.getBill100();
        moneys[4] = this.wallet.getBill50();
        moneys[3] = this.wallet.getBill20();
        moneys[2] = this.wallet.getBill10();
        moneys[1] = this.wallet.getBill5();
        moneys[0] = this.wallet.getBill1();

        if(this.wallet == null || money > getCash()) return "お金が足りません";
        int payment = 0;

        for(int i=0; i<moneys.length; i++){
            while(money > payment && moneys[i] >0){
                payment += bills[i];
                moneys[i] = moneys[i]-1;
                this.wallet.removeBill(bills[i],1);
            }
            
        }

        int change = payment - money;
        int[] changeMoneys = new int[6];
        if(change>=50){
            changeMoneys[4] = change/50;
            change %= 50;
        }
        if(change>=20){
            changeMoneys[3] = change/20;
            change %= 20;
        }
        if(change >= 10){
            changeMoneys[2] = change/10;
            change %= 10;
        }
        if(change >= 5){
            changeMoneys[1] = change/5;
            change %= 5;
        }
        if(change >= 1){
            changeMoneys[0] = change;
        }
        this.wallet.insertBill(100, changeMoneys[5]);
        this.wallet.insertBill(50, changeMoneys[4]);
        this.wallet.insertBill(20, changeMoneys[3]);
        this.wallet.insertBill(10, changeMoneys[2]);
        this.wallet.insertBill(5, changeMoneys[1]);
        this.wallet.insertBill(1, changeMoneys[0]);

        return "Thank you";
    }
    public void dropWallet(){
        this.wallet = null;
    }
    public void addWallet(Wallet wallet){
        this.wallet = new Wallet();
    }
    public void setDenominationPreference(String denomination){
        this.denomination = denomination;
    }

    public void printState(){
        System.out.println("firstname - " + this.firstName);
        System.out.println("lastname - " + this.lastName);
        System.out.println("age - " + this.age);
        System.out.println("height - " + this.weightKg);
        System.out.println("weight - " + this.weightKg);
        System.out.println("Current Money - " + this.getCash());
        System.out.println();
    }
}

class Main{
    public static void main(String[] args){
        Person p = new Person("Ryu","Poolhopper", 40, 1.8, 140);
        p.printState();
        System.out.println("5ドルを3枚追加");
        p.wallet.insertBill(5,3);
        p.printState();
        System.out.println("10ドルを3枚追加");
        p.wallet.insertBill(10,3);
        p.printState();

        System.out.println("100ドルを2枚追加");
        p.wallet.insertBill(100,2);
        p.printState();

        System.out.println("5ドルを2枚取り出す");
        p.wallet.removeBill(5,2);
        p.printState();

        System.out.println("389ドルを追加");
        System.out.println(Arrays.toString(p.getPayed(389)));
        p.printState();

        System.out.println("28ドルのお買い物");
        System.out.println(p.spendMoney(28));
        p.printState();
        System.out.println("15ドルのお買い物");
        System.out.println(p.spendMoney(15));
        p.printState();
        System.out.println("財布の中身");
        System.out.print("1ドル：");
        System.out.println(p.wallet.getBill1());
        System.out.print("5ドル：");
        System.out.println(p.wallet.getBill5());
        System.out.print("10ドル：");
        System.out.println(p.wallet.getBill10());
        System.out.print("20ドル：");
        System.out.println(p.wallet.getBill20());
        System.out.print("50ドル：");
        System.out.println(p.wallet.getBill50());
        System.out.print("100ドル：");
        System.out.println(p.wallet.getBill100());
    }
}
