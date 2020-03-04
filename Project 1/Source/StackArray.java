
/**
 * Creates a stack which utilizes an array list in the background.
 *
 * @author krohne, muellm79
 * @version 02/27/2019
 */
public class StackArray {
	
	private Character[] data; //create an array to store the data in

	/**
	 * Creates an empty stack.
	 */
	public StackArray() {
		data = new Character[2];
	}
	
	/**
	 * Adds a new char to top of the stack. 
	 * Runtime: Theta(1) unless it has to resize, then it's Theta(n)
	 * 
	 * @param x The char to be stored in the stack
	 */
	public void push(char x) {
		//if the array is too small, resize it
		int size = size();
		if(data.length == size) {
			Character[] newArray = new Character[data.length*2];
			for(int i=0; i<size; i++) {
				newArray[i] = data[i];
			}
			data = newArray;
			System.out.println("The StackArray has grown.");
		}
		//add the new element to the array
		data[size] = x;
	}
	
	/**
	 * Returns the topmost element in the stack. 
	 * Runtime: Theta(1) unless it has to resize, then it's Theta(n)
	 * 
	 * @return The topmost element in the stack
	 */
	public char pop() {
		if(isEmpty()) {
			throw new RuntimeException("Empty stack");
		}
		//gets information about the array and its last element
		int size = size();
		char x = data[size-1];
		data[size-1] = null;
		size--;
		//shrink the data array if it's too big
		if((double)size < (data.length/3.0) && data.length != 2) {
			Character[] newArray = new Character[data.length/2];
			for(int i=0; i<size; i++) {
				newArray[i] = data[i];
			}
			data = newArray;
			System.out.println("The StackArray has shrunk.");
		}
		return x;
	}
	
	/**
	 * Determines if the stack contains no elements. 
	 * Runtime: Theta(1)
	 * 
	 * @return If the stack contains no elements, return true, else false
	 */
	public boolean isEmpty() {
		if(data[0] == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the size of the stack by systematically going through the stack
	 * until it hits an index of the array that's null. 
	 * Runtime: Theta(n)
	 * 
	 * @return The number of elements in the stack
	 */
	public int size() {
		for(int i=0; i<data.length; i++) {
			if(data[i] == null) {
				return i;
			}
		}
		return data.length;
	}
}
