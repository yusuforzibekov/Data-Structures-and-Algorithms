package com.epam.bsp.disjoint.set;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for supporting disjoint sets.
 * NOTE: the expected implementation should contain:
 * "path compression" heuristic
 * "union by rank" heuristic (based on sets size)
 *
 * @param <K> type of key
 */
public class DisjointSets<K> {

    private Map<K, K> parent;
    private Map<K, Integer> rank;

    public DisjointSets() {
        parent = new HashMap<>();
        rank = new HashMap<>();
    }

    /**
     * Creates a new set that is associated to a given key.
     *
     * @param key the key
     */
    public void makeSet(K key) {
        parent.put(key, key);
        rank.put(key, 0);
    }

    /**
     * Returns a unique set identifier (key) of a given's key set.
     * NOTE: "path compression" heuristic is expected to be used.
     *
     * @param key the key
     * @return the unique set identifier
     */
    public K findSet(K key) {
        if (!parent.get(key).equals(key)) {
            parent.put(key, findSet(parent.get(key))); // path compression
        }
        return parent.get(key);
    }

    /**
     * Joins two given sets into a new one.
     * NOTE: "union by rank" heuristic is expected to be used (based on sets size).
     * NOTE: if the sets that correspond to the given keys are of the same rank -
     * prefer the second set when deciding what set is to be used as a new "root".
     *
     * @param firstKey  the key of a first set
     * @param secondKey the key of a second set
     */
    public void unionSets(K firstKey, K secondKey) {
        K rootX = findSet(firstKey);
        K rootY = findSet(secondKey);

        if (!rootX.equals(rootY)) {
            // union by rank
            if (rank.get(rootX) > rank.get(rootY)) {
                parent.put(rootY, rootX);
            } else if (rank.get(rootX) < rank.get(rootY)) {
                parent.put(rootX, rootY);
            } else {
                parent.put(rootY, rootX);
                rank.put(rootX, rank.get(rootX) + 1);
            }
        }
    }
}
