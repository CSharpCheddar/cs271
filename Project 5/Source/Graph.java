
/**
 * Creates a graph using my homemade HashMap and Vertex classes.
 * @author muellm79
 *
 * @param <N> The type keys used to find vertices (their unique names)
 * @param <D> The type of data to be stored in the vertices
 */
public class Graph<N, D> {
	//instance variable, just a HashMap of Vertices
	private HashMap<N, Vertex<N, D>> vertices;

	/**
	 * Constructor. Initializes vertex HashMap.
	 */
	public Graph(){
		vertices = new HashMap<N, Vertex<N, D>>();    
	}

	/**
	 * Determines if a Vertex exists.
	 * @param name The name of the Vertex
	 * @return Whether the vertex exists
	 */
	public boolean exists(N name){
		return vertices.contains(name);
	}

	/**
	 * Inserts an edge between two Vertices in the Graph.
	 * @param firstName The name of the first Vertex
	 * @param secondName The name of the second Vertex
	 * @param weight The weight of the edge between the two Vertices
	 */
	public void insertEdge(N firstName, N secondName, Double weight){
		if(exists(firstName) && exists(secondName)){
			//both vertices exist

			//check if the edge exists
			if(!vertices.getValue(firstName).containsEdge(secondName)){                        
				//getting the vertex for first and second
				Vertex<N, D> firstVertex = vertices.getValue(firstName);
				Vertex<N, D> secondVertex = vertices.getValue(secondName);
				//add edge to graph
				firstVertex.addEdge(secondVertex, weight);
				secondVertex.addEdge(firstVertex, weight);
			}
		} else {
			throw new RuntimeException("some vertex doesn't exist");
		}
	}
	
	/**
	 * Inserts an edge from one Vertex to another in the Graph.
	 * @param firstName The name of the first Vertex
	 * @param secondName The name of the second Vertex
	 * @param weight The weight of the edge from one Vertex to another
	 */
	public void insertDirectedEdge(N firstName, N secondName, Double weight){
		if(exists(firstName) && exists(secondName)){
			//both vertices exist

			//check if the edge exists
			if(!vertices.getValue(firstName).containsEdge(secondName)){                        
				//getting the vertex for first and second
				Vertex<N, D> firstVertex = vertices.getValue(firstName);
				Vertex<N, D> secondVertex = vertices.getValue(secondName);
				//add edge to graph
				firstVertex.addEdge(secondVertex, weight);
			}
		} else {
			throw new RuntimeException("some vertex doesn't exist");
		}
	}

	/**
	 * Inserts a Vertex into the Graph.
	 * @param name The name of the new Vertex
	 * @param data The data to be sotred in the Vertex
	 */
	public void insertVertex(N name, D data){
		//check to see if number already exists in vertex list
		if(vertices.contains(name)){
			throw new RuntimeException(name+" already exists");
		} else{
			//number doesn't exist
			Vertex<N, D> temp = new Vertex<N, D>(name, data);
			vertices.insert(name, temp);
		}
	}
	
	/**
	 * Returns all the vertices of the graph inside of an array of Entries.
	 * @return All the vertices of the graph and their names
	 */
	public Entry<N, Vertex<N, D>>[] getVerticies() {
		return vertices.getEntries();
	}
}
