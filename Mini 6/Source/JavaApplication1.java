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
        MyDoublyLinkedList list = new MyDoublyLinkedList();
        list.insert(new MyCircle(20));
        list.insert(new MyCircle(30));
        list.insert(new MyCircle(26));
        list.insert(new MyCircle(28));
        list.print();
        list.remove(3);
        System.out.println("------");
        list.print();
        //test methods for Mini Assignment 6
        System.out.println("------");
        list.insert(0, new MyCircle(10));
        list.insert(3, new MyCircle(40));
        list.insert(2, new MyCircle(15));
        list.print();
        list.insert(-1, new MyCircle(50));
        list.insert(6, new MyCircle(60));
    }
    
}
