// ここから開発しましょう。
class Node{
    public int data;
    public Node prev;
    public Node next;

    public Node(int data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }

}
class DoublyLinkedList{
    public int[] arr;
    public Node head;
    public Node tail;

    public DoublyLinkedList(int[] arr){
        
        if(arr.length<=0){
            this.head = null;
            this.tail = this.head;
        }
        this.head = new Node(arr[0]);
        Node currentNode = this.head;
        for(int i=1; i<arr.length;i++){
            currentNode.next = new Node(arr[i]);
            currentNode.next.prev = currentNode;
            currentNode = currentNode.next;
        }
        this.tail = currentNode;
    }
    public void printList(){
        Node iterator = this.head;
        String str = "";
        while(iterator != null){
            str += iterator.data + " ";
            iterator = iterator.next;
        }
        System.out.println("[" + str + "]");
    }
    public Node at(int index){
        Node iterator = this.head;
        for(int i=0;i<index;i++){
            iterator = iterator.next;
            if(iterator == null) return null;
        }
        return iterator;
    }
    public void reverse(){
        Node reverse = this.tail;
        Node iterator = this.tail.prev;

        Node currentNode = reverse;
        while(iterator != null){
            currentNode.next = iterator;

            iterator = iterator.prev;
            if(iterator != null) iterator.next = null;

            currentNode.next.prev = currentNode;
            currentNode = currentNode.next;
            
        }
        this.tail = currentNode;
        this.head = reverse;
    }
    public void printInReverse(){
        String str = "";
        Node iterator = this.tail;
        while(iterator!=null){
            str += iterator.data+ " ";
            iterator = iterator.prev;
        }
        System.out.println(str);

    }

}
class Main{
    public static void main(String[] args){
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        DoublyLinkedList numList = new DoublyLinkedList(arr);
        System.out.println(numList.at(4).data);

        numList.printList();
        numList.printInReverse();

        numList.printList();
        numList.reverse();
        numList.printList();
    }
}
