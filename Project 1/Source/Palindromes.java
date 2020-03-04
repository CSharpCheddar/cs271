
/**
 * Uses the StackArray, QueueList, and StackList classes to determine which
 * words in a given text file are palindromes. It outputs the results to a
 * separate text file called "output.txt".
 *
 * @author krohne, muellm79
 * @version 02/27/2019
 */

import java.util.Scanner;
import java.io.*;

public class Palindromes {

	//create back end data structures using all 3 available in this package
	StackList stackList;
	QueueList queueList;
	
	/**
	 * Initializes all the required data structures for this project.
	 */
	public Palindromes() {
		stackList = new StackList();
		queueList = new QueueList();
	}
	/**
	 * Outputs all palindromes in dictionary.txt to output.txt.
	 */
	public void findPalindromes() {
		try {
			//creates a Scanner and a PrintWriter for reading 
			//and writing, and reads first line of the input file
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter dictionary file path: ");
			Scanner input = new Scanner(new File(scanner.nextLine()));
			input.useDelimiter("\\s+");
			scanner.close();
			PrintWriter output = new PrintWriter(new FileOutputStream("output.txt"));
			String string;
			//loops through all the words in the input file
			while(input.hasNext()) {
				string = input.next();
				//splits up the word into characters
				char[] array = string.toCharArray();
				for(char c : array) {
					stackList.push(c);
					queueList.enqueue(c);
				}
				//checks to see if the word is a palindrome, if so, 
				//it writes the word to an output file
				if(isPalindrome(stackList, queueList, array.length)) {
					output.write(string+System.getProperty("line.separator"));
					System.out.println(string);
				}
			}
			//close the Scanner and PrintWriter
			input.close();
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Determines if a word is a palindrome by utilizing a stack and queue.
	 * 
	 * @param stack A stack of chars that make up a word
	 * @param queue A queue of chars that make up a word
	 * @param characters The number of characters in the word
	 * @return Whether or not the word is a palindrome
	 */
	public boolean isPalindrome(StackList stack, QueueList queue, int characters) {
		boolean palindrome = true;
		for(int i=0; i<characters; i++) {
			if(stack.pop() != queue.dequeue()) {
				palindrome = false;
			}
		}
		return palindrome;
	}
}
