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

class Dealer{
    //gameModeを追加
    public static ArrayList<ArrayList<Card>> startGame(int amountOfPlayers, String gameMode) {
        
        Deck deck = new Deck();
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> table = new ArrayList<>();
        //手札の数を受け取るよう書き換えます
        
        for (int i = 0; i < amountOfPlayers; i++) {      
            ArrayList<Card> playerHand = new ArrayList<Card>(initialCards(gameMode));     
            for (int j = 0; j < initialCards(gameMode); j++) {
                Card card1 = deck.draw();
                playerHand.add(card1);
            }
            table.add(playerHand);
        }
        
        return table;
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
        ArrayList<ArrayList<Card>> table1 = Dealer.startGame(5, "21");
        //プレイヤーの手札を表示
        for(int i=0;i<table1.size();i++){
            System.out.print("Player "+(i+1)+" [");
            for(int j=0;j<table1.get(i).size();j++){
                System.out.print(table1.get(i).get(j).getCardString());
            }
            System.out.println("]");
        }
        System.out.println("ポーカー");
        ArrayList<ArrayList<Card>> table2 = Dealer.startGame(3, "poker");
        //プレイヤーの手札を表示
        for(int i=0;i<table2.size();i++){
            System.out.print("Player "+(i+1)+" [");
            for(int j=0;j<table2.get(i).size();j++){
                System.out.print(table2.get(i).get(j).getCardString());
            }
            System.out.println("]");
        }
    }
}
