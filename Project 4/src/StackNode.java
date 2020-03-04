
/**
 * This is a node for a Stack. It stores a string and a 
 * pointer to the next node (which is null if the node is at the bottom of the stack).
 * 
 * @author Martin Mueller
 */
public class StackNode {
	//instance variables
	private String data;
	private StackNode nextNode;
	//constructor
	public StackNode() {
		nextNode = null;
	}
	//constructor with data
	public StackNode(String data) {
		this.data = data;
		nextNode = null;
	}
	//constructor with data and nodes
	public StackNode(String data, StackNode nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}
	//accessors
	public String getData() {
		return data;
	}
	public StackNode getNextNode() {
		return nextNode;
	}
	//mutators
	public void setData(String data) {
		this.data = data;
	}
	public void setNextNode(StackNode nextNode) {
		this.nextNode = nextNode;
	}
}
