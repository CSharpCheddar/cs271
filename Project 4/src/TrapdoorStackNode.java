
/**
 * This is a node for a Trapdoor. It stores a string,  
 * a pointer to the next node (which is null if the node is at the bottom of the stack), 
 * and a pointer to its previous node (which is null if the node is at the top of the stack).
 * 
 * @author Martin Mueller
 */
public class TrapdoorStackNode {
	//instance variables
	private String data;
	private TrapdoorStackNode previousNode;
	private TrapdoorStackNode nextNode;
	//constructor
	public TrapdoorStackNode() {
		previousNode = null;
		nextNode = null;
	}
	//constructor with data
	public TrapdoorStackNode(String data) {
		this.data = data;
		previousNode = null;
		nextNode = null;
	}
	//constructor with data and nodes
	public TrapdoorStackNode(String data, TrapdoorStackNode previousNode, TrapdoorStackNode nextNode) {
		this.data = data;
		this.previousNode = previousNode;
		this.nextNode = nextNode;
	}
	//accessors
	public String getData() {
		return data;
	}
	public TrapdoorStackNode getPreviousNode() {
		return previousNode;
	}
	public TrapdoorStackNode getNextNode() {
		return nextNode;
	}
	//mutators
	public void setData(String data) {
		this.data = data;
	}
	public void setPreviousNode(TrapdoorStackNode previousNode) {
		this.previousNode = previousNode;
	}
	public void setNextNode(TrapdoorStackNode nextNode) {
		this.nextNode = nextNode;
	}
}
