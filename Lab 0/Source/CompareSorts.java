
import java.util.Arrays;
import java.util.Random;

public class CompareSorts {

	public static void main(String[] args) {
		//create a Random object with seed 271
		Random r = new Random(271);
		//specify a size to make the arrays, then run some tests
		for(int size=10; size<=1000000; size*=10) {
			//make a new array of a specified size
			int[] arr = new int[size];
			//make a second array of the same size
			int[] arrTwo = new int[size];
			//populate both arrays
			for(int i=0; i<size; i++) {
				arr[i] = r.nextInt();
				arrTwo[i] = r.nextInt();
			}
			//display the array size
			System.out.println("\nArray Size: "+size);
			//sort the first array with the default sort, time it, and print the result
			long start = System.currentTimeMillis();
			Arrays.sort(arr);
			long end = System.currentTimeMillis();
			System.out.println("Default Sort: "+(end-start)+" milliseconds");
			//sort the second array with selection sort, time it, and print the result
			start = System.currentTimeMillis();
			selectionSort(arrTwo);
			end = System.currentTimeMillis();
			System.out.println("Selection Sort: "+(end-start)+" milliseconds");
		}
	}

	/**
	 * Sort a given array using selection sort.
	 * 
	 * @param arrTwo
	 */
	private static void selectionSort(int[] arrTwo) {
		//sort the array by putting successively larger terms towards the front
		for(int i=0; i<arrTwo.length-2; i++) {
			//keep track of the smallest element in the array and where it is
			int smallest = arrTwo[i];
			int index = i;
			//search through the array and find the smallest element
			for(int j=i+1; j<arrTwo.length-1; j++) {
				if(smallest > arrTwo[j]) {
					smallest = arrTwo[j];
					index = j;
				}
			}
			//if the next smallest element is not in the right spot, put it there
			if(index != i) {
				arrTwo[index] = arrTwo[i];
				arrTwo[i] = smallest;
			}
		}
	}
}
