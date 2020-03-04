/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class MyBST<T extends Comparable<T>> {
    private MyNode<T> root;    
    
    public MyBST(){
        root = null;
    }
    
    private void inorder(MyNode<T> current){
        if(current==null){
            return;
        }
        inorder(current.getLeft());
        System.out.print(current.getData() + " ");
        inorder(current.getRight());
    }
    
    public void inorder(){
        inorder(root);
    }
    
    private void postorder(MyNode<T> current){
        if(current==null){
            return;
        }
        postorder(current.getLeft());
        postorder(current.getRight());
        System.out.print(current.getData() + " ");

    }
    
    public void postorder(){
        postorder(root);
    }
    
    private void preorder(MyNode<T> current){
        if(current==null){
            return;
        }
        System.out.print(current.getData() + " ");
        preorder(current.getLeft());
        preorder(current.getRight());
    }
    
    public void preorder(){
        preorder(root);
    }
    
    public void remove(T value){
        if(contains(value)){
            //we know our tree contains value
            MyNode<T> parent = null;
            MyNode<T> current = root;
            while(current.getData().compareTo(value) != 0){
                parent = current;
                if(current.getData().compareTo(value) < 0){                    
                    current = current.getRight();
                } else{                    
                    current = current.getLeft();
                }
            }
            //current contains the value we want to remove
            //parent is the parent of current
            
            //removing a leaf
            if(current.getLeft()==null && current.getRight()==null){
                if(parent==null){
                    //removing the only element from the tree
                    root = null;
                } else{                    
                    if(current.getData().compareTo(value) > parent.getData().compareTo(value)){
                        parent.setRight(null);
                    } else{
                        parent.setLeft(null);
                    }
                }
            } else if(current.getLeft() == null){
                //case where we only have a right child
                if(parent==null){
                    root = current.getRight();
                } else{
                    if(current.getData().compareTo(value) > parent.getData().compareTo(value)){
                        parent.setRight(current.getRight());
                    } else{
                        parent.setLeft(current.getRight());
                    }
                }
            } else{
                //current node we want to remove has a left child, find replacement
                //node to remove has a left child                
                MyNode<T> replacement = current.getLeft();
                if(replacement.getRight()==null){
                    //If the left child has no right child, move left child's data up and remove left child
                    current.setData(replacement.getData());
                    current.setLeft(replacement.getLeft());
                } else{
                    //left child has right children, go as far right as possible
                    MyNode<T> replacementParent = replacement;
                    while(replacement.getRight()!=null){
                        replacementParent = replacement;
                        replacement = replacement.getRight();
                    }
                    current.setData(replacement.getData());
                    replacementParent.setRight(replacement.getLeft());
                }
            }
        }
        
    }
    
    public boolean contains(T value){
        MyNode<T> current = root;
        while(current != null){
            if(current.getData().compareTo(value) == 0){
                return true;
            } else if(current.getData().compareTo(value) < 0){
                current = current.getRight();
            } else{
                current = current.getLeft();
            }
        }
        return false;
    }
    
    public void insert(T value){
        if(root==null){
            root = new MyNode<T>(value);
            //root.setLeft(null);
            //root.setRight(null);
        } else{
            MyNode<T> current = root;
            boolean finished = false;
            while(!finished){
                if(current.getData().compareTo(value) < 0){
                    //go to the right
                    if(current.getRight()==null){
                        //we've found our place to insert
                        MyNode<T> temp = new MyNode<>(value);
                        current.setRight(temp);
                        finished = true;
                    } else{
                        current = current.getRight();
                    }
                } else{
                    //go to the left
                    if(current.getLeft()==null){
                        //we've found our place to insert
                        MyNode<T> temp = new MyNode<>(value);
                        current.setLeft(temp);
                        finished = true;
                    } else{
                        current = current.getLeft();
                    }
                }
            }
                    
            
        
        }
    }
}
