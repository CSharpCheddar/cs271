package trees;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne, muellm79
 */
public class MyNode {
    private MyNode left, right, parent;
    private int data;
    
    public MyNode(int data){
        this.data = data;
        left = null;
        right = null;
        parent = null;
    }
    
    public void setLeft(MyNode l){
        left = l;
    }
    
    public void setRight(MyNode r){
        right = r;
    }
    
    public void setParent(MyNode p) {
    	parent = p;
    }
    
    public void setData(int d){
        data = d;
    }
    
    public MyNode getLeft(){
        return left;
    }
    
    public MyNode getRight(){
        return right;
    }
    
    public MyNode getParent() {
    	return parent;
    }
    
    public int getData(){
        return data;
    }
}
    
