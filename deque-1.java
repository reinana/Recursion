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
}
class Main{
    public static void main(String[] args){
        
        Deque q = new Deque();
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        q.enqueueBack(4);
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        q.enqueueBack(50);
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        System.out.println("dequeued :" + q.dequeueFront());
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        q.enqueueFront(36);
        q.enqueueFront(96);
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        System.out.println("dequeued :" + q.dequeueBack());
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        System.out.println("Emptying");
        q.dequeueBack();
        q.dequeueBack();
        q.dequeueBack();
        q.dequeueBack();

        //System.out.println("Emptying");
        //q.dequeueFront();
        //q.dequeueFront();
        //q.dequeueFront();
        //q.dequeueFront();

        System.out.println(q.peekFront());
        System.out.println(q.peekBack());


    }
}
