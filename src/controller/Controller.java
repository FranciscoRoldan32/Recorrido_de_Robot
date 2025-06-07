package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import model.Dto.ResultDto;
import model_service.MatrixService;
import view.Main_View;
import view.Matrix_View;

public class Controller {
	private MatrixService matrixService;
	private Matrix_View mat_View;
	private Main_View main_View;
	
	public Controller () {

		initAplication();
		
	}
	
	

	public void initAplication() {
		int filas = main_View.getRow();
        int columnas = main_View.getCol();
        
		main_View.getButton_Init().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawGrid(filas,columnas); 
            }
        });
		
	}
	private void drawGrid(int row, int col) {
		  try {
		        int filas =row;
		        int columnas = col;
		        
		       
		        mat_View = new Matrix_View(filas, columnas);
		        mat_View.setVisible(true); // 
		    } catch (IllegalArgumentException ex) {
		        JOptionPane.showMessageDialog(null, ex.getMessage());
		    }
	}
}

////una vez q el user ingrese los archivos generar matriz
//		int[][] gridTemporal = MatrixService.generateMatrix(15, 16);//matriz temporal
//		
//		this.matrixService = new MatrixService(gridTemporal);
//		this.matrixService.printMatrix();
//		
//		ResultDto resultDto = this.matrixService.runAlgorithm();
//		
//		if (!this.matrixService.validateNumberOfStepsIsEven()) {
//          System.out.println("No se puede encontrar un camino válido: cantidad de pasos impar.");
//		} 
//
//      System.out.println("\n==== Resultados ====");
//      System.out.println("¿Camino válido encontrado?: " + resultDto.pathFound);
//      System.out.println("Tiempo sin poda: " + resultDto.timeWithoutPrune + " ms");
//      System.out.println("Tiempo con poda: " + resultDto.timeWithPrune + " ms");
//      System.out.println("Llamadas sin poda: " + resultDto.callsWithoutPrune);
//      System.out.println("Llamadas con poda: " + resultDto.callsWithPrune);
//
//      if (resultDto.pathFound) {
//          System.out.println("Camino válido:");
//          for (int[] pos : resultDto.validPath) {
//              System.out.print(Arrays.toString(pos) + " ");
//          }
//          System.out.println();
//      }
//      
//      this.matrixService.printMatrixWithPath(resultDto.validPath);