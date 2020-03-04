/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class MyQueue {
    
    private MyNode head, tail;
    
    public MyQueue(){
        head = null;
        tail = null;
    }
    
    public boolean isEmpty(){
        return head==null;
    }
    
    public MyCircle[] dequeueMany(int k){
        if(k<0){
            throw new RuntimeException("Invalid k value");
        }
        if(k==0){
            return new MyCircle[0];
        }
        
        //ensure there are k values in the queue
        MyNode current = head;
        MyCircle[] array = new MyCircle[k];
        for(int i=0; i<k; i++){
            if(current==null){
                throw new RuntimeException("k is too big");
            }
            array[i] = current.getData();
            current = current.getNext();
        }
        head = current;
        //go ahead and remove k circles, return them
        /*MyCircle[] array = new MyCircle[k];
        for(int i=0; i<k; i++){
            array[i] = dequeue();
        }*/
        return array;
    }
    
    public MyCircle dequeue(){
        //if there are no elements in the queue, exception
        if(head==null){
            throw new RuntimeException("Empty queue");
        }
        
        MyCircle returnMe = head.getData();
        head = head.getNext();
        if(head==null){
            tail = null;
        }
        
        return returnMe;
    }
    
    //runtime = \Theta(1)
    public void enqueue(MyCircle temp){
        MyNode insertMe = new MyNode(temp, null);
       //if there was no list, start it
       if(head==null){
           head = insertMe;
           tail = insertMe; //tail = head;
       } else{
            tail.setNext(insertMe);
            tail = insertMe;
       }
    }
    
}
