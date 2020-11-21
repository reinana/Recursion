// ここから開発しましょう。
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

    public Integer pop(){
        if(this.head == null) return null;
        Node temp = this.head;
        this.head = this.head.next;
        return temp.data;
    }

    public Integer peek(){
        if(this.head == null) return null;
        return this.head.data;
    }

    public static int[] reverse(int[] arr){
        Stack stack = new Stack();
        for(int i = 0; i < arr.length; i++) stack.push(arr[i]);
        
        int[] reversed = new int[arr.length];
        int i=0;
        while(stack.peek() != null){
            reversed[i++] = stack.pop();
        }
        return reversed;
    }
    public static void printList(int[] arr){
        for(Integer num: arr){
            System.out.print(num + " ");
        }
    }
}


class Main{
    public static void main(String[] args){       
        int[] arr = new int[]{1,2,3,4,5};
        Stack.printList(arr);
        System.out.println();
        Stack.printList(Stack.reverse(arr));
            
    }
}
