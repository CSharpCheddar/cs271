/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author krohne
 */
public class MyAVLTree {
    private MyNode root;    
    
    public MyAVLTree(){
        root = null;
    }
    
    private int removeSingleParents(MyNode node) {
    	//if we hit a leaf/have an empty tree
    	if(node == null) {
    		return 0;
    	}
    	//check to see if there's only 1 child
    	if(node.getLeft() == null && node.getRight() != null) {
    		if(node.getParent().getLeft() == node) {
    			node.getParent().setLeft(node.getRight());
    		} else {
    			node.getParent().setRight(node.getRight());
    		}
    		node.getRight().setParent(node.getParent());
    		if(node == root) {
    			root = node.getRight();
    		}
    		//update the heights
            updateHeights(node.getRight());
            //check for unbalanced trees
            checkUnbalanced(node.getRight());
    		return 1 + removeSingleParents(node.getRight());
    	} else if(node.getLeft() != null && node.getRight() == null) {
    		if(node.getParent().getLeft() == node) {
    			node.getParent().setLeft(node.getLeft());
    		} else {
    			node.getParent().setRight(node.getLeft());
    		}
    		node.getLeft().setParent(node.getParent());
    		if(node == root) {
    			root = node.getLeft();
    		}
    		//update the heights
            updateHeights(node.getLeft());
            //check for unbalanced trees
            checkUnbalanced(node.getLeft());
    		return 1 + removeSingleParents(node.getLeft());
    	}
    	//if we can keep looking for parents, go
    	return removeSingleParents(node.getLeft()) + removeSingleParents(node.getRight());
    }
    
    /*
     * Removes all nodes that have only 1 child and 
     * returns the number of nodes that were removed.
     * 
     * Runtime: \Theta(n)
     */
    public int removeSingleParents() {
    	return removeSingleParents(root);
    }
    
    private boolean remove(int data, MyNode node) {
    	if(node == null) {
    		return false;
    	}
    	if(node.getData() == data) {
    		if(node.getLeft()==null && node.getRight()==null){
    			if(node.getParent()==null){
    				//removing the only element from the tree
    				root = null;
    			} else{                    
    				if(node.getData() > node.getParent().getData()){
    					node.getParent().setRight(null);
    				} else {
    					node.getParent().setLeft(null);
    				}
    			}
    		} else if(node.getLeft() == null){
    			//case where we only have a right child
    			if(node.getParent()==null){
    				root = node.getRight();
    			} else{
    				if(node.getData() > node.getData()){
    					node.getParent().setRight(node.getRight());
    				} else{
    					node.getParent().setLeft(node.getRight());
    				}
    			}
    		} else{
    			//current node we want to remove has a left child, find replacement
    			//node to remove has a left child                
    			MyNode replacement = node.getLeft();
    			if(replacement.getRight()==null){
    				//If the left child has no right child, move left child's data up and remove left child
    				node.setData(replacement.getData());
    				node.setLeft(replacement.getLeft());
    			} else{
    				//left child has right children, go as far right as possible
    				MyNode replacementParent = replacement;
    				while(replacement.getRight()!=null){
    					replacementParent = replacement;
    					replacement = replacement.getRight();
    				}
    				node.setData(replacement.getData());
    				replacementParent.setRight(replacement.getLeft());
    			}
    		}
    		updateHeights(node.getParent());
    		checkUnbalanced(node.getParent());
    		return true;
    	} else if(data > node.getData()) {
    		return remove(data, node.getRight());
    	} else {
    		return remove(data, node.getLeft());
    	}
    }
    
    /*
     * Removes 1 instance of data from the tree. 
     * For example, if 10 were inserted 5 times into the tree, 
     * then remove(10) would remove just 1 of those instances. 
     * The other 4 would remain. 
     * This method returns true if data was actually removed, false otherwise.
     * 
     * Runtime: \Theta(log(n))
     */
    public boolean remove(int data) {
    	return remove(data, root);
    }

