package com.epam.bsp;

public class Solution {

    /**
     * Returns the partially sorted array after 'k' iterations of the Selection Sort
     * algorithm.
     * NOTE: after the first 'i' iterations - the first 'i + 1' elements of the
     * array should be ordered.
     * NOTE: 0 <= k < data.length
     *
     * @param data a given array to order.
     * @param k    the required number of selection sort iterations
     * @return the partially sorted array.
     * @param <T> type of array elements
     */
    public static <T extends Comparable<T>> T[] partialSelectionSort(T[] data, int k) {
        for (int i = 0; i < k; i++) {
          int minIndex = i;
          for (int j = i + 1; j < data.length; j++) {
            if (data[j].compareTo(data[minIndex]) < 0) {
              minIndex = j;
            }
          }
          if (minIndex != i) {
            T temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
          }
        }
        return data;
      } 

    /**
     * Returns the partially sorted array after 'k' iterations of the Insertion Sort
     * algorithm.
     * NOTE: after the first 'i' iterations - the first 'i + 1' elements of the
     * array should be ordered.
     * NOTE: 0 <= k < data.length
     *
     * @param data a given array to order.
     * @param k    the required number of insertion sort iterations
     * @return the partially sorted array.
     * @param <T> type of array elements
     */
    public static <T extends Comparable<T>> T[] partialInsertionSort(T[] data, int k) {
        for (int i = 1; i <= k; i++) {
            // Current element to insert
            T key = data[i];
            int j = i - 1;
            // Move elements of data[0..i-1], that are greater than key, to one position
            // ahead of their current position
            while (j >= 0 && data[j].compareTo(key) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
        return data;
    }

    /**
     * Returns the intermediate state of a given array after 'k' swaps of the Bubble
     * Sort algorithm.
     * NOTE: if 'k' exceeds the number of swaps required (and an array can be sorted
     * in less than 'k' swaps) - just ignore the other 'potential' swaps and return
     * the sorted array.
     *
     * @param data a given array to order.
     * @param k    the required number of bubble sort swaps
     * @return the intermediate array after 'k' swaps.
     * @param <T> type of array elements
     */
    public static <T extends Comparable<T>> T[] partialBubbleSort(T[] data, int k) {
        int swaps = 0; // Count the number of swaps
        boolean swapped;
        for (int i = 0; i < data.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < data.length - 1 - i; j++) {
                // Compare adjacent elements and swap if needed
                if (data[j].compareTo(data[j + 1]) > 0) {
                    // Swap data[j] and data[j + 1]
                    T temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swaps++;
                    swapped = true;
                    // If we've reached the required number of swaps, return the partially sorted
                    // array
                    if (swaps == k) {
                        return data;
                    }
                }
            }
            // If there were no swaps in this iteration, the array is already sorted
            if (!swapped) {
                break;
            }
        }
        return data;
    }

}