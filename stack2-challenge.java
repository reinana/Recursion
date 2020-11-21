import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Node{
    public String data;
    public Node next;

    public Node(String data){
        this.data = data;
        this.next = null;
    }
}

class Stack{
    public Node head;

    public Stack(){
        this.head = null;
    }

    public void push(String data){
        Node temp = this.head;
        this.head = new Node(data);
        this.head.next = temp;
    }

    public String pop(){
        if(this.head == null) return null;
        Node temp = this.head;
        this.head = this.head.next;
        return temp.data;
    }

    public String peek(){
        if(this.head == null) return null;
        return this.head.data;
    }
    public void printList(){
        while(this.peek()!=null){
            System.out.print(this.pop()+ " ");
        }
    }
    public static String applyOperation(String op1, String op2, String operator){
        int num1 = Integer.parseInt(op1);
        int num2 = Integer.parseInt(op2);

        String result = "";
        if(operator.equals("+")) result += (num1+num2);
        if(operator.equals("-")) result += (num1 - num2);
        if(operator.equals("*")) result += (num1 * num2);
        if(operator.equals("/") && num2==0) result += num1;
        else if(operator.equals("/")) result += num1/num2;

        return result;       
    }

    public static int calculationStack(String formula){
        HashMap<String, Integer> operations = new HashMap<>() {
            {
                put("*",3);
                put("/",3);
                put("+",2);
                put("-",2);
                put("(",1);
                put(")",1);
            }
        };

        String[] formulaList = formula.replace("+",",+,").replace("-",",-,").replace("*",",*,").replace("/",",/,").replace("(","(,").replace(")",",)").split(",");

        Stack operand = new Stack();
        Stack operation = new Stack();

        for(String str: formulaList){
            if(!operations.containsKey(str)) operand.push(str); //数字はoperand
            else if (str.equals("(")) operation.push(str);//"("はoperation
            else if (str.equals(")")){//　")"がきたらoperationのトップをoperandに移す"("まで　　　　
                String stackTop = operation.pop();
                while(!stackTop.equals("(")){
                    operand.push(stackTop);
                    stackTop = operation.pop();
                }
            }
            else{//"()"以外の演算子がきたら　
                while((operation.head != null) && (operations.get(operation.peek()) >= operations.get(str))){
                    operand.push(operation.pop());//operationのトップとstrを比べる連想配列で優劣 */はoperand
                }         
                operation.push(str);//残りはオペレーション           
            }
        }
        while(operation.head != null){
           operand.push(operation.pop());//operationに残った演算子をoperand
        }
        
        ArrayList<String> operandOperation = new ArrayList<>();
        while(operand.head != null){
            operandOperation.add(0,operand.pop());
        }

        Stack cal = new Stack();
        
        for(String op: operandOperation){
            if(!operations.containsKey(op)) cal.push(op);//数字をスタックに入れる
            
            else{
                String op1 = cal.pop();
                String op2 = cal.pop();
                cal.push(Stack.applyOperation(op2,op1,op));        
            }
        }
        
        int result = Integer.parseInt(cal.peek());
        return result;

    }
    
} 



class Main{
    public static void main(String[] args){
        System.out.println(Stack.calculationStack("(100+300)*5+(20-10)/10"));
        System.out.println((100+300)*5+(20-10)/10);
        System.out.println(Stack.calculationStack("(100+200)/3*100+1000/10"));
        System.out.println((100+200)/3*100+1000/10);
        
    }
}
