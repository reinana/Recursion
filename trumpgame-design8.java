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
        return this.deck.remove(this.deck.size()-1);
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
    //gameModeを追加
    public static ArrayList<ArrayList<Card>> startGame(Table table) {
        
        Deck deck = new Deck();
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();
        //手札の数を受け取るよう書き換えます
        
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

    // ゲームの内容によって手札を変更します。
    public static int initialCards(String gameMode) {
        if (gameMode == "poker") return 5;
        if (gameMode == "21") return 2;
        else return 0;
    }
}

class Main{
        
    public static void main(String[] args){
        System.out.println("ブラックジャック");
        Table table1 = new Table(5, "21");


        ArrayList<ArrayList<Card>> game1 = Dealer.startGame(table1);
        //プレイヤーの手札を表示
        for(int i=0;i<game1.size();i++){
            System.out.print("Player "+(i+1)+" [");
            for(int j=0;j<game1.get(i).size();j++){
                System.out.print(game1.get(i).get(j).getCardString());
            }
            System.out.println("]");
        }
        System.out.println("ポーカー");
        Table table2 = new Table(3, "poker");
        ArrayList<ArrayList<Card>> game2 = Dealer.startGame(table2);
        //プレイヤーの手札を表示
        for(int i=0;i<game2.size();i++){
            System.out.print("Player "+(i+1)+" [");
            for(int j=0;j<game2.get(i).size();j++){
                System.out.print(game2.get(i).get(j).getCardString());
            }
            System.out.println("]");
        }
    }
}
