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
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        ArrayList<Card> newDeckDinamic = new ArrayList<>();
        for(int i=0;i<suits.length;i++){
            for(int j=0;j<values.length;j++){
                newDeckDinamic.add(new Card(suits[i], values[j], j+1));
            }
        }
        return newDeckDinamic;
    }

    public String printDeck(){
        System.out.println("Displaying cards...");
        for (Card card: this.deck) {
                System.out.println(card.getCardString());
        }
        return "";
    }

    public void shuffleDeck() {
        for(int i=this.deck.size()-1;i>=0;i--){
            int j = (int)Math.floor(Math.random() * (i + 1));
            Card tmp = this.deck.get(i);
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j,tmp);
        }
    }

    public Card draw(){
        Card drawOne = this.deck.remove(this.deck.size()-1);
        return drawOne;
    }
} 
//プレイヤーの数とゲームモード
class Table{
    public int amountOfPlayers;
    public String gameMode;

    public Table(int amountOfPlayers, String gameMode){
        this.amountOfPlayers = amountOfPlayers;
        this.gameMode = gameMode;
    }
}

class Dealer{
    public static ArrayList<ArrayList<Card>> startGame(Table table) {
        
        Deck deck = new Deck();
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();
        
        for (int i = 0; i < table.amountOfPlayers; i++) {      
            ArrayList<Card> playerHand = new ArrayList<Card>(initialCards(table.gameMode));     
            for (int j = 0; j < initialCards(table.gameMode); j++) {
                Card card1 = deck.draw();
                playerHand.add(card1);
            }
            playerCards.add(playerHand);
        }
        
        return playerCards;
    }

    public static int initialCards(String gameMode) {
        if (gameMode == "poker") return 5;
        if (gameMode == "21") return 2;
        else return 0;
    }
    // 卓の情報を表示するメソッドを作成します。
    public static void printTableInformation(ArrayList<ArrayList<Card>> playerCards, Table table) {
        System.out.println("Amount of players: "+ table.amountOfPlayers +"... Game mode: " + table.gameMode + ". At this table: ");
        
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.print("Player " + (i + 1) + " hand is: ");             
            for(int j = 0; j < playerCards.get(i).size(); j++) {
                System.out.print(playerCards.get(i).get(j).getCardString());
            }
            System.out.println();
        }            
    }
    // 各プレーヤーの手札を受け取って、合計値を計算するscore21Individualメソッドを作成します。
    // ブラックジャックでは値の合計値が21を超えるとNGなのでその場合は0とします。
    public static int score21Individual(ArrayList<Card> cards) {
        int total = 0;
        for (int i = 0; i < cards.size(); i++) {
            total += cards.get(i).intValue;
        }
        if (total > 21) total = 0;
        return total;
    }   
}

class Main{
        
    public static void main(String[] args){

        // PlayerAの手札
        ArrayList<Card> playerA = new ArrayList<>(2);
        
        Card card1 = new Card("♦︎","A", 1);
        Card card2 = new Card("♦︎","J", 11);

        playerA.add(card1);
        playerA.add(card2);

        // PlayerBの手札
        ArrayList<Card> playerB = new ArrayList<>(2);
        Card card3 = new Card("♦︎","9", 9);
        Card card4 = new Card("♦︎","K", 13);

        playerB.add(card3);
        playerB.add(card4);

        System.out.println(Dealer.score21Individual(playerA));
        System.out.println(Dealer.score21Individual(playerB));
       
    }

}
