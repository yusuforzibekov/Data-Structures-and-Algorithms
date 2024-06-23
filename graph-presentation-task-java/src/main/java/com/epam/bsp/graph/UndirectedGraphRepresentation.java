package com.epam.bsp.graph;

/**
 * PLEASE DON'T CHANGE THIS INTERFACE
 */
public interface UndirectedGraphRepresentation {

    /**
     * Adds a new edge to the graph.
     *
     * @param vertexA the first vertex.
     * @param vertexB the second vertex.
     */
    void addEdge(int vertexA, int vertexB);

    /**
     * Returns a binary (0/1) list for the given vertex that indicates vertices adjacent to it.
     * <p>
     * Positions of 1-s in the result list correspond to vertices that are adjacent to the given vertex.
     * E.g. V = {0,1,2), E = {{0,1}, {1,2}} the following list should be returned for the vertex 0:
     * [0, 1, 0].
     *
     * @param vertex the vertex of interest.
     * @return the binary vector where 1-s indicate that the corresponding
     * vertices are adjacent to the given vertex.
     */
    int[] getAdjacencyIndicators(int vertex);

    /**
     * Returns the number of adjacent vertices to the given vertex.
     *
     * @param vertex the vertex of interest.
     * @return the number of vertices in the graph that are adjacent to the given vertex.
     */
    int getNumberOfAdjacentVertices(int vertex);

    /**
     * Returns True if the given edge exists in the graph (between two given vertices).
     *
     * @param vertexA the first vertex.
     * @param vertexB the second vertex.
     * @return whether the given edge exists in the graph.
     */
    boolean edgeExists(int vertexA, int vertexB);
}
