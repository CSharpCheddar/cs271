
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class MyNode<T extends Comparable<T>> {
    private MyNode<T> left, right;
    private T data;
    
    public MyNode(T data){
        this.data = data;
        left = null;
        right = null;
    }
    
    public void setLeft(MyNode<T> l){
        left = l;
    }
    
    public void setRight(MyNode<T> r){
        right = r;
    }
    
    public void setData(T d){
        data = d;
    }
    
    public MyNode<T> getLeft(){
        return left;
    }
    
    public MyNode<T> getRight(){
        return right;
    }
    
    public T getData(){
        return data;
    }
}
    
