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
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyBST tree = new MyBST();
        tree.insert(1);
        tree.insert(5);
        tree.insert(2);
        tree.insert(3);
        tree.insert(10);
        tree.insert(4);
        tree.insert(8);
        //remove all the elements
        tree.remove(1);
        tree.remove(5);
        tree.remove(2);
        tree.remove(3);
        tree.remove(10);
        tree.remove(4);
        tree.remove(8);
        for(int i=1; i<15; i++){
            System.out.println(i + " "+tree.contains(i));
        
        }
    }
    
}
