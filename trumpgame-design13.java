import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;// HashMapをimportします。

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
    // Tableクラスのtableの情報を受け取ります。
    public ArrayList<Card> deck;
    
    public Deck(Table table){
        this.deck = this.generateDeck(table);
    }
    // Tableクラスのtable情報を受け取ります。
    public static ArrayList<Card> generateDeck(Table table){
        ArrayList<Card> newDeck = new ArrayList<>();
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        // blackJackを追加します。
        HashMap<String, Integer> blackJack = new HashMap<>(){
            {
                put("A",1);
                put("J",10);
                put("Q",10);
                put("K",10);

            }
        };
        for(int i = 0; i < suits.length; i++){
            for(int j = 0; j < values.length; j++){
                if(table.gameMode == "21"){
                    newDeck.add(new Card(suits[i], values[j], blackJack.get(values[j]) == null ? j + 1 : blackJack.get(values[j])));
                }else{
                    newDeck.add(new Card(suits[i], values[j], j + 1));
                }                     
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
        // Tableクラスの情報を受け取ります。
        Deck deck = new Deck(table);
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();
        
        for (int i = 0; i < table.amountOfPlayers; i++) { 
            // gameModeを渡します。     
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

    public static String winnerOf21(ArrayList<ArrayList<Card>> playerCards) {

        int[] points = new int[playerCards.size()];
        int[] cache = new int[22];
        
        for (int i = 0; i < playerCards.size(); i++) {
            int point = Dealer.score21Individual(playerCards.get(i));
 
            points[i] = point;
            
            if (cache[point] >= 1) cache[point] += 1;
            else cache[point] = 1;
        }
        System.out.println(Arrays.toString(points));

        int winnerIndex = HelperFunctions.maxInArrayIndex(points);
        if (cache[points[winnerIndex]] > 1) return "It is a draw ";
        else if (cache[points[winnerIndex]] >= 0) return "player " + (winnerIndex + 1) + " is the winner";
        else return "No winners..";
    }  
}
