package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.*;
import java.awt.*;

public class Main_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private JLabel label_Title, label_Init, label_X;
	private JTextField row, col;
	private JButton button_Init;

	public Main_View() {
		setTitle("Robot en Planta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		JPanel startPanel = new JPanel();
		startPanel.setLayout(null);
		mainPanel.add(startPanel, "inicio");

		label_Title = new JLabel("Robot en Planta");
		label_Title.setFont(new Font("Mongolian Baiti", Font.BOLD, 33));
		label_Title.setBounds(349, 85, 300, 73);
		startPanel.add(label_Title);

		label_Init = new JLabel("Ingresar el tamaño de la Matriz:");
		label_Init.setFont(new Font("Mongolian Baiti", Font.PLAIN, 19));
		label_Init.setBounds(10, 270, 300, 46);
		startPanel.add(label_Init);

		row = new JTextField();
		row.setBounds(269, 270, 86, 34);
		startPanel.add(row);

		label_X = new JLabel("X");
		label_X.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_X.setBounds(377, 287, 46, 14);
		startPanel.add(label_X);

		col = new JTextField();
		col.setBounds(423, 270, 86, 34);
		startPanel.add(col);

		button_Init = new JButton("Iniciar Aplicación");
		button_Init.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
		button_Init.setBounds(316, 425, 311, 131);
		startPanel.add(button_Init);

		cardLayout.show(mainPanel, "inicio");
	}

	public JButton getButton_Init() {
		return button_Init;
	}

	private int parsePositiveInteger(String input, String fieldName) {
	    input = input.trim();
	    if (!input.matches("^\\d+$")) {
	        throw new IllegalArgumentException(fieldName + " debe ser un número entero positivo.");
	    }
	    int value = Integer.parseInt(input);
	    if (value < 1) {
	        throw new IllegalArgumentException(fieldName + " debe ser mayor que cero.");
	    }
	    return value;
	}

	public int getRow() {
	    return parsePositiveInteger(row.getText(), "La fila");
	}

	public int getCol() {
	    return parsePositiveInteger(col.getText(), "La columna");
	}
}
