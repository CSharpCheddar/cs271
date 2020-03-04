
/**
 * Creates a node for a doubly linked list or any data structures that can be
 * derived therefrom.
 *
 * @author krohne, muellm79
 * @version 02/27/2019
 */
public class DoubleNode {

	private char data; //the char to be stored in the node
	private DoubleNode next, previous; //pointers to the next and previous nodes in the data structure

	/**
	 * Initializes a blank node with no data and points to null instead of the
	 * next and previous nodes in the data structure.
	 */
	public DoubleNode() {
		next = null;
		previous = null;
	}

	/**
	 * Initializes a node, stores the given data in the node, and points to null
	 * instead of the next and previous nodes in the data structure.
	 * 
	 * @param data The char to be stored in the node
	 */
	public DoubleNode(char data) {
		this.data = data;
		next = null;
		previous = null;
	}

	/**
	 * Initializes a node, stores the given data in the node, and creates a
	 * pointer to the next and previous nodes in the data structure.
	 * 
	 * @param data The char to be stored in the node
	 * @param next The next node in the data structure
	 * @param previous The previous node in the data structure
	 */
	public DoubleNode(char data, DoubleNode next, DoubleNode previous) {
		this.data = data;
		this.next = next;
		this.previous = previous;
	}

	/**
	 * Creates a pointer to the previous node in the data structure.
	 * 
	 * @param next The previous node in the data structure
	 */
	public void setPrevious(DoubleNode previous) {
		this.previous = previous;
	}

	/**
	 * Returns the previous node in the data structure.
	 * 
	 * @return The previous node in the data structure
	 */
	public DoubleNode getPrevious() {
		return previous;
	}

	/**
	 * Creates a pointer to the next node in the data structure.
	 * 
	 * @param next The next node in the data structure
	 */
	public void setNext(DoubleNode next) {
		this.next = next;
	}

	/**
	 * Returns the next node in the data structure.
	 * 
	 * @return The next node in the data structure
	 */
	public DoubleNode getNext() {
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
