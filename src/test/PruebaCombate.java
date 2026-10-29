package test;

import controlador.ControladorBatalla;

public class PruebaCombate {
    public static void main(String[] args) {
        ControladorBatalla controlador = new ControladorBatalla();
        controlador.iniciarPrueba();
        controlador.simularTurno(0, "ATACAR");
        controlador.simularTurno(1, "HABILIDAD");
        controlador.simularTurno(2, "ATACAR");
    }
}
