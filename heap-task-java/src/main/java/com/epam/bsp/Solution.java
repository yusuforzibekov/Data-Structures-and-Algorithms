package com.epam.bsp;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * Sorts a given array in ascending order using the heap sort algorithm.
     * The idea is simple:
     * 1) First, use the `minHeapify` to get a heap
     * 2) Then, iteratively execute the `popMinimum` method (almost as in PriorityQueue.pop method) to create an ordered array
     *
     * NOTE: the expected time complexity is O(N * log N), where N=data.length
     * @param data a given array to sort
     * @return the ordered array
     */
    public static int[] heapSort(int[] data) {
        List<Integer> dataList = new ArrayList<>();
        for (int num : data) {
            dataList.add(num);
        }

        List<Integer> heap = minHeapify(dataList);

        int[] sortedArray = new int[data.length];
        int index = 0;
        while (!heap.isEmpty()) {
            sortedArray[index++] = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            heapifyDown(heap, 0);
        }
        return sortedArray;
    }

    /**
     * Returns the result of running the `heapify` (minimum) operation on a given array list of numbers.
     *
     * What we know about the `heapify` operation:
     * 1) The expected time complexity of this operation is O(n), where n=data.size()
     * 2) Here we're considering the "minimum" variation of `heapify`, so for the resulting array list,
     *     the following conditions should hold (let's call the result array list h):
     *         a) h.get(i) <= h.get(2 * i + 1), if 2 * i + 1 < n
     *         b) h.get(i) <= h.get(2 * i + 2), if 2 * i + 2 < n
     *
     * Basically the resulting array list can be used as a base for the heap.
     *
     * @param data a given array list of numbers to `heapify`
     * @return the result of running the `heapify` (minimum) operation on a given array list of numbers.
     */
    public static List<Integer> minHeapify(List<Integer> data) {
        List<Integer> mutableData = new ArrayList<>(data);
        int startIndex = (mutableData.size() - 2) / 2;
        for (int i = startIndex; i >= 0; i--) {
            heapifyDown(mutableData, i);
        }
        return mutableData;
    }    

    private static void heapifyDown(List<Integer> heap, int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size() && heap.get(leftChild) < heap.get(smallest)) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild) < heap.get(smallest)) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(heap, index, smallest);
            heapifyDown(heap, smallest);
        }
    }

    private static void swap(List<Integer> heap, int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
