package com.epam.bsp;

import java.util.*;

public class Solution {

    /**
     * Representation of a pair of indices.
     */
    public static class Pair {
        private int i;
        private int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            //the order of indices does not matter
            return (i == pair.i && j == pair.j) || (i == pair.j && j == pair.i);
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    /**
     * Returns a pair of indices so that the corresponding values add up to a given target.
     * @param values available elements for look-up.
     * @param target target sum for look-up.
     * @return two indices of interest; otherwise null, if no pair exists.
     */
    public static Pair findTargetSum(List<Integer> values, int target) {
        //put your code here
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            int complement = target - values.get(i);
            if(map.containsKey(complement)){
                return new Pair(map.get(complement), i);
            }
            map.put(values.get(i), i);
        }
        return null;
    }

    private final static String DNA_ALPHABET = "ATGC";

    /**
     * Returns all 8-letter-long substrings that occur more than once.
     * @param dnaSequence a given DNA sequence.
     * @return set of all 8-letter-long substrings that occur more than once.
     */
    public static Set<String> findRepeatedDnaSequences(String dnaSequence) {
        Set<String> set = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        for (int i = 0; i <= dnaSequence.length() - 8; i++) {
            String sequence = dnaSequence.substring(i, i + 8);
            if (!set.add(sequence)) {
                repeated.add(sequence);
            }
        }
        return repeated;

    }

}