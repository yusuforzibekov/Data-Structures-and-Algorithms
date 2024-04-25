package com.epam.bsp.gauss.elimination;

public class Solution {
    
    public final static double EPS = 1e-8;
    
    /**
    * Returns the solution of a given system of linear equations (Ax = b).
    * 
    * NOTE: It is guaranteed that the given matrix A is square.
    * NOTE: It is guaranteed that only one solution exists.
    * NOTE: The Gauss elimination should be extended with partial pivoting, 
    * as this helps to reduce rounding errors; you are less likely 
    * to add/subtract with a very small number (or a very large number).
    * NOTE: To avoid issues with precision, use EPS=1e-8 to detect zeros:
    * |x| < EPS => we assume that x is zero.
    * 
    * @param a a matrix with coefficients (a11 * x1 + ... + a1k * xk) of a given system (the left part).
    * @param b a list with values of a given system's equations (the right part, b1 = a11 * x1 + ... + a1k * xk).
    * @return the solution of a given system of linear equations.
    */
    public static double[] findSleSolution(double[][] a, double[] b) {
        int n = b.length;
        double[][] augmentedMatrix = new double[n][n + 1];
        
        // Augment matrix A with vector b
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = a[i][j];
            }
            augmentedMatrix[i][n] = b[i];
        }
        
        // Forward Elimination
        for (int pivot = 0; pivot < n; pivot++) {
            // Partial Pivoting: Find the maximum element in the current column
            int maxRowIndex = pivot;
            double max = Math.abs(augmentedMatrix[pivot][pivot]);
            for (int i = pivot + 1; i < n; i++) {
                if (Math.abs(augmentedMatrix[i][pivot]) > max) {
                    max = Math.abs(augmentedMatrix[i][pivot]);
                    maxRowIndex = i;
                }
            }
            // Swap rows if necessary
            if (maxRowIndex != pivot) {
                double[] temp = augmentedMatrix[pivot];
                augmentedMatrix[pivot] = augmentedMatrix[maxRowIndex];
                augmentedMatrix[maxRowIndex] = temp;
            }
            
            // Perform row operations to make elements below the pivot zero
            for (int i = pivot + 1; i < n; i++) {
                double factor = augmentedMatrix[i][pivot] / augmentedMatrix[pivot][pivot];
                for (int j = pivot; j < n + 1; j++) {
                    augmentedMatrix[i][j] -= factor * augmentedMatrix[pivot][j];
                }
            }
        }
        
        // Back Substitution
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += augmentedMatrix[i][j] * solution[j];
            }
            solution[i] = (augmentedMatrix[i][n] - sum) / augmentedMatrix[i][i];
        }
        
        return solution;
    }
}
