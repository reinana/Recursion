// ここから開発しましょう。
class Node{
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
    }

    public void addNextNode(Node newNode){
        Node tempNode = this.next;
        this.next = newNode;
        newNode.next = tempNode;
    }
}
class SinglyLinkedList{
    public Node head;

    public SinglyLinkedList(Node head, int[] arr){
        this.head = arr.length > 0 ?  new Node(arr[0]): null;

        Node currentNode = this.head;
        for(int i=1;i<arr.length;i++){
            currentNode.next = new Node(arr[i]);
            currentNode = currentNode.next;
        }
    }
    public void printList(){
        Node iterator = this.head;
        System.out.print("[");
        String str = "";
        while(iterator != null){
            str = iterator.data + " ";
            System.out.print(str);
            iterator = iterator.next;
        }
        System.out.print("]");
    }

    public Node at(int index){
        Node iterator = this.head;
        for(int i=0;i<index;i++){
            iterator = iterator.next;
            if(iterator == null) return null;
        }
        return iterator;
    }

    public void preappend(Node newNode){
        newNode.next = this.head;
        this.head = newNode;
    }

    public void append(Node newNode){
        Node iterator = this.head;
        while(iterator.next != null){
            iterator = iterator.next;
        }
        iterator.next = newNode;
    }

    public void popFront(){
        this.head = this.head.next;
    }

    public void delete(int index){
        if (index == 0) this.popFront();
        Node iterator = this.head;
        for(int i=0;i<index-1;i++){
            if(iterator == null) return;
            iterator = iterator.next;
        }
        iterator.next = iterator.next.next;        
    }
}


class Main{
    public static void main(String[] args){
        System.out.println(1);
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        SinglyLinkedList numList = new SinglyLinkedList(new Node(arr[0]), arr);

        numList.printList();
        System.out.println();
        System.out.println(numList.at(4).data);
        System.out.println("preappend");
        numList.preappend(new Node(4));
        numList.printList();

        System.out.println("append");
        numList.append(new Node(4));
        numList.printList();

        System.out.println("popFront");
        numList.popFront();
        System.out.println("popFront");
        numList.popFront();
        numList.printList();

        System.out.println("delete");
        numList.delete(4);
        numList.printList();

        System.out.println("delete");
        numList.delete(20);
        numList.printList();
    }
}
