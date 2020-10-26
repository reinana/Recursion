// Javaで開発しましょう。
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

        ArrayList<Card> newDeckDinamic = new ArrayList<>(52);
        for(int i=0;i<suits.length;i++){
            for(int j=0;j<values.length;j++){
                newDeckDinamic.add(new Card(suits[i], values[j], j+1));
            }
        }
        return newDeckDinamic;
    }

    public void shuffleDeck(){
        for(int i=this.deck.size()-1;i>=0;i--){
            int j = (int)Math.floor(Math.random()*(i+1));

            Card tmp = this.deck.get(i);
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j,tmp);
        }
    }
    public Card draw(){
        Card drawOne = this.deck.get(this.deck.size()-1);
        this.deck.remove(this.deck.size()-1);
        return drawOne;
    }
    public void printDeck(){
        for(int i=0;i<this.deck.size();i++){
            System.out.println(this.deck.get(i).getCardString());
        }
    }

}



class Dealer{
    public int amountOfPlayers;
    public String gameMode;
    public ArrayList<ArrayList<Card>> playerHand;

    public Dealer(int amountOfPlayers, String gameMode){
        this.amountOfPlayers = amountOfPlayers;
        this.gameMode = gameMode;
        this.playerHand = this.startGame();
    }

    public ArrayList<ArrayList<Card>> startGame() {
        

        Deck deck = new Deck();
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> table = new ArrayList<>();
        // プレーヤの手札
        ArrayList<Card> playerHand = new ArrayList<Card>();
        int cardnum = this.initialCards();

        for (int i = 0; i < this.amountOfPlayers; i++) {       
                      
            for (int j = 0; j < cardnum; j++) {

                Card card1 = deck.draw();
                playerHand.add(card1);
                 System.out.print(card1.getCardString());
            }
            table.add(playerHand);
            System.out.println();
        }
        return table;
    }
    // ゲームの内容によって手札を変更します。
    public int initialCards() {
        if (this.gameMode == "poker") return 5;
        if (this.gameMode == "21") return 2;
        else return 0;
    }

    // 卓の情報を表示するメソッドを作成します。
    public void printTableInformation() {
        System.out.println("Amount of players: " + this.amountOfPlayers + "\n... Game mode: " + this.gameMode
        + "\n At this table: ");
        // System.out.println(this.playerHand);
        for (int i = 0; i < this.amountOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + " hand is: ");           
            // System.out.print(this.playerHand.get(0).get(i).getCardString());
            for(int j = 0; j < this.initialCards(); j++) {
                // Card cardone =  ;
                System.out.print(this.playerHand.get(0).get(this.initialCards()*i+j).getCardString());
            }
            System.out.println();
        }
        System.out.println(this.playerHand.get(2).get(2).getCardString());
        
    }

}
class Main{
   
    public static void main(String[] args){
        
        Dealer table1 = new Dealer(4,"21");
        table1.printTableInformation();

    }
}
