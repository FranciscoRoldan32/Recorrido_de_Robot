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
		            ResultDto resultDto = matrixService.runAlgorithm();
		            
		            StringBuilder info = new StringBuilder();
		            info.append("Resultados del Algoritmo:\n\n");
		            
		            if (!matrixService.validateNumberOfStepsIsEven()) {
		                info.append("No se puede encontrar un camino válido: cantidad de pasos impar.\n");
		            } else {
		                info.append("¿Camino válido encontrado?: ").append(resultDto.pathFound).append("\n");
		                info.append("Tiempo sin poda: ").append(resultDto.timeWithoutPrune).append(" ms\n");
		                info.append("Tiempo con poda: ").append(resultDto.timeWithPrune).append(" ms\n");
		                info.append("Llamadas sin poda: ").append(resultDto.callsWithoutPrune).append("\n");
		                info.append("Llamadas con poda: ").append(resultDto.callsWithPrune).append("\n\n");
		                
		                if (resultDto.pathFound) {
		                    info.append("Camino válido:\n");
		                    for (int[] pos : resultDto.validPath) {
		                        info.append("[").append(pos[0]).append(",").append(pos[1]).append("] ");
		                    }
		                    info.append("\n");
		                    
		                    // Resaltar el camino en la matriz
		                    mat_View.resaltarCamino(resultDto.validPath);
		                }
		            }
		            
		            mat_View.mostrarInformacion(info.toString());
		            
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