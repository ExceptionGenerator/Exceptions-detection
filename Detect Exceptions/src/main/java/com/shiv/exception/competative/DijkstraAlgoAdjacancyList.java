package com.shiv.exception.competative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DijkstraAlgoAdjacancyList {
    private final int distances[];
    private final Set<Integer> finalizedNodeSet = new HashSet<>();
    private final Queue<DijkstraNode> priorityQueue;
    private final int vertices;

    public DijkstraAlgoAdjacancyList(int vertices) {
        this.vertices = vertices;
        distances = new int[vertices];
        priorityQueue = new PriorityQueue<>(vertices, new DijkstraNode());
    }

    public static void main(String[] args) {
        final var source = 0;
        final var vertices = 6;
        final List<List<DijkstraNode>> adjacancyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacancyList.add(new ArrayList<>());
        }
        adjacancyList.get(0).add(new DijkstraNode(1, 2));
        adjacancyList.get(0).add(new DijkstraNode(2, 5));
        adjacancyList.get(1).add(new DijkstraNode(0, 2));
        adjacancyList.get(1).add(new DijkstraNode(4, 1));
        adjacancyList.get(2).add(new DijkstraNode(0, 5));
        adjacancyList.get(2).add(new DijkstraNode(4, 3));
        adjacancyList.get(3).add(new DijkstraNode(4, 8));
        adjacancyList.get(4).add(new DijkstraNode(1, 1));
        adjacancyList.get(4).add(new DijkstraNode(2, 3));
        adjacancyList.get(4).add(new DijkstraNode(3, 8));
        adjacancyList.get(4).add(new DijkstraNode(5, 9));
        adjacancyList.get(5).add(new DijkstraNode(4, 9));
        DijkstraAlgoAdjacancyList dijkstraAlgoAdjacancyList = new DijkstraAlgoAdjacancyList(vertices);
        dijkstraAlgoAdjacancyList.dijkstraAlgorithm(adjacancyList, source);
        for (int i = 0; i < dijkstraAlgoAdjacancyList.distances.length; i++) {
            System.out.println("Distance from source " + source + " to " + i + " is : " + dijkstraAlgoAdjacancyList.distances[i]);
        }
    }

    public void dijkstraAlgorithm(List<List<DijkstraNode>> adajacncyList, int source) {
        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0;
        priorityQueue.add(new DijkstraNode(source, 0));
        while (!priorityQueue.isEmpty()) {
            final var uNode = priorityQueue.remove().node;
            if (finalizedNodeSet.contains(uNode)) {
                continue;
            }
            finalizedNodeSet.add(uNode);
            traverseNeighbours(uNode, adajacncyList);
        }
    }

    public void traverseNeighbours(int uNode, List<List<DijkstraNode>> adajacncyList) {
        var edgeDistance = -1;
        var newDistance = -1;
        for (int i = 0; i < adajacncyList.get(uNode).size(); i++) {
            final DijkstraNode dijkstraNode = adajacncyList.get(uNode).get(i);
            if (!finalizedNodeSet.contains(dijkstraNode.node)) {
                edgeDistance = dijkstraNode.cost;
                newDistance = distances[uNode] + edgeDistance;
                if (newDistance < distances[dijkstraNode.node]) {
                    distances[dijkstraNode.node] = newDistance;
                }
                priorityQueue.add(new DijkstraNode(dijkstraNode.node, distances[dijkstraNode.node]));
            }
        }
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class DijkstraNode implements Comparator<DijkstraNode> {
        public int node;
        public int cost;

        @Override
        public int compare(DijkstraNode dijkstraNode1, DijkstraNode dijkstraNode2) {
            return Integer.compare(dijkstraNode1.cost, dijkstraNode2.cost);
        }
    }

}
