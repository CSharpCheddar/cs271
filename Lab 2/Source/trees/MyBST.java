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
public class MyBST {
    private MyNode root;    
    
    public MyBST(){
        root = null;
    }
    
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
    
    public void insert(int value){
        if(root==null){
            root = new MyNode(value);
            //root.setLeft(null);
            //root.setRight(null);
        } else{
            MyNode current = root;
            boolean finished = false;
            while(!finished){
                if(current.getData() < value){
                    //go to the right
                    if(current.getRight()==null){
                        //we've found our place to insert
                        MyNode temp = new MyNode(value);
                        current.setRight(temp);
                        finished = true;
                    } else{
                        current = current.getRight();
                    }
                } else{
                    //go to the left
                    if(current.getLeft()==null){
                        //we've found our place to insert
                        MyNode temp = new MyNode(value);
                        current.setLeft(temp);
                        finished = true;
                    } else{
                        current = current.getLeft();
                    }
                }
            }
        }
    }
    
    private MyNode replace(MyNode node, MyNode parent) {
    	if(node.getRight() == null) {
    		if(node.getLeft() == null) {
    			if(node.getData() < parent.getData()) {
    				parent.setLeft(null);
    			} else {
    				parent.setRight(null);
    			}
    			return node;
    		}
    		return replace(node.getLeft(), node);
    	}
    	return replace(node.getRight(), node);
    }
    
    public void remove (int value) {
    	//make sure the tree contains the value
    	if(!contains(value)) {
    		throw new RuntimeException("No such value exists");
    	}
		if(root.getData() == value) { //if the root is being removed
			if(root.getLeft() == null &&  root.getRight() == null) { //if the root is the only value in the tree
				root = null;
			} else if(root.getLeft() != null &&  root.getRight() == null) { //if the root only has a value on the left
				root = root.getLeft();
			} else if(root.getLeft() == null &&  root.getRight() != null) { //if the root only has a value on the right
				root = root.getRight();
			} else { //if the root has a value on both sides
				MyNode replacement = replace(root.getLeft(), root);
				replacement.setLeft(root.getLeft());
				replacement.setRight(root.getRight());
				root = replacement;
			}
		} else { //if the root is not being removed
    		//start searching at the root
			MyNode parent = root;
    		MyNode node = root;
    		boolean isChildOnLeft;
    		if(node.getData() > value) {
    			node = node.getLeft();
    			isChildOnLeft = true;
    		} else {
    			node = node.getRight();
    			isChildOnLeft = false;
    		}
        	//find the value we're looking for
        	while(node.getData() != value) {
        		parent = node;
        		if(node.getData() > value) {
        			node = node.getLeft();
        			isChildOnLeft = true;
        		} else {
        			node = node.getRight();
        			isChildOnLeft = false;
        		}
        	}
        	//check for specific cases
        	if(node.getLeft() == null && node.getRight() == null) { //if the node is a leaf
        		if(isChildOnLeft) {
        			parent.setLeft(null);
        		} else {
        			parent.setRight(null);
        		}
        	} else if(node.getLeft() != null && node.getRight() == null) { //if the node has an element on the left only
        		if(isChildOnLeft) {
        			parent.setLeft(node.getLeft());
        		} else {
        			parent.setRight(node.getLeft());
        		}
        	} else if(node.getLeft() == null && node.getRight() != null) { //if the node has an element on the right only
        		if(isChildOnLeft) {
        			parent.setLeft(node.getRight());
        		} else {
        			parent.setRight(node.getRight());
        		}
        	} else { //if the node has elements on both sides
        		MyNode replacement = replace(node.getLeft(), parent);
				replacement.setLeft(node.getLeft());
				replacement.setRight(node.getRight());
				if(isChildOnLeft) {
        			parent.setLeft(replacement);
        		} else {
        			parent.setRight(replacement);
        		}
        	}
		}
    }
}