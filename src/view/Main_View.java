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
    private JTextField row,col;
    private JButton button_Init;
   
  



    public Main_View() {
        setTitle("Robot en Planta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(939, 706);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel startPanel = new JPanel();
        startPanel.setLayout(null);
        button_Init = new JButton("Iniciar Aplicación");
        button_Init.setBounds(316, 425, 311, 131);
        button_Init.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));
        startPanel.add(button_Init);

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
        row.setBounds(269, 270, 86, 34);
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
    
    
}
