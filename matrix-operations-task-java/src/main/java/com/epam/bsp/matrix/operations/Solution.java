package com.epam.bsp.matrix.operations;

public class Solution {

	/**
	* @param matrix a given numeric matrix, i.e. 2 dimensional array 
	* with the same number of elements in each row.
	* matrix.length > 0, matrix[0].length > 0
	* 
	* @return the transposed matrix. 
	*/
	public static int[][] transpose(int[][] matrix) {
		int[][] result = new int[matrix[0].length][matrix.length];

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				result[col][row] = matrix[row][col];
			}
		}
		return result;
	}

	/**
	* @param matrixA the first numeric matrix.
	* @param matrixB the second numeric matrix.
	* each matrix is a 2 dimensional array with the same number of elements in each row.
	* matrixA.length > 0, matrixA[0].length > 0, matrixB.length > 0, matrixB[0].length > 0
	* 
	* @throws IllegalArgumentException if the matrices are not compatible (dimensions differ in size).
	* @return The sum of two given matrices.
	*/
	public static int[][] addMatrices(int[][] matrixA, int[][] matrixB) {
		int height = matrixA.length;
		int length = matrixA[0].length;
		if (height != matrixB.length
				|| length != matrixB[0].length) {
			throw new IllegalArgumentException();
		}

		int[][] result = new int[height][length];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < length; col++) {
				result[row][col] = matrixA[row][col] + matrixB[row][col];
			}
		}

		return result;
	}
	
	
	/**
	* @param matrixA the first numeric matrix.
	* @param matrixB the second numeric matrix.
	* each matrix is a 2 dimensional array with the same number of elements in each row.
	* matrixA.length > 0, matrixA[0].length > 0, matrixB.length > 0, matrixB[0].length > 0
	* 
	* @throws IllegalArgumentException if the matrices are not compatible.
	* @return The product of two given matrices.
	*/
	public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
		if (matrixA[0].length != matrixB.length) {
			throw new IllegalArgumentException();
		}

		int height = matrixA.length;
		int length = matrixB[0].length;
		int[][] result = new int[height][length];

		for (int row = 0; row < result.length; row++) {
			for (int col = 0; col < result[row].length; col++) {
				result[row][col] = multiplyMatricesCell(matrixA, matrixB, row, col);
			}
		}

		return result;
	}

	private static int multiplyMatricesCell(int[][] firstMatrix, int[][] secondMatrix, int row, int col) {
		int cell = 0;
		for (int i = 0; i < secondMatrix.length; i++) {
			cell += firstMatrix[row][i] * secondMatrix[i][col];
		}
		return cell;
	}
}
