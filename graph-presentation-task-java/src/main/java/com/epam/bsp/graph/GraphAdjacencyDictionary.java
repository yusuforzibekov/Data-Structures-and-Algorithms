package com.epam.bsp.graph;

import java.util.*;

public class GraphAdjacencyDictionary implements UndirectedGraphRepresentation {
    private Map<Integer, Set<Integer>> adjacencyDictionary;

    public GraphAdjacencyDictionary(int numberOfVertices) {
        adjacencyDictionary = new HashMap<>();
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyDictionary.put(i, new HashSet<>());
        }
    }

    @Override
    public void addEdge(int vertexA, int vertexB) {
        adjacencyDictionary.get(vertexA).add(vertexB);
        adjacencyDictionary.get(vertexB).add(vertexA);
    }

    @Override
    public int[] getAdjacencyIndicators(int vertex) {
        int[] indicators = new int[adjacencyDictionary.size()];
        Set<Integer> adjacencySet = adjacencyDictionary.get(vertex);
        for (int v : adjacencySet) {
            indicators[v] = 1;
        }
        return indicators;
    }

    @Override
    public int getNumberOfAdjacentVertices(int vertex) {
        return adjacencyDictionary.get(vertex).size();
    }

    @Override
    public boolean edgeExists(int vertexA, int vertexB) {
        return adjacencyDictionary.get(vertexA).contains(vertexB);
    }
}
