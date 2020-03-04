/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class MyDoublyLinkedList {
    
    private MyDoublyNode head;
    private int numberElements;
   
    public MyDoublyLinkedList(){
        numberElements = 0;
        head = null;
    }
   
   //runtime = \Theta(n)
   public void remove(int index){
       if(index >= numberElements || index < 0){
           throw new RuntimeException("Index too big or too small!");
       }
       
       //we know that index is something that can be removed
       if(index==0){
           head = head.getNext();
           head.setPrevious(null);
       } else{
            int currentAt = 0;
            MyDoublyNode current = head;
            while(currentAt < index-1){
                current = current.getNext();
                currentAt++;
            }
            current.setNext(current.getNext().getNext());
       }
       numberElements--;
   }
   
   //runtime = \Theta(n)
   public boolean contains(MyCircle other){
       //loop through the list until getNext is null
       MyDoublyNode current = head;
       while(current != null){
           if(current.getData() == other){
               return true;
           }
           current = current.getNext();
       }
       return false;
   }
   
   //runtime = \Theta(n)
   public void print(){
       //loop through the list until getNext is null
       MyDoublyNode current = head;
       while(current != null){
           System.out.println(current.getData());
           current = current.getNext();
       }
   }
   
   //runtime = \Theta(n)
   //if you care about ordering the circles were inserted in
   public void insert(MyCircle m){
       MyDoublyNode insertMe = new MyDoublyNode(m, null, null);
       //if there was no list, start it
       if(head==null){
           head = insertMe;
       } else{
            //loop through the list until getNext is null
            MyDoublyNode current = head;
            while(current.getNext()!=null){
                current = current.getNext();
            }
            current.setNext(insertMe);
            insertMe.setPrevious(current);
       }
       numberElements++;       
   }
    
}
