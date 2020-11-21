// ここから開発しましょう。
class Node{
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

class Queue{
    public Node head;
    public Node tail;

    public Queue(){
        this.head = null;
        this.tail = null;
    }

    public Integer peekFront(){
        if(this.head == null) return null;
        return this.head.data;
    }

    public Integer peekBack(){
        if(this.tail == null) return this.peekFront();
        return this.tail.data;
    }

    public void enqueue(int data){
        if(this.head == null){
            this.head = new Node(data);
        }
        else if(this.tail == null){
            this.tail = new Node(data);
            this.head.next = this.tail;
        }
        else{
            this.tail.next = new Node(data);
            this.tail = this.tail.next;
        }
    }

    public Integer dequeue(){
        if(this.head == null) return null;
        Node temp = this.head;

        if(this.head.next == null){
            this.head = null;
            this.tail = null;
        }
        else this.head = this.head.next;

        return temp.data;
    }
}
class Main{
    public static void main(String[] args){
        Queue q = new Queue();
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        q.enqueue(4);
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        q.enqueue(50);
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        q.enqueue(64);
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

        System.out.println("dequeued :" + q.dequeue());
        System.out.println(q.peekFront());
        System.out.println(q.peekBack());

    }
}
