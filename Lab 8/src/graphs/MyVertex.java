/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.HashMap;
import java.util.Collection;

/**
 *
 * @author krohne
 */
public class MyVertex {
    
    private int number, inDegree;
    private HashMap<Integer, MyVertex> edgeList;
    private HashMap<MyVertex, Double> weightList;
    
    public MyVertex(int number){
        this.number = number;
        inDegree = 0;
        edgeList = new HashMap<>();
        weightList = new HashMap<>();
    }
    
    public Collection<MyVertex> getNeighbors(){
        return edgeList.values();
    }
    
    public HashMap<Integer, MyVertex> getEdges() {
    	return edgeList;
    }
    
    public void decrementNeighborsIndegrees(){
        Collection<MyVertex> neighbors = edgeList.values();
        for(MyVertex current : neighbors){
            current.decrementInDegree();
        }
    }
    
    public int getInDegree(){
        return inDegree;
    }
    
    public void decrementInDegree(){
        inDegree--;
    }
    
    public void incrementInDegree(){
        inDegree++;
    }
    
    public void iterateOverEdges(){
        Collection<MyVertex> neighbors = edgeList.values();
        for(MyVertex current : neighbors){
            current.incrementInDegree();
        }
    }
    
    public boolean containsEdge(int other){
        return edgeList.containsKey(other);
    }
    
    public String toString(){
        String answer = number+""; //+ " (" + inDegree + ") : ";
        //Collection<MyVertex> edges = edgeList.values();
        //for(MyVertex edge : edges){
//            answer += edge.getNumber()+" ";
  //      }
        return answer;
    }
    
    public void addEdge(MyVertex other){
        edgeList.put(other.getNumber(), other);
        weightList.put(other, null);
    }
    
    public void addEdge(MyVertex other, double weight){
        edgeList.put(other.getNumber(), other);
        weightList.put(other, weight);
    }
    
    public int getNumber(){
        return number;    
    }
    
    public HashMap<Integer, MyVertex> getEdgeList() {
    	return edgeList;
    }
}
