/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class MyDoublyNode {
    private MyCircle element;
    private MyDoublyNode next, previous;
    
    public MyDoublyNode(){
        element = null;
        next = null;
        previous = null;
    }
    
    public MyDoublyNode(MyCircle data){
        element = data;
    }
    
    public MyDoublyNode(MyCircle data, MyDoublyNode next, MyDoublyNode previous){
        element = data;
        this.next = next;
        this.previous = previous;
    }
    
    public void setPrevious(MyDoublyNode previous){
        this.previous = previous;
    }
    
    public MyDoublyNode getPrevious(){
        return previous;
    }
    
    public void setNext(MyDoublyNode next){
        this.next = next;
    }
    
    public MyDoublyNode getNext(){
        return next;
    }
    
    public void setData(MyCircle data){
        element = data;
    }
    
    public MyCircle getData(){
        return element;
    }
    
}
