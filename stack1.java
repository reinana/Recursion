class Node{
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

class Stack{
    public Node head;

    public Stack(){
        this.head = null;
    }

    public void push(int data){
        Node temp = this.head;
        this.head = new Node(data);
        this.head.next = temp;
    }

    public int pop(){
        if(this.head == null) return -1;
        Node temp = this.head;
        this.head = this.head.next;
        return temp.data;
    }

    public int peek(){
        if(this.head == null) return -1;
        return this.head.data;
    }
}
class Main{
    public static void main(String[] args){
        Stack s = new Stack();
        s.push(2);
        System.out.println(s.peek());

        s.push(4);
        s.push(3);
        s.push(-1);
        s.pop();
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
        
    }
}
