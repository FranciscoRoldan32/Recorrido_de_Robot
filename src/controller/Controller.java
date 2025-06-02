package controller;

import model.Services.MatrixService;
import view.Matrix_View;

public class Controller {
	private MatrixService service;
	private Matrix_View mat_View;
	
	public Controller () {
		this.service = new MatrixService();
		initAplication();
	}

	public void initAplication() {
		
		//inicializar vista
		
		
		
		//una vez q el user ingrese los archivos generar matriz
		this.service.generateMatrix(5, 6);
		this.service.printMatrix();
		

//		mat_View.drawMatrix(buttons, n, m);

	}
}