package com.epam.bsp;

public class Solution {

    /**
     * Merges two sorted subarrays of a given array (in-place).
     *
     * Assume the first ordered subarray is within the [leftIndex; middleIndex] interval
     * and the second subarray is within the (middleIndex; rightIndex] interval.
     *
     * NOTE: even though this method should work in-place, you can use up to O(n) auxiliary space,
     * where n = rightIndex - leftIndex + 1.
     * NOTE: the expected time complexity is O(n), where n = rightIndex - leftIndex + 1.
     *
     * @param data a given array that contains two subarrays for merging.
     * @param leftIndex defines the left bound of the first subarray.
     * @param middleIndex defines the right bound of the first subarray.
     * @param rightIndex defines the right bound of the second subarray.
     */
    public static void mergeSortedSubarrays(int[] data, int leftIndex, int middleIndex, int rightIndex) {
        // Calculate the lengths of the two subarrays
        int leftLength = middleIndex - leftIndex + 1;
        int rightLength = rightIndex - middleIndex;

        // Create temporary arrays for the left and right subarrays
        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];

        // Copy the data into the temporary arrays
        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = data[leftIndex + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightArray[i] = data[middleIndex + 1 + i];
        }

        // Merge the two arrays back into the original array
        int leftPointer = 0, rightPointer = 0, current = leftIndex;
        while (leftPointer < leftLength && rightPointer < rightLength) {
            if (leftArray[leftPointer] <= rightArray[rightPointer]) {
                data[current] = leftArray[leftPointer];
                leftPointer++;
            } else {
                data[current] = rightArray[rightPointer];
                rightPointer++;
            }
            current++;
        }

        // Copy any remaining elements from the left array
        while (leftPointer < leftLength) {
            data[current] = leftArray[leftPointer];
            leftPointer++;
            current++;
        }

        // Copy any remaining elements from the right array
        while (rightPointer < rightLength) {
            data[current] = rightArray[rightPointer];
            rightPointer++;
            current++;
        }
    }

    /**
     * Returns a `special statistic` that will be defined below.
     *
     * The idea is simple: You use the merge sort algorithm to order a given array, and that's it.
     * The algorithm is simple: You divide the current subarray, sort both parts, and then merge them.
     *
     * NOTE: The statistic you need to calculate has the following definition:
     * * At the beginning of the algorithm `statistic=0`.
     * * Each time you use the `mergeSortedSubarrays` method, you should do the following BEFORE and AFTER each call:
     *     * `statistic += data[leftIndex]`.
     *     * `statistic += data[middleIndex]` (even if leftIndex == middleIndex).
     *     * `statistic += data[rightIndex]`.
     * Ask yourself, "But why?" You know, just to avoid silly stuff like `return Arrays.sort(data)`.
     *
     * NOTE: you are expected to implement `mergeSortedSubarrays` before starting with this method.
     * NOTE: the expected time complexity is O(n * log n), where n=data.length.
     *
     * @param data a given array of elements to sort (in ascending order).
     * @return the `statistic` defined above.
     */
    public static int mergeSortAlgorithm(int[] data) {
        // Call the helper method to perform merge sort and calculate the statistic
        return mergeSort(data, 0, data.length - 1);
    }

    private static int mergeSort(int[] data, int leftIndex, int rightIndex) {
        // Check base case: if the subarray has one element or is empty
        if (leftIndex >= rightIndex) {
            return 0;
        }

        // Calculate the middle index
        int middleIndex = (leftIndex + rightIndex) / 2;

        // Initialize statistic
        int statistic = 0;

        // Recursively call mergeSort on the left and right halves
        statistic += mergeSort(data, leftIndex, middleIndex);
        statistic += mergeSort(data, middleIndex + 1, rightIndex);

        // Calculate the statistic before calling mergeSortedSubarrays
        statistic += data[leftIndex] + data[middleIndex] + data[rightIndex];

        // Call mergeSortedSubarrays to merge the two sorted halves
        mergeSortedSubarrays(data, leftIndex, middleIndex, rightIndex);

        // Calculate the statistic after calling mergeSortedSubarrays
        statistic += data[leftIndex] + data[middleIndex] + data[rightIndex];

        // Return the calculated statistic
        return statistic;
    }
}
