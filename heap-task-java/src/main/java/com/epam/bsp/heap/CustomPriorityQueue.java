package com.epam.bsp.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic data structure for custom Priority Queue representation
 */
public class CustomPriorityQueue<E extends Comparable<E>> {

    private final List<E> heap;

    public CustomPriorityQueue() {
        heap = new ArrayList<>();
    }

    /**
     * Returns the minimum in the data structure.
     * NOTE: the expected time complexity is O(1).
     *
     * @return the minimum in the data structure.
     */
    public E getMinimum() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap.get(0);
    }

    /**
     * Returns the minimum in the data structure and removes it.
     * NOTE: the expected time complexity is O(log N),
     * where N is the number of elements in the data structure.
     *
     * @return the minimum in the data structure
     */
    public E pop() {
        E min = getMinimum();
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return min;
    }

    /**
     * Inserts a given value into the data structure.
     * NOTE: the expected time complexity is O(log N),
     * where N is the number of elements in the data structure.
     *
     * @param value the given value into the data structure.
     */
    public void insert(E value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Returns true if there are no elements in the data structure.
     * NOTE: the expected time complexity is O(1).
     *
     * @return whether there are no elements in the data structure.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns the number of elements in the data structure.
     * NOTE: the expected time complexity is O(1).
     *
     * @return the number of elements in the data structure.
     */
    public int size() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
