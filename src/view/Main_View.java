package view;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import controller.Controller;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_View extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel mainPanel;
    private JLabel label_Title,label_Init,label_X;
    private JTextField row;
    private JTextField col;
    private Controller controller;

    public Main_View() {
        setTitle("Robot en Planta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(939, 706);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel startPanel = new JPanel();
        startPanel.setLayout(null);
        JButton buttonInit = new JButton("Iniciar Aplicación");
        buttonInit.setBounds(316, 425, 311, 131);
        buttonInit.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        startPanel.add(buttonInit);

        Matrix_View matrixViewPanel = new Matrix_View();

        mainPanel.add(startPanel, "inicio");
        
        label_Title = new JLabel("Robot en Planta");
        label_Title.setFont(new Font("Mongolian Baiti", Font.BOLD, 33));
        label_Title.setBounds(349, 85, 235, 73);
        startPanel.add(label_Title);
        
        label_Init = new JLabel("Ingresar el tamaño de la Matriz:");
        label_Init.setFont(new Font("Mongolian Baiti", Font.PLAIN, 19));
        label_Init.setBounds(10, 270, 249, 46);
        startPanel.add(label_Init);
        
        row = new JTextField();
        row.setBounds(257, 270, 86, 34);
        startPanel.add(row);
        row.setColumns(10);
        
        label_X = new JLabel("X");
        label_X.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_X.setBounds(377, 287, 46, 14);
        startPanel.add(label_X);
        
        col = new JTextField();
        col.setColumns(10);
        col.setBounds(423, 270, 86, 34);
        startPanel.add(col);
        mainPanel.add(matrixViewPanel, "matriz");

    
        buttonInit.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        	        String input = row.getText().trim(); 
        	        if (!input.matches("\\d+x\\d+")) {
        	            JOptionPane.showMessageDialog(null, "Formato inválido. Use 'n X m', por ejemplo: 5x7.");
        	            return;
        	        }

        	        String[] partes = input.split("x");
        	        int n = Integer.parseInt(partes[0]);
        	        int m = Integer.parseInt(partes[1]);

        	      
        	        if (n < 1 || m < 1) {
        	            JOptionPane.showMessageDialog(null, "Las dimensiones deben ser mayores que cero.");
        	            return;
        	        }
        	        controller.initAplication(n, m);
        	    }
        	});

        getContentPane().add(mainPanel); 
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_View());
    }
}
