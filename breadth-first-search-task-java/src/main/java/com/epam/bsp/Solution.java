package com.epam.bsp;

import java.util.*;

public class Solution {

    /**
     * Returns the number of connectivity components in an undirected graph.
     * 
     * E.g. there is a graph with 6 vertices from 0 to 5 and edges {{0,1}, {1,5}, {5,0}, {3,4}},
     *   there are 3 connectivity components: {0, 5, 1}, {2}, {3,4}.
     * Edges above will have the following 'adjacency dictionary' look: 
     *   {0: {1,5}, 1: {0,5}, 3: {4}, 4: {3}, 5: {0,1}}.
     * 
     * @param n the number of vertices in the graph, vertices are enumerated from 0 to n-1.
     * @param edges the adjacency dictionary which stores a set of adjacent vertices for each vertex.
     * @return the number of connectivity components in the given undirected graph.
     */
    public static int findNumberOfComponents(int n, Map<Integer, Set<Integer>> edges) {
        int numComponents = 0;
        Set<Integer> visited = new HashSet<>();
        for (int vertex = 0; vertex < n; vertex++) {
            if (!visited.contains(vertex)) {
                numComponents++;
                dfs(edges, vertex, visited);
            }
        }
        return numComponents;
    }
    
    /**
     * Depth-first search implementation to traverse the graph and mark the visited vertices.
     */
    private static void dfs(Map<Integer, Set<Integer>> edges, int vertex, Set<Integer> visited) {
        visited.add(vertex);
        Set<Integer> neighbors = edges.get(vertex);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    dfs(edges, neighbor, visited);
                }
            }
        }
    }

    /**
     * Returns True if there is at least one cycle in the undirected graph.
     * 
     * E.g. there is a graph with 6 vertices from 0 to 5 and edges {{0,1}, {1,5}, {5,0}, {3,4}},
     *   the expected result is True because there is a cycle 0 - 1 - 5 - 0.
     *
     * @param n the number of vertices in the graph, vertices are enumerated from 0 to n-1.
     * @param edges the adjacency dictionary which stores a set of adjacent vertices for each vertex.
     * @return True if there is a cycle in the undirected graph, otherwise False
     */
    public static boolean checkCycleExistence(int n, Map<Integer, Set<Integer>> edges) {
        Set<Integer> visited = new HashSet<>();
        for (int vertex = 0; vertex < n; vertex++) {
            if (!visited.contains(vertex)) {
                if (dfsCycle(edges, vertex, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Depth-first search implementation to detect cycles by keeping track of the parent for each visited vertex.
     */
    private static boolean dfsCycle(Map<Integer, Set<Integer>> edges, int vertex, Set<Integer> visited, int parent) {
        visited.add(vertex);
        Set<Integer> neighbors = edges.get(vertex);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    if (dfsCycle(edges, neighbor, visited, vertex)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    // If a neighbor except the parent has been visited, there is a cycle.
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a list with the minimum distances from the vertex to all vertices, including itself.
     * 
     * NOTE: If there is no path, please set the distance to -1.
     * 
     * E.g. you have graph with 4 vertices from 0 to 3 and edges {{0,1}, {1,2}). 
     * Initial vertex is 2, and the expected result is [2, 1, 0, -1]. 
     * - 2 at the first position (index 0) means that two edges
     *     are needed to get from vertex '2' to vertex '0': 2 -> 1 -> 0. 
     * - At the 2nd position (index 1), there is 1 because the vertices '1' and '2' are adjacent.
     * - 0 is placed on the 3rd place (index 2) because it is the starting point and 
     *     the list ends with -1 because there is no path between vertices '2' and '3'.
     *
     * @param n the number of vertices in the graph, vertices are enumerated from 0 to n-1.
     * @param edges the adjacency dictionary which stores a set of adjacent vertices for each vertex.
     * @param vertex the initial vertex
     * @return the result distance list which reflects the distances between initial vertex and 
     *   all other vertices, the number at each index I shows the distance from starting vertex to vertex I.
     */
    public static int[] getDistancesFromVertex(int n, Map<Integer, Set<Integer>> edges, int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[vertex] = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            Set<Integer> neighbors = edges.get(current);
            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    if (dist[neighbor] == -1) {
                        dist[neighbor] = dist[current] + 1;
                        queue.add(neighbor);
                    }
                }
            }
        }
        return dist;
    }
}
