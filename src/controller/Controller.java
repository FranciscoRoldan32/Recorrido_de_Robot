package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
		main_View.getBtnCargarArchivo().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int seleccion = fileChooser.showOpenDialog(null);

		        if (seleccion == JFileChooser.APPROVE_OPTION) {
		            File archivo = fileChooser.getSelectedFile();
		            try {
		                int[][] matriz = cargarMatrizDesdeArchivo(archivo);
		                matrixService = new MatrixService(matriz);
		                
		                matrixService.printMatrix(); 
		                currentMatrix = matriz;

		                main_View.setVisible(false);
		                mat_View.setVisible(true);
		                drawGrid(matriz);

		            } catch (IOException ex) {
		                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage());
		            }
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
		mat_View.getBtnReturn().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        mat_View.resetMatrixView(); 
		        mat_View.setVisible(false); 
		        main_View.setVisible(true);

		        currentMatrix = null;      
		        matrixService = null;   
		    }
		});
	}
	
	private void drawGrid(int[][] matriz) {
		mat_View.drawMatrix(matriz);
	}
	private int[][] cargarMatrizDesdeArchivo(File archivo) throws IOException {
	    List<int[]> filas = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] tokens = linea.trim().split("\\s+");
	            int[] fila = new int[tokens.length];
	            for (int i = 0; i < tokens.length; i++) {
	                fila[i] = Integer.parseInt(tokens[i]);
	            }
	            filas.add(fila);
	        }
	    }

	    // Validar que todas las filas tengan la misma cantidad de columnas
	    int columnas = filas.get(0).length;
	    for (int[] fila : filas) {
	        if (fila.length != columnas) {
	            throw new IOException("Todas las filas deben tener la misma cantidad de columnas.");
	        }
	    }

	    int[][] matriz = new int[filas.size()][columnas];
	    for (int i = 0; i < filas.size(); i++) {
	        matriz[i] = filas.get(i);
	    }

	    return matriz;
	}
}