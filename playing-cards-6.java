// Javaで開発しましょう。
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Card> deck;

    public Deck(){
        this.deck = this.generateDeck();
    }
    public static List<Card> generateDeck(){
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        //サイズの拡張はオーバーヘッドの大きな処理なので、できるだけ避けるべきです。最初から必要なサイズが判っている場合には、インスタンス化に際してサイズを明示的に宣言しておくことをお勧めします。デフォルトのサイズは10です。
        ArrayList<Card> newDeckDinamic = new ArrayList<>(52);//java7から右辺の<>の中身は省略できる
        for(int i=0;i<suits.length;i++){
            for(int j=0;j<values.length;j++){
                newDeckDinamic.add(new Card(suits[i], values[j], j+1));
            }
        }
        // Card[] newDeck = new Card[newDeckDinamic.size()];
        // for(int i=0;i<newDeckDinamic.size();i++){
        //     newDeck[i] = newDeckDinamic.get(i);
        // }

        return newDeckDinamic;
    }
    public void shuffleDeck(){
        int deckSize = this.deck.size();
        for(int i=deckSize-1;i>=0;i--){
            int j = (int)Math.floor(Math.random()*(i+1));

            Card temp = this.deck.get(i);
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j, temp);
            
        }
    }
    public Card draw(){
        Card drawOne = this.deck.get(this.deck.size()-1);
        this.deck.remove(this.deck.size()-1);
        return drawOne;     
    }
    public void printDeck(){
        int count = 0;
        for(int i=0;i<this.deck.size();i++){
            System.out.println(this.deck.get(i).getCardString());
            count +=1;
        }
        System.out.println(count);
    }
}

class Main{
    public static void main(String[] args){
        Deck deck1 = new Deck();
        // deck1.printDeck();
        deck1.shuffleDeck();
        // deck1.printDeck();
        System.out.println(deck1.draw());
        System.out.println(deck1.draw().getCardString());
        System.out.println(deck1.draw().getCardString());
        System.out.println(deck1.draw().getCardString());
        System.out.println(deck1.draw().getCardString());
        
    }
}
