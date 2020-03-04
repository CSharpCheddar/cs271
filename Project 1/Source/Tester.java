
/**
 * Tests all classes in this project.
 * 
 * @author muellm79
 * @version 02/27/2019
 */

public class Tester {

	public static void main(String[] args) {
		System.out.println("1. StackArray Test: Stack should grow twice and shrink once.");
		StackArray s = new StackArray();
		s.push('a');
		s.push('b');
		s.push('c');  //because we are adding to a full stack here, a 
		              //message is printed off stating the stack is 
		              //being resized larger.
		s.push('d');
		s.push('e');  //again, adding to a full stack, message about 
		              //resizing here
		s.pop();
		s.pop();
		s.pop();  //in doing this, 2 elements remain in an 8 element 
		          //capacity stack, a message is printed off stating 
		          //the stack is being resized smaller.
		
		System.out.println("2. Stack and queue test:");
		StackList stack = new StackList();
		QueueList queue = new QueueList();
		String string = "abcdefghiihgfedcba";
		char[] array = string.toCharArray();
		System.out.print("String: ");
		for(char c : array) {
			stack.push(c);
			queue.enqueue(c);
			System.out.print(c);
		}
		System.out.println("\nEqual Sizes? "+(stack.size() == queue.size()));
		System.out.print("Palindrome? ");
		boolean isPalindrome = true;
		for(int i=0; i<array.length; i++) {
			if(stack.pop() != queue.dequeue()) {
				isPalindrome = false;
			}
		}
		System.out.println(isPalindrome);
		System.out.println("Stack empty? "+(stack.isEmpty() ? true : false)+" Remaining: "+stack.size());
		System.out.println("Queue empty? "+(queue.isEmpty() ? true : false)+" Remaining: "+queue.size());
		
		System.out.println("\n3. Palindrome Test: This should create an output file with all the palindromes.");
		Palindromes p = new Palindromes();
		p.findPalindromes();
		System.out.println("Done!");
	}

}
