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

    public SinglyLinkedList(Node head, int[] arr){
        this.head = arr.length > 0 ? new Node(arr[0]) : null;

        Node currentNode = this.head;

        for(int i=1; i<arr.length;i++){
            currentNode.next = new Node(arr[i]);
            currentNode = currentNode.next;
            
        }
    }
    public Node at(int index){
        Node iterator = this.head;
        for(int i=0; i<index; i++){
            iterator = iterator.next;
            if(iterator == null) return null;
        }
        return iterator;
    }

    public int findNode(int key){
        int count = 0;
        Node iterator = this.head;
        while(iterator != null){
            if(iterator.data == key) return count;
            iterator = iterator.next;
            count++;
        }
        return -1;
    }
}
class Main{
    public static void main(String[] args){
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        SinglyLinkedList numList = new SinglyLinkedList(new Node(arr[0]) , arr);

        System.out.println(numList.at(2).data);
        System.out.println(numList.findNode(546));

    }
}
