// ここから開発してください
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;
class BinaryTree{
    public int data;
    public BinaryTree left;
    public BinaryTree right;



    public BinaryTree(int data){
        this.data = data;
    }    
    public BinaryTree(int data, BinaryTree left, BinaryTree right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public void printpreOrderwalk(){
        this.preOrderwalk(this);
        System.out.println("");
    }
    public void preOrderwalk(BinaryTree tRoot){
        if(tRoot !=null){
            preOrderwalk(tRoot.left);
            System.out.print(tRoot.data+ " ");
            preOrderwalk(tRoot.right);
        }
    }
}
class BinarySearchTree{
    public BinaryTree root;

    public BinarySearchTree(){
        
        this.root = root;
    }


    public void print(){
        this.root.printpreOrderwalk();
    }
    public BinaryTree succesor(int key){
        return successorHelper(this.root, key);
    }
    public BinaryTree successorHelper(BinaryTree root, int key){
        if(root == null) return null;//
        
        else if(key < root.data){ //キーが現在のdataより小さいずっと左へいく
            BinaryTree node = this.successorHelper(root.left, key);
            return (node == null)? root: node;
        }
        else if(key > root.data){ //キーがdataよりずっと大きいずっと右へ
            return this.successorHelper(root.right,key);
        }
        else{
            BinaryTree currNode= root.right; // キーとdataが同じ
            if(currNode == null) return null;  //右を探して
            while(currNode.left !=null){//左があると左はキーより小さい
                currNode = currNode.left;  //
            }
            return currNode;
        }
    }
    public boolean existKey(int key){
        BinaryTree iterator = this.root;
        while(iterator!=null){
            if(iterator.data == key) return true;
            else if(iterator.data > key) iterator = iterator.left;
            else iterator = iterator.right;
        }
        return false;
    }
    public BinaryTree seach(int key){
        BinaryTree iterator = this.root;
        while(iterator!=null){
            if(iterator.data > key) iterator = iterator.left;
            if(iterator.data < key) iterator = iterator.right;
            else return iterator;
        }
        return null;
    }
    public BinaryTree insert(int data){
        if(this.root == null) this.root = new BinaryTree(data);
        BinaryTree iterator = this.root;

        while(iterator!=null){
            if(iterator.data > data && iterator.left == null) iterator.left = new BinaryTree(data);
            else if(iterator.data < data && iterator.right == null) iterator.right = new BinaryTree(data);
            iterator = (iterator.data > data)? iterator.left: iterator.right;
        }
        return this.root;

    }
    public BinaryTree delete(int key){
        if(!existKey(key)) return this.root;//keyがないときは何もしない
        return deleteHelper(this.root, key);
    }
    public BinaryTree deleteHelper(BinaryTree root, int key){
        if(root == null) return null;
        if(key < root.data) root.left = this.deleteHelper(root.left,key);
        
        else if(root.data < key) root.right = this.deleteHelper(root.right,key);
        
        else{            
            //子を持っていない時
            if(root.left == null && root.right == null){
                root = null;           
                }
            //左の子だけ
            else if(root.right == null) root = root.left;          
            //右の子だけ
            else if(root.left == null) root = root.right;
            //子が2つ
            else{
                BinaryTree s = this.succesor(key);

                root.data = s.data;
                root.right = this.deleteHelper(root.right,s.data);
            }      
        }
               
        return root;
        
    }

    public static int maximumDepth(BinaryTree root){
        //ここから書きましょう
        if(root==null) return 0;
        int maxleft = maximumDepth(root.left);
        int maxright = maximumDepth(root.right);
        
        return (maxleft> maxright)? maxleft+1: maxright+1;
    }
}
class Main{
    public static void main(String[] args){
        
        BinarySearchTree balanceTree = new BinarySearchTree();
        
        int[] arr = new int[]{4,43,36,46,32,7,97,95,34,8,96,35,85,1010,232};
        ArrayList<Integer> list = new ArrayList<>();
        for(Integer num: arr) list.add(num);
        System.out.println(list);
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> remaining = new ArrayList<>(list);

        Random random = new Random();
        for (int i = 0; i < arr.length; i++) { 
            int remainingCount = remaining.size(); // 残っている要素の数
            int index = random.nextInt(remainingCount); // ランダムに選択されたインデックス
       
            int element = remaining.remove(index); // ランダムに選択された要素を取り出す。

            result.add(element); // ランダムに選択された要素を持たせるリストの末尾に、ランダムに選択された要素を追加する。
            balanceTree.insert(element);

        }

        balanceTree.print();
        System.out.println("最大の高さ"+BinarySearchTree.maximumDepth(balanceTree.root));
        
    }
}
