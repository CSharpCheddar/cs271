/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A vertex of a graph.
 * @author muellm79
 */
public class Vertex<N, D> {
	//instance variables
	private N name;
	private D data;
	private int inDegree;
	private HashMap<N, Vertex<N, D>> edgeList;
	private HashMap<Vertex<N, D>, Double> weightList;
	
	/**
	 * Constructor. Initializes instance variables.
	 * @param name The name of the Vertex.
	 * @param data The data the Vertex will hold.
	 */
	public Vertex(N name, D data){
		this.name = name;
		this.data = data;
		inDegree = 0;
		edgeList = new HashMap<>();
		weightList = new HashMap<>();
	}
	
	/**
	 * Returns all the neighbors of the Vertex.
	 * @return All the neighbors of the Vertex.
	 */
	public Entry<N, Vertex<N, D>>[] getNeighbors(){
		return edgeList.getEntries();
	}
	
	/**
	 * Returns the list of edges of the Vertex.
	 * @return The list of edges in the Vertex
	 */
	public HashMap<N, Vertex<N, D>> getEdges() {
		return edgeList;
	}
	
	/**
	 * Decrements the indegrees of the Vertex's neighbors.
	 */
	public void decrementNeighborsIndegrees(){
		Entry<N, Vertex<N, D>>[] neighbors = edgeList.getEntries();
		for(Entry<N, Vertex<N, D>> current : neighbors){
			current.getValue().decrementInDegree();
		}
	}
	
	/**
	 * Gets the indegree of the Vertex.
	 * @return The indegree of the Vertex
	 */
	public int getInDegree(){
		return inDegree;
	}
	
	/**
	 * Lowers the indegree of the Vertex by 1.
	 */
	public void decrementInDegree(){
		inDegree--;
	}

	/**
	 * Raises the indegree of the Vertex by 1.
	 */
	public void incrementInDegree(){
		inDegree++;
	}

	/**
	 * Increments the indegrees of all the Vertex's neighbors.
	 */
	public void iterateOverEdges(){
		Entry<N, Vertex<N, D>>[] neighbors = edgeList.getEntries();
		for(Entry<N, Vertex<N, D>> entry : neighbors){
			entry.getValue().incrementInDegree();
		}
	}

	/**
	 * Determines if this Vertex contains an edge leading to another.
	 * @param name The name of the other Vertex
	 * @return If an edge exists between this Vertex and the other
	 */
	public boolean containsEdge(N name){
		return edgeList.contains(name);
	}

	/**
	 * Adds an edge between this Vertex and another.
	 * @param other The Vertex this one will have a connection to
	 */
	public void addEdge(Vertex<N, D> other){
		edgeList.insert(other.getName(), other);
		weightList.insert(other, null);
	}

	/**
	 * Adds a weighted edge between this Vertex and another.
	 * @param other The other Vertex to which this one will connect
	 * @param weight The weight of the edge connecting the two Vertices
	 */
	public void addEdge(Vertex<N, D> other, double weight){
		edgeList.insert(other.getName(), other);
		weightList.insert(other, weight);
	}

	/**
	 * Gets the name of the Vertex
	 * @return The name of the Vertex
	 */
	public N getName(){
		return name;    
	}

	/**
	 * Retrieves the data stored in the Vertex.
	 * @return The data stored in the Vertex
	 */
	public D getData() {
		return data;
	}
	
	/**
	 * Changes the data stored in the Vertex.
	 * @param data The data to be stored in the Vertex
	 */
	public void setData(D data) {
		this.data = data;
	}
	
	/**
	 * Gets the weight of the edge from this Vertex to another.
	 * @param neighborName The name of the neighbor to get the edge weight of
	 * @return The weight of the edge from this Vertex to the specified neighbor
	 */
	public double getEdgeWeight(N neighborName) {
		return weightList.getValue(edgeList.getValue(neighborName));
	}
}
