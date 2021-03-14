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
    
    public Deck(){
        this.deck = this.generateDeck();
    }
    
    public static ArrayList<Card> generateDeck(){
        ArrayList<Card> newDeck = new ArrayList<>();
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(int i = 0; i < suits.length; i++){
            for(int j = 0; j < values.length; j++){
                newDeck.add(new Card(suits[i], values[j], j + 1));
            }
        }
        return newDeck;
    }

    public Card draw(){
        return this.deck.remove(this.deck.size()-1);
    }

    public void printDeck(){
        System.out.println("Displaying cards...");
        for (int i = 0; i < this.deck.size(); i++) {
            System.out.println(this.deck.get(i).getCardString());
        }
    }

    public void shuffleDeck() {
        for(int i = this.deck.size()-1; i >= 0; i--){
            int j = (int)Math.floor(Math.random() * (i + 1));
            Card tmp = this.deck.get(i);
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j, tmp);
        }
    }
}    

// ディーラークラス(ステートレスオブジェクト)
class Dealer{

    // 参加人数を受け取り、それぞれのプレイヤーにカードを配る処理を作成します。
    public static ArrayList<ArrayList<Card>> startGame(int amountOfPlayers) { 
        //新しいデッキを作ります
        Deck deck = new Deck();
        //デッキをシャッフルします
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> table = new ArrayList<>();
        // プレーヤの手札
        for (int i = 0; i < amountOfPlayers; i++) {            
            ArrayList<Card> playerHand = new ArrayList<Card>(2);//ブラックジャックの手札は２枚
            for (int j = 0; j < 2; j++) {
                Card card1 = deck.draw();
                playerHand.add(card1);
            }
            table.add(playerHand);
        }
        // tableのプレイヤー全員の手札を返します。
        return table;
    }    
}

class Main{
        
    public static void main(String[] args){

        ArrayList<ArrayList<Card>> table1 = Dealer.startGame(4);
        // 1人目のplayerの手札をfor文で出力してみます。
        for(Card card: table1.get(0)){
            System.out.println(card.getCardString());
        }       
    }
}
