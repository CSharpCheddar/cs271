/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	MyQueue mq = new MyQueue();
    	mq.enqueue(new MyCircle(10));
    	mq.enqueue(new MyCircle(15));
    	try{
    	  mq.dequeueMany(4);
    	} catch(RuntimeException r){
    	  System.out.println(r);
    	}
    	System.out.println(mq.dequeue());  //prints out 10
    	System.out.println(mq.dequeue());  //prints out 15
    }
    
}
