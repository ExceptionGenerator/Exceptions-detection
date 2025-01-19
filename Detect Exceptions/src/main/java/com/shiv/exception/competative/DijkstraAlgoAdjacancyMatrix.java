package com.shiv.exception.competative;

import java.util.Arrays;

public class DijkstraAlgoAdjacancyMatrix {
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 2, 5, 0, 0, 0},
                {2, 0, 0, 0, 1, 0},
                {5, 0, 0, 0, 3, 0},
                {0, 0, 0, 0, 8, 0},
                {0, 1, 3, 8, 1, 9},
                {0, 0, 0, 0, 9, 0}
        };

        DijkstraAlgoAdjacancyMatrix dijkstraAlgoAdjacancyMatrix = new DijkstraAlgoAdjacancyMatrix();
        final var result = dijkstraAlgoAdjacancyMatrix.dijkstraAlgorithm(graph, 0);
        for (int i = 0; i < result.length; i++) {
            System.out.println("Source 0 to " + i + " is " + result[i]);
        }
    }

    public int[] dijkstraAlgorithm(int[][] graph, int source) {
        int[] distances = new int[graph.length];
        boolean[] visitedStatus = new boolean[graph.length];
        Arrays.fill(visitedStatus, false);
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[source] = 0;

        for (int i = 0; i < distances.length; i++) {
            int u = minimumIndex(distances, visitedStatus);
            visitedStatus[u] = true;
            for (int j = 0; j < distances.length; j++) {
                if (!visitedStatus[j] && graph[u][j] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + graph[u][j] < distances[j]) {
                    distances[j] = distances[u] + graph[u][j];
                }
            }
        }
        return distances;
    }

    public int minimumIndex(int[] distances, boolean[] visitedStatus) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visitedStatus[i] && distances[i] <= min) {
                min = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }


}
