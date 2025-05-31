package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import entities.Button;
import model_service.Service;
import view.Matrix_View;

public class Controller {
	private Service service;
	private Matrix_View mat_View;
	public Controller () {
		this.service=new Service();
		
	}

	public void initAplication(int n, int m) {
		service.init(n, m);
		Map<Integer, Button> buttonsEntitie = service.getButtons();

		List<JButton> buttons = new ArrayList<>();
		for (Button b : buttonsEntitie.values()) {
			JButton jb = new JButton(String.valueOf(b.getId()));
			jb.setBackground(Color.ORANGE);

			buttons.add(jb);
		}

		mat_View.drawMatrix(buttons, n, m);

	}
}