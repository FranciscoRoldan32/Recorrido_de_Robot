package model_service;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import entities.Button;
import entities.Pair;

public class Service {
	private int row, col, id;
	private int[][] matrix;
	private Map<Integer, Button> buttons;

	public Service() {
		this.id = 0;
		this.matrix = new int[row][col];

	
		this.buttons = new HashMap<>();
		init(row,col);
	}

	public void init(int row, int col) {

		try {
			createMatrixAndDictionary();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	private void createMatrixAndDictionary() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
               
                matrix[i][j] = id;

                Pair par = new Pair(i, j);

                Button button = new Button(id, Color.YELLOW, par);
       
                buttons.put(id, button);
                
                this.id++;
            }
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Map<Integer, Button> getButtons() {
        return buttons;
    }
}


