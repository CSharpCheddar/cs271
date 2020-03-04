
/**
 * Creates a stack which utilizes a singly linked list in the background.
 *
 * @author krohne, muellm79
 * @version 02/27/2019
 */
public class StackList {

	private SingleNode top; //keeps track of the topmost element in the stack.

	/**
	 * Creates an empty stack.
	 */
	public StackList() {
		top = null;
	}

	/**
	 * Adds a new char to top of the stack. 
	 * Runtime: Theta(1)
	 * 
	 * @param x The char to be stored in the stack
	 */
	public void push(char x) {
		SingleNode node = new SingleNode(x, top);
		top = node;
	}

	/**
	 * Returns the topmost element in the stack. 
	 * Runtime: Theta(1)
	 * 
	 * @return The topmost element in the stack
	 */
	public char pop() {
		if (isEmpty()) {
			throw new RuntimeException("Empty stack");
		}
		char data = top.getData();
		top = top.getNext();
		return data;
	}

	/**
	 * Determines if the stack contains no elements. 
	 * Runtime: Theta(1)
	 * 
	 * @return If the stack contains no elements, return true, else false
	 */
	public boolean isEmpty() {
		return top == null;
	}

	/**
	 * Returns the size of the stack by systematically going through the stack
	 * until it hits the last node. 
	 * Runtime: Theta(n)
	 * 
	 * @return The number of elements in the stack
	 */
	public int size() {
		if(top == null) { //if there's no top, there's nothing else
			return 0;
		} else { //loop through the stack and count the findings
			SingleNode current = top;
			int size = 1;
			while (current.getNext() != null) {
				current = current.getNext();
				size++;
			}
			return size;
		}
	}
}
