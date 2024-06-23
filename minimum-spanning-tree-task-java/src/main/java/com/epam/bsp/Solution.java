package com.epam.bsp;

import com.epam.bsp.disjoint.set.DisjointSets;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;

public class Solution {

    /**
     * Returns the weight of the MST for an undirected weighted graph using Kruskal's algorithm.
     *
     * The expected algorithm complexity is O(MlogM),
     *   where M is the number of edges, M << n^2, and n is the number of vertices.
     *
     * If no MST exists, please, return `Optional.empty()`.
     *
     * @param n     the number of vertices in the graph.
     * @param edges contains the indexes of the connected vertices and the weight of this edge.
     * @return the weight of the MST.
     */
    public static Optional<Integer> kruskalMST(int n, List<Triplet<Integer, Integer, Integer>> edges) {
        // Sort edges based on their weights
        edges.sort(Comparator.comparing(Triplet::getValue2));

        DisjointSets<Integer> disjointSets = new DisjointSets<>();
        for (int i = 0; i < n; i++) {
            disjointSets.makeSet(i);
        }

        int mstWeight = 0;
        int edgeCount = 0;

        for (Triplet<Integer, Integer, Integer> edge : edges) {
            int u = edge.getValue0();
            int v = edge.getValue1();
            int weight = edge.getValue2();

            if (!disjointSets.findSet(u).equals(disjointSets.findSet(v))) {
                disjointSets.unionSets(u, v);
                mstWeight += weight;
                edgeCount++;
                if (edgeCount == n - 1) {
                    break;
                }
            }
        }

        if (edgeCount == n - 1) {
            return Optional.of(mstWeight);
        } else {
            return Optional.empty(); // No MST found
        }
    }

    /**
     * Returns the order of adding vertices to the MST according to Prim's algorithm and
     * the weight of the MST for an undirected weighted graph.
     *
     * The expected algorithm complexity is O(n^2).
     *
     * The starting vertex should be 0.
     *
     * If several vertices can be chosen at any iteration,
     * the one with the smallest index should be added.
     *
     * If no MST exists, please, return `Optional.empty()`.
     *
     * @param n               the number of vertices in the graph.
     * @param adjacencyMatrix the adjacency matrix.
     * @return the order of adding vertices to the MST, the weight of the MST.
     */
    public static Optional<Pair<Integer[], Integer>> primMST(int n, int[][] adjacencyMatrix) {
        boolean[] visited = new boolean[n];
        int[] minWeight = new int[n];
        int[] parent = new int[n];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
    
        minWeight[0] = 0; // Start from vertex 0
    
        int mstWeight = 0;
        List<Integer> mstOrder = new ArrayList<>();
    
        // Add the starting vertex to the MST order
        mstOrder.add(0);
    
        for (int i = 0; i < n; i++) {
            int u = -1;
            // Find the vertex with the smallest weight that has not been visited
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || minWeight[j] < minWeight[u])) {
                    u = j;
                }
            }
    
            if (minWeight[u] == Integer.MAX_VALUE) {
                break; // No MST found
            }
    
            visited[u] = true;
            mstWeight += minWeight[u];
    
            // Update the weights of adjacent vertices
            for (int v = 0; v < n; v++) {
                if (adjacencyMatrix[u][v] != 0 && !visited[v] && adjacencyMatrix[u][v] < minWeight[v]) {
                    minWeight[v] = adjacencyMatrix[u][v];
                    parent[v] = u;
                }
            }
    
            // Add the current vertex to the MST order if it's not the starting vertex
            if (i > 0) {
                mstOrder.add(u);
            }
        }
    
        if (mstOrder.size() == n) {
            Integer[] orderArray = mstOrder.toArray(new Integer[0]);
            return Optional.of(new Pair<>(orderArray, mstWeight));
        } else {
            return Optional.empty(); // No MST found
        }
    }
}
