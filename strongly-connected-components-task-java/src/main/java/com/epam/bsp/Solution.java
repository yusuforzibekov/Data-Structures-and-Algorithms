package com.epam.bsp;

import java.util.*;


public class Solution {

    /**
     * Returns the number of strongly connected components in a directed graph.
     * <p>
     * For example, there is a graph with six vertices from 0 to 6 exclusively and the edges
     * {{0,1}, (0,5), {1,5}, {5,0}, {3,4}, (4,3)}.
     * There are tree strongly connected components: {0, 5, 1}, {2} and {3,4}.
     * The edges above will have the following 'adjacency dictionary' representation:
     * {0: {1,5}, 1: {5}, 3: {4}, 4: {3}, 5: {0}}.
     *
     * @param n     the number of vertices in the graph.
     * @param edges the adjacency dictionary which stores a set of adjacent vertices for each vertex.
     */
    public static int findNumberOfSCC(int n, Map<Integer, Set<Integer>> edges) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, edges, visited, stack);
            }
        }

        Map<Integer, Set<Integer>> reversedEdges = reverseEdges(edges);

        Arrays.fill(visited, false);
        int count = 0;
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                dfs(vertex, reversedEdges, visited);
                count++;
            }
        }

        return count;
    }

    private static void dfs(int vertex, Map<Integer, Set<Integer>> edges, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        Set<Integer> adjacentVertices = edges.getOrDefault(vertex, new HashSet<>());
        for (int adjVertex : adjacentVertices) {
            if (!visited[adjVertex]) {
                dfs(adjVertex, edges, visited, stack);
            }
        }
        stack.push(vertex);
    }

    private static void dfs(int vertex, Map<Integer, Set<Integer>> edges, boolean[] visited) {
        visited[vertex] = true;
        Set<Integer> adjacentVertices = edges.getOrDefault(vertex, new HashSet<>());
        for (int adjVertex : adjacentVertices) {
            if (!visited[adjVertex]) {
                dfs(adjVertex, edges, visited);
            }
        }
    }

    private static Map<Integer, Set<Integer>> reverseEdges(Map<Integer, Set<Integer>> edges) {
        Map<Integer, Set<Integer>> reversedEdges = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : edges.entrySet()) {
            int vertex = entry.getKey();
            Set<Integer> adjacentVertices = entry.getValue();
            for (int adjVertex : adjacentVertices) {
                Set<Integer> reversedAdjacentVertices = reversedEdges.getOrDefault(adjVertex, new HashSet<>());
                reversedAdjacentVertices.add(vertex);
                reversedEdges.put(adjVertex, reversedAdjacentVertices);
            }
        }
        return reversedEdges;
    }
}
