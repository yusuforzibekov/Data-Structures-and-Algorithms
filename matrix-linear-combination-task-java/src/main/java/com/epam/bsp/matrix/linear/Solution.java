package com.epam.bsp.matrix.linear;

import java.util.Arrays;

public class Solution {

	/**
	* @param matrix a given numeric matrix - 2 dimensional array with the same number of elements in each row.
	* @param weights an array of weights that correspond to the columns of the matrix.
	* @return the linear combination of the columns for the given matrix using an array of corresponding weights. 
	* @throws IllegalArgumentException if the given matrix and weights are not compatible (dimensionalities don't match).
	*/
	public static int[] linearCombination(int[][] matrix, int[] weights) {
		// Check compatibility of matrix and weights
		if (matrix[0].length != weights.length) {
			throw new IllegalArgumentException("Matrix and weights are not compatible.");
		}
		int numRows = matrix.length;
		int numCols = matrix[0].length;
		int[] result = new int[numRows];
		for (int i = 0; i < numRows; i++) {
			int sum = 0;
			for (int j = 0; j < numCols; j++) {
				sum += weights[j] * matrix[i][j];
			}
			result[i] = sum;
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] matrix1 = {{3, 4}, {2, 1}};
		int[] weights1 = {2, 3};
		int[] result1 = linearCombination(matrix1, weights1);
		System.out.println(Arrays.toString(result1)); // [18, 7]

		int[][] matrix2 = {{1, 0}, {0, 1}};
		int[] weights2 = {2, 3};
		int[] result2 = linearCombination(matrix2, weights2);
		System.out.println(Arrays.toString(result2)); // [2, 3]

		int[][] matrix3 = {{1, 0}, {0, 1}};
		int[] weights3 = {2};
		try {
			int[] result3 = linearCombination(matrix3, weights3);
			System.out.println(Arrays.toString(result3));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage()); // Matrix and weights are not compatible.
		}
	}

}
