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
        ArrayList<ArrayList<Card>> table = new ArrayList<ArrayList<Card>>();
        // プレーヤの手札
        
        int cardnum = this.initialCards();

        for (int i = 0; i < this.amountOfPlayers; i++) {       
            ArrayList<Card> playerCards = new ArrayList<Card>();          
            for (int j = 0; j < cardnum; j++) {
                Card card1 = deck.draw();
                playerCards.add(card1);
                 System.out.print(card1.getCardString());
            }
            table.add(playerCards);
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
            
        for (int i = 0; i < this.amountOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + " hand is: ");           
            // System.out.print(this.playerHand.get(0).get(i).getCardString());
            for(int j = 0; j < this.initialCards(); j++) {
                System.out.print(this.playerHand.get(i).get(j).getCardString());            
            }
            System.out.println();
        }
    }

    public static int score21Individual(List<Card> cards) {
        int total = 0;
        for (int i = 0; i < cards.size(); i++) {
            total += cards.get(i).intValue;
        }
        if (total > 21) total = 0;
        return total;
    }

    public String winnerOf21() {
        ArrayList<Integer> points = new ArrayList<>();
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < this.amountOfPlayers; i++) {
            int point = this.score21Individual(this.playerHand.get(i));
            // それぞれのpointを配列に保存
            points.add(point);
            if(cache.get(point)==null) cache.put(point,1);
            else cache.put(point,cache.get(point)+1);
        }
        // 各プレイヤーの得点を確認します。
        System.out.println(points);

        int winnerIndex = HelperFunctions.maxInArrayIndex(points);
        if (cache.get(points.get(winnerIndex)) > 1) return "It is a draw ";
        else if (cache.get(points.get(winnerIndex)) >= 0) return "player " + (winnerIndex + 1) + " is the winner";
        else return "No winners..";
  
  }
}
// 計算のみを行うHelperFunctionsクラスを定義します。
class HelperFunctions {
    // 数値で構成される配列を受け取り、最大値のインデックスを返します。
    public static int maxInArrayIndex(ArrayList intArr) {
        int maxIndex = 0;
        int maxValue = (int)intArr.get(0);

        for (int i = 1; i < intArr.size(); i++) {
            if ((int)intArr.get(i) > maxValue) {
                maxValue = (int)intArr.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

class Main{
   
    public static void main(String[] args){
        Dealer table1 = new Dealer(10,"21");
        System.out.println(table1.winnerOf21());
        System.out.println(table1.playerHand.size());
        

    }
}
