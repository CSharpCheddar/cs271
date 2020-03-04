/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

/**
 *
 * @author krohne, muellm79
 */
public class MyBST {
    private MyNode root;    
    
    public MyBST(){
        root = null;
    }
    
    /**
     * Back end method to help remove all leaves from the tree.
     * Runtime: (see public method of the same name for the overall runtime)
     * @param node The node at which to start
     * @return the number of leaves removed from the tree
     */
    private int removeAllLeaves(MyNode node) {
    	if(node == null) {
    		return 0;
    	}
    	if(node.getLeft() == null && node.getRight() == null) {
    		if(node == root) {
    			root = null;
    			return 1;
    		} else if(node.getParent().getLeft() == node) {
    			node.getParent().setLeft(null);
    			return 1;
    		} else {
    			node.getParent().setRight(null);
    			return 1;
    		}
    	}
    	return removeAllLeaves(node.getLeft()) + removeAllLeaves(node.getRight());
    }
    
    /**
     * Removes all the leaves from the tree.
     * Runtime: Theta(n)
     * @return the number of leaves that were removed
     */
    public int removeAllLeaves() {
    	return removeAllLeaves(root);
    }
    
    /**
     * Back end method to help determine the number of nodes at a particular level.
     * Runtime: (see public method of the same name for the overall runtime)
     * @param node The node currently being examined
     * @param levelsLeft how many levels left until the method returns
     * @return the number of nodes at a given level
     */
    private int howManyAtLevel(MyNode node, int levelsLeft) {
    	if(node == null) {
    		return 0;
    	}
    	if(levelsLeft == 0) {
    		return 1;
    	}
    	return howManyAtLevel(node.getLeft(), levelsLeft-1) + howManyAtLevel(node.getRight(), levelsLeft-1);
    }
    
    /**
     * Returns the number of nodes at a particular level.
     * Runtime: Theta(n)
     * @param level The level at which the number of nodes is to be counted
     * @return the number of nodes at the given level
     */
    public int howManyAtLevel(int level) {
    	return howManyAtLevel(root, level);
    }
    
    /**
     * Back end method to help determine the number of nodes with 2 parents in the tree.
     * Runtime: (see public method of the same name for the overall runtime)
     * @param node the node the method is currently examining
     * @return the number of nodes with 2 children in the tree or subtree starting
     * 		   at the node initially fed into the method
     */
    private int numberOfFullParents(MyNode node) {
    	if(node == null) {
    		return 0;
    	}
    	if(node.getLeft() != null && node.getRight() != null) {
    		return 1 + numberOfFullParents(node.getLeft()) + numberOfFullParents(node.getRight());
    	}
    	return numberOfFullParents(node.getLeft()) + numberOfFullParents(node.getRight());
    }
    
    /**
     * Gets the number of parents with 2 children.
     * Runtime: Theta(n)
     * @return the number of parents with 2 children
     */
    public int numberOfFullParents() {
    	return numberOfFullParents(root);
    }
    
    /**
     * Back end method to help determine the number of leaves in the tree.
     * Runtime: (see public method of the same name for the overall runtime)
     * @param node The node currently being examined
     * @return the number of leaves in the tree or subtree starting
     * 		   at the node initially fed into the method
     */
    private int numberOfLeaves(MyNode node) {
    	if(node == null) {
    		return 0;
    	}
    	if(node.getLeft() == null && node.getRight() == null) {
    		return 1;
    	}
    	return numberOfLeaves(node.getLeft()) + numberOfLeaves(node.getRight());
    }
    
    /**
     * Finds the number of leaves in the tree.
     * Runtime: Theta(n)
     * @return the number of leaves in the tree
     */
    public int numberOfLeaves() {
    	return numberOfLeaves(root);
    }
    
    /**
     * A back end recursive method for the public method of the same name.
     * Runtime: (see public method of the same name for the overall runtime)
     * @param node The node currently being compared to the value
     * @param value The cutoff value that determines which nodes will be removed 
     * @param originalNodes The amount of nodes originally in the tree
     */
    private int removeLessThan(MyNode node, int value) {
    	if(node == null) {
    		return 0;
    	}
    	if(node.getData() < value) {
    		//if the node doesn't have right children, it's kicked out
    		//along with its children, and it and its children are tallied
    		if(node.getRight() == null) {
    			if(node == root) {
    				root = null;
    				return numberNodes(node);
    			} else if(node.getParent().getLeft() == node) {
    				node.getParent().setLeft(null);
    				return numberNodes(node);
    			} else {
    				node.getParent().setRight(null);
    				return numberNodes(node);
    			}
    		//if the node does have right children, kick out it and its left children,
    		//tally those, and replace the node with its right child,
    		//then make sure all of that node and its right children are good
    		} else {
    			if(node == root) {
    				root = root.getRight();
    				node.setRight(null);
    				return numberNodes(node) + removeLessThan(root, value);
    			}
    			if(node.getParent().getLeft() == node) {
    				node.getParent().setLeft(node.getRight());
    				node.getRight().setParent(node.getParent());
    			} else {
    				node.getParent().setRight(node.getRight());
    				node.getRight().setParent(node.getParent());
    			}
    			node.setRight(null);
    			return numberNodes(node) + removeLessThan(node.getParent(), value);
    		}
    	}
    	return removeLessThan(node.getLeft(), value) + removeLessThan(node.getRight(), value);
    }
    
