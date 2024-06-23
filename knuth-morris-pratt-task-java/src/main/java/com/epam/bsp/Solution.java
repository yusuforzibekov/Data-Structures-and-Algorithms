package com.epam.bsp;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * Returns the LPS array for a given string.
     *
     * @param text a given string.
     * @return the result LPS array.
     */
    public static int[] buildLPSArray(String text) {
        int n = text.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        
        while (i < n) {
            if (text.charAt(i) == text.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Returns all positions within a given text where a given pattern occurs.
     * 
     * NOTE: Please use the KMP algorithm.
     *
     * @param text the text to be searched.
     * @param pattern the pattern to search for.
     * @return all indices in the given text where the pattern occurs.
     */
    public static List<Integer> findOccurrencesViaKMP(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();

        int[] lps = buildLPSArray(pattern);
        int i = 0;
        int j = 0;

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                result.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    /**
     * Checks whether a given string is a tandem repeat.
     * 
     * NOTE: The expected complexity is O(n), where n is the length of `text`.
     *
     * @param text a given string.
     * @return whether a given string is a tandem repeat.
     */
    public static boolean isTandemRepeat(String text) {
        int n = text.length();
        int[] lps = buildLPSArray(text);
        int len = lps[n - 1];
        
        return len > 0 && n % (n - len) == 0;
    }
}
