package com.epam.bsp;

public class Solution {

    /**
     * Returns the optimal substructure matrix for a given MCM problem.
     *
     * The idea is to create a matrix F, where:
     * F[i][j] is the minimum number of operations required to multiply matrices
     * with the indices [i..j].
     * 
     * NOTE: assume that to multiply matrices Anm and Bmk you need n*m*k operations.
     * 
     * NOTE: F[i][i] = 0 for i in 1..|dimensionsOfMatrices|
     * 
     * NOTE: F[i][j] = 0, where i > j.
     * 
     * NOTE: the expected time complexity is O(N^3), where N=|dimensionsOfMatrices|
     * 
     * @param dimensionsOfMatrices a set of matrix dimensions that correspond to an
     *                             MCM problem.
     * @return the optimal substructure matrix `F` for an MCM problem.
     */
    public static int[][] getOptimalSubstructure(int[][] dimensionsOfMatrices) {
        int n = dimensionsOfMatrices.length;
        int[][] F = new int[n][n];

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                F[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = F[i][k] + F[k + 1][j]
                            + dimensionsOfMatrices[i][0] * dimensionsOfMatrices[k][1] * dimensionsOfMatrices[j][1];
                    if (q < F[i][j]) {
                        F[i][j] = q;
                    }
                }
            }
        }
        return F;
    }

    /**
     * Returns the solution to a given MCM problem in string format.
     *
     * NOTE: you may use the method `getOptimalSubstructure` (if you've already
     * implemented it).
     * 
     * String format of an MCM problem is a sequence of "*" symbols and brackets -
     * "(", ")".
     * 
     * If you have two matrices, A and B, the only solution (optimal, obviously) is
     * "*".
     * If you have three matrices, A, B and C, the following solutions are possible:
     * - "(*)*"
     * - "*(*)"
     * If you have four matrices, A, B, C and D, the following solutions are
     * possible:
     * - "((*)*)*"
     * - "(*(*))*"
     * - "*((*)*)"
     * - "*(*(*))"
     * 
     * NOTE: All brackets should be explicitly mentioned in the solution string.
     * 
     * NOTE: if N=|dimensionsOfMatrices|, the expected solution should:
     * - contain N-1 "*" symbols
     * - contain N-2 pairs of brackets ("(" and ")")
     * 
     * NOTE: It is guaranteed that there is only one optimal solution.
     * 
     * NOTE: The expected time complexity is O(N^3), where N=|dimensionsOfMatrices|.
     * 
     * @param dimensionsOfMatrices a set of matrix dimensions that correspond
     *                             to an MCM problem
     * @return the optimal solution to an MCM problem in string format.
     */
    public static String getMCMSolution(int[][] dimensionsOfMatrices) {
        int n = dimensionsOfMatrices.length;
        int[][] F = new int[n][n];
        int[][] s = new int[n][n];

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                F[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = F[i][k] + F[k + 1][j]
                            + dimensionsOfMatrices[i][0] * dimensionsOfMatrices[k][1] * dimensionsOfMatrices[j][1];
                    if (q < F[i][j]) {
                        F[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        return buildSolution(s, 0, n - 1);
    }

    private static String buildSolution(int[][] s, int i, int j) {
        if (i == j) {
            return "";
        } else {
            int k = s[i][j];
            String term1 = buildSolution(s, i, k);
            String term2 = buildSolution(s, k + 1, j);
            if (i == 0 && j == s.length - 1) {
                return term1 + "*" + term2;
            } else {
                return "(" + term1 + "*" + term2 + ")";
            }
        }
    }
}