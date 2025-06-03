package controller;

import java.util.Arrays;

import model.Dto.ResultDto;
import model.Services.MatrixService;
import view.Matrix_View;

public class Controller {
	private MatrixService matrixService;
	private Matrix_View mat_View;
	
	public Controller () {

		initAplication();
	}

	public void initAplication() {
		
		//inicializar vista
		
		
		
		//una vez q el user ingrese los archivos generar matriz
		int[][] gridTemporal = MatrixService.generateMatrix(15, 16);//matriz temporal
		
		this.matrixService = new MatrixService(gridTemporal);
		this.matrixService.printMatrix();
		
		ResultDto resultDto = this.matrixService.runAlgorithm();
		
		if (!this.matrixService.validateNumberOfStepsIsEven()) {
            System.out.println("No se puede encontrar un camino válido: cantidad de pasos impar.");
		} 

        System.out.println("\n==== Resultados ====");
        System.out.println("¿Camino válido encontrado?: " + resultDto.pathFound);
        System.out.println("Tiempo sin poda: " + resultDto.timeWithoutPrune + " ms");
        System.out.println("Tiempo con poda: " + resultDto.timeWithPrune + " ms");
        System.out.println("Llamadas sin poda: " + resultDto.callsWithoutPrune);
        System.out.println("Llamadas con poda: " + resultDto.callsWithPrune);

        if (resultDto.pathFound) {
            System.out.println("Camino válido:");
            for (int[] pos : resultDto.validPath) {
                System.out.print(Arrays.toString(pos) + " ");
            }
            System.out.println();
        }
        
        this.matrixService.printMatrixWithPath(resultDto.validPath);

//		mat_View.drawMatrix(buttons, n, m);

	}
}