package com.epam.bsp;

import java.util.Map;
import java.util.Optional;
import org.javatuples.Pair;

public class Solution {

    /**
     * Returns the maximum probability of getting from vertex `v` to vertex `u` in an undirected weighted graph.
     *
     *
     * Your answer will be accepted if it differs from the correct answer by 1e-5 or less.
     *
     * For example, there is a graph with three vertices from 0 to 2 and the following edges:
     * {0: {(1, 0.5), (2, 0.2)), 1: {(0, 0.5), (2, 0.5)}, 2: {(0, 0.2), (1, 0.5)}}
     * If the starting vertex is 0 and the ending vertex is 2, the expected result is 0.25.
     * There are two paths from start to end, one (0 -> 2) with a probability of success of 0.2
     * the other (0 -> 1 -> 2) with a probability of 0.5 * 0.5 = 0.25.
     *
     * @param n      the number of vertices in the graph, numbered from 0 to n-1.
     * @param edges  the adjacency dictionary, which stores a set of adjacent vertices
     *               and its weights for each vertex.
     * @param vertexV start vertex.
     * @param vertexU finish vertex.
     * @return the maximum probability of getting from vertex `v` to vertex `u`.
     */
    public static double getMaximumProbabilityPath(
            int n,
            Map<Integer, Map<Integer, Double>> edges,
            int vertexV,
            int vertexU
    ) {
        double[] prob = new double[n];
        boolean[] visited = new boolean[n];
        prob[vertexV] = 1;
        for (int i = 0; i < n; i++) {
            int maxVertex = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (maxVertex == -1 || prob[j] > prob[maxVertex])) {
                    maxVertex = j;
                }
            }
            visited[maxVertex] = true;
            if (edges.containsKey(maxVertex)) {
                for (Map.Entry<Integer, Double> entry : edges.get(maxVertex).entrySet()) {
                    int v = entry.getKey();
                    double weight = entry.getValue();
                    if (prob[maxVertex] * weight > prob[v]) {
                        prob[v] = prob[maxVertex] * weight;
                    }
                }
            }
        }
        return prob[vertexU];
    }

    /**
     * Returns a pair of integers, where the first value is the sum of minimum cost paths from vertex `v`
     * to all achievable vertices and the second is the number of unachievable vertices
     * from vertex `v` in a directed weighted graph.
     * For example, there is a graph with four vertices from 0 to 3 and the following edges/weights:
     * {0: {(1, 3), (2, 7)}, 1: {(2, 5)}, 2: {(3, 5)}}
     * If the starting vertex is 1, the expected result is (15, 1).
     * Vertex 0 is the only unachievable vertex from vertex 1.
     * The cost of getting a vertex that is equal to the starting vertex is 0,
     * the (1,2)-path min cost is 5 and (1,3)-path min cost is 10 (1 -> 2 -> 3).
     *
     * @param n      number of vertices in the graph, vertices are enumerated from 0 to n-1.
     * @param edges  adjacency dictionary which stores a set of adjacent vertices and the weights for each vertex.
     * @param vertexV start vertex.
     * @return the sum of the minimum cost paths from vertex `v` to all achievable vertices
     * and the number of unachievable vertices from vertex 'v'
     */
    public static Optional<Pair<Integer, Integer>> findShortestPathsCost(
            int n,
            Map<Integer, Map<Integer, Integer>> edges,
            int vertexV
    ) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[vertexV] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                if (edges.containsKey(u)) {
                    for (Map.Entry<Integer, Integer> entry : edges.get(u).entrySet()) {
                        int v = entry.getKey();
                        int weight = entry.getValue();
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                            dist[v] = dist[u] + weight;
                        }
                    }
                }
            }
        }
        for (int u = 0; u < n; u++) {
            if (edges.containsKey(u)) {
                for (Map.Entry<Integer, Integer> entry : edges.get(u).entrySet()) {
                    int v = entry.getKey();
                    int weight = entry.getValue();
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        return Optional.empty();
                    }
                }
            }
        }
        int sum = 0;
        int unreachableCount = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                unreachableCount++;
            } else {
                sum += dist[i];
            }
        }
        return Optional.of(new Pair<>(sum, unreachableCount));
    }
}
