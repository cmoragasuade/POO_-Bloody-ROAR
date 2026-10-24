package controlador;

import modelo.Party;

public class GameManager {
    private Party party;
    // private Tienda tienda;
    // private Batalla batallaActual;

    public GameManager() {
        // Inicialización
    }

    public void iniciarJuego() {
        System.out.println("Iniciando Bloody Roar...");
    }

    public void guardarPartida() {
        // Acá irá la persistencia luego
        System.out.println("Partida guardada.");
    }

    public void cargarPartida() {
        // Acá irá la carga del archivo
        System.out.println("Partida cargada.");
    }
}