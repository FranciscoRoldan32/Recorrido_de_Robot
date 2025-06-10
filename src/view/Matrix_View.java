package view;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Font;

public class Matrix_View extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel matrix_Panel, info_Panel;
	private JTextArea textInformation;
	private JButton btnRunAlgorythm, btnReturn;
	private JButton[][] robot_Path;
	private List<JButton> currentButtons;

	public Matrix_View() {
		setTitle("Vista de Matriz");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initializeMatrix();
	}

	private void initializeMatrix() {
		getContentPane().setLayout(new BorderLayout());

		matrix_Panel = new JPanel();
		matrix_Panel.setLayout(new GridLayout(1, 0));
		getContentPane().add(matrix_Panel, BorderLayout.CENTER);

		info_Panel = new JPanel();
		info_Panel.setPreferredSize(new Dimension(300, getHeight()));
		info_Panel.setLayout(new BoxLayout(info_Panel, BoxLayout.Y_AXIS));
		getContentPane().add(info_Panel, BorderLayout.EAST);

		btnRunAlgorythm = new JButton("Mostrar Camino");
		btnRunAlgorythm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRunAlgorythm.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		info_Panel.add(Box.createVerticalStrut(20));
		info_Panel.add(btnRunAlgorythm);

		btnReturn = new JButton("Menu");
		btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReturn.setBackground(new Color(255, 0, 0));
		btnReturn.setFont(new Font("Mongolian Baiti", Font.PLAIN, 13));
		info_Panel.add(Box.createVerticalStrut(20));
		info_Panel.add(btnReturn);

		textInformation = new JTextArea();
		textInformation.setEditable(false);
		textInformation.setLineWrap(true);
		textInformation.setWrapStyleWord(true);
		textInformation.setFont(new Font("Mongolian Baiti", Font.CENTER_BASELINE, 20));

		JScrollPane scrollPane = new JScrollPane(textInformation);
		scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		scrollPane.setPreferredSize(new Dimension(280, 400));
		info_Panel.add(Box.createVerticalStrut(20));
		info_Panel.add(scrollPane);
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

	public void showInfo(String informacion) {
		textInformation.setText(informacion);
	}

	public void repaintCell(List<int[]> path) {
		for (JButton[] fila : robot_Path) {
			for (JButton boton : fila) {
				boton.setBackground(null);
				boton.setForeground(Color.BLACK);
			}
		}

		for (int[] pos : path) {
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