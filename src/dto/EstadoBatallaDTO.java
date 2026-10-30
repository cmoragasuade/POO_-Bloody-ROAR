package dto;

import java.util.List;

// Todo lo que la pantalla de batalla necesita mostrar en un momento dado
public class EstadoBatallaDTO {

    private final List<EntidadDTO> party;
    private final List<EntidadDTO> enemigos;
    private final List<ItemDTO> inventario;

    private final String registro;
    private final String turnoDe;
    private final int ronda;

    private final boolean turnoDelJugador;
    private final boolean batallaTerminada;

    public EstadoBatallaDTO(
            List<EntidadDTO> party,
            List<EntidadDTO> enemigos,
            List<ItemDTO> inventario,
            String registro,
            String turnoDe,
            int ronda,
            boolean turnoDelJugador,
            boolean batallaTerminada) {

        this.party = party;
        this.enemigos = enemigos;
        this.inventario = inventario;
        this.registro = registro;
        this.turnoDe = turnoDe;
        this.ronda = ronda;
        this.turnoDelJugador = turnoDelJugador;
        this.batallaTerminada = batallaTerminada;
    }

    public List<EntidadDTO> getParty() {
        return party;
    }

    public List<EntidadDTO> getEnemigos() {
        return enemigos;
    }

    public List<ItemDTO> getInventario() {
        return inventario;
    }

    public String getRegistro() {
        return registro;
    }

    public String getTurnoDe() {
        return turnoDe;
    }

    public int getRonda() {
        return ronda;
    }

    public boolean isTurnoDelJugador() {
        return turnoDelJugador;
    }

    public boolean isBatallaTerminada() {
        return batallaTerminada;
    }
}
