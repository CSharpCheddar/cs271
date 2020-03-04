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
            if(current.getNext()!=null){
                current.getNext().setPrevious(current);
            }
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
   
   /**
    * Inserts a MyCircle at index k.
    * 
    * @param k index at which to insert the circle
    * @param circ the circle to be inserted
    */
   public void insert(int k, MyCircle circ) {
	   MyDoublyNode node = new MyDoublyNode(circ);
	   if(k < 0) {
		   System.out.println("Invalid index");
	   } else if(k == 0) {
		   if(head == null) {
			   head = new MyDoublyNode(circ);
		   } else {
			   head.setPrevious(node);
			   node.setNext(head);
			   head = node;
		   }
	   } else {
		   MyDoublyNode current = head;
		   int maxIndex = 0;
		   while(current.getNext() != null) {
			   current = current.getNext();
			   maxIndex++;
		   }
		   if(k > maxIndex) {
			   System.out.println("Index out of bounds");
		   } else if(k == maxIndex) {
			   current.setNext(node);
			   node.setPrevious(current);
		   } else {
			   current = head;
			   for(int i=0; i < k; i++) {
				   current = current.getNext();
			   }
			   node.setPrevious(current.getPrevious());
			   node.setNext(current);
			   current.getPrevious().setNext(node);
			   current.setPrevious(node);
		   }
	   }
   }
}
