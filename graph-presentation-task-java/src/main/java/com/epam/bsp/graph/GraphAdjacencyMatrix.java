package com.epam.bsp.graph;

public class GraphAdjacencyMatrix implements UndirectedGraphRepresentation {
    private int[][] adjacencyMatrix;

    public GraphAdjacencyMatrix(int numberOfVertices) {
        adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
    }

    @Override
    public void addEdge(int vertexA, int vertexB) {
        adjacencyMatrix[vertexA][vertexB] = 1;
        adjacencyMatrix[vertexB][vertexA] = 1;
    }

    @Override
    public int[] getAdjacencyIndicators(int vertex) {
        return adjacencyMatrix[vertex];
    }

    @Override
    public int getNumberOfAdjacentVertices(int vertex) {
        int count = 0;
        for (int i = 0; i < adjacencyMatrix[vertex].length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean edgeExists(int vertexA, int vertexB) {
        return adjacencyMatrix[vertexA][vertexB] == 1;
    }
}
