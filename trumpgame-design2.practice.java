// デッキを表すクラスDeckを生成してください。
// Cardクラスを活用し、Deckクラスにトランプのカード全種類を生成させるgenerateDeckというメソッドを作成しましょう。
// デッキ、デッキに含まれる特定のカードをインデックスを操作してコンソールに出力してみましょう。

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
    
    // ここから記述してください。

}

class Main{
        
    public static void main(String[] args){
        // 新しくデッキを作成し、コンソール上に出力します
        Deck deck1 = new Deck();
        System.out.println(deck1);

        //コンソールで確認
        // System.out.println(deck1.deck);　参照を返すためコメントアウト
        System.out.println(deck1.deck.get(3));
        System.out.println(deck1.deck.get(3).getCardString());
        
    }
}
