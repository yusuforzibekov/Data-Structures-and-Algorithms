package com.epam.bsp.plu.decomposition;

public class Solution {
  
  public final static double EPS = 1e-8;
  
  /* Returns a PLU decomposition of a given matrix.
  *
  * NOTE: It is guaranteed that a given matrix is square.
  * NOTE: It is guaranteed that the PLU decomposition exists.
  * 
  * NOTE: To avoid issues with precision, use EPS = 1e-8 to detect zeros:
  * |x| < EPS => We assume that x is zero.
  * 
  * @param matrix a given square matrix for which we want to get the PLU decomposition.
  * @return the resulting PLU decomposition consists of the following matrices:
  * - P - permutation matrix
  * - L - lower triangular matrix
  * - U - upper triangular matrix
  */
  public static PluView pluDecomposition(double[][] matrix) {
    int n = matrix.length;
    double[][] lower = new double[n][n];
    double[][] upper = new double[n][n];
    double[][] perm = new double[n][n];
    int[] pivot = new int[n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        lower[i][j] = (i == j) ? 1.0 : 0.0;
        upper[i][j] = matrix[i][j];
        perm[i][j] = 0.0;
      }
      pivot[i] = i;
    }

    for (int k = 0; k < n; k++) {
      double max = 0.0;
      int rowWithMax = k;

      for (int i = k; i < n; i++) {
        if (Math.abs(upper[i][k]) > max) {
          max = Math.abs(upper[i][k]);
          rowWithMax = i;
        }
      }

      if (rowWithMax != k) {
        double[] tempRow = upper[k];
        upper[k] = upper[rowWithMax];
        upper[rowWithMax] = tempRow;

        tempRow = lower[k];
        lower[k] = lower[rowWithMax];
        lower[rowWithMax] = tempRow;

        tempRow = perm[k];
        perm[k] = perm[rowWithMax];
        perm[rowWithMax] = tempRow;

        int tempPivot = pivot[k];
        pivot[k] = pivot[rowWithMax];
        pivot[rowWithMax] = tempPivot;
      }

      for (int i = k + 1; i < n; i++) {
        lower[i][k] = upper[i][k] / upper[k][k];
        for (int j = k; j < n; j++) {
          upper[i][j] = upper[i][j] - lower[i][k] * upper[k][j];
        }
      }
    }

    for (int i = 0; i < n; i++) {
      lower[i][i] = 1.0;
      perm[i][pivot[i]] = 1.0;
    }

    return new PluView(perm, lower, upper);
  }
  
  /* Returns the inverse for a given matrix.
  *
  * NOTE: Use the pluDecomposition method from the previous coding exercise.
  * NOTE: It is guaranteed that a given matrix is square.
  * NOTE: It is guaranteed that the PLU decomposition exists.
  * 
  * NOTE: To avoid issues with precision, use EPS = 1e-8 to detect zeros:
  * |x| < EPS => We assume that x is zero.
  * 
  * @param matrix a given square matrix for which we want to get the inverse matrix.
  * @return the inverse matrix.
  */
  public static double[][] getInverseMatrix(double[][] matrix) {
    int n = matrix.length;
    double[][] inverse = new double[n][n];
    PluView plu = pluDecomposition(matrix);
    double[][] P = plu.getPerm();
    double[][] L = plu.getLower();
    double[][] U = plu.getUpper();

    for (int k = 0; k < n; k++) {
      inverse[k][k] = 1.0;
      double[] y = new double[n];
      for (int i = 0; i < n; i++) {
        double sum = 0.0;
        for (int j = 0; j < i; j++) {
          sum += L[i][j] * y[j];
        }
        y[i] = P[i][k] - sum;
      }

      double[] x = new double[n];
      for (int i = n - 1; i >= 0; i--) {
        double sum = 0.0;
        for (int j = i + 1; j < n; j++) {
          sum += U[i][j] * x[j];
        }
        x[i] = (y[i] - sum) / U[i][i];
      }

      for (int i = 0; i < n; i++) {
        inverse[i][k] = x[i];
      }
    }

    return inverse;
  }

}