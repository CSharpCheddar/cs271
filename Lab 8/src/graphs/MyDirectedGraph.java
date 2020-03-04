/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author krohne
 */
public class MyDirectedGraph {
    private HashMap<Integer, MyVertex> vertices;
    
    public MyDirectedGraph(){
        vertices = new HashMap<>();    
    }
    
    public void topologicalSort(){
        //calculate inDegree
        //\Theta(V+E)
        Collection<MyVertex> allValues = vertices.values();
        for(MyVertex current : allValues){
            current.iterateOverEdges();
        }
        
        Stack<MyVertex> zeroInDegree = new Stack<>();
        
        //O(V)
        //find a vertex with inDegree of 0
        for(MyVertex current : allValues){
            if(current.getInDegree() == 0){
                zeroInDegree.push(current);
            }
        }
        
        //O(V+E)
        while(!zeroInDegree.empty()){
            MyVertex current = zeroInDegree.pop();
            System.out.print(current+" ");
            current.decrementNeighborsIndegrees();
            //check neighbors, if their inDegree is 0, push onto stack
            Collection<MyVertex> currentNeighbors = current.getNeighbors();
            for(MyVertex currentNeighbor : currentNeighbors){
                if(currentNeighbor.getInDegree() == 0){
                    zeroInDegree.push(currentNeighbor);
                }
            }
        }
    }
    
    private boolean exists(int number){
        return vertices.containsKey(number);
        /*for(MyVertex current : vertices){
            if(number == current.getNumber()){
                return true;
            }
        }
        return false;*/
    }
    
    public void printData(){
        Collection<MyVertex> allValues = vertices.values();
        for(MyVertex current : allValues){
            System.out.println(current);
        }
    }
    
    public void insertEdge(int first, int second){
        if(exists(first) && exists(second)){
            //both vertices exist
            
            //check if the edge exists
            if(!vertices.get(first).containsEdge(second)){                        
                //getting the vertex for first and second
                MyVertex firstVertex = vertices.get(first);
                MyVertex secondVertex = vertices.get(second);
                /*for(MyVertex current : vertices){
                    if(current.getNumber() == first){
                        firstVertex = current;
                    } else if(current.getNumber() == second){
                        secondVertex = current;
                    }
                }*/
                //add edge to graph
                firstVertex.addEdge(secondVertex);
            }
        } else{
            throw new RuntimeException("some vertex doesn't exist");
        }
        
    }
    
    public void insertVertex(int number){
        //check to see if number already exists in vertex list
        if(exists(number)){
            throw new RuntimeException(number+" already exists");
        } else{
            //number doesn't exist
            MyVertex temp = new MyVertex(number);
            vertices.put(number, temp);
        }
    }
    
    /**
     * Identical to the other insertEdge method, but there is a weight component.
     * @param src source vertex
     * @param dest destination vertex
     * @param weight weight of the edge
     */
    public void insertEdge(int src, int dest, double weight) {
    	if(exists(src) && exists(dest)){
            //both vertices exist
            
            //check if the edge exists
            if(!vertices.get(src).containsEdge(dest)){                        
                //getting the vertex for first and second
                MyVertex firstVertex = vertices.get(src);
                MyVertex secondVertex = vertices.get(dest);
                /*for(MyVertex current : vertices){
                    if(current.getNumber() == first){
                        firstVertex = current;
                    } else if(current.getNumber() == second){
                        secondVertex = current;
                    }
                }*/
                //add edge to graph
                firstVertex.addEdge(secondVertex, weight);
            }
        } else{
            throw new RuntimeException("some vertex doesn't exist");
        }
    }
    
    /**
     * Determines if this directed graph is strongly connected.
     * @return if the graph is strongly connected
     */
    public boolean connected() {
    	//make a hashmap of all vertices and whether or not they've been visited
    	HashMap<MyVertex, Boolean> visited = new HashMap<MyVertex, Boolean>();
    	for(Map.Entry<Integer, MyVertex> vertex : vertices.entrySet()) {
    		visited.put(vertex.getValue(), false);
    	}
    	//determine if each node is connected to every other one
    	for(Map.Entry<MyVertex, Boolean> vertex : visited.entrySet()) {
    		connected(vertex.getKey(), visited);
    		if(visited.containsValue(false)) {
    			return false;
    		}
    		visited.replaceAll((k, v) -> false);
    	}
    	//if we made it through the whole hashmap, then return true
    	return true;
    }
    
    /**
     * Recursive method to help the non-recursive connected
     */
    private void connected(MyVertex vertex, HashMap<MyVertex, Boolean> visited) {
    	//say we've visited the vertex
    	visited.replace(vertex, true);
    	//visit all its edges
    	HashMap<Integer, MyVertex> edges = vertex.getEdgeList();
    	for(Map.Entry<Integer, MyVertex> edge : edges.entrySet()) {
    		if(!visited.get(edge.getValue())) {
    			connected(edge.getValue(), visited);
    		}
    	}
    }
}
