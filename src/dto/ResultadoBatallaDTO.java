package dto;

import java.util.List;

// Lo que muestra la pantalla de resultados al terminar una batalla
public class ResultadoBatallaDTO {

    private final boolean victoria;
    private final boolean juegoGanado;
    private final List<String> detalle;

    public ResultadoBatallaDTO(boolean victoria, boolean juegoGanado, List<String> detalle) {
        this.victoria = victoria;
        this.juegoGanado = juegoGanado;
        this.detalle = detalle;
    }

    public boolean isVictoria() {
        return victoria;
    }

    // true si además era la última sala (se derrotó al Boss)
    public boolean isJuegoGanado() {
        return juegoGanado;
    }

    public List<String> getDetalle() {
        return detalle;
    }
}
