import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

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

    public static String winnerOf21(ArrayList<ArrayList<Card>> playerCards) {
        //ポイントを計算して配列に
        int[] points = new int[playerCards.size()];
        //引き分けがいないかハッシュマップにポイントを入れる　＋１する

        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < playerCards.size(); i++) {
            int point = score21Individual(playerCards.get(i));
            // それぞれのpointを配列に保存
            points[i] = point;
            System.out.println("Player " + (i+1) + " : " + point);

            if(cache.get(point) == null) cache.put(point,1);
            else cache.replace(point, cache.get(point)+1);
        }

        int winnerIndex = HelperFunctions.maxInArrayIndex(points);
        if (cache.get(points[winnerIndex]) > 1) return "It is a draw ";
        else if (cache.get(points[winnerIndex]) >= 0) return "player " + (winnerIndex + 1) + " is the winner";
        else return "No winners..";
    
    }  
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

        Table table1 = new Table(2,"21");
        ArrayList<ArrayList<Card>> game1 = Dealer.startGame(table1);
        Dealer.printTableInformation(game1, table1);

        System.out.println(Dealer.winnerOf21(game1));
    }

}
