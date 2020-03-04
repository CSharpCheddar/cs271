/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class MyNode {
    private MyCircle element;
    private MyNode next;
    
    public MyNode(){
        element = null;
        next = null;
    }
    
    public MyNode(MyCircle data){
        element = data;
    }
    
    public MyNode(MyCircle data, MyNode next){
        element = data;
        this.next = next;
    }
    
    public void setNext(MyNode next){
        this.next = next;
    }
    
    public MyNode getNext(){
        return next;
    }
    
    public void setData(MyCircle data){
        element = data;
    }
    
    public MyCircle getData(){
        return element;
    }
    
}
