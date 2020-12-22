// 次にデッキを表すクラスDeckを生成しましょう。
// Cardクラスを活用し、Deckクラスにトランプのカード全種類を生成させるメソッドを作成しましょう。その後、そのメソッドを使って、デッキをインスタンス化させるコンストラクタを作成します。
import java.util.Arrays;
import java.util.ArrayList;

class Card{
    public String suit;
    public String value;
    public int intValue;

    public Card(String suit, String value, int intValue){
        this.suit = suit;
        this.value = value;
        this.intValue = intValue;
    }
    public String getCardString(){
        return this.suit + this.value + "(" + this.intValue + ")";
    }

}
class Deck{
    public ArrayList<Card> deck;
    // コンストラクタ
    public Deck(){
        this.deck = this.generateDeck();
    }
    // デッキを生み出すメソッドを作成します。staticメソッドを使います。
    // ここではインスタンス無しでも使える関数と考えていただければ問題ありません。
    // 全記号・全ての値を用意し、for文で一つずつカードを生成します。
    public static ArrayList<Card> generateDeck(){
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        ArrayList<Card> newDeckDynamic = new ArrayList<>(52);
        for(int i=0;i<suits.length;i++){
            for(int j=0;j<values.length;j++){
                newDeckDynamic.add(new Card(suits[i], values[j], j+1));
            }
        }
        return newDeckDynamic;
    }
}    

class Main{
        
    public static void main(String[] args){
        // 新しくデッキを作成し、コンソール上に出力します
        Deck deck1 = new Deck();
        System.out.println(deck1.deck.get(0).getCardString());
        for(Card card: deck1.deck){
            System.out.println(card.getCardString());
        }  
    }
}
