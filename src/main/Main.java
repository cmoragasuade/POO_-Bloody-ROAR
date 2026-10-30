package main;

import javax.swing.SwingUtilities;

import controlador.GameManager;

// Único punto de entrada del juego: abre el menú principal
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> GameManager.getInstance().iniciarJuego());
    }
}
