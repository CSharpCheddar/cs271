
/**
 * Creates a node for a singly linked list or any data structures that can be
 * derived therefrom.
 *
 * @authors krohne, muellm79
 * @version 02/27/2019
 */
public class SingleNode {

	private char data; //the char that is stored in the node
	private SingleNode next; //the next node in the singly linked list

	/**
	 * Initializes a blank node with no data and points to null instead of the
	 * next node in the data structure.
	 */
	public SingleNode() {
		next = null;
	}

	/**
	 * Initializes a node, stores the given data in the node, and points to null
	 * instead of the next node in the data structure.
	 * 
	 * @param data The char to be stored in the node
	 */
	public SingleNode(char data) {
		this.data = data;
		next = null;
	}

	/**
	 * Initializes a node, stores the given data in the node, and creates a
	 * pointer to the next node in the data structure.
	 * 
	 * @param data The char to be stored in the node
	 * @param next The next node in the data structure
	 */
	public SingleNode(char data, SingleNode next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * Creates a pointer to the next node in the data structure.
	 * 
	 * @param next The next node in the data structure
	 */
	public void setNext(SingleNode next) {
		this.next = next;
	}

	/**
	 * Returns the next node in the data structure.
	 * 
	 * @return The next node in the data structure
	 */
	public SingleNode getNext() {
		return next;
	}

	/**
	 * Stores data in the node.
	 * 
	 * @param data The char to be stored in the node
	 */
	public void setData(char data) {
		this.data = data;
	}

	/**
	 * Returns the data stored in the node.
	 * 
	 * @return The char currently stored in the node
	 */
	public char getData() {
		return data;
	}
}
