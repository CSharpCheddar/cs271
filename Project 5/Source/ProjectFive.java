
import java.lang.StringBuilder;
import java.io.*;

/**
 * This is the main class that runs the project.
 * Largest input #1: 35
 * Largest input #2: 13
 * 
 * @author muellm79
 */
public class ProjectFive {
	//variables for number 1
	public static Entry<String, Vertex<String, Boolean>>[] cheapestCombo; //the cheapest combo of people to buy
	public static boolean found; //whether the cheapestCombo has been found
	//variables for number 2
	public static Entry<String, Vertex<String, Boolean>> startingPoint; //start/end point for the paper route
	public static Entry<String, Vertex<String, Boolean>>[] shortestRoute; //an array containing each stop in the shortest route
	public static double distance; //the distance of the shortest route
	/**
	 * This pretty much just reads input, calls other methods to solve the problems, 
	 * then neatly packages the output and spits it out.
	 * @param args Don't worry about these.
	 */
	public static void main(String[] args) {
		//solve problem one
		//string is the name of the person, and the boolean is whether it's been visited
		Graph<String, Boolean> numberOne = new Graph<>();
		//fill in the graph of friends from the input file
		try {
			//create the BufferedReader and nextLine string
			BufferedReader inputOne = new BufferedReader(new FileReader("input1.txt"));
			String nextLine = inputOne.readLine();
			//string builder to store the whole input
			StringBuilder sb = new StringBuilder("");
			//while there's still stuff left to read
			while(nextLine != null) {
				//add the new stuff to a string
				sb.append(nextLine+"\n");
				//read in the next line
				nextLine = inputOne.readLine();
			}
			//close the BufferedReader
			inputOne.close();
			//create a string from sb
			String input = sb.toString();
			//split the input into lines
			String[] split = input.split("\n");
			//stores each node and its distance info
			String[][] info = new String[split.length][];
			//insert all the nodes into the graph and the string array
			for(int i=0; i<split.length; i++) {
				info[i] = split[i].split("[\\s+]-");
				numberOne.insertVertex(info[i][0], null);
			}
			//insert all the edges into the graph
			for(int i=0; i<split.length-1; i++) {
				if(!info[i][1].equals("") && !info[i][1].equals("\\s+")) {
					String[] connections = info[i][1].split("\\s+");
					for(int j=0; j<connections.length; j++) {
						if(!connections[j].equals("") && numberOne.exists(connections[j])) {
							numberOne.insertEdge(info[i][0], connections[j], 1.0);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//keep track of all vertices in an array
		Entry<String, Vertex<String, Boolean>>[] vertices = numberOne.getVerticies();
		cheapestCombo = null;
		found = false;
		getCheapestCombo(vertices);
		System.out.print("\nCheapest People to Buy: ");
		for(int i=0; i<cheapestCombo.length; i++) {
			System.out.print(cheapestCombo[i].getKey()+" ");
		}
		System.out.println();
		
		
		
		
		
		
		
		
		
		//solve problem two
		//string is the building name and boolean is a placeholder
		Graph<String, Boolean> numberTwo = new Graph<>();
		startingPoint = null;
		//fill in the graph of friends from the input file
		try {
			//create the BufferedReader and nextLine string
			BufferedReader inputTwo = new BufferedReader(new FileReader("input2.txt"));
			String nextLine = inputTwo.readLine();
			//string builder to store the whole input
			StringBuilder sb = new StringBuilder("");
			//while there's still stuff left to read
			while(nextLine != null) {
				//add the new stuff to a string
				sb.append(nextLine+"\n");
				//read in the next line
				nextLine = inputTwo.readLine();
			}
			//close the BufferedReader
			inputTwo.close();
			//create a string from sb
			String input = sb.toString();
			//split the input into lines
			String[] split = input.split("\n");
			//stores each node and its distance info
			String[][] info = new String[split.length][];
			//insert all the nodes into the graph and the string array
			for(int i=0; i<split.length; i++) {
				info[i] = split[i].split("[\\s+]-");
				numberTwo.insertVertex(info[i][0], null);
				if(i == 0) {
					startingPoint = numberTwo.getVerticies()[0];
				}
			}
			//insert all the edges into the graph
			for(int i=0; i<split.length-1; i++) {
				if(!info[i][1].equals("") && !info[i][1].equals("\\s+")) {
					String[] distances = info[i][1].split("\\s+");
					int correction = 0;
					for(int j=0; j<distances.length; j++) {
						if(!distances[j].equals("")) {
							numberTwo.insertEdge(info[i][0], info[i+correction+1][0], Double.parseDouble(distances[j]));
							correction++;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//keep track of all vertices in an array
		Entry<String, Vertex<String, Boolean>>[] verticesTwo = numberTwo.getVerticies();
		@SuppressWarnings("unchecked")
		Entry<String, Vertex<String, Boolean>>[] possibleRoutes = new Entry[verticesTwo.length-1];
		int correction = 0;
		for(int i=0; i<verticesTwo.length; i++) {
			if(!verticesTwo[i].getKey().equals(startingPoint.getKey())) {
				possibleRoutes[i-correction] = verticesTwo[i];
			} else {
				correction++;
			}
		}
		//find the shortest route
		shortestRoute = null;
		distance = 0.0;
		permute(possibleRoutes.length, possibleRoutes);
		for(int i=1; i<shortestRoute.length; i++) {
			distance += shortestRoute[i].getValue().getEdgeWeight(shortestRoute[i-1].getKey());
		}
		System.out.println("Shortest Route:");
		for(int i=1; i<shortestRoute.length; i++) {
			System.out.println(shortestRoute[i-1].getKey()+" to "+shortestRoute[i].getKey()+" *** Distance: "+
								shortestRoute[i].getValue().getEdgeWeight(shortestRoute[i-1].getKey()));
		}
		System.out.println("Total Distance: "+distance);
	}
	//methods to help solve number 1
	/**
	 * Finds successively bigger combinations of a given array until found == true.
	 * @param array
	 */
    @SuppressWarnings("unchecked")
	public static void getCheapestCombo(Entry<String, Vertex<String, Boolean>>[] array) { 
        //find all combinations of length i
    	for(int i=1; i<array.length; i++) {
    		//temp array for keeping track of combos
        	Entry<String, Vertex<String, Boolean>>[] data = new Entry[i];
    		findCombo(array, data, 0, array.length-1, 0, i);
    		if(found) {
    			i = array.length;
    		}
    	}
    }
	/**
	 * Finds all combinations of length r in a given array.
	 * @param array The array to find combos of
	 * @param data A temp array used for storing combos
	 * @param start The starting index of the array
	 * @param end The ending index of the array
	 * @param index The index we're starting to search from
	 * @param r The length of all the combos
	 */
    @SuppressWarnings("unchecked")
	public static void findCombo(Entry<String, Vertex<String, Boolean>>[] array, Entry<String, Vertex<String, Boolean>>[] data, 
    		int start, int end, int index, int r) { 
        //generate and check current combo
        if (index == r) {
        	Entry<String, Vertex<String, Boolean>>[] combo = new Entry[r];
            for (int j=0; j<r; j++) {
            	combo[j] = data[j];
            }
            if(checkCombo(array, combo)) {
            	found = true;
            }
            return;
        } else {
        	//find a new combo
        	for (int i=start; i<=end && end-i+1>=r-index; i++) {
        		data[index] = array[i]; 
        		findCombo(array, data, i+1, end, index+1, r);
        		if(found) {
        			i = end+1;
        		}
        	}
        }
    }
    /**
     * Determines if a given combination is a valid solution to number 1.
     * @param array The array of graph values
     * @param combo The combination being tested
     * @return Whether the combination is valid
     */
    public static boolean checkCombo(Entry<String, Vertex<String, Boolean>>[] array, Entry<String, Vertex<String, Boolean>>[] combo) {
    	//keeps track of if each thing in array is in combo or any neighbors of an element of combo
    	boolean[] connected = new boolean[array.length];
    	//search for each thing in array in combo and neighbors of elements of combo
    	for(int i=0; i<array.length; i++) {
    		for(int j=0; j<combo.length; j++) {
    			if(array[i].getKey().equals(combo[j].getKey())) {
    				connected[i] = true;
    				j = combo.length;
    			} else {
    				Entry<String, Vertex<String, Boolean>>[] neighbors = combo[j].getValue().getNeighbors();
    				if(neighbors != null) {
    					for(int h=0; h<neighbors.length; h++) {
        					if(array[i].getKey().equals(neighbors[h].getKey())) {
        						connected[i] = true;
        	    				h = neighbors.length;
        						j = combo.length;
        	    			}
        				}
    				}
    			}
    		}
    	}
    	//if anything is not connected, return false
    	for(int i=0; i<connected.length; i++) {
    		if(!connected[i]) {
    			return false;
    		}
    	}
    	//...otherwise, set cheapestCombo and return true
    	cheapestCombo = combo;
    	return true;
    }
	
	
	
    
    
    
    
    
    
	
	
	//methods to help solve number 2
	/**
	 * Generates all possible permutations of a given Entry array.
	 * @param n The size of the array
	 * @param array The array to permute
	 */
	public static void permute(int n, Entry<String, Vertex<String, Boolean>>[] array) {
		if(n == 1) {
			checkArray(array);
		} else {
			for(int i=0; i<n-1; i++) {
				permute(n - 1, array);
				if(n%2 == 0) {
					swap(array, i, n-1);
				} else {
					swap(array, 0, n-1);
				}
			}
			permute(n - 1, array);
		}
	}
	/**
	 * Swaps two elements in a given Entry array of the specified type.
	 * @param array The array where we swap the elements
	 * @param a The index of the first element
	 * @param b The index of the second element
	 */
	public static void swap(Entry<String, Vertex<String, Boolean>>[] array, int a, int b) {
		Entry<String, Vertex<String, Boolean>> tmp = array[a];
	    array[a] = array[b];
	    array[b] = tmp;
	}
	/**
	 * Checks to see if a given route is the shortest so far.
	 * @param array The permutation to check
	 */
	public static void checkArray(Entry<String, Vertex<String, Boolean>>[] array) {
		//create a new route from the given array with startingPoint == first and last element
		@SuppressWarnings("unchecked")
		Entry<String, Vertex<String, Boolean>>[] route = new Entry[array.length+2];
		route[0] = startingPoint;
	    for(int i=0; i<array.length; i++) {
	        route[i+1] = array[i];
	    }
	    route[route.length-1] = startingPoint;
	    //if the new route is the shortest (or first), shortestRoute = route
	    //also, update the distance if there's a new shortest route
	    if(shortestRoute == null) {
	    	shortestRoute = route;
	    	for(int i=1; i<route.length; i++) {
	    		distance += route[i].getValue().getEdgeWeight(route[i-1].getKey());
	    	}
	    } else {
	    	double totalDistance = 0.0;
	    	for(int i=1; i<route.length; i++) {
	    		totalDistance += route[i].getValue().getEdgeWeight(route[i-1].getKey());
	    	}
	    	if(totalDistance < distance) {
	    		shortestRoute = route;
	    		distance = totalDistance;
	    	}
	    }
	}
}
