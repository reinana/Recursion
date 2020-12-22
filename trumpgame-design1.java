class Card {
    public String suit;
    public String value;
    public int intValue;
    // インスタンス生成のためのコンストラクタ
    public Card(String suit, String value, int intValue) {
        this.suit = suit;
        this.value = value;
        // intValueは値の大きさになります（例：A=1,K=13）
        this.intValue = intValue;
    }
    public void printCard(){
        System.out.println(this.suit+this.value+"("+this.intValue+")");
    }
}
class Main{
    
    public static void main(String[] args){
        // 新しくカードを作成し、カード情報を返す関数を使用します
        Card card1 = new Card("♦︎","A",1);
        card1.printCard();
    }
}
