package main;

import controller.Controller;
import view.Main_View;

public class Main {
	public static void main(String[] args) {
		try {
//			javax.swing.SwingUtilities.invokeLater(() -> {
//				Main_View view = new Main_View();
//
//				new Controller();
//			});

			new Controller();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
