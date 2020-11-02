// ここから開発しましょう。
class Node{
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
    }
}
class SinglyLinkedList{
    public Node head;

    public SinglyLinkedList(Node head){
        this.head = head;
    }
}
class Main{
    public static void main(String[] args){
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        //リストの先頭をNodeのnode1に入れる
        Node node1 = new Node(arr[0]);
        //node1をSinglyLinkedListのnumlist にいれる
        //numListはheadを持っている
        SinglyLinkedList numlist = new SinglyLinkedList(node1);

        //headをcurrentNodeにする
        Node currentNode = numlist.head;
        for(int i=0;i<arr.length;i++){
            currentNode.next = new Node(arr[i]);
            currentNode = currentNode.next;
            //currentNodeのdataを一つずつプリント
            System.out.println(currentNode.data);
        }
        // numlistのheadのdata
        System.out.println(numlist.head.data);
        
    }
}
