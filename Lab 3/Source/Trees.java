/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krohne
 */
public class Trees {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("A)");
        try{
            MyBST<Integer> tree = new MyBST<>();
            tree.insert(1);
            tree.remove(1);
            System.out.println("False: "+tree.contains(1));
        } catch(Exception e){
            System.out.println("Removing only root failed.");
        }
        
        System.out.println("\n------------\nB)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(1);
            tree.insert(3);
            tree.insert(2);
            tree.insert(4);
            tree.remove(1);
            System.out.println("False: "+tree.contains(1));
            System.out.println("True: "+tree.contains(2));
            System.out.println("True: "+tree.contains(3));
            System.out.println("True: "+tree.contains(4));
        } catch(Exception e){
            System.out.println("Removing root with only right child failed.");
        }
        
        System.out.println("\n------------\nC)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(4);
            tree.insert(2);
            tree.insert(3);
            tree.insert(1);
            tree.remove(4);
            System.out.println("False: "+tree.contains(4));
            System.out.println("True: "+tree.contains(2));
            System.out.println("True: "+tree.contains(3));
            System.out.println("True: "+tree.contains(1));
        } catch(Exception e){
            System.out.println("Removing root with only left child failed.");
        }
        
        System.out.println("\n------------\nD)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(1);
            tree.insert(2);
            tree.remove(2);
            System.out.println("True: "+tree.contains(1));
            System.out.println("False: "+tree.contains(2));
        } catch(Exception e){
            System.out.println("Removing root right child leaf failed.");
        }
        
        System.out.println("\n------------\nE)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(2);
            tree.insert(1);
            tree.remove(1);
            System.out.println("False: "+tree.contains(1));
            System.out.println("True: "+tree.contains(2));
        } catch(Exception e){
            System.out.println("Removing root left child leaf failed.");
        }
        
        System.out.println("\n------------\nF)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(10);
            tree.insert(5);
            tree.insert(4);
            tree.insert(7);
            tree.insert(6);
            tree.insert(9);
            tree.insert(8);
            tree.remove(10);
            System.out.println("False: "+tree.contains(10));
            System.out.println("True: "+tree.contains(4));
            System.out.println("True: "+tree.contains(5));
            System.out.println("True: "+tree.contains(6));
            System.out.println("True: "+tree.contains(7));
            System.out.println("True: "+tree.contains(8));
            System.out.println("True: "+tree.contains(9));
        } catch(Exception e){
            System.out.println("Removing root with left child failed.");
        }
        
        System.out.println("\n------------\nG)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(11);
            tree.insert(10);
            tree.insert(5);
            tree.insert(4);
            tree.insert(7);
            tree.insert(6);
            tree.insert(9);
            tree.insert(8);
            tree.remove(10);
            System.out.println("False: "+tree.contains(10));
            System.out.println("True: "+tree.contains(4));
            System.out.println("True: "+tree.contains(5));
            System.out.println("True: "+tree.contains(6));
            System.out.println("True: "+tree.contains(7));
            System.out.println("True: "+tree.contains(8));
            System.out.println("True: "+tree.contains(9));
            System.out.println("True: "+tree.contains(11));
        } catch(Exception e){
            System.out.println("Removing left child with left child failed.");
        }
        
        System.out.println("\n------------\nH)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(0);
            tree.insert(10);
            tree.insert(5);
            tree.insert(4);
            tree.insert(7);
            tree.insert(6);
            tree.insert(9);
            tree.insert(8);
            tree.remove(10);
            System.out.println("False: "+tree.contains(10));
            System.out.println("True: "+tree.contains(4));
            System.out.println("True: "+tree.contains(5));
            System.out.println("True: "+tree.contains(6));
            System.out.println("True: "+tree.contains(7));
            System.out.println("True: "+tree.contains(8));
            System.out.println("True: "+tree.contains(9));
            System.out.println("True: "+tree.contains(0));
        } catch(Exception e){
            System.out.println("Removing right child with left child failed.");
        }
        
        System.out.println("\n------------\nI)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(13);
            tree.insert(12);
            tree.insert(10);
            tree.insert(5);
            tree.insert(4);
            tree.insert(7);
            tree.insert(6);
            tree.insert(9);
            tree.insert(8);
            tree.remove(12);
            System.out.println("False: "+tree.contains(12));
            System.out.println("True: "+tree.contains(4));
            System.out.println("True: "+tree.contains(5));
            System.out.println("True: "+tree.contains(6));
            System.out.println("True: "+tree.contains(7));
            System.out.println("True: "+tree.contains(8));
            System.out.println("True: "+tree.contains(9));
            System.out.println("True: "+tree.contains(13));
            System.out.println("True: "+tree.contains(10));
        } catch(Exception e){
            System.out.println("Removing left child with no right child failed.");
        }
        
        System.out.println("\n------------\nJ)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(0);
            tree.insert(1);
            tree.insert(3);
            tree.insert(2);
            tree.insert(4);
            tree.remove(1);
            System.out.println("True: "+tree.contains(0));
            System.out.println("False: "+tree.contains(1));
            System.out.println("True: "+tree.contains(2));
            System.out.println("True: "+tree.contains(3));
            System.out.println("True: "+tree.contains(4));
        } catch(Exception e){
            System.out.println("Removing right child with only right child failed.");
        }
        
        System.out.println("\n------------\nK)");
        try{
        	MyBST<Integer> tree = new MyBST<>();
            tree.insert(5);
            tree.insert(1);
            tree.insert(3);
            tree.insert(2);
            tree.insert(4);
            tree.remove(1);
            System.out.println("True: "+tree.contains(5));
            System.out.println("False: "+tree.contains(1));
            System.out.println("True: "+tree.contains(2));
            System.out.println("True: "+tree.contains(3));
            System.out.println("True: "+tree.contains(4));
        } catch(Exception e){
            System.out.println("Removing left child with only right child failed.");
        }
        
        //code added for Lab 3
        MyBST<Integer> myInts = new MyBST<>();
        myInts.insert(new Integer(10));
        myInts.insert(new Integer(6));
        myInts.insert(new Integer(14));
        myInts.insert(new Integer(12));
        myInts.insert(new Integer(7));
        myInts.inorder(); //prints 6 7 10 12 14

        MyBST<Double> myDoubles = new MyBST<>();
        myDoubles.insert(new Double(10.0));
        myDoubles.insert(new Double(6.0));
        myDoubles.insert(new Double(14.0));
        myDoubles.insert(new Double(12.0));
        myDoubles.insert(new Double(7.0));
        myDoubles.inorder(); //prints 6.0 7.0 10.0 12.0 14.0
        
    }
    
}