// Javaで開発しましょう。
import java.util.ArrayList;//ArrayListをインポートする

//Cardクラスを作る
class Card{
    public String suit;
    public String value;
    public int intValue;

    public Card(String suit, String value, int intValue){
        this.suit = suit;
        this.value = value;
        this.intValue = intValue;
    }
    //カードを表示する関数
    public String getCardString(){
        return this.suit + this.value + "(" + this.intValue + ")";
    }

}

//Deckクラスを作る
class Deck{
    public Card[] deck;
    //Card[]はオブジェクトの配列の型
    public Deck(){
        //generateDeck関数で作ったカードのオブジェクトのリストを入れる
        this.deck = this.generateDeck();
    }

    public static Card[] generateDeck(){
        //動的配列を作る
        ArrayList<Card> newDeckDinamic = new ArrayList<Card>();
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        for(int i=0;i<suits.length;i++){
            for(int j=0;j<values.length;j++){
                //動的配列にadd　インデックス＋１する
                newDeckDinamic.add(new Card(suits[i], values[j], j+1));
            }
        }
        //動的配列を固定配列にする
        Card[] newDeck = new Card[newDeckDinamic.size()];
        for(int i=0;i<newDeckDinamic.size();i++){
            newDeck[i] = newDeckDinamic.get(i);
        }
        return newDeck;
    }
    public void printDeck() {
        System.out.println("Displaying cards...");
        for (int i=0; i<this.deck.length; i++) {
                System.out.println(this.deck[i].getCardString());
        }
    }
}

class Main{
    

    public static void main(String[] args){
        Card card1 = new Card("♦︎","A",1);

        System.out.println(card1.getCardString());
        Deck deck1 = new Deck();
        System.out.println(deck1.deck[0].getCardString());
        deck1.printDeck();
    }

} 
