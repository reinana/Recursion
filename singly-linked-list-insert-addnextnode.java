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

    public SinglyLinkedList(int[] arr, Node head){
        this.head = arr.length > 0? new Node(arr[0]) : null;

        Node currentNode = this.head;
        for(int i=1;i<arr.length;i++){
            currentNode.next = new Node(arr[i]);
            currentNode = currentNode.next;
        }
    }

    public Node at(int index){
        Node iterator = this.head;
        for(int i=1;i<index;i++){
            iterator = iterator.next;
            if(iterator == null) return null;
        }
        return iterator;
    }

    public String printList(){
        Node iterator = this.head;
        String str = "";
        while(iterator.next != null){
            str += iterator.data + " ";
            iterator = iterator.next;
        }
        System.out.println(str);
        return " ";
    }

}

class Main{
    public static void main(String[] args){
        
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        
        
        SinglyLinkedList numList = new SinglyLinkedList(arr, new Node(arr[0]));
        numList.printList();

        Node iterator = numList.head;
        int i = 0;
        while(iterator != null){
            Node currentNode = iterator;
            
            if(i%2 == 0){
                currentNode.addNextNode(new Node(currentNode.data*2));
                iterator= iterator.next;
            }    
            iterator = iterator.next;
            i++;
        }
        numList.printList();

    }
}
