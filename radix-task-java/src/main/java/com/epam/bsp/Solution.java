package com.epam.bsp;

import java.util.*;

public class Solution {

    public static class Pair {
        private int global;
        private int local;

        public Pair(int global, int local) {
            this.global = global;
            this.local = local;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return global == pair.global && local == pair.local;
        }

        @Override
        public int hashCode() {
            return Objects.hash(global, local);
        }

        @Override
        public String toString() {
            return "Pair{" + global + ", " + local + '}';
        }
    }

    record Node(String str, int idx) {

    }

    public static List<Pair> bucketSort(List<String> patterns, int prefixLength) {
        Map<String, List<Node>> buckets = new HashMap<>();

        // Create buckets based on prefixLength
        for (int i = 0; i < patterns.size(); i++) {
            var pattern = patterns.get(i);

            String prefix = pattern.substring(0, prefixLength);
            buckets.computeIfAbsent(prefix, k -> new ArrayList<>()).add(new Node(pattern, i));
        }

        // Sort individual buckets and calculate positions
        var result = new ArrayList<Pair>(patterns.size());
        for (int i = 0; i < patterns.size(); i++) {
            result.add(null);
        }

        var keys = new ArrayList<>(buckets.keySet());
        keys.sort(Comparator.naturalOrder());

        int globalPosition = 0;
        for (var prefix : keys) {
            var bucket = buckets.get(prefix);
            bucket.sort(Comparator.comparing(a -> a.str));
            for (int i = 0; i < bucket.size(); i++) {
                result.set(bucket.get(i).idx, new Pair(globalPosition++, i));
            }

        }

        return result;
    }


    public static List<Integer> radixSort(List<String> array, int k) {
        var list = new ArrayList<Node>(array.size());
        for (int i = 0; i < array.size(); i++) {
            list.add(new Node(array.get(i), i));
        }

        int index = array.get(0).length() - 1;
        for (; k > 0; k--, index--) {
            var digs = new List[10];
            for (int i = 0; i < 10; i++) {
                digs[i] = new ArrayList<Node>();
            }

            for (Node node : list) {
                digs[node.str.charAt(index) - '0'].add(node);
            }

            var newList = new ArrayList<Node>(list.size());
            for (int i = 0; i < 10; i++) {
                for (var node : digs[i])
                    newList.add((Node) node);
            }

            list = newList;
        }

        List<Integer> result = new ArrayList<>(array.size());
        for (int i = 0; i < array.size(); i++) {
            result.add(0);
        }
        int i = 0;
        for (var node : list) {
            result.set(node.idx, i++);
        }

        return result;
    }

//    /**
//     * Representation of a pair of integers.
//     *
//     * Do not change this class!
//     */
//    public static class Pair {
//        /**
//         * Global position of a pattern in an ordered list.
//         */
//        private int global;
//        /**
//         * Local position of a pattern in an ordered list.
//         */
//        private int local;
//
//        public Pair(int global, int local) {
//            this.global = global;
//            this.local = local;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Pair pair = (Pair) o;
//            return global == pair.global && local == pair.local;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(global, local);
//        }
//    }


//    /**
//     * Orders a given list of strings using bucket sort.
//     *
//     * NOTE: for bucketing purposes, you need to use the first `prefixLength` characters
//     * of each input pattern.
//     *     Example 1:
//     *     | patterns = ["aa", "ab", "ba", "bb", "bbac"], prefixLength=1
//     *     | buckets = {"a": ["aa", "ab"], "b": ["ba", "bb", "bbac"]}
//     *
//     *     Example 2:
//     *     | patterns = ["aa", "ab", "ba", "bb", "bbac"], prefixLength=2
//     *     | There will be four buckets (two patterns in the bucket "bb").
//     *
//     * NOTE: for each input pattern, you should return two things:
//     * 1. the final (global) position of pattern after bucket sort
//     * 2. the local position of a pattern in corresponding bucket after ordering within a bucket
//     *
//     * @param patterns a given list of patterns to order
//     * @param prefixLength a parameter that should be used to bucket patterns
//     * @return positions that correspond to given patterns both globally and
//     *             within individual buckets
//     */
//    public static List<Pair> bucketSort(List<String> patterns, int prefixLength) {
//        //put your code here
//        return List.of();
//    }


//    /**
//     * Emulates a given number of iterations of the radix sort algorithm.
//     *
//     * NOTE: all elements of a given list are in string format but contain only '0'-'9' characters.
//     * NOTE: assume that all input elements have the same length d (k <= d).
//     *
//     * @param array a given list of 'integers' to order
//     * @param k the required number of radix sort iterations
//     * @return the list of intermediate positions after `k` iterations.
//     */
//    public static List<Integer> radixSort(List<String> array, int k) {
//        return List.of();
//    }

}