
/**
 * Creates a queue which utilizes a doubly linked list in the background.
 *
 * @author krohne, muellm79
 * @version 02/27/2019
 */
public class QueueList {

	private DoubleNode head; //the first element in the queue
	private DoubleNode tail; //the last element in the queue

	/**
	 * Creates an empty queue.
	 */
	public QueueList() {
		head = null;
		tail = null;
	}

	/**
	 * Adds a new node at the end of the queue. 
	 * Runtime: Theta(1)
	 * 
	 * @param x The character to be stored in the node
	 */
	public void enqueue(char x) {
		DoubleNode node = new DoubleNode(x, null, null);
		if(isEmpty()) { //if the head's empty, the new element becomes the head and the tail
			head = node;
			tail = node;
		} else if(head == tail) { //if the head is also the tail, the new element becomes the tail
			head.setNext(node);
			node.setPrevious(head);
			tail = node;
		} else { //if the head and tail are different, the new element supplants the old tail
			tail.setNext(node);
			node.setPrevious(tail);
			tail = node;
		}
	}
	
	/**
	 * Returns the first element in the queue. 
	 * Runtime: Theta(1)
	 * 
	 * @return The first element in the queue
	 */
	public char dequeue() {
		if(isEmpty()) { //if there's nothing to dequeue, whoops
			throw new RuntimeException("Empty queue");
		} else if(head == tail) { //if there's only one element...
			char data = head.getData();
			head = null;
			tail = null;
			return data;
		} else if(head.getNext() == tail) { //if there are only 2 elements...
			char data = head.getData();
			head = tail;
			head.setPrevious(null);
			return data;
		} else { //if there are more than 2 elements...
			char data = head.getData();
			head = head.getNext();
			head.setPrevious(null);
			return data;
		}
	}
	
	/**
	 * Determines if the queue contains no elements. 
	 * Runtime: Theta(1)
	 * 
	 * @return If the queue contains no elements, return true, else false
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * Determines how many elements are in the queue. 
	 * Runtime: Theta(n)
	 * 
	 * @return How many elements are in the queue
	 */
	public int size() {
		if(isEmpty()) { //if the queue is empty, there are 0 elements
			return 0;
		} else if(head == tail) { //if the head and the tail are the same, there's only one element
			return 1;
		} else { //else loop through the queue and count how many elements there are
			DoubleNode current = head;
			int size = 1;
			while(current.getNext() != null) {
				current = current.getNext();
				size++;
			}
			return size;
		}
	}
}
