package model_service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.Dto.ResultDto;

public class MatrixService {
	private int rows, cols;
	private int[][] matrix;
	private List<int[]> bestPath = new ArrayList<>();
	private boolean[][] visited;
	private int totalCallsWithoutPrune = 0;
	private int totalCallsWithPrune = 0;
	
	public MatrixService() {}

	public MatrixService(int[][] matrix) {
		this.matrix = matrix;
		this.rows = matrix.length;
		this.cols = matrix[0].length;
		this.bestPath = new ArrayList<>();
	}

	public static int[][] generateMatrix(int rows, int cols) {
		Random rand = new Random();

		int[][] grid = new int[rows][cols];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = rand.nextBoolean() ? 1 : -1;
			}
		}
		return grid;
	}

	public boolean validateNumberOfStepsIsEven() {
		return (rows + cols - 1) % 2 == 0;
	}

	public int getNumberOfSteps() {
		return rows + cols - 1;
	}
	
	public ResultDto runAlgorithm() {
		
        if (!validateNumberOfStepsIsEven()) {
            return new ResultDto(false, 0, 0, 0, 0, Collections.emptyList());
        }
        
        // Sin poda
        totalCallsWithoutPrune = 0;
        long start1 = System.currentTimeMillis();
        boolean foundWithoutPrune = backtrack(0, 0, 0, new ArrayList<>(), false);
        long end1 = System.currentTimeMillis();
        long timeWithoutPrune = end1 - start1;

        // Con poda
        bestPath.clear();
        totalCallsWithPrune = 0;
        long start2 = System.currentTimeMillis();
        boolean foundWithPrune = backtrack(0, 0, 0, new ArrayList<>(), true);
        long end2 = System.currentTimeMillis();
        long timeWithPrune = end2 - start2;
        
        return new ResultDto(
                foundWithPrune || foundWithoutPrune,
                timeWithoutPrune,
                timeWithPrune,
                totalCallsWithoutPrune,
                totalCallsWithPrune,
                new ArrayList<>(bestPath)
            );
	}
	
	public void printMatrix() {
		for (int[] row : this.matrix) {
			for (int val : row) {
				System.out.print(String.format("%2d ", val));
			}
			System.out.println();
		}
	}
	
	public void printMatrixWithPath(List<int[]> path) {
	    Set<String> pathSet = new HashSet<>();
	    for (int[] pos : path) {
	        pathSet.add(pos[0] + "," + pos[1]);
	    }

	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) {
	            String key = i + "," + j;
	            String cellStr;

	            if (pathSet.contains(key)) {
	                cellStr = String.format("[%d]", matrix[i][j]);
	            } else {
	                cellStr = String.format(" %d ", matrix[i][j]);
	            }

	            System.out.printf("%4s", cellStr);
	        }
	        System.out.println();
	    }
	}


	private boolean backtrack(int i, int j, int sum, List<int[]> path, boolean usePrune) {
		
		if (usePrune)
			totalCallsWithPrune++;
		else
			totalCallsWithoutPrune++;

		if (i >= this.rows || j >= this.cols)
			return false;

		sum += this.matrix[i][j];
		path.add(new int[] { i, j });
		if (usePrune) {
			int stepsLeft = (rows - 1 - i) + (cols - 1 - j);

			if (Math.abs(sum) > stepsLeft) {
				path.remove(path.size() - 1);
				return false;
			}
		}

		if (i == rows - 1 && j == cols - 1) {
			if (sum == 0) {
				bestPath = new ArrayList<>(path);
				return true;
			}
			path.remove(path.size() - 1);
			return false;
		}

		boolean found = backtrack(i + 1, j, sum, path, usePrune) || backtrack(i, j + 1, sum, path, usePrune);

		path.remove(path.size() - 1); // backtrack
		return found;
	}
}
