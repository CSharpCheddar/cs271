
public class ProjectThree {
	//main method
	public static void main(String args[]) {
		//test first part
		MyAVLTree tree = new MyAVLTree();
		//test printLevel
		for(int i=0; i<9; i++) {
			tree.insert(i);
		}
		tree.preorder();
		System.out.println();
		tree.printLevel(3); //6, 8
		//test remove
		if(tree.remove(4)) { //true
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		tree.preorder();
		if(tree.remove(8)) { //true
			System.out.println("\nTrue");
		} else {
			System.out.println("\nFalse");
		}
		if(tree.remove(9)) { //false
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		//test removeSingleParents
		tree.preorder();
		System.out.println("\n"+tree.removeSingleParents()); //0
		tree.preorder(); //should be same as above
		tree.insert(8);
		System.out.println();
		tree.preorder(); // includes 8 at the end
		System.out.println("\n"+tree.removeSingleParents()); //1
		tree.preorder(); // all but 7
		System.out.println();
		//test second part (algorithm explanation in WaitList)
		WaitList list = new WaitList();
		int tips = 0;
		while(!list.isEmpty()) {
			tips += list.removeMin().getTip();
		}
		System.out.println("Tips earned: "+tips);
	}
}
