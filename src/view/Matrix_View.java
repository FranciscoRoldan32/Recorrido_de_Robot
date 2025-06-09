package view;

import java.awt.GridLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Matrix_View extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel matrix_Panel,info_Panel;
	private JTextArea textInformation;
	private JButton btnRunAlgorythm,btnReturn;
	private JButton[][] robot_Path;
	private List<JButton> currentButtons;
	
	public Matrix_View() {
		initializeMatrix();
		setTitle("Vista de Matriz");
		setSize(950, 700); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void initializeMatrix() {
		getContentPane().setLayout(null); 

		matrix_Panel = new JPanel();
		matrix_Panel.setBounds(0, 0, 636, 667);
		getContentPane().add(matrix_Panel);
		matrix_Panel.setLayout(new GridLayout(1, 0, 0, 0));

		info_Panel = new JPanel();
		info_Panel.setBounds(637, 0, 286, 667);
		getContentPane().add(info_Panel);
		info_Panel.setLayout(null);
		
		textInformation = new JTextArea();
		textInformation.setEditable(false);
		textInformation.setLineWrap(true);
		textInformation.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textInformation);
		scrollPane.setBounds(10, 217, 266, 439);
		info_Panel.add(scrollPane);
		
		btnRunAlgorythm = new JButton("Mostrar Camino");
		btnRunAlgorythm.setBounds(45, 11, 200, 67);
		btnRunAlgorythm.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		info_Panel.add(btnRunAlgorythm);
		
		btnReturn = new JButton("Menu");
		btnReturn.setBackground(new Color(255, 0, 0));
		btnReturn.setFont(new Font("Mongolian Baiti", Font.PLAIN, 13));
		btnReturn.setBounds(95, 100, 89, 23);
		info_Panel.add(btnReturn);
	}
	
	public JButton getBtnRunAlgorythm() {
		return btnRunAlgorythm;
	}

	public void drawMatrix(int[][] matriz) {
	    int n = matriz.length;
	    int m = matriz[0].length;

	    currentButtons = new ArrayList<>();
	    robot_Path = new JButton[n][m];

	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < m; j++) {
	            JButton boton = new JButton(String.valueOf(matriz[i][j]));
	            boton.setEnabled(false);
	            currentButtons.add(boton);
	            robot_Path[i][j] = boton;
	        }
	    }

	    drawMatrix(currentButtons, n, m); 
	}
	
	private void drawMatrix(List<JButton> botones, int n, int m) {
	    matrix_Panel.removeAll(); 
	    matrix_Panel.setLayout(new GridLayout(n, m)); 
	    for (JButton boton : botones) {
	        matrix_Panel.add(boton);
	    }

	    matrix_Panel.revalidate(); 
	    matrix_Panel.repaint();  
	}
	
	public void mostrarInformacion(String informacion) {
		textInformation.setText(informacion);
	}
	
	public void resaltarCamino(List<int[]> camino) {
	    // Primero resetear todos los botones
	    for (JButton[] fila : robot_Path) {
	        for (JButton boton : fila) {
	            boton.setBackground(null);
	            boton.setForeground(Color.BLACK);
	        }
	    }
	    
	    // Luego resaltar el camino
	    for (int[] pos : camino) {
	        int i = pos[0];
	        int j = pos[1];
	        robot_Path[i][j].setBackground(Color.GREEN);
	        robot_Path[i][j].setForeground(Color.BLACK);
	    }
	}
	public JButton getBtnReturn() {
	    return btnReturn;
	}
	public void resetMatrixView() {
	    matrix_Panel.removeAll();  
	    matrix_Panel.revalidate();
	    matrix_Panel.repaint();

	    textInformation.setText(""); 

	    robot_Path = null;         
	    currentButtons = null;      
	}
}