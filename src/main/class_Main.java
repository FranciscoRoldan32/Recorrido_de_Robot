package main;

import java.security.Provider.Service;

import controller.Controller;

public class class_Main {
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Main_View view = new Main_View();
            Service service = new Service();
            Controller controller = new Controller(view, service);

            // Podés probar con una matriz de 5x5, por ejemplo
            controller.iniciarJuego(5, 5);
        });
    }

}
