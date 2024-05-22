package com.epam.bsp;

import java.util.Objects;

public class Solution {

    /**
     * Representation of a pair of integers.
     *
     * Do not change this class!
     */
    public static class Pair {
        /**
         * the index
         */
        private int index;
        /**
         * the number of swaps used
         */
        private int swapsNumber;

        public Pair(int index, int swapsNumber) {
            this.index = index;
            this.swapsNumber = swapsNumber;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getSwapsNumber() {
            return swapsNumber;
        }

        public void setSwapsNumber(int swapsNumber) {
            this.swapsNumber = swapsNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return index == pair.index && swapsNumber == pair.swapsNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, swapsNumber);
        }
    }

    /**
     * Returns a partition index after Lomuto partitioning and the number of swaps used.
     *
     * The idea is simple:
     * - you need to partition a given array using the `rightIndex` element
     * - `partitionIndex` (the first number that should be returned) should contain the last element
     *     of a given interval (the pivot itself), and all elements to the left of it should be strictly lower than the pivot
     * - while partitioning, you need to keep track of the number of swaps required
     *     (according to the pseudocode below)
     *
     *     NOTE: refer to this pseudocode if necessary: https://www.baeldung.com/cs/algorithm-quicksort#1-lomuto-partitioning
     *       The idea is to keep two pointers and gradually move them toward the center.
     *
     *     NOTE: the expected time complexity is O(n), where n = rightIndex - leftIndex + 1
     *     NOTE: this method should work in-place
     *
     * @param array a given array to partition
     * @param leftIndex a starting index for partitioning
     * @param rightIndex an ending index for partitioning
     * @return a Pair instance, where the `pair.index` is the index of the pivoting element after partitioning
     */
    public static <T extends Comparable<T>> Pair lomutoPartition(T[] array, int leftIndex, int rightIndex) {
        T pivot = array[rightIndex];
        int i = leftIndex - 1;
        int swaps = 0;

        for (int j = leftIndex; j < rightIndex; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                swap(array, i, j);
                swaps++;
            }
        }

        swap(array, i + 1, rightIndex);
        swaps++;

        return new Pair(i + 1, swaps);
    }

    /**
     * Implements the quicksort algorithm with Lomuto partitioning.
     *
     * @param array a given array to sort in ascending order
     * @return the number of swaps used
     */
    public static <T extends Comparable<T>> int quicksortLomuto(T[] array) {
        return iterationLomuto(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int iterationLomuto(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) return 0;

        Pair pair = lomutoPartition(array, leftIndex, rightIndex);
        int swaps = pair.getSwapsNumber();

        swaps += iterationLomuto(array, leftIndex, pair.getIndex() - 1);
        swaps += iterationLomuto(array, pair.getIndex() + 1, rightIndex);

        return swaps;
    }

    /**
     * Returns a partition index after Hoare partitioning and the number of swaps used.
     *
     * The idea is simple:
     * - you need to partition a given array using the `middle` element
     * - while partitioning, you need to keep track of the number of swaps required
     *     (according to the pseudocode below)
     *
     *     NOTE: refer to this pseudocode if necessary: https://www.baeldung.com/cs/algorithm-quicksort#2-hoare-partitioning
     *       The idea is to keep two pointers and gradually move them toward the center.
     *
     *     NOTE: the expected time complexity is O(n), where n = rightIndex - leftIndex + 1
     *     NOTE: this method should work in-place
     *
     * @param array a given array to partition
     * @param leftIndex a starting index for partitioning
     * @param rightIndex an ending index for partitioning
     * @return a Pair instance, where the `pair.index` is the final position of the 'right' pointer just before partitioning
     *         ('left' pointer >= 'right' pointer)
     */
    public static <T extends Comparable<T>> Pair hoarePartition(T[] array, int leftIndex, int rightIndex) {
        T pivot = array[(leftIndex + rightIndex) / 2];
        int i = leftIndex;
        int j = rightIndex;
        int swaps = 0;

        while (true) {
            while (array[i].compareTo(pivot) < 0) i++;
            while (array[j].compareTo(pivot) > 0) j--;

            if (i >= j) return new Pair(j, swaps);

            swap(array, i, j);
            swaps++;
            i++;
            j--;
        }
    }

    /**
     * Implements the quicksort algorithm with Hoare partitioning.
     *
     * @param array a given array to sort in ascending order
     * @return the number of swaps used
     */
    public static <T extends Comparable<T>> int quicksortHoare(T[] array) {
        return iterationHoare(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int iterationHoare(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) return 0;

        Pair pair = hoarePartition(array, leftIndex, rightIndex);
        int swaps = pair.getSwapsNumber();

        swaps += iterationHoare(array, leftIndex, pair.getIndex());
        swaps += iterationHoare(array, pair.getIndex() + 1, rightIndex);

        return swaps;
    }

    private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
