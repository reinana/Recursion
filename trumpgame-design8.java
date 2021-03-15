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
//他のゲームにも対応できるよう、プレイヤーの人数とgameModeを記録するTableクラスを作成します。
class Table{
    public int amountOfPlayers;
    public String gameMode;

    public Table(int amountOfPlayers, String gameMode){
        this.amountOfPlayers = amountOfPlayers;
        this.gameMode = gameMode;
    }
}

class Dealer{
    //書き換えます。
    public static ArrayList<ArrayList<Card>> startGame(Table table) {
        
        Deck deck = new Deck();
        deck.shuffleDeck();

        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();
        //ブラックジャックの時から書き換えます。
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

    // ゲームの内容によって手札を変更します。
    public static int initialCards(String gameMode) {
        if (gameMode == "poker") return 5;
        if (gameMode == "21") return 2;
        else return 0;
    }
}

class Main{
        
    public static void main(String[] args){

        // 卓の設定　2 players、ポーカー
        ArrayList<ArrayList<Card>> game1 = Dealer.startGame(new Table(2, "poker"));

        // 1人目のプレイヤーの手札をfor文で出力してみます
        for(int i = 0; i < game1.get(0).size();i++){
            System.out.println(game1.get(0).get(i).getCardString());
        }
    }
}
