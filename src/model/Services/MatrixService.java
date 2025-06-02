package model.Services;

import java.util.Random;

public class MatrixService {
	private int row, col;
	private int[][] matrix;

	public MatrixService() {}

	public int[][] generateMatrix(int rows, int cols) {
		Random rand = new Random();
		this.row = rows;
		this.col = cols;
		this.matrix = new int[row][col];
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				this.matrix[i][j] = rand.nextBoolean() ? 1 : -1;
			}
		}
		return this.matrix;
	}

	public boolean validateNumberOfStepsIsEven() {
		return (row + col - 1) % 2 == 0;
	}

	public int getNumberOfSteps() {
		return row + col - 1;
	}

	public void printMatrix() {
		for (int[] row : this.matrix) {
			for (int val : row) {
				System.out.print(String.format("%2d ", val));
			}
			System.out.println();
		}
	}
}
