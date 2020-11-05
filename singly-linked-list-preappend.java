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
    public int[] arr;

    public SinglyLinkedList(Node head, int[] arr){
        this.head = arr.length > 0 ?  new Node(arr[0]): null;
        
        Node currentNode = this.head;
        for(int i=1; i<arr.length; i++){
            currentNode.next = new Node(arr[i]); 
            currentNode = currentNode.next;           
        }
    }
    public void printList(){
        String str = "";
        Node iterator = this.head;
        System.out.print("[");
        while(iterator != null){
            str = iterator.data + " ";
            System.out.print(str);
            iterator = iterator.next;
        }
        System.out.print("]");
    }
    //先頭に受け取ったノードを追加します。

    public void preappend(Node newNode){
        newNode.next = this.head;
        this.head = newNode;
    }
}


class Main{
    public static void main(String[] args){
        System.out.println(1);
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        SinglyLinkedList numList = new SinglyLinkedList(new Node(arr[0]), arr);

        numList.printList();
        numList.preappend(new Node(45));
        numList.preappend(new Node(236));
        numList.printList();

    }
}
