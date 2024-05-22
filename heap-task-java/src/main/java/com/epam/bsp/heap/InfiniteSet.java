package com.epam.bsp.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates a set of all natural numbers.
 */
public class InfiniteSet {

    private final CustomPriorityQueue<Integer> priorityQueue;
    private int nextNumber;

    public InfiniteSet() {
        priorityQueue = new CustomPriorityQueue<>();
        nextNumber = 1;
    }

    /**
     * Returns the minimum natural number available and removes it from the set.
     * NOTE: the expected complexity is O(log K),
     * where K is the total number of `popMinimum/insert` calls done previously.
     *
     * @return the minimum natural number available
     */
    public int popMinimum() {
        if (priorityQueue.isEmpty()) return nextNumber++;
        return priorityQueue.pop();
    }

    /**
     * Inserts a given number back into the set.
     * NOTE: the expected complexity is O(log K),
     * where K is the total number of `popMinimum/insert` calls done previously.
     *
     * @param x the given number to insert back into the set.
     */
    public void insert(int x) {
        priorityQueue.insert(x);
    }
}
