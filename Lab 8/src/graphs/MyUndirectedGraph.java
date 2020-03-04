/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author krohne
 */
public class MyUndirectedGraph {
    private HashMap<Integer, MyVertex> vertices;
    
    public MyUndirectedGraph(){
        vertices = new HashMap<>();    
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
                secondVertex.addEdge(firstVertex);
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
}
