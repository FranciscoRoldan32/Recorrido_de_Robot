package view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Main_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private JLabel label_Title, label_Init, label_X;
	private JTextField row, col;
	private JButton button_Init, btnCargarArchivo;

	public Main_View() {
		setTitle("Robot en Planta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		JPanel startPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		label_Title = new JLabel("Robot en Planta", SwingConstants.CENTER);
		label_Title.setFont(new Font("Mongolian Baiti", Font.BOLD, 33));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		startPanel.add(label_Title, gbc);

		label_Init = new JLabel("Ingresar el tamaño de la Matriz:");
		label_Init.setFont(new Font("Mongolian Baiti", Font.PLAIN, 19));
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		startPanel.add(label_Init, gbc);

		row = new JTextField();
		row.setColumns(5);
		gbc.gridx = 1;
		startPanel.add(row, gbc);

		label_X = new JLabel("X");
		label_X.setFont(new Font("Tahoma", Font.PLAIN, 17));
		gbc.gridx = 2;
		startPanel.add(label_X, gbc);

		col = new JTextField();
		col.setColumns(5);
		gbc.gridx = 3;
		startPanel.add(col, gbc);

		button_Init = new JButton("Iniciar Aplicación");
		button_Init.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		startPanel.add(button_Init, gbc);

		btnCargarArchivo = new JButton("Cargar Archivo");
		gbc.gridy = 3;
		startPanel.add(btnCargarArchivo, gbc);

		mainPanel.add(startPanel, "inicio");
		getContentPane().add(mainPanel);

		cardLayout.show(mainPanel, "inicio");
	}

	public JButton getButton_Init() {
		return button_Init;
	}

	public int getRow() throws IllegalArgumentException {
		String input = row.getText().trim();
		if (!input.matches("\\d+")) {
			throw new IllegalArgumentException("La fila debe ser un número entero positivo.");
		}
		int n = Integer.parseInt(input);
		if (n < 1) {
			throw new IllegalArgumentException("La fila debe ser mayor que cero.");
		}
		return n;
	}

	public int getCol() {
		String input = col.getText().trim();
		if (!input.matches("\\d+")) {
			throw new IllegalArgumentException("La columna debe ser un número entero positivo.");
		}
		int m = Integer.parseInt(input);
		if (m < 1) {
			throw new IllegalArgumentException("La columna debe ser mayor que cero.");
		}
		return m;
	}

	public JButton getBtnCargarArchivo() {
		return btnCargarArchivo;
	}

}