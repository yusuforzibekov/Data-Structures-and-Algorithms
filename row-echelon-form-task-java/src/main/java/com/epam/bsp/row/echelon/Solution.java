package com.epam.bsp.row.echelon;

public class Solution {
	
	public final static double EPS = 1e-8;

	/**
	 * Checks whether a given matrix is in row-echelon form.
	 *
	 * NOTE: To avoid issues with precision, use EPS=1e-8 to detect zeros:
	 * |x| < EPS => We assume that x is zero.
	 *
	 * @param matrix a given numeric matrix.
	 * @return whether a given matrix is in row-echelon form.
	 */
	public static boolean isRowEchelonForm(double[][] matrix) {
		// Check that all leading coefficients are non-zero and appear in columns to the right of the leading coefficients
		int numRows = matrix.length;
		int numCols = matrix[0].length;
		int prevLeadingCol = -1;
		for (int row = 0; row < numRows; row++) {
			int leadingCol = -1;
			for (int col = 0; col < numCols; col++) {
				if (Math.abs(matrix[row][col]) > EPS) {
					leadingCol = col;
					break;
				}
			}
			if (leadingCol == -1) {
				// The row is all zeros
				continue;
			}
			if (leadingCol <= prevLeadingCol) {
				// The leading coefficient appears in a column to the left of the previous leading coefficient
				return false;
			}
			for (int col = 0; col < leadingCol; col++) {
				if (Math.abs(matrix[row][col]) > EPS) {
					// A non-zero coefficient appears to the left of the leading coefficient
					return false;
				}
			}
			prevLeadingCol = leadingCol;
		}
		return true;
	}
	
	/**
	* Returns the reduced row-echelon form of a given matrix.
	* 
	* NOTE: Reduced row-echelon form of a matrix is unique and does not depend on the algorithm used to compute it.
	* 
	* NOTE: You are not allowed to permute rows or columns.
	* 
	* NOTE: To avoid issues with precision, use EPS=1e-8 to detect zeros:
	* |x| < EPS => We assume that x is zero.
	* 
	* @param matrix a given numeric matrix.
	* @return the reduced row-echelon form of a given matrix.
	*/
	public static double[][] convertIntoRref(double[][] matrix) {
		int numRows = matrix.length;
		int numCols = matrix[0].length;
		int lead = 0;

		for (int r = 0; r < numRows; r++) {
			if (lead >= numCols) {
				return matrix;
			}
			int i = r;
			while (Math.abs(matrix[i][lead]) < EPS) {
				i++;
				if (i == numRows) {
					i = r;
					lead++;
					if (lead == numCols) {
						return matrix;
					}
				}
			}

			double[] temp = matrix[i];
			matrix[i] = matrix[r];
			matrix[r] = temp;

			double lv = matrix[r][lead];
			for (int j = 0; j < numCols; j++) {
				matrix[r][j] /= lv;
			}

			for (int i1 = 0; i1 < numRows; i1++) {
				if (i1 != r) {
					double lv1 = matrix[i1][lead];
					for (int j = 0; j < numCols; j++) {
						matrix[i1][j] -= lv1 * matrix[r][j];
					}
				}
			}
			lead++;
		}
		return matrix;
	}

	/**
	 * Returns the rank of a given square matrix.
	 *
	 * NOTE: You may use the 'convertIntoRref' function here.
	 *
	 * NOTE: To avoid issues with precision, use EPS=1e-8 to detect zeros:
	 * |x| < EPS => We assume that x is zero.
	 *
	 * @param matrix a given numeric matrix.
	 * @return the rank of a given square matrix.
	 */
	public static int getRankOfSquareMatrix(double[][] matrix) {
		int rank = 0;
		double[][] refMatrix = convertIntoRref(matrix);

		for (double[] doubles : refMatrix) {
			boolean allZeroes = true;
			for (int j = 0; j < refMatrix[0].length; j++) {
				if (Math.abs(doubles[j]) > EPS) {
					allZeroes = false;
					break;
				}
			}
			if (!allZeroes) {
				rank++;
			}
		}

		return rank;
	}


}
