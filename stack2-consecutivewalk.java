import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static void printList(int[] arr){
        for(Integer num: arr){
            System.out.print(num + " ");
        }
    }
    
}
class Main{
    // リストを受け取り、単調減少している部分リストを返す関数を実装します。
    // リストの途中で単調増加する部分が出現したら、部分リストをリセットします。
    public static int[] consecutiveWalk(int[] arr){
        Stack stack = new Stack();
        stack.push(arr[0]);
        for(int i = 1; i < arr.length; i++){
            // スタックの上にある要素より、arr[i]が大きい場合、スタックをリセットします。
            
            if(stack.peek() <= arr[i]){
                // スタックがnullになるまで繰り返されます。
                while(stack.pop() != null);
            }
            // スタックにプッシュします。スタックは常に単調減少になっています。
            stack.push(arr[i]);
        }
        
        ArrayList<Integer> resultDynamic = new ArrayList<>();
        // resultは逆向きになっています。
        // add(0, num)は、配列の先頭にnumを追加します。
        while(stack.peek() != null) resultDynamic.add(0,stack.pop());
        // System.out.println(resultDynamic);
        int[] result = new int[resultDynamic.size()];
        int i=0;
        for(Integer num: resultDynamic){
            result[i++] = num;
        }
        return result;
    }
    

    public static void main(String[] args){
        int[] arr = new int[]{1,2,3,3,2,1};
        Stack.printList(consecutiveWalk(arr));
        System.out.println();
        Stack.printList(consecutiveWalk(new int[]{1,2,3,3,2,1})); 
        System.out.println();
        Stack.printList(consecutiveWalk(new int[]{4,5,4,2,4,3646,34,64,3,0,-34,-54})); // [64, 3, 0, -34, -54]
        System.out.println();
        Stack.printList(consecutiveWalk(new int[]{4,5,4,2,4,3646,34,64,3,0,-34,-54,4})); // [4]
    }
}
