package com.epam.bsp;

public class Solution {

    /**
     * Returns the optimal substructure matrix using a given parameters of 0-1 knapsack problem.
     * 
     * The idea is to build a matrix f, where
     *   f[i][j] - maximum sum of values for some subset of packages among the first 'i'
     *     packages, where their total weight does not exceed 'j'
     * 
     * NOTE: sometimes you won't be able to fill in the matrix f completely, for all
     *   'undefined' combinations of 'i' and 'j' just use 0 as a placeholder.
     * 
     * NOTE: the result f is expected to have (N+1, w+1) shape, where N is the number of packages.
     * 
     * NOTE: O(w * N) complexity is expected.
     * 
     * @param weights the weights that correspond to available packages.
     * @param values the values that correspond to available packages.
     * @param w the given capacity of the knapsack.
     * @return the optimal substructure matrix.
     */
    public static int[][] getOptimalSubstructure(int[] weights, int[] values, int w) {
        int n = weights.length;
        int[][] f = new int[n + 1][w + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (weights[i - 1] <= j) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }

        return f;
    }

    /**
     * Returns an optimal solution using the given list of weights and optimal substructure.
     *
     * NOTE: if there are multiple optimal solutions, feel free to return any of them.
     * 
     * @param weights the weights that correspond to available packages.
     * @param optimalSubstructure the optimal substructure of the 0-1 knapsack problem previously
     *                            calculated using the `getOptimalSubstructure` method.
     * @return the optimal solution that is represented by the binary vector (0/1 values),
     * where 1 means that the corresponding package is included in the optimal solution and 0 otherwise.
     */
    public static int[] getOptimalSolution(int[] weights, int[][] optimalSubstructure) {
        int n = optimalSubstructure.length - 1;
        int w = optimalSubstructure[0].length - 1;
        int[] solution = new int[n];

        for (int i = n, j = w; i > 0 && j > 0; i--) {
            if (optimalSubstructure[i][j] != optimalSubstructure[i - 1][j]) {
                solution[i - 1] = 1;
                j -= weights[i - 1];
            }
        }

        return solution;
    }
}
