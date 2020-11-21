// ここから開発しましょう。
// ここから開発しましょう。
class Node{
    public int data;
    public Node next;
    public Node prev;

    public Node(int data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class Deque{
    public Node head;
    public Node tail;

    public Deque(){
        this.head = null;
        this.tail = null;
    }

    public Integer peekFront(){
        if(this.head == null) return null;
        return this.head.data;
    }

    public Integer peekBack(){
        if(this.tail == null) return null;
        return this.tail.data;

    }

    public void enqueueFront(int data){
        if(this.head == null){
            this.head = new Node(data);
            this.tail = this.head;
            
        }
       
        Node node = new Node(data);
        this.head.prev = node;
        node.next = this.head;
        this.head = node;
    }

    public void enqueueBack(int data){
        if(this.head == null){
            this.head = new Node(data);
            this.tail = this.head;
        }
        else{
            Node node = new Node(data);
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
    }

    public Integer dequeueFront(){
        if(this.head == null) return null;

        Node temp = this.head;
        this.head = this.head.next;
        if(this.head != null) this.head.prev = null;
        else this.tail = null;
        return temp.data;
    }
    public Integer dequeueBack(){
        if(this.head == null) return null;
        
        Node temp = this.tail;
        this.tail = this.tail.prev;

        if(this.tail != null) this.tail.next = null;
        else this.head = null;
        return temp.data;
    }
    public void printList(){
        Node iterator = this.head;
        while(iterator!=null){
            System.out.print(iterator.data + " ");
            iterator = iterator.next;
        }
    }
}
class Main{
    public static int getMax(int[] arr){
        Deque deque = new Deque();
        // 最初の要素を両端キューの最初に追加します。
        deque.enqueueFront(arr[0]);

        // 最大値は両端キューの先頭へ、その他の値は末尾へ向かいます。
        for(int i = 1; i < arr.length; i++){
            // System.out.println(arr[i]);
            if(arr[i] > deque.peekFront()) deque.enqueueFront(arr[i]);
            else deque.enqueueBack(arr[i]);
        }
            // deque.printList();

        return deque.peekFront();
    }

    public static void main(String[] args){
        int[] arr = new int[]{34,35,64,34,10,2,14,5,353,23,35,63,23};
        System.out.println(getMax(arr));//353
         

    }
}
