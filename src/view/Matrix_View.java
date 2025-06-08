package view;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.Font;

public class Matrix_View extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel matrix_Panel, info_Panel;
	private JTextArea textInformation;
	private JButton btnRunAlgorythm, btnBack;
	private JButton[][] robot_Path;
	private List<JButton> currentButtons;

	public Matrix_View() {
		initializeMatrix();
		setTitle("Vista de Matriz");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void initializeMatrix() {
		setLayout(new BorderLayout());

		matrix_Panel = new JPanel(new GridLayout(1, 0, 0, 0));

		info_Panel = new JPanel(new BorderLayout());

		JPanel topButtonPanel = new JPanel();
		topButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		btnRunAlgorythm = new JButton("Mostrar Camino");
		btnRunAlgorythm.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		topButtonPanel.add(btnRunAlgorythm);

		btnBack = new JButton("Volver");
		btnBack.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		topButtonPanel.add(btnBack);

		info_Panel.add(topButtonPanel, BorderLayout.NORTH);

		textInformation = new JTextArea();
		textInformation.setEditable(false);
		textInformation.setLineWrap(true);
		textInformation.setWrapStyleWord(true);
		textInformation.setFont(new Font("Mongolian Baiti",Font.CENTER_BASELINE, 20));

		JScrollPane scrollPane = new JScrollPane(textInformation);
		info_Panel.add(scrollPane, BorderLayout.CENTER);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, matrix_Panel, info_Panel);
		splitPane.setResizeWeight(0.7);
		add(splitPane, BorderLayout.CENTER);
	}

	public JButton getBtnRunAlgorythm() {
		return btnRunAlgorythm;
	}
	
	public JButton getBtnBack() {
		return btnBack;
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
}