	private String printLevel(int k, MyNode node, String string) {
    	if(node == null) {
    		return "";
    	}
    	if(k == 0) {
    		return node.getData()+" ";
    	}
    	return printLevel(k-1, node.getLeft(), string) + printLevel(k-1, node.getRight(), string);
    }
    
    /*
     * Prints out the values of the tree at level k. 
     * If there are no nodes at level k, a message stating 
     * there are no values at level k is printed out.
     * 
     * Runtime: \Theta(n)
     */
    public void printLevel(int k) {
    	String string = printLevel(k, root, "");
    	if(string.equals("")) {
    		System.out.println("There are no nodes at level "+k+".");
    	} else {
    		System.out.println(string);
    	}
    }
    
    //works without changes
    private void inorder(MyNode current){
        if(current==null){
            return;
        }
        inorder(current.getLeft());
        System.out.println("Data: " + current.getData() + "\tHeight: " + current.getHeight() + "\tBalance Factor: " + getBalanceFactor(current));
        inorder(current.getRight());
    }
    
    //works without changes
    public void inorder(){
        inorder(root);
    }
    
    //works without changes
    private void postorder(MyNode current){
        if(current==null){
            return;
        }
        postorder(current.getLeft());
        postorder(current.getRight());
        System.out.print(current.getData() + " ");

    }
    
    //works without changes
    public void postorder(){
        postorder(root);
    }
    
    //works without changes
    private void preorder(MyNode current){
        if(current==null){
            return;
        }
        System.out.print(current.getData() + " ");
        preorder(current.getLeft());
        preorder(current.getRight());
    }
    
    //works without changes
    public void preorder(){
        preorder(root);
    }
    
    //DOES NOT WORK WITH AVL TREE, NEEDS TO BE UPDATED
//    public void remove(int value){
//        if(contains(value)){
//            //we know our tree contains value
//            MyNode parent = null;
//            MyNode current = root;
//            while(current.getData() != value){
//                parent = current;
//                if(current.getData() < value){                    
//                    current = current.getRight();
//                } else{                    
//                    current = current.getLeft();
//                }
//            }
//            //current contains the value we want to remove
//            //parent is the parent of current
//            
//            //removing a leaf
//            if(current.getLeft()==null && current.getRight()==null){
//                if(parent==null){
//                    //removing the only element from the tree
//                    root = null;
//                } else{                    
//                    if(current.getData() > parent.getData()){
//                        parent.setRight(null);
//                    } else{
//                        parent.setLeft(null);
//                    }
//                }
//            } else if(current.getLeft() == null){
//                //case where we only have a right child
//                if(parent==null){
//                    root = current.getRight();
//                } else{
//                    if(current.getData() > parent.getData()){
//                        parent.setRight(current.getRight());
//                    } else{
//                        parent.setLeft(current.getRight());
//                    }
//                }
//            } else{
//                //current node we want to remove has a left child, find replacement
//                //node to remove has a left child                
//                MyNode replacement = current.getLeft();
//                if(replacement.getRight()==null){
//                    //If the left child has no right child, move left child's data up and remove left child
//                    current.setData(replacement.getData());
//                    current.setLeft(replacement.getLeft());
//                } else{
//                    //left child has right children, go as far right as possible
//                    MyNode replacementParent = replacement;
//                    while(replacement.getRight()!=null){
//                        replacementParent = replacement;
//                        replacement = replacement.getRight();
//                    }
//                    current.setData(replacement.getData());
//                    replacementParent.setRight(replacement.getLeft());
//                }
//            }
//        }
//        
//    }
    
    //works without changes
    public boolean contains(int value){
        MyNode current = root;
        while(current != null){
            if(current.getData()==value){
                return true;
            } else if(current.getData() < value){
                current = current.getRight();
            } else{
                current = current.getLeft();
            }
        }
        return false;
    }
    
