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
        this.deck = Deck.generateDeck();
    }

    // デッキを生み出すメソッドを作成します。staticメソッドを使います。
    // ここではインスタンス無しでも使える関数と考えていただければ問題ありません。
    // 全記号・全ての値を用意し、for文で一つずつカードを生成します。
    public static ArrayList<Card> generateDeck(){
        ArrayList<Card> newDeck = new ArrayList<>();
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(int i = 0; i < suits.length; i++){
            for(int j = 0; j < values.length; j++){
                //j=0からスタートしているので、最後はj+1にします。トランプに0というカードが存在しないため。
                newDeck.add(new Card(suits[i], values[j], j + 1));
                //入るカードが何か確認します
                System.out.println(new Card(suits[i], values[j], j + 1).getCardString());
            }
        }
        return newDeck;
    }
}    

class Main{
        
    public static void main(String[] args){
        // 新しくデッキを作成し、コンソール上に出力します
        Deck deck1 = new Deck();
        System.out.println(deck1);

        //コンソールで確認
        // System.out.println(deck1.deck);　参照を返すためコメントアウト
        System.out.println(deck1.deck.get(3));
        System.out.println(deck1.deck.get(3).getCardString());
        
    }
}
