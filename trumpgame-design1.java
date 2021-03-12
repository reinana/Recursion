// トランプアプリを開発していきます。ここでは21を開発しますが、どのトランプゲームでも組み込めるような設計にします。

// まずは1枚分のカードを表すクラスCardを生成しましょう。
// 記号(♣, ♦, ♥, ♠の内1つ)・値（A,2,~,Kの内1つ）・数値（0~12の内1つ）をインスタンス化させるコンストラクタと、それらの情報を返す関数を作成します。
import java.util.ArrayList;
import java.util.Arrays;

class Card {
    public String suit;
    public String value;
    public int intValue;
    // インスタンス生成のためのコンストラクタ
    public Card(String suit, String value, int intValue) {
        this.suit = suit;
        this.value = value;
        // intValueは値の大きさになります（例：A=1,K=13）
        this.intValue = intValue;
    }
    public String getCardString(){
        return this.suit + this.value + "(" + this.intValue + ")";
    }
}
class Main{
    
    public static void main(String[] args){
        // 新しくカードを作成し、カード情報を返す関数を使用します
        Card card1 = new Card("♦︎","A",1);
        System.out.println(card1);
        System.out.println(card1.getCardString());
    }
}
