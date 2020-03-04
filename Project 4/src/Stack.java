
/**
 * This is a Stack.
 * 
 * @author Martin Mueller
 *
 */
public class Stack {
	//instance variables
	public StackNode top;
	//constructor without specific top
	public Stack() {
		top = null;
	}
	//constructor with specific top
	public Stack(StackNode top) {
		this.top = top;
	}
	/**
	 * Returns true if the stack is empty, false otherwise.
	 * 
	 * @return Whether the stack is empty
	 */
	public boolean isEmpty() {
		return top == null ? true : false;
	}
	/**
	 * Pushes new data onto the stack.
	 * 
	 * @param data The data to be pushed onto the stack
	 */
	public void push(String data) {
		top = new StackNode(data, top);
	}
	/**
	 * Returns the topmost node's data without removing it.
	 * 
	 * @return The topmost node's data
	 */
	public String peek() {
		if(top == null) {
			return null;
		} else {
			return top.getData();
		}
	}
	/**
	 * Removes the topmost node of the stack, then returns its data.
	 * 
	 * @return The data of the topmost node of the stack
	 */
	public String pop() {
		//if the stack is empty, return null
		if(top == null) {
			return null;
		} else { //otherwise, return the data at the top of the stack
			String poppedData = top.getData();
			top = top.getNextNode();
			return poppedData;
		}
	}
}
