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
            ArrayList<Card> playerHand = new ArrayList<Card>(Dealer.initialCards(table.gameMode));     
            for (int j = 0; j < Dealer.initialCards(table.gameMode); j++) {
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

    public static void printTableInformation(ArrayList<ArrayList<Card>> playerCards, Table table) {
        System.out.println("Amount of players: " + table.amountOfPlayers +"... Game mode: " + table.gameMode + ". At this table: ");
        
        for (int i = 0; i < playerCards.size(); i++) {
            System.out.println("Player " + (i + 1) + " hand is: ");             
            for(int j = 0; j < playerCards.get(i).size(); j++) {
                System.out.println(playerCards.get(i).get(j).getCardString());
            }
            System.out.println();
        }            
    }

    public static int score21Individual(ArrayList<Card> cards) {
        int value = 0;
        for (int i = 0; i < cards.size(); i++) {
            value += cards.get(i).intValue;
        }
        if (value > 21) value = 0;
        return value;
    }

    // ここから記述してください。
    
}

class HelperFunctions {

    public static int maxInArrayIndex(int[] intArr) {
        int maxIndex = 0;
        int maxValue = intArr[0];

        for (int i = 1; i < intArr.length; i++) {
            if (intArr[i] > maxValue) {
                maxValue = intArr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

class Main{
        
    public static void main(String[] args){

        Table table1 = new Table(6,"21");
        ArrayList<ArrayList<Card>> game1 = Dealer.startGame(table1);
        Dealer.printTableInformation(game1, table1);

        // winnerOf21関数によりどのプレイヤーが勝利するか出力してください。

    }

}
