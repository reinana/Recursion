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

    public static int score21Individual(ArrayList<Card> cards) {
        int total = 0;
        for (int i = 0; i < cards.size(); i++) {
            total += cards.get(i).intValue;
        }
        if (total > 21) total = 0;
        return total;
    }   
}
// 計算のみを行うHelperFunctionsクラスを定義します。
class HelperFunctions {
    // 数値で構成される配列を受け取り、最大値のインデックスを返します。
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

        // 最大値は19(= index 2)
        int[] arr1 = new int[]{1, 9, 19, 3, 4, 6};
        System.out.println(HelperFunctions.maxInArrayIndex(arr1));

        // 最大値は5(= index 0)
        int[] arr2 = new int[]{5, 2, 1, 3, 5, 5};
        System.out.println(HelperFunctions.maxInArrayIndex(arr2));
       
    }

}
