package com.shiv.exception.research.dsa.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Graph<T>{
    protected Map<T, List<T>> map=new HashMap<>();

    public void addNewVertex(T s){
        map.put(s, new LinkedList<>());
    }

    public void addNewEdge(T source,T destination,boolean isBidirectional){
        if(!map.containsKey(source))
            addNewVertex(source);
        if(!map.containsKey(destination))
            addNewVertex(destination);
        map.get(source).add(destination);
        if(isBidirectional)
            map.get(destination).add(source);
//        System.out.println(map);
    }
}
public class GraphImplementation{
    public static void main(String[] args) {
        var graph=new Graph<Integer>();
        graph.addNewEdge(2,5,true);
        graph.addNewEdge(2,6,true);
        graph.addNewEdge(2,7,true);
        graph.addNewEdge(2,8,false);
        graph.addNewEdge(2,9,false);
        System.out.println(graph.map);
//        System.out.println(graph.map.keySet());
    }
}
