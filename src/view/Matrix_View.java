package view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Matrix_View extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel matrix_Panel,info_Panel;
	public Matrix_View() {
		initializeMatrix();
	}

	private void initializeMatrix() {
		setLayout(null); 

		matrix_Panel = new JPanel();
		matrix_Panel.setBounds(0, 0, 636, 667);
		add(matrix_Panel);

		info_Panel = new JPanel();
		info_Panel.setBounds(637, 0, 286, 667);
		add(info_Panel);
	}
	
	public void drawMatrix(List <JButton> buttons,int n, int m) {
		matrix_Panel.removeAll();
		matrix_Panel.setLayout(new GridLayout(n, m));

	    for (JButton boton : buttons) {
	    	matrix_Panel.add(boton);
	    }

	    matrix_Panel.revalidate();
	    matrix_Panel.repaint();
	
}
}
