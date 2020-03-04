/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class MyArrayList {
   private MyCircle[] data;
   private int numberElements;
   
   public MyArrayList(){
       data = new MyCircle[10];
       numberElements = 0;
   }
   
   //runtime = \Theta(n)
   public void remove(int index){
       for(int start = index; start < numberElements; start++){
           data[start] = data[start+1];
       }
       numberElements--;
   }
   
   //runtime = \Theta(n)
   public boolean contains(MyCircle other){
	   for(int index = 0; index < numberElements; index++){
		   if(other==data[index]){
			   return true;
		   }
	   }
	   return false;
   }
   
   //runtime = \Theta(n)
   public void print(){
       for(int index = 0; index < numberElements; index++){
           System.out.println("Circle is: "+ data[index].getRadius());
       }
   }
   
   //runtime = \Theta(1)
   public MyCircle get(int index){
       //ensure the element exists
       if(index < numberElements){
           return data[index];
       }
       throw new ArrayIndexOutOfBoundsException("Index out of range");
   }
   
   //runtime = \Theta(n)
   public void insert(MyCircle m){
       if(numberElements < data.length){
           data[numberElements++] = m;  
       } else{
           //create a bigger array so we can store new circle
           MyCircle[] biggerArray = new MyCircle[data.length*2];
           //copy original circles from data to biggerArray
           for(int index = 0; index < data.length; index++){
               biggerArray[index] = data[index];
           }
           biggerArray[numberElements++] = m;
           data = biggerArray;
       }
       
   }
}














