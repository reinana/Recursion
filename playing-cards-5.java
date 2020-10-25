// Javaで開発しましょう。
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
    public Card[] deck;

    public Deck(){
        this.deck = this.generateDeck();
    }
    public static Card[] generateDeck(){
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        ArrayList<Card> newDeckDinamic = new ArrayList<Card>();
        for(int i=0;i<suits.length;i++){
            for(int j=0;j<values.length;j++){
                newDeckDinamic.add(new Card(suits[i], values[j], j+1));
            }
        }
        Card[] newDeck = new Card[newDeckDinamic.size()];
        for(int i=0;i<newDeckDinamic.size();i++){
            newDeck[i] = newDeckDinamic.get(i);
        }
        return newDeck;
    }
    public void printDeck(){
        for(int i=0;i<this.deck.length;i++){
            System.out.println(this.deck[i].getCardString());
        }
    }
    //デッキをシャッフルする関数
    public void shuffleDeck(){
        int deckSize = this.deck.length;
        for(int i=deckSize-1;i>=0;i--){
            int j = (int)Math.floor(Math.random()*(i+1));
            Card temp = this.deck[i];
            this.deck[i] = this.deck[j];
            this.deck[j] = temp;
        }
    }
}

class Main{
    public static void main(String[] args){
        Deck deck1 = new Deck();
        //シャッフルする前
        deck1.printDeck();
        //deck1をシャッフル
        deck1.shuffleDeck();
        //シャッフルした後
        deck1.printDeck();
    }
}