    /**
     * Removes all values from the tree less than the given value.
     * Runtime: Theta(n*log(n))
     * @param value Any int less than value will be removed
     */
    public int removeLessThan(int value) {
    	return removeLessThan(root, value);
    }
    
    /**
     * Inserts all the values from an array into the tree.
     * Runtime: Theta(n*log(n))
     * @param list The array of values to be stored in the tree
     */
    public void insertList(int[] list) {
    	for(int value : list) {
    		insert(value);
    	}
    }
    
    /**
     * Assists the public numberNodes method by recursively calling methods on each node's children.
     * Runtime: (see public method of the same name for the overall runtime)
     * @param current The node that is currently being traversed through
     */
    private int numberNodes(MyNode current) {
    	if(current == null) {
    		return 0;
    	}
    	return numberNodes(current.getLeft()) + numberNodes(current.getRight()) + 1;
    }
    
    /**
     * Prints out the number of nodes in the tree.
     * Runtime: Theta(n)
     */
    public void numberNodes() {
    	System.out.println("There are "+numberNodes(root)+" nodes in the tree.");
    }
    
    /**
     * Assists the public print method by recursively calling methods on each node's children.
     * Runtime: (see public method of the same name for the overall runtime)
     * @param current The node that is currently being traversed through
     */
    private void print(MyNode current) {
    	if(current == null) {
    		return;
    	}
    	print(current.getLeft());
    	System.out.print(current.getData()+" ");
    	print(current.getRight());
    }
    
    /**
     * Prints out the values of the tree in ascending order.
     * Runtime: Theta(n)
     */
    public void print() {
    	print(root);
    }
    
    public void remove(int value) {
        if(contains(value)) {
            //we know our tree contains value
            MyNode current = root;
            while(current.getData() != value) {
                if(current.getData() < value) {                    
                    current = current.getRight();
                } else {                    
                    current = current.getLeft();
                }
            }
            //current contains the value we want to remove
            //parent is the parent of current
            
            //removing a leaf
            if(current.getLeft() == null && current.getRight() == null) {
                if(current.getParent() == null) {
                    //removing the only element from the tree
                    root = null;
                } else {                    
                    if(current.getData() > current.getParent().getData()) {
                    	current.getParent().setRight(null);
                    } else {
                    	current.getParent().setLeft(null);
                    }
                }
            } else if(current.getLeft() == null) {
                //case where we only have a right child
                if(current.getParent() == null) {
                    root = current.getRight();
                } else {
                    if(current.getData() > current.getParent().getData()) {
                    	current.getParent().setRight(current.getRight());
                    } else {
                    	current.getParent().setLeft(current.getRight());
                    }
                }
            } else {
                //current node we want to remove has a left child, find replacement
                //node to remove has a left child                
                MyNode replacement = current.getLeft();
                if(replacement.getRight()==null){
                    //If the left child has no right child, move left child's data up and remove left child
                    current.setData(replacement.getData());
                    current.setLeft(replacement.getLeft());
                } else{
                    //left child has right children, go as far right as possible
                    while(replacement.getRight()!=null){
                        replacement = replacement.getRight();
                    }
                    current.setData(replacement.getData());
                    replacement.getParent().setRight(replacement.getLeft());
                }
            }
        }
        
    }
    
    public boolean contains(int value) {
        MyNode current = root;
        while(current != null) {
            if(current.getData()==value) {
                return true;
            } else if(current.getData() < value) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return false;
    }
    
    public void insert(int value) {
        if(root == null) {
            root = new MyNode(value);
        } else {
            MyNode current = root;
            boolean finished = false;
            while(!finished) {
                if(current.getData() < value) {
                    //go to the right
                    if(current.getRight() == null) {
                        //we've found our place to insert
                        MyNode temp = new MyNode(value);
                        temp.setParent(current);
                        current.setRight(temp);
                        finished = true;
                    } else {
                        current = current.getRight();
                    }
                } else {
                    //go to the left
                    if(current.getLeft() == null) {
                        //we've found our place to insert
                        MyNode temp = new MyNode(value);
                        temp.setParent(current);
                        current.setLeft(temp);
                        finished = true;
                    } else {
                        current = current.getLeft();
                    }
                }
            }
                    
            
        
        }
    }
}
