package com.epam.bsp.plu.decomposition;

public class Solution {

  public final static double EPS = 1e-8;

  /**
   * Returns a PLU decomposition of a given matrix.
   *
   * NOTE: It is guaranteed that a given matrix is square.
   * NOTE: It is guaranteed that the PLU decomposition exists.
   * 
   * NOTE: To avoid issues with precision, use EPS = 1e-8 to detect zeros:
   * |x| < EPS => We assume that x is zero.
   * 
   * @param matrix a given square matrix for which we want to get the PLU
   *               decomposition.
   * @return the resulting PLU decomposition consists of the following matrices:
   *         - P - permutation matrix
   *         - L - lower triangular matrix
   *         - U - upper triangular matrix
   */
  public static PluView pluDecomposition(double[][] matrix) {
    // Get the size of the matrix
    int n = matrix.length;

    // Initialize the upper triangular matrix as a copy of the input matrix
    // Initialize the permutation matrix and the lower triangular matrix both as the
    // identity matrix
    double[][] perm = new double[n][n];
    double[][] lower = new double[n][n];
    double[][] upper = new double[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        upper[i][j] = matrix[i][j];
      }
      perm[i][i] = 1;
      lower[i][i] = 1;
    }

    // Loop over each column of the upper triangular matrix
    for (int k = 0; k < n; k++) {
      // Find the first nonzero entry in the column
      int pivot = -1;
      for (int i = k; i < n; i++) {
        if (Math.abs(upper[i][k]) > EPS) {
          pivot = i;
          break;
        }
      }

      // If the pivot is not in the first row, swap the rows and update the
      // permutation and lower matrices accordingly
      if (pivot != k) {
        swapRows(upper, k, pivot);
        swapRows(perm, k, pivot);
        swapRows(lower, k, pivot);
        swapColumns(lower, k, pivot);
      }

      // Use the pivot to eliminate the entries below it in the column and update the
      // lower matrix accordingly
      for (int i = k + 1; i < n; i++) {
        double factor = upper[i][k] / upper[k][k];
        lower[i][k] = factor;
        for (int j = k; j < n; j++) {
          upper[i][j] -= factor * upper[k][j];
        }
      }

    }

    // Swap the columns of the upper triangular matrix
    for (int k = 0; k < n; k++) {
      int index = -1;
      for (int j = 0; j < n; j++) {
        if (Math.abs(perm[k][j] - 1) < EPS) {
          index = j;
          break;
        }
      }
      if (index != k) {
        swapColumns(upper, k, index);
      }
    }

    return new PluView(perm, lower, upper);
  }

  // A helper method to swap two rows of a matrix
  public static void swapRows(double[][] matrix, int row1, int row2) {
    int n = matrix.length;
    for (int j = 0; j < n; j++) {
      double temp = matrix[row1][j];
      matrix[row1][j] = matrix[row2][j];
      matrix[row2][j] = temp;
    }
  }

  // A helper method to swap two columns of a matrix
  public static void swapColumns(double[][] matrix, int col1, int col2) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      double temp = matrix[i][col1];
      matrix[i][col1] = matrix[i][col2];
      matrix[i][col2] = temp;
    }
  }

  /**
   * Returns the inverse for a given matrix.
   *
   * NOTE: Use the `pluDecomposition` method from the previous coding exercise.
   * NOTE: It is guaranteed that a given matrix is square.
   * NOTE: It is guaranteed that the PLU decomposition exists.
   * 
   * NOTE: To avoid issues with precision, use EPS = 1e-8 to detect zeros:
   * |x| < EPS => We assume that x is zero.
   * 
   * @param matrix a given square matrix for which we want to get the inverse
   *               matrix.
   * @return the inverse matrix.
   */
  public static double[][] getInverseMatrix(double[][] matrix) {
    // Get the size of the matrix
    int n = matrix.length;

    // Get the PLU decomposition of the matrix
    PluView plu = pluDecomposition(matrix);

    // Get the permutation, lower and upper matrices
    double[][] perm = plu.getPerm();
    double[][] lower = plu.getLower();
    double[][] upper = plu.getUpper();

    // Initialize the inverse matrix as a copy of the permutation matrix
    double[][] inverse = new double[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        inverse[i][j] = perm[i][j];
      }
    }

    // Loop over each column of the inverse matrix
    for (int k = 0; k < n; k++) {
      // Solve the lower triangular system Ly = Pk using forward substitution
      double[] y = new double[n];
      for (int i = 0; i < n; i++) {
        y[i] = inverse[i][k];
        for (int j = 0; j < i; j++) {
          y[i] -= lower[i][j] * y[j];
        }
        y[i] /= lower[i][i];
      }

      // Solve the upper triangular system Ux = y using backward substitution
      double[] x = new double[n];
      for (int i = n - 1; i >= 0; i--) {
        x[i] = y[i];
        for (int j = i + 1; j < n; j++) {
          x[i] -= upper[i][j] * x[j];
        }
        x[i] /= upper[i][i];
      }

      // Copy the solution vector x to the corresponding column of the inverse matrix
      for (int i = 0; i < n; i++) {
        inverse[i][k] = x[i];
      }

    }

    return inverse;
  }

}