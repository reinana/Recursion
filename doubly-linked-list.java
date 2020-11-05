// ここから開発しましょう。
class Node{
    public int data;
    public Node prev;
    public Node next;

    public Node(int data){
        this.data = data;
    }
}

// リストは少なくとも1つのノードを持っている必要があります。
// ヌルリストをサポートしたい場合は、それに応じてコードを追加してください。

class DoublyLinkedList{
    public Node head;
    public Node tail;

    public DoublyLinkedList(Node head, Node tail, int[] arr){
        if(arr.length <= 0){
            this.head = null;
            this.tail = this.head;
            return;
        }
        this.head = new Node(arr[0]);
        Node currentNode = this.head;
        for(int i=1; i<arr.length;i++){
            currentNode.next = new Node(arr[i]);
            // 次のノードの前のノードをcurrent Nodeに割り当てます。
            currentNode.next.prev = currentNode;
            currentNode = currentNode.next;
        }
        // このcurrent Nodeは最後のnodeです。
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
}


class Main{{}
    public static void main(String[] args){
        int[] arr = new int[]{35,23,546,67,86,234,56,767,34,1,98,78,555};
        DoublyLinkedList numList = new DoublyLinkedList(new Node(arr[0]), new Node(arr[arr.length-1]), arr);

        numList.printList();
        System.out.println(numList.head.data);
        System.out.println(numList.head.next.data);
        System.out.println(numList.head.next.prev.data);

        System.out.println(numList.tail.data);
        System.out.println(numList.tail.prev.data);
    }
}
