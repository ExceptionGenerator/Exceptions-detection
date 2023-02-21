package com.shiv.exception.research.dsa.graph;

public class GraphImplUsingMatrix {
    public static void main(String[] args) {
        int vertex=5;
        int[][] array=new int[vertex+1][vertex+1];
        addEdge(array,1,2,true);
        addEdge(array,1,4,true);
        addEdge(array,1,5,true);
        addEdge(array,2,1,true);
        addEdge(array,2,4,true);
        addEdge(array,3,5,true);
        addEdge(array,4,1,true);
        addEdge(array,4,2,true);
        addEdge(array,4,5,true);
        addEdge(array,5,1,true);
        addEdge(array,5,3,true);
        addEdge(array,5,4,true);
        for(int i=1;i< array.length;i++){
            for(int j=1;j<array.length;j++)
                System.out.print(" "+array[i][j]);
            System.out.println();
        }
        System.out.println("Total vertex-"+getVertexCount(array));
    }

    /**
     *
     * @param arr array of the matrix 2D
     * @param source source vertex
     * @param destination destination vertex
     * @param isBiDirectional if is it bidirectional or not
     */
    public static void addEdge(int arr[][], int source,int destination,boolean isBiDirectional){
        arr[source][destination]=1;
        if(isBiDirectional)
            arr[destination][source]=1;
    }

    public static int getVertexCount(int arr[][]){
        return arr.length-1;
    }
    public static int getEdgeCount(int arr[][]){

        return arr.length-1;
    }
}
