package com.epam.bsp;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * Returns all positions within a given text where a given pattern occurs.
     *
     * NOTE: Nothing fancy is expected here, you can just use the naive algorithm.
     *
     * @param text a given text for searching.
     * @param pattern a given pattern for searching.
     * @return all indices in the text where the pattern occurs.
     */
    public static List<Integer> findOccurrences(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                occurrences.add(i);
            }
        }
        return occurrences;
    }

    /**
     * Returns the Levenshtein distance between two strings.
     *
     * @param a the first string.
     * @param b the second string.
     * @return the Levenshtein distance between the given strings.
     */
    public static int findLevenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
