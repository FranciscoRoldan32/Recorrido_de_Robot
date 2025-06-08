package main;

import javax.swing.SwingUtilities;

import controller.Controller;
import view.Main_View;
import view.Matrix_View;

public class Main {
	    public static void main(String[] args) {
	    	  Matrix_View matView = new Matrix_View();  
	          Main_View mainView = new Main_View();

	          Controller controller = new Controller(mainView,matView);

	          SwingUtilities.invokeLater(() -> {
	              mainView.setVisible(true);
	          });
	}
}

