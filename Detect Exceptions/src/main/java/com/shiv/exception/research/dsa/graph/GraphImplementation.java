package com.shiv.exception.research.dsa.graph;

import java.util.*;

class Graph{
    protected Map<Integer, List<Integer>> map=new HashMap<>();

    public void addNewVertex(Integer s){
        map.put(s, new LinkedList<>());
    }

    public void addNewEdge(Integer source,Integer destination,boolean isBidirectional){
        if(!map.containsKey(source))
            addNewVertex(source);
        if(!map.containsKey(destination))
            addNewVertex(destination);
        map.get(source).add(destination);
        if(isBidirectional)
            map.get(destination).add(source);
    }

    public boolean bfs(Integer source,Integer destination,int vertex){
        boolean[] visited=new boolean[vertex+1];
        Queue<Integer> queue=new LinkedList<>();
        queue.add(source);
        visited[source]=true;
        while (!queue.isEmpty()){
            int cur=queue.remove();
            for(int i=0;i<map.get(cur).size();i++){
                int neighbor=map.get(cur).get(i);
                System.out.println(neighbor);
                if(!visited[neighbor]){
                    visited[neighbor]=true;
                    queue.add(neighbor);
                    if(neighbor==destination)
                        return true;
                }
            }
        }
        for(int i=0;i<visited.length;i++)
            System.out.println(visited[i]);
        return false;
    }

}
public class GraphImplementation{
    public static void main(String[] args) {
        var graph=new Graph();
        graph.addNewEdge(1,3,true);
        graph.addNewEdge(1,4,true);
        graph.addNewEdge(2,7,true);
        graph.addNewEdge(2,5,false);
        graph.addNewEdge(2,6,false);
        graph.bfs(2,7,7);
    }
}
