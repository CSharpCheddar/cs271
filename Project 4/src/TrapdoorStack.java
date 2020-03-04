
/**
 * This functions like a normal stack, but it drops its bottom 
 * element when the stack reaches a certain maximum size.
 * 
 * @author Martin Mueller
 */
public class TrapdoorStack {
	//instance variables
	private TrapdoorStackNode top;
	private TrapdoorStackNode undoPoint;
	private TrapdoorStackNode bottom;
	private int size;
	private int maxSize;
	/**
	 * Constructor.
	 * 
	 * @param maxSize The maximum size a stack can reach before dropping elements
	 */
	public TrapdoorStack(int maxSize) throws SmallTrapdoorStackException {
		if(maxSize < 2) {
			throw new SmallTrapdoorStackException();
		}
		this.maxSize = maxSize;
		top = new TrapdoorStackNode("");
		undoPoint = top;
		bottom = null;
		size = 1;
	}
	/**
	 * Redoes an undo by moving a pointer up 
	 * the stack and returning the data of the 
	 * topmost node.
	 * 
	 * @return The data of the topmost node
	 */
	public String redo() {
		if(undoPoint == top) { //if we can't redo anymore
			return top.getData();
		} else { //if we can keep redoing, redo
			undoPoint = undoPoint.getPreviousNode();
			return undoPoint.getData();
		}
	}
	/**
	 * Moves the undoPoint pointer throughout the stack. 
	 * This works like a normal undo function.
	 * 
	 * @return The next element in the stack
	 */
	public String undo() {
		if(bottom == null) { //if the stack doesn't have any history
			return "";
		} else if(undoPoint == bottom) { //if we can't undo anymore
			return bottom.getData();
		} else { //if we can keep undoing, undo
			undoPoint = undoPoint.getNextNode();
			return undoPoint.getData();
		}
	}
	/**
	 * Pushes new data onto the TrapdoorStack.
	 * If the data overflows the TrapdoorStack, 
	 * it drops its bottom element.
	 * 
	 * @param data The data to be stored in the TrapdoorStack
	 */
	public void push(String data) {
		if(bottom == null) { //if needed, make first history entry
			bottom = top;
			top = new TrapdoorStackNode(data, null, bottom);
			bottom.setPreviousNode(top);
			undoPoint = undoPoint.getPreviousNode();
			size = 2;
		} else { //make non-first entry
			if(undoPoint == top) { //normal insert if the undoPoint is at the top
				top = new TrapdoorStackNode(data, null, undoPoint);
				undoPoint.setPreviousNode(top);
				undoPoint = undoPoint.getPreviousNode();
			} else { //rearrange nodes if undoPoint is not on top
				//insert new node above undoPoint
				TrapdoorStackNode newNode = new TrapdoorStackNode(data);
				newNode.setNextNode(undoPoint);
				newNode.setPreviousNode(undoPoint.getPreviousNode());
				undoPoint.getPreviousNode().setNextNode(newNode);
				undoPoint.setPreviousNode(newNode);
				undoPoint = newNode;
				//make each successive node above undoPoint the new bottom
				while(undoPoint.getPreviousNode() != null) {
					TrapdoorStackNode newBottom = undoPoint.getPreviousNode();
					undoPoint.setPreviousNode(undoPoint.getPreviousNode().getPreviousNode());
					if(undoPoint.getPreviousNode() != null) {
						undoPoint.getPreviousNode().setNextNode(undoPoint);
					}
					bottom.setNextNode(newBottom);
					newBottom.setPreviousNode(bottom);
					newBottom.setNextNode(null);
					bottom = newBottom;
				}
				//undoPoint is the new top
				top = undoPoint;
			}
			//increment size after insertion
			size++;
		}
		//resize if needed
		while(size > maxSize) {
			bottom = bottom.getPreviousNode();
			bottom.setNextNode(null);
			size--;
		}
	}
}

/**
 * Exception thrown when the given maxSize for 
 * a TrapdoorStack is too small.
 * 
 * @author Martin Mueller
 */
@SuppressWarnings("serial")
class SmallTrapdoorStackException extends Exception {
	@Override
	public String toString() {
		return "Given TrapdoorStack size too small.";
	}
}
