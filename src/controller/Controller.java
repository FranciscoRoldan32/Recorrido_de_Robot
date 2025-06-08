package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.Dto.ResultDto;
import model_service.MatrixService;
import view.Main_View;
import view.Matrix_View;

public class Controller {
	private MatrixService matrixService;
	private Matrix_View mat_View;
	private Main_View main_View;
	private int[][] currentMatrix;
	
	public Controller (Main_View main_View,Matrix_View mat_View) {
		this.main_View=main_View;
		 this.mat_View = mat_View;

		initAplication();
		
	}
	
	

	public void initAplication() {
		main_View.getButton_Init().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int filas = main_View.getRow();
					int columnas = main_View.getCol();

					int[][] matrix = MatrixService.generateMatrix(filas, columnas);
					matrixService = new MatrixService(matrix);

					matrixService.printMatrix(); 
					
					main_View.setVisible(false);
					
	                mat_View.setVisible(true);
	                
	            	drawGrid(matrix); 
	            	
	            	currentMatrix = matrix;

				} catch (Exception ex) {
					ex.printStackTrace(); 
					JOptionPane.showMessageDialog(null, "Error al inicializar la matriz: " + ex.getMessage());
				}
			}
		});
		mat_View.getBtnRunAlgorythm().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
//		            String resultado = correrAlgoritmoSobreMatriz(currentMatrix);
//		            mat_View.mostrarInformacion(resultado);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al ejecutar el algoritmo: " + ex.getMessage());
		        }
		    }
		});
	}
	
		private void drawGrid(int[][] matriz) {
			 mat_View.drawMatrix(matriz);
		
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