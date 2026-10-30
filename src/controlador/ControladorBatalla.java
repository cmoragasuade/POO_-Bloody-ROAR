package controlador;

import dto.EstadoBatallaDTO;
import modelo.Batalla;
import modelo.Entidad;
import vista.PantallaBatalla;

// Intermediario entre la pantalla de batalla y el modelo (igual que en EjemploBatallaMVC).
// La pantalla le avisa qué botón se tocó, él le pide la acción a Batalla
// y después le manda a la pantalla los datos nuevos en un DTO.
public class ControladorBatalla {

    private final Batalla batalla;
    private final PantallaBatalla vista;
    private final GameManager gameManager;

    public ControladorBatalla(Batalla batalla, PantallaBatalla vista, GameManager gameManager) {
        this.batalla = batalla;
        this.vista = vista;
        this.gameManager = gameManager;
    }

    public void atacar(int indiceEnemigo) {
        batalla.atacar(indiceEnemigo);
        refrescarVista();
    }

    public void defender() {
        batalla.defender();
        refrescarVista();
    }

    public void usarHabilidad(int indiceEnemigo) {
        batalla.usarHabilidad(indiceEnemigo);
        refrescarVista();
    }

    public void transformar() {
        batalla.transformar();
        refrescarVista();
    }

    public void usarItem(int indiceItem) {
        batalla.usarItem(indiceItem);
        refrescarVista();
    }

    // Cuando la batalla terminó, el GameManager muestra los resultados
    public void continuar() {
        if (batalla.termino()) {
            gameManager.terminarBatalla();
        }
    }

    public void refrescarVista() {
        vista.actualizar(crearEstadoBatallaDTO());
    }

    private EstadoBatallaDTO crearEstadoBatallaDTO() {
        Entidad actual = batalla.obtenerSiguienteTurno();
        String turnoDe = "";
        if (actual != null) {
            turnoDe = actual.getNombre();
        }
        return new EstadoBatallaDTO(
            ConversorDTO.convertirPersonajes(batalla.getParty().getPersonajes()),
            ConversorDTO.convertirEnemigos(batalla.getEnemigos()),
            ConversorDTO.convertirItems(batalla.getParty().getInventario()),
            batalla.getRegistroReciente(),
            turnoDe,
            batalla.getRonda(),
            batalla.esTurnoDelJugador(),
            batalla.termino()
        );
    }
}
