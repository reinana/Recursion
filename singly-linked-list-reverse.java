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
        this.head = arr.length >0 ? new Node(arr[0]) : null;

        Node currentNode = this.head;
        for(int i=1;i<arr.length;i++){
            currentNode.next = new Node(arr[i]);
            currentNode = currentNode.next;
        }
    }
    public void printList(){
        System.out.print("[");
        Node iterator = this.head;
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
        for(int i=0; i<index; i++){
            if (iterator == null) return null;
            iterator = iterator.next;    
        }
        return iterator;
    }

    public void preappend(Node newNode){
        newNode.next = this.head;
        this.head = newNode;        
    }

    public void popFront(){
        this.head = this.head.next;
    }

    public void delete(int index){
        if(index == 0) this.popFront();
        Node iterator = this.head;
        for(int i=0;i<index-1;i++){
            if(iterator == null) return;
            iterator = iterator.next;
        }
        iterator.next = iterator.next.next;
    }

    public void reverse(){
        Node reverse = this.head;
        this.head = this.head.next;
        reverse.next = null;

        while(this.head != null){
            Node temp = this.head;
            this.head = this.head.next;
            temp.next = reverse;
            reverse = temp;
        }
        this.head = reverse;
    }
}

class Main{
    public static void main(String[] args){
        // System.out.println(1);
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        SinglyLinkedList numList = new SinglyLinkedList(new Node(arr[0]), arr);
        System.out.println(numList.at(4).data);
        numList.printList();
        System.out.println();
        numList.preappend(new Node(1));
        numList.printList();
        numList.popFront();
        numList.printList();
        System.out.println();
        numList.delete(4);
        numList.printList();
        System.out.println();
        // 逆側に表示してみましょう。
        numList.reverse();
        numList.printList();
    }
}
