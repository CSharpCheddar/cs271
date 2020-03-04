/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trees;

/**
 *
 * @author krohne
 */
public class ProjectTwo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("A)");
        try{
            MyBST tree = new MyBST();
            tree.insert(1);
            tree.remove(1);
            System.out.println("False: "+tree.contains(1));
        } catch(Exception e){
            System.out.println("Removing only root failed.");
        }
        
        System.out.println("\n------------\nB)");
        try{
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
            MyBST tree = new MyBST();
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
        
        //Project 2 tests
        System.out.println("\n------------\nP2 Tests)");
        MyBST tree = new MyBST();
        int[] array = {10, 5, 15, 2, 8, 12, 17, 1, 3, 4, 6, 7, 9, 11, 14, 13, 16, 19, 18, 20};
        for(int i=0; i<array.length; i++) {
        	tree.insert(array[i]);
        }
        System.out.print("Numbers 1-20: ");
        tree.print();
        System.out.print("\n20: ");
        tree.numberNodes();
        System.out.print("Should insert all values of a list (1-31) into the tree: ");
        tree = new MyBST();
        int[] arrayTwo = {16, 8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15, 24, 20, 18, 17, 19, 22, 21, 23, 28, 26, 25, 27, 30, 29, 31};
        tree.insertList(arrayTwo);
        tree.print();
        System.out.print("\nShould remove 20 nodes less than 21: "+tree.removeLessThan(21));
        System.out.print("\nNodes Remaining: ");
        tree.print();
        System.out.print("\nNumber of leaves (6): "+tree.numberOfLeaves());
        System.out.print("\nNumber of full parents (5): "+tree.numberOfFullParents());
        System.out.print("\nHow many at level 2 (4): "+tree.howManyAtLevel(2));
        System.out.print("\nRemove all 6 leaves: "+tree.removeAllLeaves());
        System.out.print("\nNodes Remaining: ");
        tree.print();
    }
    
}