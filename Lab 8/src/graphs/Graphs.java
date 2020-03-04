/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.ArrayList;

/**
 *
 * @author krohne
 */
public class Graphs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyDirectedGraph graph = new MyDirectedGraph();
        for(int i=0; i<5; i++){
            graph.insertVertex(i);
        }
        graph.insertEdge(0, 1);
        graph.insertEdge(1, 2);
        graph.insertEdge(2, 0);
        graph.insertEdge(0, 3);
        graph.insertEdge(3, 4);
        graph.insertEdge(4, 0);
        
        if(graph.connected()) {
        	System.out.println("True");
        } else {
        	System.out.println("False");
        }
    }
    
}