    //works without changes
    public void insert(int value){
        if(root==null){
            root = new MyNode(value);
        } else{
            MyNode current = root;
            boolean finished = false;
            MyNode temp = new MyNode(value);
            while(!finished){
                if(current.getData() < value){
                    //go to the right
                    if(current.getRight()==null){
                        //we've found our place to insert                        
                        current.setRight(temp);
                        temp.setParent(current);
                        finished = true;
                    } else{
                        current = current.getRight();
                    }
                } else{
                    //go to the left
                    if(current.getLeft()==null){
                        //we've found our place to insert
                        current.setLeft(temp);
                        temp.setParent(current);
                        finished = true;
                    } else{
                        current = current.getLeft();
                    }
                }
            }
            //update the heights
            updateHeights(temp);
            //check for unbalanced trees
            checkUnbalanced(temp);
        }
    }
    
    private int getBalanceFactor(MyNode current){
        int heightLeftChild = current.getLeft()==null ? -1 : current.getLeft().getHeight();
        int heightRightChild = current.getRight()==null ? -1 : current.getRight().getHeight(); 
        return heightRightChild - heightLeftChild;    
    }

    private void rotateRight(MyNode current){
        MyNode originalParent = current.getParent();
        MyNode grandparent = originalParent.getParent();
        MyNode originalRightChild = current.getRight();
        
        current.setParent(grandparent);
        if(grandparent == null){
            root = current;
        } else{
            if(grandparent.getData() < current.getData()){
                //to the right of the grandparent
                grandparent.setRight(current);
            } else{
                grandparent.setLeft(current);
            }
        }
        
        current.setRight(originalParent);
        originalParent.setParent(current);
        originalParent.setLeft(originalRightChild);
        
        if(originalRightChild != null){
            originalRightChild.setParent(originalParent);
        }
    }
    
    private void rotateLeft(MyNode current){
        MyNode originalParent = current.getParent();
        MyNode grandparent = originalParent.getParent();
        MyNode originalLeftChild = current.getLeft();
        
        current.setParent(grandparent);
        if(grandparent == null){
            root = current;
        } else{
            if(grandparent.getData() < current.getData()){
                //to the right of the grandparent
                grandparent.setRight(current);
            } else{
                grandparent.setLeft(current);
            }
        }
        
        current.setLeft(originalParent);
        originalParent.setParent(current);
        originalParent.setRight(originalLeftChild);
        
        if(originalLeftChild != null){
            originalLeftChild.setParent(originalParent);
        }
    }
    
    private void checkUnbalanced(MyNode current){
        if(current!=null){
            int heightLeftChild = current.getLeft()==null ? -1 : current.getLeft().getHeight();
            int heightRightChild = current.getRight()==null ? -1 : current.getRight().getHeight(); 
            int balanceFactor = heightRightChild - heightLeftChild;
            if(balanceFactor==0 || balanceFactor==1 || balanceFactor==-1){
                //current is balanced, check parent
                checkUnbalanced(current.getParent());
            } else{
                //current is unbalanced, why are we unbalanced?
                //right right
                if(balanceFactor == 2 && getBalanceFactor(current.getRight()) == 1){
                    rotateLeft(current.getRight());
                    updateHeights(current);
                } else if(balanceFactor == 2 && getBalanceFactor(current.getRight()) == -1){
                    //right left
                    rotateRight(current.getRight().getLeft());
                    rotateLeft(current.getRight());
                    updateHeights(current);
                    updateHeights(current.getParent().getRight());
                } else if(balanceFactor == -2 && getBalanceFactor(current.getLeft()) == -1){
                    //left left
                    rotateRight(current.getLeft());
                    updateHeights(current);
                } else if(balanceFactor == -2 && getBalanceFactor(current.getLeft()) == 1){
                    //left right
                    rotateLeft(current.getLeft().getRight());
                    rotateRight(current.getLeft());
                    updateHeights(current);
                    updateHeights(current.getParent().getLeft());
                }
                
            }
        }
    }
    
    private void updateHeights(MyNode current){        
        if(current!=null){
            int heightLeftChild = current.getLeft()==null ? -1 : current.getLeft().getHeight();
            int heightRightChild = current.getRight()==null ? -1 : current.getRight().getHeight(); 
            current.setHeight(Math.max(heightLeftChild, heightRightChild)+1);
            updateHeights(current.getParent());
        }        
    }
}
